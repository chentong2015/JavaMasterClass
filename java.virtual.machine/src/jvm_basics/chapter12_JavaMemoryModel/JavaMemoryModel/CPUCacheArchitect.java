package jvm_basics.chapter12_JavaMemoryModel.JavaMemoryModel;

public class CPUCacheArchitect {

    // 多核(CPU)并发缓存架构
    //   CPU          CPU
    // CPU寄存器     CPU寄存器
    //  CPU缓存      CPU缓存   ----> 多级缓存 L1 cache, L2 cache, L3 cache(共享缓存)
    //      主内存(RAM)       <---- 硬盘
    //
    // 1. CPU和主内存中间的交互瓶颈，内存运算速度的增长并不大，访问延时约100ns
    // 2. CPU和CPU缓存直接的允许存储速度相当，cache缓存访问延时约1ns
}
