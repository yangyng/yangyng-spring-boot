package com.yangy.test.test.current;

import com.yangy.test.domain.User;
import com.yangy.test.test.thread.CallableImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yang yang
 * @create 2018/10/20
 */
public class CurrentTest {

    private static Integer count = 0;

    public static void main(String[] args) {
        int size = 30;
        ExecutorService pool = Executors.newFixedThreadPool(size);
        List<Future> futures = new ArrayList<>();
        User build = User.builder().name("yangy").gender(1).desp("测试").phone("17700000000").build();

//        ReentrantLock lock = new ReentrantLock();
//
//        lock.lock();
//        boolean tryLock = lock.tryLock();
//        if(tryLock){
            for (int i = 0; i < size; i++) {
                CallableImpl task = new CallableImpl("callable " + i + " ",build);
                Future submit = pool.submit(task);
                futures.add(submit);
            }
//        }
//        lock.unlock();
        pool.shutdown();

        for (Future future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


}