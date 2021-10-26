package JavaThreadsConcurrency.Deadlocks;

// TODO: wait(), notify(), notifyAll() 目的是实现线程之间的通讯, 不是用来阻塞
// 1. 在线程被阻塞的时候等待，在线程结束操作时候，唤起等待处于等待中的线程
// 2. 只能在非静态方法中调用，锁所关联的都是object's monitor
// 3. 只能在synchronized block语句块中调用，必须要拥有object对象上的lock，然后才能调用wait()

// Question 01. 如果一个在waiting的线程，同时被notify和interrupt, 会如何?
// 1. 从wait()中正常返回，但是这个线程的.isInterrupted()被中断状态会被修改成true
// 2. 从wait()中返回，同时抛出异常InterruptedException，可能不会重置它的interrupt status
// 3. wait set集中别的一些线程必须要收到notify通知

// Question 02. 唤醒不能因为中断而丢失，如果object m的等待集中包含线程集s，别的线程在m执行notify，会如何?
// 1. s中至少有一个等待的线程，会正常的从wait()中返回
// 2. s中所有的线程都必须从wait()中返回，但是抛出异常
public class JavaWaitNotifyAll {

    // TODO: wait() 当前线程等待, 释放掉它所拥有的lock, 直到被唤醒, 被通知或中断
    // Causes the current thread to wait until it is awakened,
    // typically by being notified or interrupted

    // notify() 唤醒正在此"对象的监视器"上等待的单个线程, 选择唤醒的线程是随机的
    // Wakes up a single thread that is waiting on this object's monitor.
    // If any threads are waiting on this object, one of them is chosen to be awakened.
    // The choice is arbitrary and occurs at the discretion of the implementation.

    // notifyAll() 唤醒正在此"对象的监视器"上等待的所有线程, 等待的线程通过调用其中一个wait方法在"对象的监视器"上等待
    // 当线程过多时，避免使用notifyAll()对性能造成的影响

    // writeThread.interrupt() 线程被中断的操作流程
    // 1. 在sleep()或wait()中断, 线程的中断状态将改为: .isInterrupted() = true
    // 2. writeThread会从message的wait set集中移除, 然后重新wait, 在lock object's monitor之后, 抛出中断异常
    public void testThreadInterrupt() {
        Message message = new Message();
        Thread writeThread = new Thread(() -> {
            String[] messages = {"message 01", "message 02"};
            for (int i = 0; i < messages.length; i++) {
                message.writeSync(messages[i]);
                // Thread.sleep(1000);
            }
            message.writeSync("Done");
        });
        Thread readThread = new Thread(() -> {
            for (String str = message.readSync(); !str.equals("Done"); str = message.readSync()) {
                System.out.println(str);
                // Thread.sleep(1000);
            }
        });
        writeThread.start();
        readThread.start();
        writeThread.interrupt();
    }
}

class Message {
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