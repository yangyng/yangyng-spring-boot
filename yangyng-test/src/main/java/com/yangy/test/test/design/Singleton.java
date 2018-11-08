package com.yangy.test.test.design;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 设计模式
 * 保证在一个JVM中,只能存在一个实例对象,保证对象的唯一性
 * 优点: 节约内存 重复利用 方便管理
 * 缺点: 线程安全问题
 * </P>
 *
 * @author yang yang
 * @create 2018/11/8
 */
public class Singleton {

    public static void main(String[] args) throws InterruptedException {
        Lazy lazy = Lazy.getInstance();
        TimeUnit.SECONDS.sleep(1);
        new Thread() {
            @Override
            public void run() {
                Lazy lazy1 = Lazy.getInstance();
                System.out.println(lazy1 == lazy1);
                try {
                    TimeUnit.SECONDS.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}

/**
 * <p>
 * 饿汉式
 *
 *
 * </P>
 */
class Hungry {

    private static Hungry hungry = new Hungry();

    public static Hungry getInstance() {
        return hungry;
    }

    //1.构造函数私有化
    private Hungry() {
    }
}

class Lazy {
    private static Lazy lazy;

    public static synchronized Lazy getInstance() {
        if (null == lazy) {
            lazy = new Lazy();
        }
        return lazy;
    }

    //1.构造函数私有化
    private Lazy() {
    }
}

class Inner {

    private Inner() {

    }

    public static class Instance {
        public static final Inner inner = new Inner();
    }

    public static Inner getInstance() {
        return Instance.inner;
    }
}