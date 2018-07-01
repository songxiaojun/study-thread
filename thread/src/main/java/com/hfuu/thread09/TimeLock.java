package com.hfuu.thread09;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁 限时等待锁
 */
public class TimeLock implements Runnable{
    static ReentrantLock lock = new ReentrantLock();
    public void run() {
        try{
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
            }else {
                System.out.println("get lock failed");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        TimeLock tl = new TimeLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start();
        t2.start();
    }
}