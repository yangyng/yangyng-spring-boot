package com.yangy.test.test.thread;

import com.yangy.test.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程的多种实现方式
 *
 * @author yang yang
 * @create 2018/10/20
 */
@Slf4j
public class TestThread {

    public static void main(String[] args) {
        TestThread thread = new TestThread();

        thread.thread().start();
        thread.runnable().start();
        thread.callable();

    }

    /**
     * <p>
     * 线程的继承实现
     * </P>
     *
     * @return
     */
    public Thread thread() {
        ThreadExtendImpl thread = new ThreadExtendImpl("thread");
        return thread;
    }

    /**
     * <p>
     * 线程的runnable实现方式
     * </P>
     *
     * @return
     */
    public Thread runnable() {
        ThreadRunnableImpl runnable = new ThreadRunnableImpl();
        runnable.setName("runnable");
        Thread thread = new Thread(runnable);
        return thread;
    }

    /**
     * <p>
     * 线程的callable实现方式
     * </p>
     */
    public void callable() {
        ThreadCallableImpl<User> callable = new ThreadCallableImpl<>();
        callable.setName("callable");
        FutureTask<User> futureTask = new FutureTask<User>(callable);
        new Thread(futureTask).start();
        try {
            User user = futureTask.get();
            log.info(user.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

@Slf4j
class ThreadExtendImpl extends Thread {
    public ThreadExtendImpl(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " is running !");
    }
}

@Slf4j
class ThreadRunnableImpl implements Runnable {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " is running !");
    }
}

@Slf4j
class ThreadCallableImpl<AnyType> implements Callable<AnyType> {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public AnyType call() throws Exception {
        log.info(this.name + " is running !");
        User build = User.builder().name("yangy").gender(1).desp("测试").phone("17700000000").build();
        return (AnyType) build;
    }
}