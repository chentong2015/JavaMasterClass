package JavaThreadsConcurrency.DeadlocksWaitNotify;

import JavaThreadsConcurrency.DeadlocksWaitNotify.Model.Message;
import JavaThreadsConcurrency.DeadlocksWaitNotify.Model.Reader;
import JavaThreadsConcurrency.DeadlocksWaitNotify.Model.Writer;

/**
 * DeadLock 高级解决方案：wait(), notify(), notifyAll()
 * 1. 在线程被阻塞的时候，等待，在线程结束操作时候，唤起等待
 * 2. 方法都是非静态的方法，只能在非static的方法中调用吗, 锁所关联的都是object's monitor !!
 * 3. 需要在synchronized block语句块中调用 !!!
 * 4. Thread线程必须要拥有object对象上的lock, 然后才能调用wait(), 在调用之后，线程会释放掉获得的这个object上的lock !!!
 * --------------------------------------------------
 * 1. 每个对象除具有关联的monitor监视器外，还具有关联的"wait set"等待集, 一组"set of threads"
 * 2. wait set初始时为空, 可在集中添加或者删除等待的线程
 * 3. wait set集的操作会被线程的isInterrupted()状态所影响
 * --------------------------------------------------
 * 1. 当多个线程同时想要获得锁时，最后所获得锁的线程是由JVM所决定的，并不是最先来的Thread
 * The first thread block could be the last thread to get the lock
 */
public class DeadLocksExample01 {

    /**
     * wait() 当前线程等待, 释放掉它所拥有的lock, 直到被唤醒或被通知或中断
     * Causes the current thread to wait until it is awakened, typically by being notified or interrupted
     */

    /**
     * notify() 唤醒正在此"对象的监视器"上等待的单个线程, 选择唤醒的线程是随机的
     * Wakes up a single thread that is waiting on this object's monitor.
     * If any threads are waiting on this object, one of them is chosen to be awakened.
     * The choice is arbitrary and occurs at the discretion of the implementation.
     */

    /**
     * notifyAll() 唤醒正在此"对象的监视器"上等待的所有线程, 等待的线程通过调用其中一个wait方法在"对象的监视器"上等待
     */

    /**
     * Interruptions 中断操作
     * if there exists some object m whose wait set contains u, then u is removed from m's wait set.
     * This enables u to resume in a wait action, after re-locking m's monitor, this wait will throw InterruptedException.
     */
    public static void main(String[] args) {
        Message message = new Message();
        Thread writeThread = new Thread(new Writer(message));
        writeThread.start();
        Thread readThread = new Thread(new Reader(message));
        readThread.start();

        /**
         * 在sleep()或者wait()中断, 修改Thread的中断状态: .isInterrupted() = true
         * writeThread会从message的wait set集中移除, 然后从新wait, 在重新lock object's monitor之后, 抛出中断异常
         */
        writeThread.interrupt();
    }

    /**
     * Waits, Notification, and Interruption 相互的交互: 如果一个在waiting的线程，同时被notify和interrupt, 会如何 ?
     * 1. 从wait()中正常返回，但是这个线程的.isInterrupted()被中断状态会被修改成true
     * 2. 从wait()中返回，同时抛出异常InterruptedException，可能不会重置它的interrupt status
     * 3. wait set集中别的一些线程必须要受到notify通知
     * -------------------------------------------------
     * Notifications cannot be lost due to interrupts: 唤醒不能因为中断而丢失，如果object m的等待集中包含线程集s，别的线程在m执行notify，会如何 ?
     * 1. s中至少有一个等待的线程，会正常的从wait()中返回
     * 2. s中所有的线程都必须从wait()中返回，但是抛出异常
     */
}
