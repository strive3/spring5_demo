package com.spring.study.gywork;

/**
 * @Author duxiaopeng
 * @Date 2021/4/25 8:04 下午
 * @Description 高毓作业
 */
public class GyWork {
    public static void main(String[] args) {
        doWork(11, 22, 233, true);
    }

    public static void doWork(int a, int b, int c, boolean addOrSub){
        int value = 0;
        if (addOrSub)
            value = a * b + c;
        else
            value = a * b - c;
        System.out.println("运算结果：" + value);
        if (value < 500)
            System.out.println("小于500");
        if (value < 1000)
            System.out.println("小于1000");
        if (value > 1000)
            System.out.println("大于1000");
        if (value < 0)
            System.out.println("负数！");

    }
}
