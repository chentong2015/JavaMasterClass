package concurrent_program.features.Volatile;

import java.util.HashMap;
import java.util.Map;

// volatile 三大核心特性(功能) => 只提供同步的通讯效果 !!
// 1. 部分原子性
// 2. 保证并发编程的"可见性" > JMM架构+读写层面
//    硬件架构原理：缓存一致性协议(MESI) + CPU总线嗅探机制(监听)
//    > 将处理器缓存行的数据立即写回到主内存
//    > 这个立即回写的操作会引起其他CPU里缓存的该内存地址的数据无效(MESI)
// 3. 保证并发编程的"有序性" > 禁止指令重排优化(内存栅栏)
//    > 编译阶段，Java层面没有做指令重排，代码编译后的字节码文件没有任何区别
//        使用volatile修饰的变量会在"Access flags"上做标记，通过标记，在执行时通过汇编指令来实现
//    > 运行阶段，加了内存屏障，CPU无法乱序执行，只能按照顺序执行
//        重排时不允许后面的指令重排到屏障之前, 保证代码的执行顺序和程序的顺序相同
public class BaseVolatile {

    // JVM的即时编译器具有指令重排优化 ==> 由于CPU的乱序执行造成
    // 1. 处理器会对输入代码乱序执行优化(Out-Of-Order Execution), 然后对结果重组, 保证和程序"顺序执行"结果一致
    // 2. 处理器会将多条指令不按照程序规定的顺序分开发送给各个相应的电路单元进行处理
    volatile boolean initialized = false;

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
