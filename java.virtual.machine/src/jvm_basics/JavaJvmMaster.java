package jvm_basics;

// 底层实现：
// OpenJDK8源码，手写GC算法，JVM，CGI，编译原理，JNI版线程池
// Java的代码在底层实际上运行的是C++代码
public class JavaJvmMaster {

    // 1. 跨平台的本质: 汇编语言的不同
    // 2. 为什么很多语言选择在JVM上面运行?
    //    Java, Kotlin, Groovy, Scala, JRuby, Jython, Fantom, Clojure, Rhino, Ceylon
    //    2.1 定义一种语言规范(语法)
    //    2.2 构建编译器，把语言语法编译成class文件规范
    // 优秀的内存管理: 内存模型
    // 精简的字节码指令(255字节码指令，一个指令占一个字节)
    // 高效的执行引擎
    // 安全可扩展的类加载器系统
    // 高性能
    // 低延迟的垃圾收集器

    // 四个概念
    // 1. class文件: 硬盘上静态文件
    // 2. class content: 将class文件内容读到内存中，在内存中数据区域
    // 3. class对象: 将class content中的内容解析成class，存在方法区，存储class的元数据信息
    // 4. 对象: 存在堆区，通过class元数据信息创建出来的实例
}
