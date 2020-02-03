package com.github.iappapp.proxy;

import com.github.iappapp.service.MallService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MallServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return args[1];
    }

    public static void main(String[] args) {
        MallService mallService =
                (MallService) Proxy.newProxyInstance(MallService.class.getClassLoader(), new Class[]{MallService.class}, new MallServiceProxy());

        System.out.println(mallService.sellSomething(1, "2"));
    }
}
