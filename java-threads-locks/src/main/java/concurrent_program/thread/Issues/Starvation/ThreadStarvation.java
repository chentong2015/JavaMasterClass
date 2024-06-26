package concurrent_program.thread.Issues.Starvation;

// Thread Starvation 线程饥饿
// 一个线程很少有机会能够被运行到，没有什么机会获得锁
// 线程获得锁的机制并不是first come first served，可能导致某些线程等待时间过长，无法执行

// TODO: 线程饥饿产生的本质: 线程调度方式是"抢占式调度"
// 1. 每个线程由OS来分配执行的时间，最终的调度由OS决定
// 2. 线程的切换不由线程本身决定，也不由设置的线程优先级决定
public class ThreadStarvation {

    // Thread scheduler decides thread priority !
    // 线程调度器决定线程优先级，但是优先级不是稳定的调节手段
    // MIN_PRIORITY = 1 ~ 10;
    // Priority优先级只是给OS一个执行的建议，不是准确的执行顺序
    private void testThreadStarvation() {
        Thread thread1 = new Thread(() -> System.out.println("Thread 01"));
        Thread thread2 = new Thread(() -> System.out.println("Thread 02"));
        thread1.setPriority(8);
        thread2.setPriority(6);
    }
}