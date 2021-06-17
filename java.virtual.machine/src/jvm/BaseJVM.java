package jvm;

public class BaseJVM {

    /**
     * Java源码
     * public static void main(String[] args) {
     *   System.out.println("Test OK");
     * }
     *
     * ByteCode字节码: 编译之后的Class文件
     * // access flags 0x9
     * public static main([Ljava/lang/String;)V
     *  L0
     *   LINENUMBER 18 L0
     *   GETSTATIC java/lang/System.out : Ljava/io/PrintStream;         ==> getstatic是JVM的指令
     *   LDC "Test OK"
     *   INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
     *  L1
     *   LINENUMBER 19 L1
     *   RETURN
     *  L2
     *   LOCALVARIABLE args [Ljava/lang/String; L0 L2 0
     *   MAXSTACK = 2
     *   MAXLOCALS = 1
     */

    /**
     * Java对象的回收方式，回收算法？
     * CMS解决什么问题，说一下回收的过程？CMS回收停顿了几次？
     * GC的机制是什么？GC算法和回收策略？
     *
     * Java 内存分配？
     * Java 堆的结构是什么样子的？
     * 什么是堆中的永久代（Perm Gen space）?
     * 简述各个版本内存区域的变化？
     * 说说各个区域的作用？
     * Java 中会存在内存泄漏吗，简述一下？ ===> 多线程拿到锁，没有释放，导致对象没有被回收
     * Java 类加载过程？
     * 描述一下 JVM 加载 Class 文件的原理机制?
     * 什么是类加载器？
     * 类加载器有哪些？
     * 什么是tomcat类加载机制？
     * 类加载器双亲委派模型机制？
     * 什么是GC? 为什么要有 GC？
     * 简述一下Java 垃圾回收机制？
     * 如何判断一个对象是否存活？
     * 垃圾回收的优点和原理，并考虑 2 种回收机制？
     * 垃圾回收器的基本原理是什么？
     * 垃圾回收器可以马上回收内存吗？有什么办法主动通知虚拟机进行垃圾回收？
     * System.gc() 和 Runtime.gc() 会做些什么？
     * 如果对象的引用被置为 null，垃圾收集器是否会立即释放对象占用的内存？
     * 什么是分布式垃圾回收（DGC）？它是如何工作的？
     */
}
