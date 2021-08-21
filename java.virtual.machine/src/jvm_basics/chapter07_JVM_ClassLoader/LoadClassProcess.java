package jvm_basics.chapter07_JVM_ClassLoader;

/**
 * JVM的生命周期: Java程序执行的7个阶段
 * 1. Java Virtual Machine Startup
 * 2. Loading of Classes and Interfaces
 * 3. Linking of Classes and Interfaces
 * 4. Initialization of Classes and Interfaces
 * 5. Creation of New Class Instances
 * 6. Finalization of Class Instances
 * 7. Unloading of Classes and Interfaces
 * 8. Program Exit, Invoke exit()/halt() of Runtime
 */
// 类装载子系统加载类的三个主要过程
public class LoadClassProcess {

    // 1. Loading
    //    通过特殊的名称找到2进制表示形式的class或者interface (.class file format)，获取定义此类的二进制字节流
    //    TODO: "Class Loader类加载器"：通过一个类的全限定命来获取描述该类的二进制字节流
    //    类本身和它的类加载器共同决定了在其虚拟机中的唯一性
    //    "类相等"是指由同一个类加载器的前提下才有意义: equals(), isAssignableFrom(), isInstance()方法返回的结果必须一致
    public void testClassLoader() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader();
        // 加载同一路径下面的(同一个)Class文件
        Object obj = myClassLoader.loadClass("jvm_basics.chapter07_JVM_ClassLoader.MyClassLoader").newInstance();
        System.out.println(obj.getClass());
        // instanceof 判定对象所属类型关系: false
        // JVM中存在两个MyClassLoader类，一个是由JVM应用程序类加载器所加载的，另一个是由自定义的类加载器所加载 !!
        System.out.println(obj instanceof MyClassLoader);
    }

    // 2. Linking
    //    将上面找到的2进制形式"组合"到Java虚拟机的运行时状态(run-time state)以便可以执行
    //    2.1 Verification      验证符合规范，对JVM无害: 文件格式，元数据，字节码，符合验证
    //    2.2 Preparation       为类中定义的静态变量分配内存，并设置类变量的初始值 ==> 注意这时的值和初始化时候的值的差别
    //    2.3 Resolution        将常量池内"符号引用"替换成"直接引用"
    private static int value = 123; // value在准备阶段的值为0, 把123赋值给value的动作要等到"类的初始化阶段"才会被执行

    // 3. Initialization 只有6种情况会立即执行"类的初始化"
    //    执行类的构造器<clinit>()方法的过程：
    //    3.1 <clinit>()并不是Java代码中字写的方法，合并类中变量的赋值动作和静态的语句块
    //    3.2 JVM会保证子类的<clinit>()方法执行前，父类的<clinit>()方法已经执行完毕
    //    3.3 <clinit>()方法必须保证多线程的安全
    // TODO: 该语句块是在需要执行"初始化"过程中，由Java编译器自动调用(只执行一次)
    static {
        if (true) {
            // 多个线程同时去初始化一个类，则只有一个线程去执行这方法，其他会阻塞
            while (true) {
            }
        }
    }
}
