package com.hfuu.thread01;

/**
 * @author sj
 * @date 2018/5/31 10:08
 * 单例模式中 懒汉式 双重判空来保证线程安全性
 */
public class SingletonDemo {
    private SingletonDemo singletonDemo;
    private SingletonDemo(){}
    public SingletonDemo getInstance(){
        if(singletonDemo==null){
            synchronized (SingletonDemo.class){
                if(singletonDemo==null){
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }
}
