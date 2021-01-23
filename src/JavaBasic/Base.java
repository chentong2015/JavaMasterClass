package JavaBasic;

public class Base {

    // 机器语言，低级语言，高级语言
    // Login: chen2015tong@gmail.com
    // Password: TChong15

    /**
     * 1. 语言文化背景: 背后的文化和技术支撑
     * 相似性和差异(逻辑兼容性)
     * C++ 庞杂 复杂 不能回收内存
     * sun公司 更安全的C++ => 被Oracle购买
     * 1996 java => Microsoft C#
     * java => python
     *
     * 2. 语言本身：值 类型 表达式 操作符 语句 方法(函数)
     * 3. IO 常用类库 Library
     * 4. 面向对象基础
     * 5. 算法初级 + 数据结构
     * 6. 面向对象高级(反射，SOLID)
     * 7. 高级，项目库
     * 8. 高级算法(大公司)
     */

    /**
     * IDE: 编程 编译 调试 > 部署
     * 一般服务器上没有IDE环境，通过终端执行
     *
     * 不和跟硬件交互，而是通过操作系统OS去管理
     * OS for PC: windows, MacOS, linux
     * OS for Server: 服务器组成集群 Linux, Unix/Fedora, Windows server
     * 一种系统上的程序不能扩平台运行 ==> 给什么OS写的程序
     * 通过OS的接口来操作，不同操作系统之间暴露出来的接口不同 !!!!
     *
     * 跨平台方式：
     * 1. 编程语言方式    C C++ 编译成指定OS平台的语言
     * 2. 虚拟机方式     java -> JVM / C# -> CLR
     *    java 编译完成之后，生成 .class ML 中间语言
     *    在以下的虚拟机中运行出来几乎是同样的效果
     *    JVM for windows / JVM for Linux / JVM for MacOS / JVM for Unix
     *    通过虚拟机的方式屏蔽了不同OS之间的不兼容问题，实现真正的夸平台
     *    > java version "1.8.0_144"
     *      Java(TM) SE Runtime Environment (build 1.8.0_144-b01)
     *      Java HotSpot(TM) 64-Bit Server VM (build 25.144-b01, mixed mode)
     *    可以在 控制面板\程序 目录下面查看安装的JVM的详细信息 !!!!
     *
     * 系统虚拟机：将系统切出一块了, 像一台机器一样运转, 在硬件中虚拟出来, 和主机共享硬件资源
     *
     * JDK: java development kit 开发包 SE标准 EE企业 -> Web开发
     *      单号的版本 修复大的bug 一般不会引入新的bug
     *
     * System types / CPU 一次处理数据的位数 x64 bits / 向32bits兼容; x86 -> 32 bits
     * JDK 安装的时候 可能改变操作系统的配置 c盘下面的安装文件
     *     > 包含一些常用的Tool工具 (javac编译器 对源代码文件进行编译) 和APIs
     *     > 编译出来，交给JVM
     * JRE Java Runtime Environment 运行环境
     *     > JVM 虚拟机
     */

}
