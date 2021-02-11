package JavaThreadsConcurrency.Synchronisation;

import JavaThreadsConcurrency.Synchronisation.Model.CountDown;
import JavaThreadsConcurrency.Synchronisation.Model.CountDownThread;

/**
 * Synchronisation: the process of controlling when threads execute codes and access the heap space 同步化
 * 1. 可以同步方法(除了构造器方法之外), 实例方法或者静态方法
 * 2. 可以同步Block of Statements, 一段语句块
 */
public class BaseSynchronisation {

    /**
     * Thread1 & 2共享一个对象，对于线程所使用的变量，有如下的特点
     * 1. 所有的Thread都会共享process所用的heap内存空间            ==> JVM allocate the space from Heap for instance variables
     * 2. 所有的Thread都各自拥有自己的Stack栈空间，支持各自的局部变量 ==> JVM allocate the space Stack for the local variables
     * 3. Stack栈内存存放的是对象的引用，而对象的数据值存储在heap内存中 !!
     * 4. Threads will create their own copy of object 每个线程中对象的引用不一致
     * 5. Thread Stack only contains primitive values / object reference / method reference
     */
    private static void testThreadVariables() {
        CountDown countDown = new CountDown();
        CountDownThread thread1 = new CountDownThread(countDown);
        thread1.setName("Thread 1");
        CountDownThread thread2 = new CountDownThread(countDown);
        thread2.setName("Thread 2");
        thread1.start();
        thread2.start();
    }

    // 使用多个对象来取消线程之间的相互影响和干扰
    // 实际应用中, 通常是多个线程共享一个对象和数据, 相互之间存在等待和影响 !!
    private static void testSynchronisation() {
        CountDown countDown1 = new CountDown();
        CountDownThread thread1 = new CountDownThread(countDown1);
        thread1.setName("Thread 1");

        CountDown countDown2 = new CountDown();
        CountDownThread thread2 = new CountDownThread(countDown2);
        thread2.setName("Thread 2");

        thread1.start();
        thread2.start();
    }
}
