package com.github.iappapp;

import org.apache.ibatis.io.Resources;
import org.junit.Test;

import java.util.Properties;

public class PropertyTest {
    @Test
    public void testProperty() throws Exception {
        Properties properties = new Properties();
        properties.load(Resources.getResourceAsStream("com/github/iappapp/dao/mapper/jdbc.properties"));
        System.out.println(properties);
        System.out.println(properties.get("password"));
        System.out.println(properties.get("url"));
        System.out.println(properties.get("username"));
    }
}
