package com.hfuu.thread04;

/**
 * @author sj
 * @date 2018/5/31 16:09
 * 线程中 Thread.stop() 方法太过暴力  容易引起数据不一致问题  id 和name 不一致
 */
public class StopThreadUnsafe {
    public static User user = new User();
    public static class User {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User() {
            id = 0;
            name = "0";
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
    public static class ChangeObjectThread extends Thread{
        public void run(){
            while (true) {
                synchronized (user) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    user.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(v + "");
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        public void run() {
            while (true) {
                synchronized (user) {
                    if (user.getId() != Integer.parseInt(user.getName())) {
                        System.out.println(user.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            Thread thread = new ChangeObjectThread();
            thread.start();
            Thread.sleep(150);
            thread.stop();
        }
    }
}
