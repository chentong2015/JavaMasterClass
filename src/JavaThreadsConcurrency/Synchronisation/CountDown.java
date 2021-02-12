package JavaThreadsConcurrency.Synchronisation;

public class CountDown {

    // 隶属于类型的成员，也具有多线程共享性
    private static int id;
    private static String syncStr;

    // 对象的field成员存储在内存的heap(堆)空间中: 所有的Threads共享数据
    private int index;

    /**
     * For循环所操作的每一步，都可能因为线程的调度而中断 ===> 在共享数据的过程中，Writing比Reading更容易造成错误
     * Thread 1 => 10
     * Thread 2 => 10
     * Thread 1 => 9
     * Thread 2 => 8
     * Thread 1 => 7
     * Thread 6 => 6
     * Thread 1 => 5
     * Thread 2 => 4
     * Thread 2 => 2  共享数据，但是线程的调度具有不规则性
     * Thread 1 => 3  由于在输出前中断，先交给Thread 2输出2之后再由Thread 1输出3
     * Thread 2 => 1
     */
    public void doCountDown() {
        String name = Thread.currentThread().getName();
        for (index = 10; index > 0; index--) {
            System.out.println(name + ": " + index);
        }
        // 方法成员的local variables局部变量存储在所在thread线程的Stack(栈)空间中 ===> 独立于其他Thread, 不被共享
        for (int i = 5; i > 0; i--) {
            System.out.println(name + ": " + i);
        }
    }

    /**
     * Synchronized Method: 同一时刻只有一个线程(可能)在执行该方法，别的线程会等待直到结束
     * 1. 使用"同步化"确保线程安全操作数据, 避免数据受到多个线程的interference干扰, 取消数据共享性, No interleave交错
     * 2. 如果一个线程获得到同步锁之后，可以再继续执行需要该同步锁的代码块
     */
    public synchronized void doCountDownSynchronized() {
        String name = Thread.currentThread().getName();
        for (index = 10; index > 0; index--) {
            System.out.println(name + ": " + index);
        }
    }

    /**
     * Synchronized Statement (Lock锁): 同一时刻只有一个线程(可能)在执行该语句块，别的线程会等待直到结束      ====> C#区别；lock(object) {}
     * 1. 线程必须先获得一个(共享)对象的锁，同一时刻只有一个线程拥有该锁, 执行完之后释放给其他等待的线程去执行
     * 2. 不能使用primitive type作为同步锁, 不是对象，没有固有的锁
     */
    public void doCountDownLocked() {
        String name = Thread.currentThread().getName();

        // NOK: 不可同步化局部变量，由于每个Thread都将拥有自己的局部变量，都将获得锁，然后执行
        String localStr = "Lock";
        synchronized (localStr) {
            for (index = 10; index > 0; index--) {
                System.out.println(name + ": " + index);
            }
        }

        // OK: 同一时刻，最多只有一个Thread会拿到当前(共享)对象的锁，执行block of statements, 然后释放锁
        synchronized (this) {
            for (index = 10; index > 0; index--) {
                System.out.println(name + ": " + index);
            }
        }
    }

    /**
     * Synchronized Static:
     * 1. 可以同步类型的静态方法
     * 2. 可以使用静态的对象static object作为同步锁
     */
    public synchronized static void testLockStaticMethod() {
        String name = Thread.currentThread().getName();
        for (id = 10; id > 0; id--) {
            System.out.println(name + ": " + id);
        }
        synchronized (syncStr) {
            for (id = 10; id > 0; id--) {
                System.out.println(name + ": " + id);
            }
        }
    }
}
