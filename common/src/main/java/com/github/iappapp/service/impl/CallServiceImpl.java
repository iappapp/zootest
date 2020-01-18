package com.github.iappapp.service.impl;

import com.github.iappapp.service.CallService;
import org.springframework.stereotype.Service;

@Service(value = "callService")
public class CallServiceImpl implements CallService {
    @Override
    public void call() {
        System.out.println("call you call me");
    }
}
