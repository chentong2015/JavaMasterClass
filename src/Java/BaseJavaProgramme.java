package Java;

import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

// IDEA背景设置 https://www.cnblogs.com/goodAndyxublog/p/14737271.html
// IDEA官方文档 https://www.jetbrains.com/help/idea/discover-intellij-idea.html#IntelliJ-IDEA-editions
// 1. IDEA会自动indexing扫描jdk中的工具包; idea需要沟通网络，实现工具的更新
// 2. configure > settings > line number 显示出来
// 3. change font-size with Ctrl+Mouse Wheel (Zoom) 滚动放缩代码
// 4. Add unambiguous 明确的 imports on the fly / Optimise imports on the fly 移除不需要的import导入
// 5. Open Module Settings 来添加.jar的类库和框架
// 6. Duplicate code 使IDEA自动的监测是否代码有重复
// 7. IDEA Terminal Git：Settings > Terminal > Shell Path > ...sh.exe --login -i 将终端的Shell显示在IDEA界面内部
// IDEA build软件，部署和发布
// 1. File > Project Structure > Artifacts > Create Jar Form Module > All Module + Main Class
// 2. META-INF的位置必须在主项目的目录下面
// 3. Build > Build Artifacts > Build

// 同一个文件中，只能有一个public的类型声明
public class BaseJavaProgramme {

    /**
     * 1. 语言文化背景: 背后的文化和技术支撑
     *    机器语言 > 低级语言 > 高级语言
     *    相似性和差异(逻辑兼容性)
     *    C++ 庞杂 复杂 不能回收内存  => C++ Standard for programming
     *    sun公司 更安全的C++ => 被Oracle购买
     *    1996 java => Microsoft C#
     *    java => python
     * 2. 语言本身：值 类型 表达式 操作符 语句 方法(函数)
     * 3. IO 常用类库 Library
     * 4. 面向对象基础
     * 5. 算法初级 + 数据结构
     * 6. 面向对象高级 (反射，SOLID敏捷软件开发原则) !!!!
     * 7. 高级，项目库
     * 8. 高级算法(公司项目实践)
     */

    /**
     * 1. 计算机的基本工作原理 ? 分时抢先，CPU多核高速处理
     *      > 程序的静态
     *      > 程序的动态 runtime 运行时
     * 2. 程序不和跟硬件交互，而是通过操作系统去管理
     *      OS for PC: windows, MacOS, linux
     *      OS for Server: 服务器组成集群 Linux, Unix/Fedora, Windows Server
     * 3. 多核计算机：分布是运算，是程序占满核，充分利用计算机的能力
     *      > 进程: 程序运行起来之后，加载到内存中，正在运行的东西
     *      > 病毒：运行必须占CPU 占内存，自主进程，或者嵌入到被的进程中
     *      > 内存泄漏: 内存只增不减，内存没有被清理
     * 4. IO操作：内存和硬盘之间的沟通 (带宽瓶颈)  ===> SSD固态硬盘(Solid-state drive)比机械硬盘的IO沟通快, 带宽大
     * 5. Thread线程: 至少有一个主线程 (可开启和关闭分支线程)
     * 6. System types / CPU 一次处理数据的位数 x64 bits / 向32bits兼容; x86 -> 32 bits
     * 7. 1G = 1024M = 1024 * 1024KB = 1024 * 1024 * 1024B  (8 bits = 1 Byte)
     */

    /**
     * 什么是程序? 什么是进程Process?
     * 程序存在硬盘中，执行的时候，必须加载到内存中
     * 代码编译出来的结果 objective 目标代码: C/C++编译.exe可以直接执行，Java需要交给虚拟机执行
     *      Link 需要组合在一起的代码
     *      > 安装时候安装的多个模块和库(默认)
     *      > 自定义开发编译出来的
     *      > 扩展和添加类库/模块
     *
     * SDK: software development kit 软件开发包，包含类库/编辑器/编译器
     *      JDK, windows SDK, .Net Framework, QT SDK (for Linux)
     * 级别: ME for mobile, SE 标准版, EE for web 标准(规范的集合) 维护和推进问题 -> Spring 框架
     *
     * IDE: 编程 编译 调试 > 部署   ==> 一般服务器上没有IDE环境，通过终端执行
     * 3期: 编辑期，编译器期，运行(调试)期
     *      plain text 纯文本; 什么是非代码
     *      编译错误
     *      调试可能出现在SDK中 | debug调试, 会有调试器在程序中监视(断点) > 虫子按钮 (光的吸引，程序有小虫子，需要调试)
     * 3器: 编辑器, 编译器, 调试器
     */

    /**
     * 软件测试：
     * 1. 黑盒测试 (功能测试、数据驱动测试 DDT):
     * 测试者不了解程序的内部情况，不需具备应用程序的代码、内部结构和编程语言的专门知识, 只知道程序的输入、输出和系统的功能
     * 这是从用户的角度针对软件界面、功能及外部结构进行测试，而不考虑程序内部逻辑结构
     * 2. 白盒测试 (透明盒测试、结构测试、逻辑驱动测试)
     * 测试者了解待测试程序的内部结构、算法等信息，这是从程序设计者的角度对程序进行的测试
     * 白盒测试可以应用于单元测试（unit testing）
     */

    // Scanner 文本扫描器: A simple text scanner which can parse primitive types and strings using regular expressions
    private static void retrieveUserConsoleInput() {
        Scanner scanner = new Scanner(System.in); // Standard input stream => Keyboard input
        System.out.println("Input your year of birth");

        boolean isValidYear = scanner.hasNextInt(); // 判断输入的是有效值 !!
        if (isValidYear) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR); // 使用Calendar拿到当前的年份
            int yearOfBirth = scanner.nextInt();  // 解析输入成int类型的值 ==> Throws Exception
            scanner.nextLine(); // Handle the next line data
            System.out.println("Enter your name:");
            String username = scanner.nextLine(); // read information form scanner ==> 安装Line行进行读取 !!
        } else {
            System.out.println("Uable to parse year of birth");
        }
        scanner.close();
    }

    private static int[] getIntegers(int count) {
        int[] values = new int[count];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input " + count + " values \r");
        for (int i = 0; i < values.length; i++) {
            values[i] = scanner.nextInt();  // 确保输入的值都能被读取到, 有效的int值
        }
        return values;
    }

    private static void testRandomNumber() {
        Random random = new Random();
        int randomNum = random.nextInt(100); // 约束随机值的范围
    }
}
