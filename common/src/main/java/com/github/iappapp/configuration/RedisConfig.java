package com.github.iappapp.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(autowire = Autowire.BY_NAME, preConstruction = true)
@Getter
public class RedisConfig {
    private String host;
    private Integer port;
    private String username;
    private String password;
    @Autowired
    private DbConfig dbConfig;
}
