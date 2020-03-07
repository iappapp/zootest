package com.github.iappapp.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Service
@Slf4j
public class RedisUtil {
    @Autowired
    private JedisPool pool;

    public long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = pool.getResource();
            result = jedis.del(key);
        } catch (Exception ex) {

        } finally {
            close(jedis);
        }
        return result;
    }

    public Long delete(String ...key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = pool.getResource();
            result = jedis.del(key);
        } catch (Exception ex) {

        } finally {
            close(jedis);
        }
        return result;
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
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            result = jedis.set(key, value);
        } catch (Exception ex) {

        } finally {
            close(jedis);
        }

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
            close(jedis);
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
            close(jedis);
        }

        return false;
    }

    public Long hset(String key, String field, String value) {
        Long result = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            result = jedis.hset(key, field, value);
        } catch (Exception ex) {

        } finally {
            close(jedis);
        }

        return result;
    }

    public String hmset(String key, Map<String, String> keyValueMap) {
        try (Jedis jedis = pool.getResource()){
            String result = jedis.hmset(key, keyValueMap);
            return result;
        } catch (Exception ex) {
        }
        return null;
    }

    private void close(Jedis jedis) {
        if (null != jedis) {
            try {
                jedis.close();
            } catch (Exception ex) {

            }
        }
    }
}
