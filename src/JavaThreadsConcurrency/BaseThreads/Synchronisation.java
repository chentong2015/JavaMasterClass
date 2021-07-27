package JavaThreadsConcurrency.BaseThreads;

/**
 * Synchronisation: the process of controlling when threads execute codes and access the heap space 同步化
 * 1. 可以同步Methods(除了构造器方法之外) & 可以同步Block of Statements
 * 2. Critical Section(临界区域): Shared resource like a variable, only one thread can execute critical section 共享资源的区域
 * 3. Thread-Safe: Has synchronized all the critical sections within the code 线程安全的，如果不是，则需要自定义添加
 * 4. 需要同步所有可能出现多线程interferences的地方 !!! 但最大限度的同步最小的语句块，避免同步对线程造成中断，影响程序的性能 !!!
 * 5. 在共享数据的过程中，Writing比Reading更容易造成错误
 */
public class Synchronisation {

    /**
     * Thread 1 & 2共享一个对象，对于线程所使用的变量:
     * 1. 所有的Thread都会共享process所用的heap内存空间             ==> JVM allocate the space from Heap for instance variables
     * 2. 所有的Thread都各自拥有自己的Stack栈空间，支持各自的局部变量   ==> JVM allocate the space Stack for the local variables
     * 3. TODO: Threads will create their own copy of object   每个线程中对象的引用不一致 + 17.4. Memory Model ===> 深入理解Java虚拟机
     * 4. Stack栈内存存放的是对象的引用，而对象的数据值存储在heap内存中
     * 5. Stack contains primitive values, object reference, method reference
     */
    private static void testThreadVariables() {
        ShareVariables threadShareVariables = new ShareVariables();
        Thread thread1 = new Thread(threadShareVariables::doCountDown, "Thread 1");
        Thread thread2 = new Thread(threadShareVariables::doCountDown, "Thread 2");
        thread1.start();
        thread2.start();
    }

    /**
     * 使用多个对象来取消线程之间的相互影响和干扰
     * 实际应用中, 通常是多个线程共享一个对象和数据, 相互之间存在等待和影响 !!
     */
    private static void testSynchronisation() {
        ShareVariables variables1 = new ShareVariables();
        Thread thread1 = new Thread(variables1::doCountDown, "Thread 1");
        ShareVariables variables2 = new ShareVariables();
        Thread thread2 = new Thread(variables2::doCountDown, "Thread 2");
        thread1.start();
        thread2.start();
    }

    /**
     * Asynchronization 异步化：
     * 以下两个方法由于需要lock monitor不同，可以在不同线程中"同时"执行, 但可能造成数据相互interference
     */
    public synchronized void testInstance() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    public static synchronized void testClass() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    // TODO: 两个线程调用两个方法(正向和反向)，会不会有问题 ?
    // public synchronized void test() {}
    // public void test02() {
    //     synchronized(ClassA) { }
    // }
}
