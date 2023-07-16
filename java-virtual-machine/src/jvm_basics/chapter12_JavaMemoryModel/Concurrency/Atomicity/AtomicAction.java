package jvm_basics.chapter12_JavaMemoryModel.Concurrency.Atomicity;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// Atomic Action: 在statement操作过程中，线程不能中断
//   1. 读写引用变量object obj1 = obj2;
//   2. 读写primitive type的变量myInt = 10; 32位值的赋值操作是不可中断的
//   3. 读写添加了volatile关键字的long, double类型
// Not Atomic Action
//   1. 读写long, double类型的值, JVM需要两步操作去读写: one to each 32-bit half
//      Declare shared 64-bit values as volatile or synchronize their programs to avoid complications

// TODO: volatile能保证部分原子性
// 1. 只能保证基本数据类型的原子性
//    int, char, short, 内存地址：开启指针压缩，占4byte
//    1+1 通过锁总线能够保证是原子操作
// 2. i++ 本身不是原子操作，多步步字节码指令
//    加volatile锁总线，并不能保证原子性 !!
public class AtomicAction {

    private volatile int race = 0;

    // 2 getfield  #7 <jvm_basics/../AtomicAction.race : I>
    // 5 dup_x1
    // 6 iconst_1
    // 7 iadd
    // 8 putfield
    private int increase() {
        return race++;
    }

    // Java中原子操作: 线程安全
    // Ensure the reading and writing variables is atomic 使得增加和减少都是线程安全的
    // Support lock-free thread-safe programming on single variables, do not worry about thread interference
    // 1. 基本类型: AtomicBoolean, AtomicInteger, AtomicLong
    // 2. 数组: AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray
    // 3. 引用类型: AtomicReference, AtomicReferenceArrayFieldUpdater
    // 4. 原子更新字段类: AtomicIntegerFieldUpdater, AtomicLongFieldUpdater, AtomicStampedReference(添加版本号)

    // Java如何实现原子操作 ? CAS操作 & 锁
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

    public static void main(String[] args) {
        AtomicBoolean success = new AtomicBoolean(true);
        success.set(false);
        System.out.println(success.get());
    }
}
