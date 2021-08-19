package jvm_basics.chapter04_Monitoring;

// JVM基础故障处理工具
public class JVMDebugTools {

    // > Jmap 查看内存信息，实例格式及占内存的大小
    //        jmp -histo 14660  查看历史生成的实例
    //        jmp -histo:live 14660 查看当前存活的实例，可能触发full gc

    // > Jstack 查看线程的堆栈情况(需要转换)
    //          用jstack+线程id可以查找死锁，找出占用CPU最高的线程的堆栈信息

    // > jps 显示系统中所有的虚拟机进程

    // > jstat 收集虚拟机的运行数据

    // > jinfo 显示虚拟机的配置信息

}


