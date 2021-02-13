package JavaThreadsConcurrency.DeadlocksWaitNotify;

/**
 * 只能在同步化的语句块里面被调用的方法 ?????
 * wait() 当前线程等待，直到被唤醒或被唤醒（通常是通过通知或中断）
 * notify()
 * notifyAll() 唤醒正在此对象的监视器上等待的所有线程, 等待的线程通过调用其中一个wait方法在对象的监视器上等待
 */
public class BaseLocks {

    public static void main(String[] args) {
        Message message = new Message();
        Thread writeThread = new Thread(new Writer(message));
        writeThread.start();
        Thread readThread = new Thread(new Reader(message));
        readThread.start();
    }
}
