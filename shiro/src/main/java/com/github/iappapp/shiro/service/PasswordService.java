package com.github.iappapp.shiro.service;

import com.github.iappapp.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordService {
    @Autowired
    private RedisUtil redisUtil;



}
