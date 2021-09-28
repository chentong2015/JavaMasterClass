package jvm_basics.chapter01_JVM_Config;

// JVM参数配置分类: https://www.cnblogs.com/jpfss/p/12237079.html
// 1. 标准参数(-): 所有的JVM实现都必须实现这些参数的功能，而且向后兼容
//       -d32/-d64   运行在多少位的平台上
//       -jar        运行jar包程序
//       -server     指定虚拟机运行在服务端模式
//       -verbose:gc 显示GC Event的信息
//       -agentlib:jdwp=transport=dt_socket,server=y,address=8000 使用JDWP在本机调式在服务端的程序，以debug形式启动
//
// 2. 非标准参数(-X): 默认jvm实现这些参数的功能，但是并不保证所有jvm实现都满足，且不保证向后兼容  ==> 可以被移除
//       -Xmn<size>        为年轻代（新生代）设置初始和最大堆大小(以字节为单位)
//       -Xms<size>        设置初始Java堆大小
//       -Xmx<size>        设置最大Java堆大小
//       -Xnoclassgc       禁用类垃圾收集
//       -Xss<size>        设置Java线程堆栈大小

// 3. Advanced Runtime Options(-XX)
// 4. Advanced JIT Compiler Options
// 5. Advanced Service Options
// 6. Advanced GC Options: 设置垃圾收集器，及其优化
public class BaseJVMConfig {

    // jclasslib Bytecode Viewer 程序对应字节码查看器
    // 1. 编译项目成bytecode字节码
    // 2. 选择指定的java file查找编译后的信息

    // Java程序反汇编代码查看
    // 1. hsdis-amdb64.dll 下载包，然后放置到jre/bin/
    // 2. 在运行时设置参数 VM Options
    //    -server -Xcomp -XX:+UnlockDisgnosticVMOptions -XX:+PrintAssembly
    //    -XX:ComplileCommand=compileonly,*VolatileVisibilityTest.prepareData

}
