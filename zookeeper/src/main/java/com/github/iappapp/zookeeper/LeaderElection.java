package com.github.iappapp.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

public class LeaderElection {
    private ZooKeeper zooKeeper;

    private final static byte[] DATA = {0x12, 0x34};

    private int sessionTimeout;

    private static Object mutex = new Object();

    private static String ROOT = "/leader";

    private String self;

    private String selfNode;

    private static CountDownLatch firstElection = new CountDownLatch(1);

    public static String leader;

    public LeaderElection(String self) throws IOException, InterruptedException, KeeperException {
        this.zooKeeper = ZooKeeperClient.getInstance();
        this.sessionTimeout = zooKeeper.getSessionTimeout();
        this.self = self;

        ensureExists(ROOT);
        ensureNodeExists();
    }

    public void ensureExists(String path) {
        try {
            Stat stat = zooKeeper.exists(path, false);
            if (stat == null) {
                zooKeeper.create(path, DATA, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException  | InterruptedException ex) {

        }
    }

    public void ensureNodeExists() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(ROOT, new NodeDeleteWatcher());

        for (String child : children) {
            Stat stat = new Stat();
            String path = ROOT + "/" + child;

            byte[] data = zooKeeper.getData(path, false, stat);
            if (Arrays.equals(data, self.getBytes())) {
                selfNode = path;
                return;
            }
        }
        selfNode = zooKeeper.create(ROOT + "/", self.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    public void start() throws KeeperException, InterruptedException, UnsupportedEncodingException {
        do {
            synchronized (mutex) {
                System.out.println("start leader election...");

                List<String> nodes = zooKeeper.getChildren(ROOT, null);

                SortedSet<String> sortedSet = new TreeSet<String>();

                for (String node : nodes) {
                    sortedSet.add(ROOT + "/" + node);
                }

                String first = sortedSet.first();
                leader = first;

                NodeDeleteWatcher watcher = self.equals(leader) ? null : new NodeDeleteWatcher();
                byte[] data = zooKeeper.getData(leader, watcher, null);
                leader = new String(data, "utf-8");
                System.out.println("leader election end, leader is : " + leader);

                if (firstElection.getCount() != 0) {
                    firstElection.countDown();
                }

                if (self.equals(first)) {
                    return;
                }

                mutex.wait();
            }
        } while (true);
    }

    public static String getLeader() throws InterruptedException {
        firstElection.await();
        return leader;
    }

    class NodeDeleteWatcher implements Watcher {
        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeDeleted) {
                System.out.println("node delete event path = " + event.getPath());
                synchronized (mutex) {
                    mutex.notify();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LeaderElection leaderElection = new LeaderElection("1");
                    leaderElection.start();
                } catch (Exception ex) {
                    System.out.println("ex=" + ex.getMessage());
                }
            }
        }).start();

        String leader = LeaderElection.getLeader();
        System.out.println(leader);

        while (true) {

        }
    }
}
