package com.github.iappapp.service;

import com.github.iappapp.modal.PushTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class LookupPushMsgPool {
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void pushMsg(String msg){
        System.out.println("LookupPushMsgPool" + " " + msg);
        threadPoolTaskExecutor.submit(pushTask(msg));
    }

    @Lookup
    protected PushTask pushTask(String msg) {
        return new PushTask(msg);
    }
}
