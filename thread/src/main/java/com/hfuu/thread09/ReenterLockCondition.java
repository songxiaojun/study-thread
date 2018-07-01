package com.hfuu.thread09;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 *  await()
 *  awaitUninterruptibly()
 *  signalAll()
 *  signal()
 */
public class ReenterLockCondition implements Runnable{
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition tl = new ReenterLockCondition();
        Thread t1 = new Thread(tl);
        t1.start();
        Thread.sleep(2000);
        //通知线程t1继续执行
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}