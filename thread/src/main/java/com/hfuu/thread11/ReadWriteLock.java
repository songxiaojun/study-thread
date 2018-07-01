package com.hfuu.thread11;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadWriteLock {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    /**
     * 模拟读操作
     * @param lock
     * @return
     */
    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return  value;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 模拟写操作
     */
    public void handleWrite(Lock lock,int index) throws InterruptedException {
        try{
            lock.lock();
            Thread.sleep(1000);
            value = index;
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        final ReadWriteLock demo = new ReadWriteLock();
        Runnable readRunnable = new Runnable() {
            public void run() {
                try{
                 demo.handleRead(readLock);
                    demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnale = new Runnable() {
            public void run() {
                try {
                    demo.handleWrite(writeLock,new Random().nextInt());
                    demo.handleWrite(lock,new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 20; i++) {
            new Thread(readRunnable).start();

        }
        for (int i = 18; i <20 ; i++) {
            new Thread(writeRunnale).start();
        }
    }
}