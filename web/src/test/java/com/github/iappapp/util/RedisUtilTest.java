package com.github.iappapp.util;

import com.github.iappapp.BaseTest;
import com.github.iappapp.shiro.constant.ShiroConstant;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class RedisUtilTest extends BaseTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void set() {
        for (int i = 0; i < 10000; i++) {
            String result = redisUtil.set(String.valueOf(i), i);
            log.info("set value result={}", result);
        }
    }

    @Test
    public void setNx() {
        for (int i = 0; i < 5; i++) {
            Boolean result = redisUtil.setNx("1", "1", 2);
            log.info("setnx result={}", result);
        }
    }

    @Test
    public void ping() {
        System.out.println(redisUtil.ping());
    }

    @Test
    public void del() {
        for (int i = 0; i < 1000000; i++) {
            Long result = redisUtil.del(String.valueOf(i));
            log.info("del key={} result={}", i, result);
        }
    }

    @Test
    public void delKeys() {
        String[] keys = new String[10000];
        for (int i = 0; i < 10000; i++) {
            keys[i] = String.valueOf(i);
        }

        System.out.println(redisUtil.delete(keys));
    }

    @Test
    public void hmset() {
        Map<String, String> stringMap = Maps.newHashMap();
        for (int i = 0; i < 10000; i++) {
            stringMap.putIfAbsent(String.valueOf(i), String.valueOf(i));
        }

        String result = redisUtil.hmset("key_map", stringMap);
        log.info("hmset result={}", result);
    }

    @Test
    public void hmget() {
        String[] fields = new String[10000];
        for (int i = 0; i < 100; i++) {
            fields[i] = String.valueOf(i + 10000);
        }

        List<String> result = redisUtil.hmget("key_map", fields);
        log.info("hmset result={}", result);
    }

    @Test
    public void rpush() {
        String[] fields = new String[100];
        for (int i = 0; i < 100; i++) {
            fields[i] = String.valueOf(i);
        }
        log.info("rpush values={}", fields);
        Long result = redisUtil.rpush("key_list", fields);
        log.info("lpush result={}", result);
    }

    @Test
    public void llen() {
        Long result = redisUtil.llen("key_list");
        log.info("lpush result={}", result);
    }

    @Test
    public void lpush() {
        String[] fields = new String[100];
        for (int i = 0; i < 100; i++) {
            fields[i] = String.valueOf(i);
        }
        log.info("rpush values={}", fields);
        Long result = redisUtil.lpush("key_list", fields);
        log.info("lpush result={}", result);
    }

    @Test
    public void lrange() {
        List<String> strings = redisUtil.lrange("key_list", 0, 200);
        String result = redisUtil.lset("key_list", 0, "100");
        System.out.println(strings);
        System.out.println(result);
        System.out.println(redisUtil.lindex("key_list", 0));
    }

    @Test
    public void configGet() {
        String pattern = "*";
        List<String> result = redisUtil.configGet(pattern);
        List<String> keyValue = new ArrayList<>(2);
        for (String s : result) {
            keyValue.add(s);
            if (keyValue.size() == 2) {
                System.out.println(keyValue);
                keyValue.clear();
            }
        }
    }

    @Test
    public void keys() {
        String pattern = ShiroConstant.SHIRO_SESSION_PATTERN;
        Set<String> stringSet = redisUtil.keys(pattern);

        for (String key : stringSet) {
            System.out.println(redisUtil.get(key));
            // redisUtil.del(key);
        }

        System.out.println(stringSet);
    }

}
