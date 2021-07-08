package jvm.chapter12;

// Java Memory Model是围绕在并发过程中如何处理"原子性","可见性","有序性"而建立的
// 绝大部分的并发控制都能够使用synchronized来实现，但也会有性能的影响 !!
public class JavaMemoryModel {

    // 原子性 Atomicity
    // 原子性的操作：lock, unlock, read, load, assign, use, store, wirte
    // 1. 基本的数据访问和读写是具备原子性, 除了long和double
    // 2. 使用synchronized来实现更大规模的原子性保证 ==> 对应更高层次的字节码指令monitorexter, monitorexit

    // 可见性 Visibility
    // Java的内存模型如何实现可见性 ?
    // 1. Java内存模型会将会将在工作内存中变量修改的后的新值写回到主内存
    // 2. 在变量读取前从主内存中刷新变量值，通过依赖主内存这个媒介来实现可见性
    //    ==> volatile保证立即同步回，保证可见性，普通变量无法保证
    //    ==> synchronized也能实现可见性，"在unlock之前，必须将变量同步回主内存"
    //    ==> final修改，在初始化之后不可更改，保证其他线程正确访问

    // 有序性 Ordering
    // 1. synchronized确保一个变量在同一个时刻只允许一条线程对其进行lock，保证同步快的串行进入
}