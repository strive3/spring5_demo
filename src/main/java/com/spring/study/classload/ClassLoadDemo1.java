package com.spring.study.classload;

import java.net.URLClassLoader;
import java.security.SecureClassLoader;

/**
 * @Author duxiaopeng
 * @Date 2021/3/20 9:20 下午
 * @Description TODO
 */
public class ClassLoadDemo1 {
    public static void main(String[] args) {
        ClassLoadDemo1 demo1 = new ClassLoadDemo1();
        ClassLoader classLoader = demo1.getClass().getClassLoader();
        //应用程序类加载器
        System.out.println("classLoader\t" + classLoader);
        //扩展类加载器
        System.out.println("parent classLoader\t" + classLoader.getParent());
        //BootStrap 类机载器
        System.out.println("parent parent classLoader\t" + classLoader.getParent().getParent());

        //ClassLoader
        //SecureClassLoader
        //URLClassLoader

    }
}
