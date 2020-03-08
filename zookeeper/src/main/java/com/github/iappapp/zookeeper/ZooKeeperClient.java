package com.github.iappapp.zookeeper;

import com.github.iappapp.Constant;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZooKeeperClient {
    private static final String ZOO_HOST = Constant.ZOO_ADDRESS;
    private static int sessionTimeout = 10 * 1000;

    public static ZooKeeper getInstance() throws IOException, InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        ZooKeeper zk = new ZooKeeper(ZOO_HOST, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    signal.countDown();
                }
            }
        });
        signal.await(sessionTimeout, TimeUnit.MILLISECONDS);
        return zk;
    }

    public static int getSessionTimeout() {
        return sessionTimeout;
    }

    public static void setSessionTimeout(int sessionTimeout) {
        ZooKeeperClient.sessionTimeout = sessionTimeout;
    }
}
