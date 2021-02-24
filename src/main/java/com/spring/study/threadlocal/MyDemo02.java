package com.spring.study.threadlocal;

/**
 * @Author duxiaopeng
 * @Date 2021/2/24 11:29 上午
 * @Description 线程隔离：
 *              在多线程并发的场景下，每个线程的变量都是相互独立
 *              线程A： 设置（变量1）    获取（变量1）
 *              线程B： 设置（变量2）    获取（变量2）
 *              ThreadLocal:
 *                  1.set():    将变量绑定到当前线程中
 *                  2.get():    获取当前线程绑定的变量
 */
public class MyDemo02 {


    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        MyDemo02 demo01 = new MyDemo02();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(()->{
                /**
                 * @Description 每个线程存一个变量，过会取出这个变量
                 */
                synchronized (MyDemo02.class){
                    demo01.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println("--------------------");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo01.getContent());
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }

    }

}
