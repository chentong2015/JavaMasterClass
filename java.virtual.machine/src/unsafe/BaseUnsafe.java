package unsafe;

// https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
public class BaseUnsafe {

    /**
     * A collection of methods for performing low-level, unsafe operations.
     * Although the class and all methods are public, use of this class is limited because only trusted code can obtain instances of it.
     * Note: It is the responsibility of the caller to make sure arguments are checked before methods of this class are called
     */
    public void test() {
        // Unsafe 主要提供一些用于执行低级别、不安全操作的方法，如直接访问系统内存资源、自主管理内存资源等，
        // 这些方法在提升Java运行效率、增强Java语言底层资源操作能力方面起到了很大的作用
        // 但是使得Java变得不再是类型安全, 增加了程序发生相关指针问题的风险
    }
}
