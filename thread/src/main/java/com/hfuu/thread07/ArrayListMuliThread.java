package com.hfuu.thread07;

import java.util.ArrayList;

/**
 * 并发下的ArrayList
 * ArrayList 在扩容过程中，内部一致性被破坏，由于没有锁的保护，另外一个线程访问到了不一致的内部状况
 * 导致出现越界问题
 *
 * 由于多线程访问的冲突，使得保存容器大小的变量被多个线程不正常访问，
 * 同时两个线程也同时对ArrayList中的同一个位置进行赋值导致的
 */
public class ArrayListMuliThread {
    static ArrayList<Integer> al = new ArrayList<Integer>(10);
    public static class AddThread implements Runnable{
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
    }
}