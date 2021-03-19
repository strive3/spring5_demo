package com.spring.study.text;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author duxiaopeng
 * @Date 2021/3/12 7:33 下午
 * @Description 3个线程按照顺序打印ABCABCABCABCABCABC
 */
public class ThreadOrder {
    private static ReentrantLock lock = new ReentrantLock();

    private static Condition conA = lock.newCondition();
    private static Condition conB = lock.newCondition();
    private static Condition conC = lock.newCondition();

    private static CountDownLatch latchB = new CountDownLatch(1);
    private static CountDownLatch latchC = new CountDownLatch(1);

    public static void main(String[] args){
        Thread threadA = new Thread(()->{
            lock.lock();
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.print("A");
                    if (i != 0) conB.signal();//唤醒B
                    if (i == 0) latchB.countDown();
                    conA.await();//A进入睡眠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            conB.signal();//为了保障程序能够执行完毕
            lock.unlock();
        },"a");

        Thread threadB = new Thread(()->{
            try {
                latchB.await();//这里是为了保障第一次打印的是线程A
                lock.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.print("B");
                    conC.signal();//唤醒C
                    if (i == 0) latchC.countDown();
                    conB.await();//B进入睡眠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            conC.signal();
            lock.unlock();
        },"b");

        Thread threadC = new Thread(()->{
            try {
                latchC.await();//这里是为了保障第一次打印的是线程A
                lock.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.print("C");
                conA.signal();
                try {
                    conC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            conA.signal();
            lock.unlock();
        },"c");

        threadB.start();
        threadC.start();
        threadA.start();
    }
}
