package com.github.iappapp.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
@Slf4j
public class RedisUtil {
    @Autowired
    private JedisPool pool;

    public long del(String key) {
        Jedis jedis = pool.getResource();
        Long result = jedis.del(key);
        return result;
    }

    public void delete(String ...key) {
        Jedis jedis = pool.getResource();
        jedis.del(key);
    }

    public String set(String key, Object value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            result = jedis.set(key, JSON.toJSONString(value));
        } catch (Exception ex) {
            log.info("set key={} value={} error", key, value, ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return result;
    }

    public String set(String key, String value) {
        Jedis jedis = pool.getResource();
        String result = jedis.set(key, value);
        return result;
    }

    public String ping() {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            result = jedis.ping();
        } catch (Exception ex) {

        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        return result;
    }

    public boolean setNx(String key, String value, int timeout) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            result = jedis.set(key, value, "NX", "EX", timeout);
            if ("OK".equals(result)) {
                return true;
            }
        } catch (Exception ex) {

        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        return false;
    }
}
