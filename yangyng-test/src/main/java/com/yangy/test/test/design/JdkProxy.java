package com.yangy.test.test.design;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式
 *
 * @author yang yang
 * @create 2018/11/10
 */
public class JdkProxy implements InvocationHandler {

    private Object object;

    public JdkProxy(Object o){
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(object, args);
        return invoke;
    }

    public static void main(String[] args) {
        testInterface anInterface = new testClass();

        JdkProxy jdkProxy = new JdkProxy(anInterface);
        ClassLoader classLoader = anInterface.getClass().getClassLoader();
        Class<?>[] interfaces = anInterface.getClass().getInterfaces();

        testInterface instance = (testInterface) Proxy.newProxyInstance(classLoader, interfaces, jdkProxy);
        instance.test();
    }

}




