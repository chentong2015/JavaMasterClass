package jvm_basics.chapter12_JavaMemoryModel.Concurrency;

/**
 * 造成多线程问题的来源:
 * 1. 不同的线程共享数据(对象), 并发操作问题
 * 2. 多线程在操作共同数据时, 读取判断和操作上面, 由于线程调度造成数据一致性问题或者损坏
 * 3. 线程在执行非原子操作, 在执行的过程中中断造成问题
 * 4. 在多核CPU机器上, 不同线程可能在不同的CPU上运行，其中各自CPU Cache数据可能和主内存中的数据出现一致性问题 !!
 */
// 1. 绝大部分的并发控制都能够使用synchronized来实现，符合3种特性
// 2. TODO: synchronized是Java语言一个重量级的操作，对应到操作系统内核线程之上，造成用户态和核心态的转换 !!
public class BaseConcurrency {

    // TODO: Java并发编程三个特征
    // 原子性 Atomicity
    // 原子性的操作：lock, unlock, read, load, assign, use, store, write
    // 1. 基本的数据访问和读写是具备原子性, 除了long和double
    // 2. 使用synchronized来实现更大规模的原子性保证，对应更高层次的字节码指令monitor enter, monitor exit

    // 可见性 Visibility
    // Java的内存模型如何实现可见性 ?
    // 1. Java内存模型会将会将在工作内存中变量修改的后的新值写回到主内存
    // 2. 在变量读取前从主内存中刷新变量值，通过依赖主内存这个媒介来实现可见性
    //    2.1 volatile保证立即同步回，保证"可见性"，普通变量无法保证
    //    2.2 synchronized也能实现可见性，"在unlock之前，必须将变量同步回主内存"
    //    2.3 final修改，在初始化之后不可更改，保证其他线程正确访问

    // 有序性 Ordering
    // 1. volatile可以保证有序性
    // 2. synchronized确保一个变量在同一个时刻只允许一条线程对其进行lock，保证同步快的串行进入
}
