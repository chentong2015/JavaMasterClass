package concurrent_jmm.monitor;

// A synchronized method acquires a monitor before it executes
// 1. For instance method, the monitor associated with "this" (the object for which the method was invoked) is used
// 2. For static method, the monitor associated with the "Class" object for the method's class is used
public class Monitors {

    public int count;
    public static int classCount;

    // TODO: 以下两个方法由于需要lock monitor不同，可以在不同线程中"同时"执行
    // If the method is an instance method,
    // it locks the monitor associated with the instance for which it was invoked
    // 如果是实例方法，锁定的是这个实例对象关联的monitor
    public synchronized void instanceBump() {
        count++;
    }

    // After the lock action has been performed, the body of the synchronized statement is executed.
    // 如果lock action执行成功，则执行同步的语句块，结束后自动在同一个monitor上执行unlock action
    public void instanceBumpEqual() {
        synchronized (this) {
            count++;
        }
    }

    // 如果是静态方法，锁定的是关联这个class"类型对象"的monitor
    // If the method is static,
    // it locks the monitor associated with the Class object
    // that represents the class in which the method is defined.
    public static synchronized void classBump() {
        classCount++;
    }

    public static void classBumpEqual() {
        try {
            synchronized (Class.forName("BaseMonitors")) {
                classCount++;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
