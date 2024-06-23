package concurrent_program.thread.Synchronisation;

/**
 * Synchronisation: the process of controlling when threads execute codes and access the heap space
 * 1. 可以同步Methods(除了构造器方法之外) & 可以同步Block of Statements
 * 2. Critical Section(临界区域): Shared resource like a variable, only one thread can execute critical section 共享资源的区域
 * 3. BaseThread-Safe: Has synchronized all the critical sections within the code 线程安全的，如果不是则需要自定义添加
 * 4. 需要同步所有可能出现多线程interferences的地方
 * 5. 但最大限度的同步最小的语句块，避免同步对线程造成中断，影响程序的性能
 * 6. 在共享数据的过程中，Writing比Reading更容易造成错误
 */
public class Synchronisation {

    /**
     * BaseThread 1 & 2共享一个对象，对于线程所使用的变量:
     * 1. 所有的Thread都会共享process所用的heap内存空间
     * 2. 所有的Thread都各自拥有自己的Stack栈空间，支持各自的局部变量
     * 3. Threads will create their own copy of object
     * 4. Stack栈内存存放的是对象的引用，而对象的数据值存储在heap内存中
     * 5. Stack contains primitive values, object reference, method reference
     */
    private static void testThreadVariables() {
        ShareVariables threadShareVariables = new ShareVariables();
        Thread thread1 = new Thread(threadShareVariables::doCountDown, "BaseThread 1");
        Thread thread2 = new Thread(threadShareVariables::doCountDown, "BaseThread 2");
        thread1.start();
        thread2.start();
    }
}
