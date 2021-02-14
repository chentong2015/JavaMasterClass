package JavaThreadsConcurrency.Base;

public class DemoThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread name = " + currentThread().getName());
        /**
         * 如果线程没有被中断打扰，则会在3S时间后自动唤醒 ===>
         * Sleep: JVM会调底层的OS去将线程sleep相应时间, 但可能OS无法确保支持纳秒级别的sleep
         * Sleep期间可能"中断"：中断线程正在做的事情，转去做别的事情，或者终止
         * 判断中断的2种方式：catch捕获异常，周期性的调用isInterrupted()方法判断是否被中断
         */
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
