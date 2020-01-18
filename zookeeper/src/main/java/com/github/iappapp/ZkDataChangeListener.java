package com.github.iappapp;

import org.I0Itec.zkclient.IZkDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkDataChangeListener implements IZkDataListener {
    private static final Logger logger = LoggerFactory.getLogger(ZkDataChangeListener.class);

    public void handleDataChange(String dataPath, Object data) throws Exception {
        logger.info("handleDataChange path={} data={}", dataPath, data);
    }

    public void handleDataDeleted(String dataPath) throws Exception {
        logger.info("handle data delete data path={}", dataPath);
    }
}
