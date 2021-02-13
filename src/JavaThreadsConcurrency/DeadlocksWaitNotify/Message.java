package JavaThreadsConcurrency.DeadlocksWaitNotify;

public class Message {

    private String message;
    private boolean empty = true;

    /**
     * DeadLock死锁:
     * 1. 当一下线程进入write & read方法，并处在while循环中时
     * 2. 另外一个线程没有办法获取同一个锁(共享一个实例对象)，因为该线程在等待empty的状态变化，结束while循环，同时解锁 !!
     * A thread enters into java synchronized method or block it acquires a lock (the object on which the method is called).
     * So other method cannot be called at the same time on the same object until the first method is completed and lock(on object) is released.
     */
    public synchronized void write(String message) {
        while (!empty) {
        }
        empty = false;
        this.message = message;
    }

    public synchronized String read() {
        while (empty) {
        }
        empty = true;
        return message;
    }

    /**
     * DeadLock死锁: 解决方案
     * 1. 在线程blocked阻塞的时候，wait()
     * 2. 在线程结束之后，notify()
     */
    public synchronized void writeSync(String message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }

    public synchronized String readSync() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = true;
        notifyAll();
        return message;
    }
}
