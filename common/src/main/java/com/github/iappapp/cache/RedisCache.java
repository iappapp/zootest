package com.github.iappapp.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.Callable;

@Slf4j
public class RedisCache implements Cache {

    private RedisTemplate<String, Object> redisTemplate;

    private String name;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        //
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        log.info("get key={}", key);
        return new ValueWrapper() {
            @Override
            public Object get() {
                byte[] result =  redisTemplate.getConnectionFactory().getConnection()
                        .hGet(name.getBytes(), ((String) key).getBytes());
                System.out.println(result);
                if (null == result) {
                    throw new RuntimeException("key not exist");
                }
                return result != null ? new SimpleValueWrapper(result) : null;
            }
        };
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        byte[] result = redisTemplate.getConnectionFactory().getConnection()
                .hGet(name.getBytes(), ((String) key).getBytes());
        String json = new String(result);
        return JSON.parseObject(json, type);
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        log.info("get key={}", key);
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        log.info("put key={} value={}", key, value);
        redisTemplate.getConnectionFactory().getConnection()
                .hSet(name.getBytes(), ((String) key).getBytes(), JSON.toJSONString(value).getBytes());
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
        boolean exist = redisConnection.hExists(name.getBytes(), ((String) key).getBytes());
        if (!exist) {
            redisConnection.hSet(name.getBytes(), ((String) key).getBytes(), JSON.toJSONString(value).getBytes());
        }
        return new ValueWrapper() {
            @Override
            public Object get() {
                return redisConnection.hGet(name.getBytes(), ((String) key).getBytes());
            }
        };
    }

    @Override
    public void evict(Object key) {
        redisTemplate.getConnectionFactory().getConnection().hDel(name.getBytes(), ((String) key).getBytes());
    }

    @Override
    public void clear() {
        redisTemplate.getConnectionFactory().getConnection().del(name.getBytes());
    }
}
