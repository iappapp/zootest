package com.github.iappapp.util;

import com.github.iappapp.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RedisUtilTest extends BaseTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void set() {
        for (int i = 0; i < 1000; i++) {
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
}
