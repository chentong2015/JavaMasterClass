package jvm_basics.chapter12_JavaMemoryModel.Concurrency.Volatile;

import java.util.HashMap;
import java.util.Map;

// volatile两个核心特性(功能)
// 1. 保证并发编程的"可见性" > JMM架构+读写层面
//    底层实现：
//    硬件架构的原理：缓存一致性协议(MESI) + CPU总线嗅探机制(监听)
//    > 将处理器缓存行的数据立即写回到主内存
//    > 这个立即回写的操作会引起其他CPU里缓存的该内存地址的数据无效(MESI)
//
// 2. 保证并发编程的"有序性" > 禁止指令重排优化(内存栅栏)
//    底层实现：
//    在编辑层面，代码编译后的字节码文件没有任何区别 !!
//    > 使用volatile修饰的变量会在"Access flags"上做标记，通过标记，在真正执行的时候，通过汇编指令来实现
//    > 添加汇编的前缀指令lock(汇编语言的lock指令的作用)，提供内存屏障(四种类型)的功能

// TODO: 指令重排相关的两个阶段
// 1. 编译阶段，Java层面没有做指令重排
// 2. 运行阶段，
public class BaseVolatile {

    // 立即同步到主内存
    // JVM writes the value back to main memory immediately
    // after a thread updates the value in its CPU cache
    // Because the thread CPU caches may get out of sync with the value in main memory
    // 确保线程在CPU cache中更新的数据能够立即回写到主内存，确保(对别线程)数据一致性
    // 确保变量从Volatile variable获取最近值
    // 确保在reading and writing double and long类型数据时，只用一步操作
    volatile boolean initialized = false;

    // JVM的即时编译器具有指令重排优化
    // 处理器会对输入的代码进行乱序执行优化(Out-Of-Order Execution), 然后对结果进行重组, 保证和程序"顺序执行"结果一致
    // 1. 通过添加内存屏障来实现, 重排时不允许后面的指令重排到屏障之前, 保证代码的执行顺序和程序的顺序相同
    // 2. 处理器会将多条指令不按照程序规定的顺序分开发送给各个相应的电路单元进行处理
    private void testInstructionReorder() {
        Map configOptions;
        char[] configText;

        // 如果initialized没有被设置成volatile，则线程在执行的过程中，由于即时编译器的重排优化
        // 导致线程A先执行最后一条设置true的代码，线程B在判断已经初始化之后，实际却没有完成配置
        configOptions = new HashMap();
        // configText = readConfigFile(filename);
        // processConfigOptions(configText, configOptions)
        initialized = true;

        // 假设以下线程B执行，如果判断为true则认为前面的配置已经完成
        while (!initialized) {
            // BaseThread.sleep(2000);
        }
        // doSomethingWithConfig();
    }
}
