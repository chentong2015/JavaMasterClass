package jvm_basics.chapter01_Jvm_Config;

// 基本操作: 终端 & 命令提示符运行
// > javac HelloWorld.java 编辑源代码，生成HelloWorld.class中间代码
// > java HelloWorld 运行程序
// > java -jar HelloWorld.jar 可以运行打包出来的jar程序
// > nohup java -jar HelloWorld.jar & 始终运行jar程序
public class BaseJVMConfig {

    // JVM参数配置分类: https://www.cnblogs.com/jpfss/p/12237079.html
    // 1. 标准参数(-): 所有的JVM实现都必须实现这些参数的功能，而且向后兼容
    //    -d32/-d64   运行在多少位的平台上
    // 2. 非标准参数(-X): 默认jvm实现这些参数的功能，但是并不保证所有jvm实现都满足，且不保证向后兼容
    //    -Xmn<size>  为年轻代（新生代）设置初始和最大堆大小(以字节为单位)
    //    -Xms<size>  设置初始Java堆大小
    //    -Xmx<size>  设置最大Java堆大小
    //    -Xnoclassgc 禁用类垃圾收集
    //    -Xss<size>  设置Java线程堆栈大小
    // 3. 非Stable参数(-XX): 每个jvm实现会有所不同，将来可能会随时取消，需要慎重使用

    // TODO. VM标准参数设置: Edit Configuration > VM Options > 设置参数序列
    // -Dproperty1=test -Dproperty2="chen tong"
    // 获取Java启动配置的全局变量值(参数), 实现对参数的解耦
    public static void main(String[] args) {
        System.out.println(System.getProperty("property1"));
        System.out.println(System.getProperty("property2"));
    }
}
