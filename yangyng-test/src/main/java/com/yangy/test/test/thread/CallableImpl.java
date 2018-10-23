package com.yangy.test.test.thread;

import com.yangy.test.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * <p>
 * 线程的callable实现方式
 * </P>
 *
 * @param <AnyType>
 */
@Slf4j
public class CallableImpl<AnyType> implements Callable<AnyType> {

    private String name;
    private User user;

    public CallableImpl() {
    }

    public CallableImpl(String name) {
        this.name = name;
    }

    public CallableImpl(String name, User user) {
        this.name = name;
        log.info(user.getName() + "|" + this.name);
        user.setName(name);
        this.user = user;
    }

    @Override
    public AnyType call() throws Exception {
        log.info(this.name + " is running !");
        return (AnyType) this.user;
    }
}