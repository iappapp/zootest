package com.github.iappapp.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class RedisUtil {
    @Autowired
    private JedisPool pool;

    public long del(String key) {
        Long result = null;
        try (Jedis jedis = pool.getResource()){
            result = jedis.del(key);
        } catch (Exception ex) {
            // ignore error
            log.info("del key={} error={}", key, ex);
        }
        return result;
    }

    public Long delete(String ...keys) {
        Long result = null;
        try (Jedis jedis = pool.getResource();){
            result = jedis.del(keys);
        } catch (Exception ex) {
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

    public String hmset(String key, Map<String, String> keyValueMap) {
        try (Jedis jedis = pool.getResource()){
            String result = jedis.hmset(key, keyValueMap);
            return result;
        } catch (Exception ex) {
        }
        return null;
    }

    public List<String> hmget(String key, String ...fields) {
        List<String> result = null;
        try (Jedis jedis = pool.getResource()){
            result = jedis.hmget(key, fields);
        } catch (Exception ex) {
        }
        return result;
    }

    public String hget(String key, String field) {
        String result = null;
        try (Jedis jedis = pool.getResource()){
            result = jedis.hget(key, field);
        } catch (Exception ex) {

        }
        return result;
    }

    public Long hset(String key, String field, String value) {
        Long result = null;
        try (Jedis jedis = pool.getResource()){
            result = jedis.hset(key, field, value);
        } catch (Exception ex) {

        }
        return result;
    }

    public Boolean hexists(String key, String field) {
        try (Jedis jedis = pool.getResource()){
            return jedis.hexists(key, field);
        } catch (Exception ex) {

        }
        return Boolean.FALSE;
    }

    public Long hlen(String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.hlen(key);
        } catch (Exception ex) {

        }
        return 0L;
    }

    public Long hdel(String key, String ...fields) {
        try (Jedis jedis = pool.getResource()){
            return jedis.hdel(key, fields);
        } catch (Exception ex) {

        }
        return 0L;
    }

    public Set<String> hkeys(String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.hkeys(key);
        } catch (Exception ex) {

        }
        return null;
    }

    public List<String> hvals(String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.hvals(key);
        } catch (Exception ex) {

        }
        return null;
    }

    public Map<String, String> hgetAll(String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.hgetAll(key);
        } catch (Exception ex) {

        }
        return null;
    }

    public Long rpush(String key, String ...strings) {
        try (Jedis jedis = pool.getResource()){
            return jedis.rpush(key, strings);
        } catch (Exception ex) {
            log.info("rpush error", ex);
        }
        return 0L;
    }

    public Long lpush(String key, String ...strings) {
        try (Jedis jedis = pool.getResource()){
            return jedis.lpush(key, strings);
        } catch (Exception ex) {
        }
        return 0L;
    }

    public Long llen(String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.llen(key);
        } catch (Exception ex) {
        }
        return 0L;
    }

    public String lpop(String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.lpop(key);
        } catch (Exception ex) {
        }
        return null;
    }

    public String rpop(String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.rpop(key);
        } catch (Exception ex) {
        }
        return null;
    }

    public List<String> lrange(String key, long start, long length) {
        try (Jedis jedis = pool.getResource()){
            return jedis.lrange(key, start, length);
        } catch (Exception ex) {
        }
        return null;
    }

    public String ltrim(String key, long start, int end) {
        try (Jedis jedis = pool.getResource()){
            return jedis.ltrim(key, start, end);
        } catch (Exception ex) {
        }
        return null;
    }

    public String lindex(String key, long index) {
        try (Jedis jedis = pool.getResource()){
            return jedis.lindex(key, index);
        } catch (Exception ex) {
        }
        return null;
    }

    public String lset(String key, long index, String value) {
        try (Jedis jedis = pool.getResource()){
            return jedis.lset(key, index, value);
        } catch (Exception ex) {
        }
        return null;
    }

    public Long lrem(String key, long count, String value) {
        try (Jedis jedis = pool.getResource()){
            return jedis.lrem(key, count, value);
        } catch (Exception ex) {
        }
        return null;
    }

    public Long sadd(String key, String ...members) {
        try (Jedis jedis = pool.getResource()){
            return jedis.sadd(key, members);
        } catch (Exception ex) {

        }
        return null;
    }

    public Set<String> smembers(String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.smembers(key);
        } catch (Exception ex) {

        }
        return null;
    }

    public Long srem(String key, String ...members) {
        try (Jedis jedis = pool.getResource()){
            return jedis.srem(key, members);
        } catch (Exception ex) {

        }
        return null;
    }

    public String spop(String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.spop(key);
        } catch (Exception ex) {

        }
        return null;
    }

    public Set<String> spop(String key, long count) {
        try (Jedis jedis = pool.getResource()){
            return jedis.spop(key, count);
        } catch (Exception ex) {

        }
        return null;
    }

    public Boolean sismember(final String key, final String member) {
        try (Jedis jedis = pool.getResource()){
            return jedis.sismember(key, member);
        } catch (Exception ex) {

        }
        return Boolean.FALSE;
    }

    public Long smove(String srcKey, String dstKey, String member) {
        try (Jedis jedis = pool.getResource()){
            return jedis.smove(srcKey, dstKey, member);
        } catch (Exception ex) {

        }
        return null;
    }

    public Long scard(String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.scard(key);
        } catch (Exception ex) {

        }
        return null;
    }

    public Set<String> sinter(String ...keys) {
        try (Jedis jedis = pool.getResource()){
            return jedis.sinter(keys);
        } catch (Exception ex) {

        }
        return Sets.newHashSet();
    }

    public Long sinterstore(String dstKey, String ...keys) {
        try (Jedis jedis = pool.getResource()){
            return jedis.sinterstore(dstKey, keys);
        } catch (Exception ex) {

        }
        return null;
    }

    public Set<String> sunion(String ...keys) {
        try (Jedis jedis = pool.getResource()){
            return jedis.sunion(keys);
        } catch (Exception ex) {

        }
        return Sets.newHashSet();
    }

    public Long sunionstore(String dstKey, String ...keys) {
        try (Jedis jedis = pool.getResource()){
            return jedis.sunionstore(dstKey, keys);
        } catch (Exception ex) {

        }
        return null;
    }

    public Set<String> sdiff(String ...keys) {
        try (Jedis jedis = pool.getResource()){
            return jedis.sdiff(keys);
        } catch (Exception ex) {
        }
        return Sets.newHashSet();
    }

    public Long sdiffstore(String dstKey, String ...keys) {
        try (Jedis jedis = pool.getResource()){
            return jedis.sdiffstore(dstKey, keys);
        } catch (Exception ex) {
        }
        return null;
    }

    public List<String> configGet(String pattern){
        try (Jedis jedis = pool.getResource()){
            return jedis.configGet(pattern);
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
