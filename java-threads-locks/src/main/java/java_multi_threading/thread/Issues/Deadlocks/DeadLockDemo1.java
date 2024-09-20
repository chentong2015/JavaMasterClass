package java_multi_threading.thread.Issues.Deadlocks;

// 1. 当一个线程拿到对象上的锁，在执行方法的过程中由于条件标识不满足处于循环
//    另外的线程没有办法再拿到同一个对象上的锁，从而无法修改条件标识
public class DeadLockDemo1 {

    private String message;
    private boolean empty = true;

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
}
