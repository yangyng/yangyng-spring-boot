package com.yangy.test.test.design;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理
 *
 * @author yang yang
 * @create 2018/11/10
 */
public class CglibProxy implements MethodInterceptor {

    private Object object;

    public Object getInstace(Object o) {
        this.object = o;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(o.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invoke(object, objects);
    }


    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        testClass instace = (testClass) proxy.getInstace(new testClass());
        instace.test();
    }
}