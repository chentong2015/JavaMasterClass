package jvm_basics.chapter13_Lock_ThreadSafety.ThreadSafety;

// ThreadLocal:
// 保护线程的局部变量，变量只被某个线程所独享，实现一个线程的本地存储
// 如果共享数据的代码能够保证在同一个线程中执行，则将共享数据范围约束在同一个线程之内

// ThreadLocal底层实现原理:
// 1. 线程Thread对象都有一个ThreadLocalMap对象，存储<ThreadLocalHashCode, Value>的键值对
// 2. ThreadLocal对象就是当前线程的ThreadLocalMap的入口，它包含唯一的HashCode值，用于在K-V中找到本地的线程变量
public class ThreadLocalDemo {

    // 主线程中的线程变量不能被另一个线程共享
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("value");

        new Thread(() -> {
            System.out.println("new thread:" + threadLocal.get());
        }).start();

        System.out.println(threadLocal.get());
    }
}
