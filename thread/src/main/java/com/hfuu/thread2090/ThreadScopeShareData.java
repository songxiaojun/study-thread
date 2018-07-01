package com.hfuu.thread20;

import java.util.Random;

/**
 * @author sj
 * @date 2018/6/3 14:36
 * threadlocal
 *
 */
public class ThreadScopeShareData {
    private static int data = 0;
    private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();


    public static void main(String[] args) {
        for (int i = 0; i <2 ; i++) {
            new Thread(new Runnable() {
                public void run() {
                    data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()+" has put data:"+data);
                    x.set(data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }


    }
    static class A{
        public void get(){
            int data = x.get();
            System.out.println("A from "+Thread.currentThread().getName()+" get data:"+data);

        }
    }

    static class B{
        public void get(){
            int data = x.get();
            System.out.println("B from "+Thread.currentThread().getName()+ " get data:"+data);

        }
    }
}
