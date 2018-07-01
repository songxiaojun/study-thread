package com.hfuu.thread02;

/**
 * @author sj
 * @date 2018/5/3110:51
 *
 *  Long 类型在32位虚拟机上不是原子性操作
 *  在64位上回得到一个结果 333
 */
public class MultiThreadLong {
    public static long t = 0;
    public static class ChangeT implements Runnable {
        private long to;
        public ChangeT(long to){
            this.to=to;
        }
        public void run() {
            while (true) {
                MultiThreadLong.t=to;
                Thread.yield();
            }
        }
    }
    public static class ReadT implements Runnable {
        public void run() {
            while (true){
                long tem = MultiThreadLong.t;
                if(tem!=111L && tem!=-999L &&tem!=-333L &&tem!=-444L){
                    System.out.println(tem);
                    Thread.yield();
                }
            }
        }
    }
    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();

    }
}
