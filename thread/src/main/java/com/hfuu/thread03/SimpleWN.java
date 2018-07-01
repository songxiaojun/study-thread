package com.hfuu.thread03;

/**
 * @author sj
 * @date 2018/5/31 11:53
 * wait() 和notify() 案例
 *
 * 一个线程调用了object.wait();那么它会进入object对象等待队列，在这个等待队列中，可能会有多个线程，因为系统运行多个线程同时等待
 * 某一个对象，当object.notify()被调用时，它就会从这个等待队列中随机选择一个线程，并将其唤醒，这个选择不是公平的，并不是先等待的
 * 线程会优先选择，这个选择是完全随机的
 */
public class SimpleWN {
    final static Object object = new Object();
    public static class T1 extends Thread {

        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+": T1 Start");

                try {
                    System.out.println(System.currentTimeMillis()+": T1 wait for Object ");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+": T1 end");
            }
        }
    }
    public static class T2 extends Thread{

        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T2 start! notify one Thread");
                object.notify();
                System.out.println(System.currentTimeMillis()+":T2 end");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {

        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();

    }

}
