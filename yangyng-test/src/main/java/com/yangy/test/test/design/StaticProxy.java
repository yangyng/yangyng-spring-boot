package com.yangy.test.test.design;

/**
 * 代理模式
 *
 * @author yang yang
 * @create 2018/11/10
 */
public class StaticProxy implements testInterface{

    private testInterface testInterface;

    public StaticProxy(testInterface testInterface){
        this.testInterface = testInterface;
    }

    @Override
    public void test() {
        testInterface.test();
    }

    public static void main(String[] args) {
        new StaticProxy(new testClass()).test();
    }
}

interface testInterface{
    void test();
}

class testClass implements testInterface{

    @Override
    public void test() {
        System.out.println("这是一个测试方法");
    }
}



