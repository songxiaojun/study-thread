package com.hfuu.thread06;

/**
 * volatile 无法保证i++是原子性 ，
 * 如果i++ 是原子性 结果是 10000
 *
 */
public class Volatile {

    static volatile int i =0;
    public static class PlusTask implements Runnable{
        public void run() {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i <10 ; i++) {
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();

        }
        System.out.println(i);
    }
}