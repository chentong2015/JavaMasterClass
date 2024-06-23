package concurrent_program.features.Volatile;

// TODO: 使用"DCL双检锁"声明一个线程安全的单例类型 => 有更优设计
public class SingletonDoubleCheckLock {

    // 1. 在申明静态属性的时候，将唯一对象实例化出来
    //    真正赋值的动作要到"类的初始化阶段(只有6种情况)"才会被执行
    private static SingletonDoubleCheckLock singleton = new SingletonDoubleCheckLock();

    // 2. 双检锁技术：使用volatile在创建单列的时候禁止"处理器的指令重排"，使用lock添加内存屏障
    //              voaltile应该置于static后面
    private static volatile SingletonDoubleCheckLock instance;

    // 通过方法调用来创建单例，只在需要的时候才创建
    public static SingletonDoubleCheckLock getInstance() {
        // 如果对象已经创建，则不需要线程同步
        if (instance == null) {
            // 对应字节码:
            // 10 moniterenter
            // 11 getstatic       获取静态变量
            // 14 ifnotnull       判断非null
            // 17 new             创建对象，先分配内存
            // 20 dup
            // 21 invokespecial   执行<init>
            // 24 putstatic       赋值给静态变量
            // 27 aload_0
            // 28 moniterexit

            // 21 & 24字节码操作可能重排序
            // 先putstatic返回非空的引用，其他线程直接使用没有初始化完成的对象(半初始化对象)
            // 具体是否重排序，决定于算法和高并发场景 ==> 一般重排序的可能性并不高

            // 对于volatile修饰的变量，在底层对它前后的操作会添加屏障
            synchronized (SingletonDoubleCheckLock.class) {
                // 即使获取到类型锁，也需要判断(以免别的线程已经创建过对象，将锁释放，然后被多线程的其他线程所拿到)
                if (instance == null) {
                    instance = new SingletonDoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
