package java_thread.thread.wait_notify;

// TODO. 使用wait()方法的标准模式: 用来使线程等待某个条件，它必须在同步区域内部被调用
// 始终使用wait循环模式来调用，不要在循环外调用
// public void testWait() {
//     synchronized (obj) {
//         while (<condition does not hold>) {
//             obj.wait(); release lock
//         }
//     }
// }
public class JavaWaitNotify {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    // 由于lock1和lock2并不一定被thread1和thread2先获得
    // 需要通过标识符来判断是否是错误的获得，如果是则wait()，然后等待被notify()
    private static Boolean t1Run = false;
    private static Boolean t2Run = false;

    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 done");
                t1Run = true;
                lock1.notify();
            }
        });

        final Thread thread2 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    if (!t1Run) {      // 可能由thread2先拿到锁，但是不能执行，必须等待.wait()
                        lock1.wait();  // 释放到获取的myLock1锁，等待thread1执行完毕之后的通知.notify();
                    }
                    synchronized (lock2) {
                        System.out.println("Thread 2 done");
                        lock2.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            synchronized (lock2) {
                try {
                    if (!t2Run) {
                        lock2.wait();
                    }
                    System.out.println("Thread 3 done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread3.start();
        thread2.start();
    }
}
