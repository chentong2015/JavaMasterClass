package jvm_basics.chapter12_JavaMemoryModel;

// JMM: Java Memory Model
// 屏蔽各种硬件和OS的内存访问差异，让java程序在各个平台都能达到一致的内存访问效果(访问操作没有歧义)
public class JavaMemoryModelArchitect {

    // TODO: 多核(CPU)并发缓存架构
    // 不同的CPU架构的实现不同，汇编语言的指令不同(参考手册)
    //                           
    //      CPU          CPU
    //    CPU寄存器     CPU寄存器   ----> cache缓存访问延时约1ns
    //     CPU缓存      CPU缓存    ----> 多级缓存 L1 cache, L2 cache, L3 cache(共享缓存)
    //         主内存(RAM)        <---- 内存运算速度的增长并不大，访问延时约100ns
    //           硬盘存储
    //
    // 1. CPU和主内存中间存在交互瓶颈，于是添加了多级缓存
    // 2. CPU和CPU缓存直接的允许存储速度相当

    // TODO: JMM基本架构(对应到硬件)
    //
    //          CPU                      CPU
    //        线程1"use"             线程2"use"/"assign"
    //        工作内存"load"          工作内存"load"
    //        flag=false            flag --> true
    // 总线(缓存一致性协议) ---------------------------- "read"
    // CPU和内存直接交互的连接线
    //                     主内存                     "store"
    //                   flag=false             <--  "write"

    // JMM原子操作规范
    // 1. Java产品的规范，也就是Java提供的需求
    // 2. 这种需求在不同的JVM的中的实现可能不同(不同的开发商)，汇编的实现不一致

    // 缓存不一致的问题: 硬件级别实现的功能，不同的CPU有不同的实现
    // 1. 缓存一致性协议(MESI): Intel 64处理器
    //       M: modified
    //       E: Exclusive 独享，互斥
    //       S: Shared
    //       I: Invalid
    //    多个cpu从主内存读取同一个数据到各自的高速缓存，当其中某个cpu修改了其缓存中的数据，该数据会里面同步会主内存
    //    其他cpu通过"CPU总线嗅探机制"感知到数据的变化从而将自己缓存里的数据失效
    //    将工作内存中缓存值置于invalid状态，之后CPU再次读取使用的时候，发现已经失效，则重新read->load再运行
    // 2. CPU总线嗅探机制
    //    线程"监听"总线这个"消息队列"，嗅探共享的变量
    // 3. 缓存加锁
    //    缓存锁基于缓存一致性协议来实现，一个处理器的缓存回写到内存会导致其他处理器的缓存无效
}
