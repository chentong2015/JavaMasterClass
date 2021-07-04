package JavaThreadsMaster;

// 单线程应用程序(作为一个整体大循环)，目的和时机紧密耦合，通过堆栈追踪即可判断程序执行的状态(设置断点和断点序列)
// 并发编程是一种解耦策略，把做什么和什么时候做分开，使得程序像是多台电脑协同工作一样
// 1. 多个线程和处理器之间能够分享大量的等待时间
// 2. 并发会在性能和额外代码上面增加开销
// 3. 并发需要对设计策略做很大修改
public class ConcurrentProgramming {

    // 并发编程 > 设计原则
    // 1. SRP单一职责原则：分离并发代码(有自己独立的开发，修改和声明周期)和其他代码，线程尽可能的独立
    // 2. 限制数据作用域，synchronized限制共享数据，保存同步的区域微小
    // 3. 使用数据副本，收集多线程的结果
    // 4. 避免使用一个共享对象的多个方法

    // 并发编程 > 执行模型
    // 概念：限定资源，互斥，线程饥饿，死锁，活锁(执行次序一致的线程，始终尝试启动，确启动不了)
    // 1. 生成者-消费者：生产者创建工作在缓存或者队列中，消费者从队列中获取并完成
    // 2. 作者-读者   ：平衡读者线程和作者线程，避免线程饥饿 / 作者更新的时候，需要协调读者线程，不去读正在更新的信息
    // 3. 宴席哲学家   ：进程(哲学家)争强资源(刀叉)的问题

    // 并发编程 > 测试代码
    // 1. 确保非线程的代码能够测试，例如POJO中的代码
    // 2. 即使"偶发事件/硬件错误"也应该避免, 测试在多次极端条件中出现的偶然错误
    // 3. 测试在不同的配置环境下运行 / 不同平台运行, 不同的操作系统有着不同的线程策略
    // 4. 测试运行多于处理器或处理器数量的线程，由于任务交换频繁而找到临界区
    // 5. 装置试错代码：捕捉极少出现的错误缺陷

    // 硬编码
    // 手动添加wait(); sleep(); yield(); priority();
    // yield() 向调度程序提示当前线程愿意放弃其当前对处理器的使用，"让步"
    //         它对于调试或测试目的可能很有用，它可能有助于重现由于竞争条件引起的错误
    public synchronized String nextUrlOrNull(boolean hasNext) {
        if (!hasNext) return null;
        //String url = ureGenerator.next();
        Thread.yield();
        // updateHasNext(); insert for testing
        return "url";
    }

    // 自动化
    // 使用Aspect-Oriented Framework, cglib, ASM, conTest工具
    // cglib   Byte Code Generation Library is high level API to generate and transform JAVA byte code.
    //         https://github.com/cglib/cglib
}
