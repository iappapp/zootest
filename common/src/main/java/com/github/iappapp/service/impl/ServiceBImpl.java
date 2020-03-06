package com.github.iappapp.service.impl;

import com.github.iappapp.service.ServiceA;
import com.github.iappapp.service.ServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServiceBImpl implements ServiceB {
    @Autowired
    private ServiceA serviceA;

    @Override
    public void pushSomething() {
        log.info("ServiceBImpl pushSomething");
    }
}
