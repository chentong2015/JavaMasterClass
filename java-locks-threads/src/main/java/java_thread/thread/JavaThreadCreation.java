package java_thread.thread;

import java.util.concurrent.ThreadFactory;

// TODO. 创建线程的四种形式，本质上都需要实现Runnable接口
// 1. 自定义实现Runnable接口
// 2. 通过继承Thread类型(Thread本身实现Runnable接口)
// 3. 使用匿名类型来创建线程
// 4. 自定义ThreadFactory线程工厂来创建线程
public class JavaThreadCreation {

    static class DemoRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable thread");
            // Do something
        }
    }

    static class DemoThread extends Thread {
        @Override
        public void run() {
            System.out.println(currentThread().getName());
            // Do something
        }
    }

    // TODO. 使用匿名类型创建线程并执行，可替换成lambda表达式
    public void testAnonymousThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Runnable");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("Anonymous class");
            }
        }.start();
    }

    // TODO. 本质上是创建一个线程工厂，提供创建线程的自定义方式
    public void testThreadFactory() {
        ThreadFactory myThreadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                // Create new thread by Runnable Impl
                return new Thread(runnable);
            }
        };
        myThreadFactory.newThread(() -> {
            System.out.println("Impl Runnable");
        }).start();
    }
}
