package java_thread.thread;

public class JavaThreadStopSleep {

    // TODO: 线程的Sleep休眠，调用底层的OS去将线程sleep相应时间(可能OS无法支持纳秒级别)
    //  - sleep()期间不会释放掉线程所拥有的锁，造成并发问题
    //  - sleep()期间的线程可能被"中断"，抛出中断异常
    public static void main(String[] args) {
        try {
            // 如果线程没有被中断打扰，则会在3S时间后自动唤醒
            Thread.sleep(3000);
            Thread.sleep(3000, 10);
        } catch (InterruptedException e) {
            // 线程在sleep期间被中断
            System.out.println("Thread interrupt");
        }
        testThreadInterrupted();
        testThreadStop();
    }

    // TODO. 线程的中断interrupt()
    // If this thread is blocked (wait(), join(), sleep())
    // its interrupt status will be cleared and it will receive an InterruptedException.
    private static void testThreadInterrupted() {
        Thread thread = new DemoThread();
        thread.start();

        // 中断后线程的"中断状态"立即被改写，并且抛出异常
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

    // TODO. 等待线程的结束join(), 同时控制线程结束的顺序
    // 等待一个线程执行彻底结束, 也在多线程中调用另一个线程的.join()方法
    // 应用场景：控制线程的结束，约束执行的顺序，等待数据fetch之后再执行相应的操作
    private static void testThreadStop() {
        Thread thread = new DemoThread();
        thread.start();
        try {
            thread.join(); // Waits for this thread to die.
        } catch (InterruptedException exception) {
            System.out.println("Interrupted");
        }
    }

    static class DemoThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
