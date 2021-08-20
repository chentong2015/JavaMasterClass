package jvm_basics.chapter07_JVM_ClassLoader;

public class ObjectCreationProcess {

    // 对象创建的流程: new MyClass();
    //   类加载检查
    //   是否已加载类  --NO--> 加载类
    //   分配内存
    //   初始化(准备阶段)
    //   设置对象头
    //   执行<init>方法, 构造方法
}
