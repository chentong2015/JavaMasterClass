package jvm_basics.chapter07_JVM_ClassLoader;

// JVM如何创建一个对象，主要流程有那些 ?
public class CreationObjectProcess {

    // MyClass object = new MyClass();
    //                    类的信息会加载到Constant Pool, 在new时找到对应的类信息
    // 4 new              #2  //class//jvm_basics//chapter07_JVM_ClassLoader//MyClass
    // 7 dup              复制对象在内存空间的地址，在操作数栈中double一份
    // 10 invokespecial   使用对象在内存的地址(消耗掉一个对象的引用)，去完成对象的初始化
    // 11 astore_3

    // 1. 类加载检查
    // 2. 是否已加载类(加载类型信息到方法区)  --NO-->  类加载子系统先加载类
    //                                           > 加载
    //                                           > 链接: 验证，准备，解析
    //                                           > 初始化
    // 3. 为对象分配内存空间
    // 4. 为内存空间初始化
    // 5. 设置对象头Object Header, 完善对象的信息
    // 6. 执行<init>方法, 构造方法
    // 7. 对象创建完成, 位于堆空间的Eden区

    // TODO: this指针何时被赋值
    // 当一个对象创建后，Java虚拟机就会为其分配一个指向对象本身的this指针
    // 关键字this只能用于方法方法体内，并且位于"栈帧"顶部index=0的位置
}
