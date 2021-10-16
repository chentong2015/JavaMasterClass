package Java;

public class JavaJDK {

    /**
     * JRE: Java Runtime Environment, 包含运行Java程序的所有需要: 6层结构
     *      > Deployment: plugin 插件
     *      > UI Tools 用户界面开发工具: Swing, AWT, JavaFX, Sound and BaseSources
     *      > Integration Library 一些集成的类库: JDBC, JNDI
     *      > Class Library: IO, DateTime, Serialization, Networking
     *      > Lang and Util base Libraries: Math, Regular Express, Reflection
     *      > JVM 虚拟机
     * JDK: Java development kit开发包, 相当于Java SDK
     *      单号的版本 修复大的bug 一般不会引入新的bug
     *      JDK 安装的时候 可能改变操作系统的配置 c盘下面的安装文件
     *          > 包含JRE所包含的所有
     *          > 包含一些常用的Tool工具(javac编译器, JavaDoc)和APIs
     *
     * 一种系统上的程序不能扩平台运行 ==> 给什么OS写的程序
     * 通过OS的接口来操作，不同操作系统之间暴露出来的接口不同 !!!!
     * /系统虚拟机：将系统切出一块了, 像一台机器一样运转, 在硬件中虚拟出来, 和主机共享硬件资源/
     *
     * 实现跨平台方式：
     * 1. 编程语言方式   C, C++ 编译成指定OS平台的语言
     * 2. 虚拟机方式     java -> JVM, C# -> CLR
     *    源代码编译完成之后生成.class中间语言, 在不同OS平台的虚拟机中运行出来保持同样的效果
     *    JVM for windows / JVM for Linux / JVM for MacOS / JVM for Unix
     *    JVM: 从软件层面屏蔽了不同操作系统在底层硬件和指令上的区别, 底层用C++实现
     * 由于硬件和指令级的区别，同一个Java源代码在不同平台生成的二进制码不同
     */

    /**
     * 测试终端(或命令提示符)运行
     * > javac HelloWorld.java 编辑源代码，生成HelloWorld.class中间代码
     * > java HelloWorld 运行程序
     * > java -jar HelloWorld.jar 可以运行打包出来的jar程序
     * > nohup java -jar HelloWorld.jar & 始终运行jar程序
     * TODO: war package vs jar package 一般使用war包部署，war中包含了许多的jar包
     */

    /**
     * Java 应用程序部署方案：如何在没有装JRE的操作系统上运行程序(用户PC)
     * https://github.com/libgdx/libgdx/wiki/Bundling-a-JRE
     * 1. 下载jre或者jdk进行安装
     * 2. 使用launch4j工具生成.exe可执行文件，静态绑定指定路径的jdk
     * 3. 配置生成self-container的应用程序
     */
}
