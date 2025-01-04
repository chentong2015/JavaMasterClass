package concurrent_tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

// Java常用并发工具类(同步器): 实现各种同步结构
// https://www.oracle.com/technical-resources/articles/javase/concurrent-programming.html
public class JavaConcurrentTools {

    // CountDownLatch 倒计时锁存器
    // 允许一个或多个线程，等待其他一组线程完成操作，再继续执行
    // 应用场景:
    // 1. 倒数计时器
    // 2. 需要确保某一个线程在其他一个或者多个优先级线程完成之后，再执行
    public void testCountDownLatch() {
        // 1. 通过一个计数器来实现的，计数器的初始值为需要等待线程的数量
        // 2. 主线程调用CountDownLatch的await()方法会阻塞当前线程，直到等到计数器值为0
        // 3. 当一个工作线程完成了自己任务后，调用CountDownLatch的countDown()方法，计数器的值就会减1
        //    当计数器值为0时，说明所有的工作线程都执行完了，此时，在闭锁上等待的主线程就可以恢复执行任务
        CountDownLatch count = new CountDownLatch(10);
        count.countDown();
    }

    // CyclicBarrier 循环屏障
    // 让一组线程到达一个屏障(同步点)时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行
    // 与CountDownLatch区别
    // 1. 和CountDownLatch相似，也是等待某些线程都做完以后再执行
    // 2. TODO: 这个计数器可以反复使用: 计数器设置为10, 那么凑齐第一批10个线程后，计数器就会归零，然后接着凑齐下一批10个线程
    public void testCyclicBarrier() {
        // 1. 通过一个计数器来实现的，计数器的初始值为需要等待线程的数量
        // 2. 每个线程调用CyclicBarrier的await()方法，使自己进入等待状态
        // 3. 当所有的线程都调用了CyclicBarrier的await()方法后，所有的线程停止等待，继续运行
        CyclicBarrier c = new CyclicBarrier(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println("一次计数完成"));
    }

    // Semaphore 信号量
    // 它通过控制一定数量的许可(permit)的方式，来达到限制通用资源访问的目的。例如：控制并发的线程数
    // 应用场景:
    // 1. TODO: Semaphore可以用于做流量控制，特别是公用资源有限的应用场景，比如数据库连接
    // 2. 当很多空出租车就位时，为防止过度拥挤，调度员指挥排队等待坐车的队伍一次进来5个人上车，等这5个人坐车出发，再放进去下一批
    public void testSemaphore() throws InterruptedException {
        // 1. 通过一个计数器(记录许可证的数量)来实现的，计数器的初始值为需要等待线程的数量
        // 2. 线程通过acquire()方法获取许可证(计数器的值减1)，只有获取到许可证才可以继续执行下去，否则阻塞当前线程
        // 3. 线程通过release()方法归还许可证(计数器的值加1)
        // 为了避免阻塞，使用tryAcquire()方法可以立即得到执行的结果：
        // 尝试获取一个许可证，若获取成功，则立即返回true，若获取失败，则立即返回false
        Semaphore semaphore = new Semaphore(10);
        semaphore.acquire();
        semaphore.release();
    }

    // Exchanger 交换者 ==> TODO: 线程之间数据交流的方式之一
    // 用于进行线程间的数据交换，它提供一个同步点，在这个同步点两个线程可以交换彼此的数据
    // 这两个线程通过exchange方法交换数据
    //   如果第一个线程先执行exchange方法，它会一直等待第二个线程也执行exchange，
    //   当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方
    // 应用场景:
    //   用于校对工作的场景
    public void testExchanger() throws InterruptedException {
        // 1. 线程A调用public V exchange(V dataA)方法，线程A到达同步点，并且在线程B到达同步点前一直等待
        // 2. 线程B调用public V exchange(V dataB)方法，线程B到达同步点
        // 3. 线程A与线程B都达到同步点时，线程将自己的数据传递给对方，两个线程完成了数据的交换了
        Exchanger<String> exchanger = new Exchanger<>();
        String valueFromAnotherThread = exchanger.exchange("exchange info");
    }
}
