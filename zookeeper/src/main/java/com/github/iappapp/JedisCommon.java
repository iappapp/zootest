package com.github.iappapp;

import org.apache.commons.io.IOUtils;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.util.Date;

public class JedisCommon {

    public static void main(String[] args) throws Exception {
        String fileName = "KeyTest.lua";
        String path = "G:\\zoo-test\\zootest\\src\\main\\resources\\" + fileName;
        System.out.println(fileName);
        Jedis jedis = new Jedis(Constant.REDIS_HOST, Constant.REDIS_PORT);
        Date start = new Date();
        Object result = jedis.eval(IOUtils.toString(new FileInputStream(path)));
        Date end = new Date();
        System.out.println("result=" + result);
        System.out.println("redis spent=" + (end.getTime() - start.getTime()) / 1000 + " seconds");
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    }
}
