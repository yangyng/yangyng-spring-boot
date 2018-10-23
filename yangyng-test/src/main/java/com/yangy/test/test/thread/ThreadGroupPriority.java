package com.yangy.test.test.thread;

/**
 * <p>
 * 线程优先级
 * </P>
 *
 * @author yang yang
 * @create 2018/10/20
 */
public class ThreadGroupPriority {

    public static void main(String[] args) {

        /*
         * 线程的优先级不能高于当前线程组的优先级
         * */

        ThreadGroup group = new ThreadGroup("group");
        group.setMaxPriority(7);

        Thread thread = new Thread(group, "thread");
        thread.setPriority(10);
        System.out.println(thread.getPriority());
    }

}