package JavaThreadsConcurrency.BaseThreads.model;

public class DemoThread extends Thread {

    /**
     * 如果线程没有被中断打扰，则会在3S时间后自动唤醒，期间处于pause状态
     * sleep(): JVM会调底层的OS去将线程sleep相应时间, 但可能OS无法确保支持纳秒级别的sleep
     * 1. sleep()期间可能"中断"：中断线程正在做的事情，转去做别的事情，或者终止
     * 2. sleep()期间不会释放掉线程所拥有的lock
     */
    @Override
    public void run() {
        System.out.println("Thread name = " + currentThread().getName());
        // 判断中断的2种方式：
        // 1. catch捕获InterruptedException异常
        // 2. 周期性的调用isInterrupted()判断线程状态
        try {
            Thread.sleep(3000);
            Thread.sleep(3000, 10);
        } catch (InterruptedException e) {
            System.out.println("Another thread interrupt");
            return;
        }
        System.out.println("After 3 seconds, thread Wake up");
    }
}
