package jvm.chapter12.DoubleCheckLocking;

public class JavaDoubleCheckLock {
    
    private volatile static JavaDoubleCheckLock instance;

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
