package jvm_basics.chapter12_JavaMemoryModel;

// TODO: 使用"DCL双检锁"声明一个线程安全的单例类型
public class JavaDoubleCheckLock {

    // 1. 在申明静态属性的时候，将唯一对象实例化出来
    //    真正赋值的动作要到"类的初始化阶段(只有6中情况下)"才会被执行
    private static JavaDoubleCheckLock singleton = new JavaDoubleCheckLock();

    // 2. 双检锁技术：使用volatile在创建单列的时候禁止"处理器的指令重排"，使用lock添加内存屏障
    //              1> 为JavaDoubleCheckLock()分配内存
    //              2> 调用构造器来初始化(字段)
    //              3> 将引用"发布"给instance引用变量
    private volatile static JavaDoubleCheckLock instance;

    public static JavaDoubleCheckLock getInstance() {
        // 如果对象已经创建，则不需要线程同步
        if (instance == null) {
            synchronized (JavaDoubleCheckLock.class) {
                // 即使在获取到类型上的锁时，也需要判断(以免别的线程已经创建过对象，将锁释放后)
                if (instance == null) {
                    instance = new JavaDoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
