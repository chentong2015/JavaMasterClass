package jvm_basics.chapter07_JVM_ClassLoader;

public class CreationObjectProcess {

    // Java中new的执行流程 ?
    // TODO: new操作并不是原子操作，在字节码层面由4个指令组成
    // MyClass object = new MyClass();
    // 4 new              #2  //class//jvm_basics//chapter07_JVM_ClassLoader//MyClass
    //                    类的信息会加载到Constant Pool, 在new时找到对应的类信息
    //                    创建空对象，并压入栈顶
    //
    // 7 dup              复制栈顶元素，再压入栈顶
    // 10 invokespecial   使用对象在内存的地址(消耗掉一个对象的引用)，去完成对象的初始化
    // 11 astore_3        把创建的对象的引用存入本地变量表

    // 1. 类加载检查
    // 2. 是否已加载类(加载类型信息到方法区)
    //       --NO-->  类加载子系统先加载类: 加载 -> 链接5(验证，准备，解析) -> 初始化
    // 3. 为对象分配内存空间
    // 4. 为内存空间初始化
    // 5. 设置对象头Object Header, 完善对象的信息
    // 6. 执行<init>方法, 构造方法
    // 7. 对象创建完成, 位于堆空间的Eden区
}
