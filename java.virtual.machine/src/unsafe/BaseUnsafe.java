package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

// Unsafe: 通过VarHandle开放给外部使用
// 主要提供一些用于执行低级别、不安全操作的方法，如直接访问系统内存资源、自主管理内存资源等，
// 提升Java运行效率，增强Java语言底层资源操作能力方面起到了很大的作用
// 但是使得Java变得不再是类型安全，增加了程序发生相关指针问题的风险
// https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
public class BaseUnsafe {

    /**
     * A collection of methods for performing low-level, unsafe operations
     * Although the class and all methods are public, use of this class is limited
     * because only trusted code can obtain instances of it.
     * Note: It is the responsibility of the caller to make sure arguments are checked before methods of this class are called
     */

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
