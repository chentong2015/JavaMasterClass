package jvm_memory_object;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

// Unsafe Class:
// 1. 主要提供一些用于执行低级别、不安全操作的方法，如直接访问系统内存资源、自主管理内存资源等
// 2. 只有受信任的代码可以获得Unsafe Class实例
// 3. 可以分配堆外的内存空间 > NIO中使用Unsafe的地方DirectBuffer()

// 不安全类的设计初衷：
// 1. 提升Java运行效率，增强Java操作底层资源能力
// 2. 使得Java变得不再是类型安全，增加了程序发生相关"指针问题"的风险
public class AllocationUnsafeMemory {

    // Unsafe: 通过VarHandle开放给外部使用
    // 测试直接内存抛出异常OutOfMemoryError
    private static final int _1M = 1024 * 1024;

    public void testAllocateMemory() throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1M); // 申请分配内存的方法
        }
    }
}
