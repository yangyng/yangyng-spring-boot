package com.yangy.test.test.thread;

/**
 * @author yang yang
 * @create 2018/10/23
 */
public class TestGoto {
    public static void main(String[] args) {

        int temp = 200;
        Integer fee = 200;
        System.out.println(fee);

        System.out.println(0x7fffffff);
        int i = 2147483647 + 1;
        System.out.println(i);
        System.out.println(i <200);

    }

    static void saveDefault(){
        System.out.println("this is save default");
    }

    static void save(int fee){
        System.out.println(fee);
    }
}