package com.github.iappapp.util;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource(value = "classpath:jdbc.properties")
public class RedisConfig {
    @Value(value = "${redis.host}")
    private String host;

    @Value(value = "${redis.port}")
    private Integer port;

    @Value(value = "${redis.password}")
    private String password;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(2000L);
        config.setMaxIdle(32);
        config.setMaxTotal(128);

        return config;
    }

    @Bean
    public JedisPool jedisPool(@Autowired JedisPoolConfig jedisPoolConfig) {
        return new JedisPool(jedisPoolConfig, host, port, 2000, password.trim());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setStringSerializer(new FastJsonRedisSerializer<>(String.class));
        redisTemplate.setKeySerializer(new FastJsonRedisSerializer<>(String.class));
        redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setDatabase(0);
        configuration.setHostName(host);
        configuration.setPassword(password);
        configuration.setPort(port);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration);

        return jedisConnectionFactory;
    }

    /*@Bean
    public RedisCache redisCache() {
        RedisCache redisCache = new RedisCache();
        redisCache.setName("redis-cache-for-annotation-");
        redisCache.setRedisTemplate(redisTemplate());
        return redisCache;
    }*/
}
