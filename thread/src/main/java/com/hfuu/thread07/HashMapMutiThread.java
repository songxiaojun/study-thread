package com.hfuu.thread07;

import java.util.HashMap;
import java.util.Map;

/**
 * 并发环境下的HashMap
 */
public class HashMapMutiThread {
    static Map<String,String> map = new HashMap<String, String>(16);
    public static class AddThread implements Runnable{
        int start = 0;
        public AddThread(int start){
            this.start= start;
        }
        public void run() {
            for (int i = start; i <100000 ; i+=2) {
                map.put(Integer.toString(i),Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new HashMapMutiThread.AddThread(0));
        Thread t2 = new Thread(new HashMapMutiThread.AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}