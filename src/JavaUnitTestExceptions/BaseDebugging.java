package JavaUnitTestExceptions;

import JavaUnitTestExceptions.Model.StringUtil;

// Debugger: Connect to the target VM address '127.0.0.1:56461', transport: 'socket'
// Breakpoint: 结束在单断点行的前一行，断点行尚未执行
// Run > View breakpoints: 查看所有标注的断点位置
// Frames / Threads : Stack trace 运行时的堆栈调用
// Variables: 运行时的实时变量 ==> Watches points 代码右侧的实时变量, add / remove, add field Watchpoints 在field值变化的时中断
// Rerun main: 重启测试
// Show current execution point: 跳转到当前测试的断点
// Step Over: 执行下一行，如果是方法调用，则不进入方法，直接退出
// Step Into(F7): 执行下一行，如果是方法调用，则进入  ====> JDK中的源码方法，默认不是bug的来源，所有debug时不进入 !! JDK并非没有错误
// Force Step into: 执行下一行，如果是方法调用，强制进入
// Step Out: 跳出当前方法的调用
// Drop Frame: Time travel into the past 倒带本地变量的值, 重置
// Run to cursor: 在鼠标指定的位置(行数)中断，进行测试
// Resume Program: 继续执行到最后, 或被后续的断点终止
public class BaseDebugging {

    private static void testDebugging() {
        StringUtil stringUtil = new StringUtil();
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < 10) {
            stringUtil.addChar(stringBuilder, 'A');
        }
        System.out.println(stringBuilder);
    }

}

