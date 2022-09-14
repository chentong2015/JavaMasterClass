package jvm_basics.chapter12_JavaMemoryModel;

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

    // TODO: 并发编程不一定有竞争，可能有大量的线程是按照先后顺序执行 !!
    // 并发编程 > 概念：
    //    限定资源
    //    互斥
    //    线程饥饿
    //    死锁(互斥，上锁及等待，无强先机制，循环等待)
    //    活锁(执行次序一致的线程，始终尝试启动，确启动不了 / 线程一直处于Active运行的状态)
    //        一个线程持续(Active)拥有一个锁, 并等待其他线程去完成它们的task
    //        多个线程都同时持续运行(looping状态), 在等待其他线程释放锁

    // TODO: 正确理解死锁机制，产生的4个条件
    // 1. 互斥     : 同一个资源有数量上的限制，无法同一个时间为多个线程服务，例如，数据库连接，要写入的打开文件...
    //              >> 允许同时使用资源，增加资源数量，获取资源前检查可用
    // 2. 上锁及等待: 某线程获取到一个资源，在获取其他全部资源并完成工作前，不会释放资源 (等待 + 执行)
    //              >> 如果遇到繁忙资源，自身释放所有资源，重新来过 ?
    // 3. 无强先机制: 无法强夺其他线程的资源，只能等待获得资源的线程释放
    //              >> 使用请求机制，允许从被的线程中强夺繁忙资源 ?
    // 4. 循环等待 : 拥有资源，却始终循环等待资源
    //              >> 固定获取资源的顺序 ? 可能无法强求获取资源的顺序

    // 并发编程场景
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
    // yield() 向调度程序提示当前线程愿意放弃其当前对处理器的使用，"主动让出执行时间" > 给优先级相等或者更高的线程
    //         重新去竞争CPU的资源 > JDK1.8 ConcurrentHashmap
    //         它对于调试或测试可能很有用，有助于模拟竞争条件引起的错误
    public synchronized String nextUrlOrNull(boolean hasNext) throws InterruptedException {
        Thread.sleep(1000); // sleep() 方法有异常抛出
        if (!hasNext) return null;
        //String url = ureGenerator.next();
        Thread.yield(); // yield()没有异常抛出
        // updateHasNext(); insert for testing
        return "url";
    }

    // TODO: 使用Thread.sleep的问题，如果当前的线程拥有锁，在sleep的期间是不会释放掉锁
    //   会造成性能和并发的安全问题
    public synchronized void testThreadSleepProblem() throws InterruptedException {
        Thread.sleep(2000);
        // To do something
    }

    // 自动化
    // AOP, cglib, ASM字节码, conTest工具
    // cglib: Byte Code Generation Library is high level API to generate and transform JAVA byte code.

    // 实例测试多线程代码: 给出可靠的错误证明
    // 1. 蒙特卡洛测试：测试灵活，多变，利于调整，同时多次运行测试，连续集成，如果测试有错，则代码有错
    // 2. 在每种目标部署平台都运行测试
    // 3. 模拟生产环境的负载，在大量执行次数中发现错误
}
