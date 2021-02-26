package JavaDebuggingExceptions;

// Debugger: Connect to the target VM address '127.0.0.1:56461', transport: 'socket'
// Breakpoint: 结束在单断点行的前一行，断点行尚未执行
// Watches points 代码右侧的实时变量, add / remove
// Field Watchpoints 每当field值变化的时中断 (field access, field modification, remove once 只在第一次中断, Resume之后不再中断)
// Run > View breakpoints: 查看所有标注的断点位置
// Frames / Threads : Stack trace 运行时的堆栈调用
// Variables: 运行时的实时变量 ===> Set value 可以设置变量的测试值
// Rerun main: 重启测试
// Show current execution point: 跳转到当前测试的断点
// Step Over: 执行下一行，如果是方法调用，则不进入方法，直接退出
// Step Into(F7): 执行下一行，如果是方法调用，则进入  ====> JDK中的源码方法，默认不是bug的来源，所有debug时不进入 !! JDK并非没有错误
// Force Step into: 执行下一行，如果是方法调用，强制进入
// Run > Smart Step into: 指定要跳入的方法, 当同时调用多个方法的时候
// Step Out: 跳出当前方法的调用
// Drop Frame: Time travel into the past 倒带本地变量的值, 重置
// Run to cursor: 在鼠标指定的位置(行数)中断，进行测试
// Resume Program: 继续执行到最后, 或被后续的断点终止
public class BaseDebugging {

    /**
     * JTransforms-3.1-with-dependencies.jar
     * JTransforms-3.1-sources.jar
     */
    private static void testDebugging() {
        // 在使用JDK或是第三方的代码时，可能无法查看source源代码
        // 提供的binary二进制jar只具有类型和方法的声明，没有实现源码, 没有提供debug信息
        // 1. 可以将两种jar关联在一起，在调试时使用源码
        // 2. Project Settings > Libraries > Add > Add Class + Sources
    }
}

