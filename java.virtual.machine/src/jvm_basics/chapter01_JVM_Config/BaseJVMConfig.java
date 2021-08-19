package jvm_basics.chapter01_JVM_Config;

// JVM参数配置分类:
// 1. 标准参数（-）: 所有的JVM实现都必须实现这些参数的功能，而且向后兼容；
// 2. 非标准参数（-X）: 默认jvm实现这些参数的功能，但是并不保证所有jvm实现都满足，且不保证向后兼容；
// 3. 非Stable参数（-XX）: 此类参数各个jvm实现会有所不同，将来可能会随时取消，需要慎重使用；
// https://www.cnblogs.com/jpfss/p/12237079.html
public class BaseJVMConfig {

    // Java程序反汇编代码查看
    // 1. hsdis-amdb64.dll 下载包，然后放置到jre/bin/
    // 2. 在运行时设置参数 VM Options
    // -server -Xcomp -XX:+UnlockDisgnosticVMOptions -XX:+PrintAssembly
    // -XX:ComplileCommand=compileonly,*VolatileVisibilityTest.prepareData

}
