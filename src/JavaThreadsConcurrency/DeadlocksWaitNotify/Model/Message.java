package JavaThreadsConcurrency.DeadlocksWaitNotify.Model;

/**
 * wait(); 当前线程等待，直到被唤醒或被唤醒（通常是通过通知或中断）
 * notifyAll(); 唤醒正在此对象的监视器上等待的所有线程, 等待的线程通过调用其中一个wait方法在对象的监视器上等待
 */
public class Message {

    private String message;
    private boolean empty = true;

    /**
     * 以下Write & read会出现deadlock死锁
     * Write & read 使得同一时间只有一个线程会进入方法
     * Thread writer & reader 可能在某个时间同时blocked，同时等待对方thread改变empty状态
     */
    public synchronized void write(String message) {
        while (!empty) {
            // blocked: holding the lock for the message object
        }
        empty = false;
        this.message = message;
    }

    public synchronized String read() {
        while (empty) {
            // blocked
        }
        empty = true;
        return message;
    }

    // For writer thread: 在消息非空的时候，不写入 ==> 等待消息被读取之后再写
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

    // For reader thread: 在消息为空的时候，不读取 ==> 等待消息被写入之后再读
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
