package com.spring.study.bean;


/**
 * @Author duxiaopeng
 * @Date 2021/3/12 2:41 下午
 * @Description TODO
 */
public class Singleton {

    private static volatile Singleton singleton;

    public static Singleton getSingleton(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                    //
                }
            }
        }
        return singleton;
    }

    private static synchronized void a(){
        System.out.println("aaaa");
        System.out.println(Thread.currentThread().getName());
        b();
    }
    private static synchronized void b(){
        System.out.println(Thread.currentThread().getName());
        System.out.println("b");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                a();
            });
            thread.start();
        }

    }
}
