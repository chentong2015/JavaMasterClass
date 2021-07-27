package jvm.chapter12;

// Java Memory Model
// 主内存和工作内存的拆分
// 围绕在并发过程中如何处理"原子性","可见性","有序性"而建立的

// synchronized
// 1. 绝大部分的并发控制都能够使用synchronized来实现，符合3种特性
// 2. TODO: synchronized是Java语言一个重量级的操作，对应到操作系统内核线程之上，造成用户态和核心态的转换 !!
public class JavaMemoryModel {

    // 原子性 Atomicity
    // 原子性的操作：lock, unlock, read, load, assign, use, store, write
    // 1. 基本的数据访问和读写是具备原子性, 除了long和double
    // 2. 使用synchronized来实现更大规模的原子性保证 ==> 对应更高层次的字节码指令monitor enter, monitor exit

    // 可见性 Visibility
    // Java的内存模型如何实现可见性 ?
    // 1. Java内存模型会将会将在工作内存中变量修改的后的新值写回到主内存
    // 2. 在变量读取前从主内存中刷新变量值，通过依赖主内存这个媒介来实现可见性
    //    2.1 volatile保证立即同步回，保证可见性，普通变量无法保证
    //    2.2 synchronized也能实现可见性，"在unlock之前，必须将变量同步回主内存"
    //    2.3 final修改，在初始化之后不可更改，保证其他线程正确访问

    // 有序性 Ordering
    // 1. synchronized确保一个变量在同一个时刻只允许一条线程对其进行lock，保证同步快的串行进入

    // Happen-Before原则：先行发生原则
    // 1. 用于判断数据是否竞争，线程是否安全的方式
    // 2. 描述的是两项操作数之间的偏序关系，即A的操作产生的"影响"被操作B观察到
    // 3. 一共8条规则，在不适用同步手段保障就能成立的先行发生的规则 !! 可以以此判断线程是否安全
    // 4. 先行发生原则和时间先后顺序之间没有必然的因果关系 !!
    //    时间先并不代表操是"先行发生"的
    //    时间后的可能被处理器先处理
}
