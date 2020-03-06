package com.github.iappapp.util;

import com.github.iappapp.service.BookService;
import com.github.iappapp.service.CallService;
import com.github.iappapp.service.LookupPushMsgPool;
import com.github.iappapp.service.ServiceB;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CommonApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        BeanDefinition beanDefinition =
                context.getBeanFactory().getBeanDefinition("callService");
        System.out.println(beanDefinition);
        String applicationName = context.getApplicationName();
        System.out.println(applicationName);
        Object object = context.getBean("book");
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.addBook();
        CallService callService = context.getBean(CallService.class);
        callService.call();
        LookupPushMsgPool pool = (LookupPushMsgPool) context.getBean(LookupPushMsgPool.class);
        pool.pushMsg("hello");

        ServiceB serviceB = context.getBean(ServiceB.class);
        serviceB.pushSomething();
        context.close();
        System.out.println(object);
    }
}
