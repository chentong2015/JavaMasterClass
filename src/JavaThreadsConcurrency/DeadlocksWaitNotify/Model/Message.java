package JavaThreadsConcurrency.DeadlocksWaitNotify.Model;

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
     * 1. 将wait()置于循环，是因为在被唤醒的时候，同样需要判断标识状态的改变与否，而不是确幸往后执行
     * 2. notify()没有参数，无法唤醒一个指定的Thread线程，所以使用notifyAll()来全部唤醒
     * 3. 当线程过多时，避免使用notifyAll();对性能造成的影响
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
