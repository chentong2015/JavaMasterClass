package jvm_basics.chapter00_Jdk_jvm;

// Java Virtual Machine
// Hotspot是OpenJDK中默认的Java虚拟机
// OpenJDK代码使用C++实现，和OS操作系统进行交互，调用底层开放的上层接口
//   使用"类型名_本地方法名称"在底层openjdk源码中可以找到对应的实现
public class BaseJdkJvm {

    // JDK: Java development kit开发包, 相当于Java SDK
    //      单号的版本 修复大的bug 一般不会引入新的bug
    //      JDK 安装的时候 可能改变操作系统的配置 c盘下面的安装文件
    // JRE: Java Runtime Environment 包含运行Java程序的所有需要: 6层结构

    // 1. 跨平台的本质: 汇编语言的不同 ==> 一种系统上的程序不能扩平台运行
    //    实现跨平台方式:
    //       编程语言方式: C, C++编译成指定OS平台的语言
    //       虚拟机方式: java通过JVM底层OS的区别(硬件和指令集)

    // 2. 为什么很多语言选择在JVM上面运行?
    //    Java, Kotlin, Groovy, Scala, JRuby, Jython, Fantom, Clojure, Rhino, Ceylon
    //    优秀的内存管理: 内存模型
    //    精简的字节码指令(255字节码指令，一个指令占一个字节)
    //    高效的执行引擎
    //    安全可扩展的类加载器系统
    //    高性能
    //    低延迟的垃圾收集器
    // TODO: 定义一种语言规范(语法)，只需构建编译器，把(特定)语言语法编译成class文件规范，便可以运行在JVM
    // 由于硬件和指令级的区别，同一个Java源代码在不同平台生成的二进制码不同


    // 如何运行jar包中指定的启动类 ?
    // > java -cp myjar.jar com.mypackage.myClass 提供类的全路径

    // 如何在没有装JRE的操作系统(用户PC)上运行Java程序 ?
    //    https://github.com/libgdx/libgdx/wiki/Bundling-a-JRE
    // 1. 下载jre或者jdk进行安装
    // 2. 使用launch4j工具生成.exe可执行文件，静态绑定指定路径的jdk，整体打包
    // 3. 配置生成self-container的应用程序
}
