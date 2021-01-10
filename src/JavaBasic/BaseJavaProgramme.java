package JavaBasic;

import java.io.*;
import java.util.Scanner;

public class BaseJavaProgramme {

    /** 金典书籍
     *  Java language Specification
     * 	Java Virtual Machine Specification
     * 	Java in a nutShell
     * 	OCA & OCP Oracle Java认证
     */

    /**
     * SDK: software development kit 软件开发包，包含类库/编辑器/编译器
     * JDK, windows SDK, .Net Framework, QT SDK (for Linux)
     * 级别: ME for mobile, SE 标准版, EE for web 标准(规范的集合) 维护和推进问题 -> Spring 框架
     *
     * 3期: 编辑期，编译器期，运行(调试)期
     *      plain text 纯文本; 什么是非代码
     *      编译错误
     *      调试可能出现在SDK中 | debug调试, 会有调试器在程序中监视(断点) > 虫子按钮(光的吸引，程序有小虫子，需要调试)
     * 3器: 编辑器，编译器, 调试器
     *
     * 什么是程序? 什么是进程Process?
     * 程序存在硬盘中，执行的时候，必须加载到内存中
     * 1. 代码编译出来的结果 objective 目标代码: C/C++编译.exe可以直接执行，Java需要交给虚拟机执行
     *      Link 需要组合在一起
     *      > 安装时候安装的多个模块和库(默认)
     *      > 自定义开发编译出来的
     *      > 扩展和添加类库/模块
     * 2. 多核计算机：分布是运算，是程序占满核，充分利用计算机的能力
     *    进程: 程序运行起来之后，加载到内存中，正在运行的东西
     *    病毒：运行必须占CPU 占内存，自主进程，或者嵌入到被的进程中
     *    内存泄漏: 内存只增不减，内存没有被清理
     * 3. IO操作：内存和硬盘之间的沟通 (带宽瓶颈)
     *      > SSD固态硬盘比机械硬盘的IO沟通快, 带宽大
     * 4. Thread线程: 至少有一个主线程 (可开启和关闭分支线程)
     */

    /**
     * 计算机工作原理：分时抢先，CPU多核高速处理
     * 1. 程序的静态
     * 2. 程序的动态 runtime 运行时
     */

    // 标准的读写文件流的操作
    public static void testReaderWriter() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("path..."));
        Scanner scanner = new Scanner(new File("path ..."));
        PrintWriter writer = new PrintWriter(new FileWriter("path..."));
        reader.close();
        scanner.close();
        writer.close();
    }

}
