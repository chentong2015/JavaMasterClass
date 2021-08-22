package jvm_basics.chapter12_JavaMemoryModel.Concurrency.Atomicity;

import java.util.concurrent.atomic.AtomicInteger;

// Atomic Action: 在statement操作过程中，线程是不能中断的
// Atomic Action
//   1. 读写引用变量 object obj1 = obj2;
//   2. 读写primitive type的变量 myInt = 10;  32位值的赋值操作是不可中断的
//   3. 读写添加了volatile关键字的long, double类型
// Not Atomic Action
//   1. 读写long, double类型的值, JVM需要两步操作去读写: one to each 32-bit half
//      Should declare shared 64-bit values as volatile or synchronize their programs to avoid possible complications !!
public class AtomicAction {

    // Java中的原子操作: 线程安全
    // Ensure the reading and writing variables is atomic 使得增加和减少都是线程安全的
    // Support lock-free thread-safe programming on single variables, do not worry about thread interference
    // 1. 基本类型: AtomicBoolean, AtomicInteger, AtomicLong
    // 2. 数组: AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray
    // 3. 引用类型: AtomicReference, AtomicReferenceArrayFieldUpdater
    // 4. 原子更新字段类: AtomicIntegerFieldUpdater, AtomicLongFieldUpdater, AtomicStampedReference(添加版本号)

    // TODO: Java如何实现原子操作
    // 1. 使用CAS操作
    // 2. 锁
    private void testAtomicPackage() {
        AtomicInteger counter = new AtomicInteger(10);
        counter.incrementAndGet(); // +1
        counter.decrementAndGet(); // -1
        int value = counter.get();

        // 可以在确保线程具有指定的初始值的情况下，再执行更新的操作
        // 1. if equal the expected value, set and return true
        // 2. if not equal the expected value, set doesn't take place and return false
        counter.compareAndSet(10, 15);
    }
}
