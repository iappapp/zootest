package com.github.iappapp;

import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ResourcesTest {
    private static final Logger logger = LoggerFactory.getLogger(ResourcesTest.class);

    @Test
    public void TestResources() throws IOException {
        File f = new File("~/Desktop/zootest/common/src/main/resources/jdbc.properties");
        logger.info("file exist={}", f.exists());
        //自定义类加载器
        ClassLoader classLoader = new ClassLoader() {

        };
        InputStream inputStream = classLoader.getResourceAsStream("jdbc.properties");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        logger.info("file content={}", new String(bytes, "utf-8"));
        File file = Resources.getResourceAsFile(classLoader, "jdbc.properties");
        logger.info("file={} exist={}", file, file.exists());
        Properties properties = Resources.getResourceAsProperties(classLoader, "jdbc.properties");
        logger.info("properties={}", properties.keySet().toString());
        URL url = Resources.getResourceURL("jdbc.properties");
        logger.info("properties={}", properties.keySet().toString());
    }

}
