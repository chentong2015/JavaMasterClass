package jvm.chapter12;

// TODO: 如何声明线程安全的单例类型
public class JavaDoubleCheckLock {

    // 1. 在申明静态属性的时候，将唯一对象实例化出来
    private volatile static JavaDoubleCheckLock instance;

    // 2. 使用方法，在需要的是时候通过调用创建对象
    //    如果对象已经创建，则不需要线程同步
    //    双检锁技术：两个判断对象是否为null，即使在获取到类型上的锁时，也需要判断(以免别的线程已经创建过对象，将锁释放后)
    public static JavaDoubleCheckLock getInstance() {
        if (instance == null) {
            synchronized (JavaDoubleCheckLock.class) {
                if (instance == null) {
                    instance = new JavaDoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
