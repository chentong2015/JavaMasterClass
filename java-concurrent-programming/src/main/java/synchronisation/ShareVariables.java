package synchronisation;

/**
 * 1. All instance fields, static fields, and array elements are stored in heap memory 程序的主内存, 堆空间
 * 2. Local variables, formal method parameters, and exception handler parameters are never shared between threads
 */
public class ShareVariables {

    // 对象field成员存储在内存的堆空间(heap memory): 所有Threads共享数据
    private int index;
    // 隶属于类型的成员，也具有多线程共享性: 作为锁, 一般需要设置成final static, 保存值的不可变性
    private final static String syncStr = "lock";

    /**
     * For循环所操作的每一步，都可能因为线程的调度而中断
     * BaseThread 1 => 10
     * BaseThread 2 => 10
     * BaseThread 1 => 9
     * BaseThread 2 => 8
     * BaseThread 1 => 7
     * BaseThread 6 => 6
     * BaseThread 1 => 5
     * BaseThread 2 => 4
     * BaseThread 2 => 2  共享数据，但是线程的调度具有不规则性
     * BaseThread 1 => 3  由于在输出前中断，先交给Thread 2输出2之后再由Thread 1输出3
     * BaseThread 2 => 1
     */
    public void doCountDown() {
        String name = Thread.currentThread().getName();
        for (index = 10; index > 0; index--) {
            System.out.println(name + ": " + index);
        }

        // 方法成员的local variables局部变量存储在所在thread线程的Stack(栈)空间中
        // 独立于其他Thread, 不被内存模型所影响 !!
        for (int i = 5; i > 0; i--) {
            System.out.println(name + ": " + i);
        }
    }

    /**
     * Synchronized Method: 同一时刻只有一个线程(可能)在执行该方法, 别的线程会等待直到结束
     * 确保线程安全操作数据, 避免数据受到多个线程的interference干扰, 取消数据共享性, No interleave交错
     */
    public synchronized void doCountDownSynchronized() {
        String name = Thread.currentThread().getName();
        for (index = 10; index > 0; index--) {
            System.out.println(name + ": " + index);
        }
    }

    /**
     * Synchronized Statement: 同一时刻只有一个线程(可能)在执行该语句块，别的线程会等待直到结束
     * 线程必须先获得一个(共享)对象的锁，同一时刻只有一个线程拥有该锁, 之后释放给其他等待的线程去执行
     */
    public void doCountDownLocked() {
        String name = Thread.currentThread().getName();

        // 1. 不可同步化局部变量，由于每个Thread都将拥有自己的局部变量，都将获得锁，然后执行 !!
        String localStr = "Lock";
        synchronized (localStr) {
            for (index = 10; index > 0; index--) {
                System.out.println(name + ": " + index);
            }
        }

        // 2. 同一时刻，最多只有一个Thread会拿到当前(共享)对象的锁，执行statements, 然后释放锁
        synchronized (this) {
            for (index = 10; index > 0; index--) {
                System.out.println(name + ": " + index);
            }
        }

        // 3. 不能使用primitive type作为同步锁, 不是对象，没有固有的锁
        synchronized (syncStr) {
            for (int id = 10; id > 0; id--) {
                System.out.println(name + ": " + id);
            }
        }
    }
}
