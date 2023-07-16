package jvm_basics.chapter04_Monitoring;

// JVM基础故障处理工具: 切换到JAVA_HOME/bin目录下使用
public class JVMDebugTools {

    // > jps 显示系统中所有的虚拟机进程
    //   12160 Jps
    //   5088 Launcher
    //   9284 JVMDebugToolsGUI
    //   8444 

    // > jstat 收集虚拟机的运行数据
    // > jinfo 9284 显示虚拟机的配置信息
    //    VM Flags:
    //       -XX:CICompilerCount=3 -XX:ConcGCThreads=1 -XX:G1ConcRefinementThreads=4
    //       -XX:G1HeapRegionSize=1048576 -XX:GCDrainStackTargetSize=64
    //       -XX:InitialHeapSize=134217728 -XX:MarkStackSize=4194304
    //       -XX:MaxHeapSize=2120220672 -XX:MaxNewSize=1271922688
    //       -XX:MinHeapDeltaBytes=1048576 -XX:MinHeapSize=8388608
    //       -XX:NonNMethodCodeHeapSize=5832780 -XX:NonProfiledCodeHeapSize=122912730
    //       -XX:ProfiledCodeHeapSize=122912730 -XX:ReservedCodeCacheSize=251658240
    //       -XX:+SegmentedCodeCache -XX:SoftMaxHeapSize=2120220672
    //       -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
    //       -XX:+UseFastUnorderedTimeStamps -XX:+UseG1GC --> Java15默认使用G1收集器
    //       -XX:-UseLargePagesIndividualAllocati

    // > jcmd pid VM.native_memory detail 查看内存分布
    // > jmap 查看内存信息，实例格式及占内存的大小
    //   jmap -heap 进程_ID       查看堆空间的分配情况(For Java 8)
    //   jmap -histo 进程_ID      查看历史生成的实例
    //   jmap -histo:live 进程_ID 查看当前存活的实例，可能触发full gc

    // > jstack pid 查看线程的堆栈情况(注意10进制和16进制转换)
    //          用jstack+线程id可以查找死锁，找出占用CPU最高的线程的堆栈信息

    // gperftools: 系统层面的工具定位堆外内存
    // > strace -f -e”brk,mmap,munmap” -p pid 追踪向OS申请内存请求
    // 判断像堆外申请的内存是否被释放，是否存在依赖GC释放内存的情况
}


