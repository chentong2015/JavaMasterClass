package locks.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private static Lock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    // 设置等待标识符，避免运行结束的线程没有办法.signal()正在.await()的线程
    private static Boolean t1Run = false;
    private static Boolean t2Run = false;

    public static void main(String[] args) {

        final Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 1 done");
                t1Run = true;
                condition1.signal();
            } finally {
                lock.unlock();
            }
        });

        // 使用ReentrantLock Condition来实现线程同步执行，并不需要设置自旋(阻塞)
        final Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                if (!t1Run) condition1.await();
                System.out.println("Thread 2 to do");
                t2Run = true;
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread thread3 = new Thread(() -> {
            lock.lock();
            try {
                if (!t2Run) condition2.await();
                System.out.println("Thread 3 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
