package jvm_basics.chapter12_JavaMemoryModel.Concurrency.Ordering;

public class BaseMemoryBarrier {

    // 不同CPU硬件对于JVM的内存屏障规范实现的指令不同
    // Intel CPU硬件级内存屏障的实现指令
    // lfence: Load Barrier读屏障
    // sfence: Store Barrier写屏障
    // mfence: 所有屏障能力

    // JVM底层简化了内存屏障硬件指令的实现
    // lock前缀：lock指令不是一种内存屏障，它只是能完成类型内存屏障的功能

    // JVM规范定义的内存屏障
    // LoadLoad        load1;LoadLoad;load2
    // StoreStore
    // LoadStore
    // StoreLoad
}
