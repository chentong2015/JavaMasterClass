// <深入理解Java虚拟机>源码 https://github.com/fenixsoft/jvm_book
// JVM与高级架构全集       https://www.bilibili.com/video/BV1Rf4y1y7xE      55小时
// JVM全套参考教程         https://www.bilibili.com/video/BV1PJ411n7xZ?p=1  69小时
public class BaseJVM {

    // JVM: 从软件层面屏蔽了不同操作系统在底层硬件和指令上的区别, 底层是用C++写的
    /**
     * Java 基本面试题目 https://www.shuzhiduo.com/A/QV5ZLax2zy/
     *
     * This 指针何时赋值的 ?
     * 虚拟机栈和线程栈有何区别 ?
     * -Xss -XX:ThreadStackSize -XX:VMThreadStackSize 都使用了设置线程堆栈的空间大小 ?
     *
     * Java对象的回收方式，回收算法？
     * CMS解决什么问题，说一下回收的过程？CMS回收停顿了几次？
     * GC的机制是什么？GC算法和回收策略？
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
     * 如何判断一个对象是否存活？ 对象已死 ?
     * 垃圾回收的优点和原理，并考虑 2 种回收机制？
     * 垃圾回收器的基本原理是什么？
     * 垃圾回收器可以马上回收内存吗？有什么办法主动通知虚拟机进行垃圾回收？
     * System.gc() 和 Runtime.gc() 会做些什么？
     * 如果对象的引用被置为 null，垃圾收集器是否会立即释放对象占用的内存？
     * 什么是分布式垃圾回收（DGC）？它是如何工作的？
     */

    /**
     * TODO: Java Network问题
     * TCP建立连接和断开连接的过程？
     * HTTP协议的交互流程，HTTP和HTTPS的差异，SSL的交互流程？
     * TCP的滑动窗口协议有什么用？
     * HTTP协议都有哪些方法？
     * Socket交互的基本流程？
     * 讲讲tcp协议（建连过程，慢启动，滑动窗口，七层模型）？
     * webservice协议（wsdl/soap格式，与restt办议的区别）？
     * 说说Netty线程模型，什么是零拷贝？
     * TCP三次握手、四次挥手？
     * DNS解析过程？
     * TCP如何保证数据的可靠传输的
     */

}
