package com.hfuu.thread06;

/**
 * volatile 保证数据可见性 和有序性
 */
public class NoVisibility {
    private volatile static boolean ready;
    private volatile static int number;
    private static class ReadyThread extends Thread{
        @Override
        public void run() {
            while (!ready){
                System.out.println(number);
            }
        }

        public static void main(String[] args) throws InterruptedException {
            new ReadyThread().start();
            Thread.sleep(1000);
            number = 42;
            ready = true;
            Thread.sleep(10000);
        }
    }
}