package com.github.iappapp;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import java.util.concurrent.CountDownLatch;

import static com.github.iappapp.Constant.SESSION_OUT_TIME;
import static com.github.iappapp.Constant.ZOO_ADDRESS;

public class ZkClientBase {
    public static final CountDownLatch lock = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        System.out.println(Math.round(-1.5));
        ZkClient zkClient = new ZkClient(new ZkConnection(ZOO_ADDRESS), SESSION_OUT_TIME);

        zkClient.createEphemeral("/temp");
        zkClient.createPersistent("/zoo/node", true);

        zkClient.subscribeDataChanges("/zoo/node", new IZkDataListener() {

            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("handleDataChange path=" + dataPath + " data=" + data);
            }

            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("handle data delete data path=" + dataPath);
            }
        });

        System.out.println("now = " + System.currentTimeMillis());
        for (long i = 0; i < 10; i++) {
            Thread.sleep(200);
            zkClient.writeData("/zoo/node", String.valueOf(i));

        }
        Thread.sleep(200);
        System.out.println("now spend = " + System.currentTimeMillis());
        zkClient.close();
    }
}
