package com.hfuu.thread10;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sj
 * @date 2018/6/1 15:49
 * 可重入锁 中断响应
 */
public class IntLock implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    /**
     * 控制加锁顺序，方便构造死锁
     */
    public IntLock(int lock){
        this.lock=lock;
    }
    public void run() {
        if(lock==1){
            try {
                lock1.lockInterruptibly();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                try {
                    lock2.lockInterruptibly();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
