package jvm_basics.jvm_base_sections.classLoader_system;

public class CreationObjectProcess {

    // TODO: new的执行流程: new操作并不是原子操作，在字节码层面由4个指令组成
    // MyClass object = new MyClass();
    // 4 new              #2 //class//jvm_basics//chapter07_Jvm_ClassLoader//MyClass
    //                    类的信息会加载到Constant Pool, 在new时找到对应的类信息
    //                    创建空对象，并对象的引用压入栈顶
    //
    // 7 dup              复制栈顶元素，再压入栈顶
    //
    // 10 invokespecial   使用对象在内存的地址(消耗掉一个对象的引用)，去完成对象的初始化
    //                    执行这个类的默认构造方法(初始化普通属性)，需要this指针(aload_0 把this指针压入栈)
    //
    // 11 astore_3        把创建的对象的引用存入本地变量表
    //                    赋值给指定的变量

    // 1. 类加载检查
    // 2. 判断是否已经加载类(加载类型信息到方法区)
    //       --NO-->  使用类加载子系统先加载类: 加载 -> 链接5(验证，准备，解析) -> 初始化
    // 3. 为对象分配内存空间
    // 4. 为内存空间初始化
    // 5. 设置对象头Object Header, 完善对象的信息
    // 6. 执行<init>方法, 构造方法
    // 7. 对象创建完成, 位于堆空间的Eden区
}
