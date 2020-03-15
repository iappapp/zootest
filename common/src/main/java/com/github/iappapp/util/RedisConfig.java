package com.github.iappapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
}
