package classloader_system;

import classloader_system.classloader.CustomClassLoader;

// TODO. "类装载子系统"加载类的三个主要过程
// Loading -> Linking -> Initialization
public class ClassloaderProcess {

    // 1. Loading ==> 类加载器
    // (通过特殊的名称找二进制表示形式的class或者interface .class file)，获取定义的二进制字节流
    public void testClassLoader() throws Exception {
        CustomClassLoader myClassLoader = new CustomClassLoader();
        // 加载同一路径下面的(同一个)Class文件
        String classPath = "jvm_basics.chapter07_Jvm_ClassLoader.classloader.MyClassLoader";
        Object obj = myClassLoader.loadClass(classPath).newInstance();
        System.out.println(obj.getClass());
        // instanceof 判定对象所属类型关系: false
        // JVM中存在两个MyClassLoader类，一个是由JVM应用程序类加载器所加载的，另一个是由自定义的类加载器所加载 !!
        System.out.println(obj instanceof CustomClassLoader);
    }

    // 2. Linking
    // 将上面找到的2进制形式"组合"到Java虚拟机的运行时状态(run-time state)以便可以执行
    // 2.1 Verification 验证符合规范，对JVM无害: 文件格式，元数据，字节码，符合验证
    //
    // 2.2 Preparation  为类中定义的静态变量分配内存(在方法区)，并设置类变量的初始值(该值和初始化时值可能不同)
    //                  TODO. creating the static fields (class variables and constants) for a class or interface
    //                   and initializing such fields to the default values 在这个阶段静态属性会被赋默认值
    //
    // 2.3 Resolution   将常量池内"符号引用"替换成"直接引用"
    //                  "符号引用": 定位到目标的一组符号，和具体的JVM实现无关
    //                  "直接引用": 指向目标的指针，偏移量，或者句柄
    //                  "句柄": 通过句柄池中的指针来指向java堆中实例池中或是方法区中的"对象类型数据"
    //
    // value在准备阶段为0, 把123赋值给value的动作要等到"类的初始化阶段"才会被执行
    // 当初始化条件满足时(比如静态字段被调用)，静态属性的值被更新(只初始化一次)
    // 该静态属性(的值)被所有类型对象所共享
    private static int value = 123;

    // 3. Initialization
    //    只有6种情况会立即执行"类的初始化", 执行类的构造器<clinit>()
    //    3.1 <clinit>()并不是Java代码中直接编写的方法，而是合并类中变量的赋值动作和静态代码块
    //    3.2 JVM会保证子类的<clinit>()方法执行前，父类的<clinit>()方法已经执行完毕
    //    3.3 <clinit>()方法必须保证多线程的安全
    //
    // TODO: 该语句块是在需要执行"初始化"过程中，由Java编译器自动调用(只执行一次)
    //  如果类型没有initialized，则静态构造器不会被调用
    static {
        if (true) {
            // 多个线程同时去初始化一个类，则只有一个线程去执行这方法，其他会阻塞
            while (true) {
            }
        }
    }
}
