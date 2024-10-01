package java_threads.thread.wait_notify;

public class ThreadMessage {

    private String message;
    private boolean empty = true;

    // 将wait()置于循环，在被唤醒的时候判断标识状态再执行
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
