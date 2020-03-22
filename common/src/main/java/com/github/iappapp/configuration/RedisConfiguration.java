package com.github.iappapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedisConfiguration {
    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.password}")
    private String password;

}
