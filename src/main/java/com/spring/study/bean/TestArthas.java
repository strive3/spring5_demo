package com.spring.study.bean;


import java.util.concurrent.TimeUnit;

/**
 * @Author duxiaopeng
 * @Date 2021/3/8 6:56 下午
 * @Description TODO
 */
public class TestArthas {

    public static void main(String[] args) throws Exception{
        a();
    }

    public static void a() throws Exception{
        for (;;){
            TimeUnit.SECONDS.sleep(1);
            b();
        }
    }

    private static void b() throws Exception{
        TimeUnit.SECONDS.sleep(2);
        c();
    }

    private static void c() throws Exception{
        TimeUnit.SECONDS.sleep(3);
    }
}
