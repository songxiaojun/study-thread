package com.hfuu.thread10;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量 Semaphore
 *
 */
public class SemapDemo implements Runnable{
    final Semaphore semp = new Semaphore(5);
    public void run() {
        try {
            semp.acquire();
            Thread.sleep(20000);
            System.out.println(Thread.currentThread().getId()+":done");
            semp.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(demo);
        }
    }
}