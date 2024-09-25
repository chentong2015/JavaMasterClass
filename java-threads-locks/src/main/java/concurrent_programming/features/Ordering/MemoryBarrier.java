package concurrent_programming.features.Ordering;

// 屏障：
// 编译屏障：编译阶段
// 内存屏障：运行阶段，发生在CPU内部
public class MemoryBarrier {

    // TODO: 因为异步写的存在，才设计了内存屏障
    // CPU写内存的方式:
    // 1. 同步写: CPU把数值写入Store buffer, 然后写入内存
    // 2. 异步写: CPU把数值写入Store buffer, 等CPU空闲之后再把buffer中间的数值写入到内存中
    //           如果不加屏障，CPU等待的延迟时间是不确定的，保证不了"内存可见性"
    //           一旦锁住地址总线之后，使得CPU闲下来，尽快写回主内存

    // JVM规范定义的内存屏障
    //    LoadLoad & StoreStore & LoadStore & StoreLoad
    // 内联汇编:
    //            C++的volatile(禁止编译器优化无效代码) + 内存屏障 + 编译屏障
    //    __asm__ volatile ("lock, addl $0,0(%%rsp)" : : : "cc", "memory");

    // CPU提供的内存屏障: 不同CPU硬件对于<JVM内存屏障规范>实现的指令不同
    // 1. fence族: 串行读写
    //    Intel CPU硬件级内存屏障的实现指令
    //    lfence: Load Barrier读屏障
    //    sfence: Store Barrier写屏障
    //    mfence: 所有屏障能力
    //
    // 2. lock指令: JVM方式
    //    lock锁的不是代码层面，而是锁的硬件，以此来实现内存屏障的功能
    //    TODO: lock会锁定地址总线，使得同一个时刻，只有一个CPU能够操作内存，保证读写的有序性
}
