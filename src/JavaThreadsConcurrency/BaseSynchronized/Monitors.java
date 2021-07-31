package JavaThreadsConcurrency.BaseSynchronized;

/**
 * A synchronized method acquires a monitor before it executes
 * 1. For instance method, the monitor associated with "this" (the object for which the method was invoked) is used
 * 2. For static method, the monitor associated with the "Class" object for the method's class is used
 */
// 1. Synchronization is implemented by using monitors
// 2. Each object in Java is associated with a monitor, which a thread can lock or unlock
// 3. Only one thread at a time may hold a lock on a monitor 同一时间只有一个线程能获取监视器上的锁
// 4. 如果正常或突然完成了方法主体的执行，则将在同一个monitor监视器上自动执行解锁操作
public class Monitors {

    public int count;
    public static int classCount;

    // TODO: 以下两个方法由于需要lock monitor不同，可以在不同线程中"同时"执行
    // If the method is an instance method, it locks the monitor associated with the instance for which it was invoked
    // 如果是实例方法，锁定的是这个实例对象关联的monitor
    public synchronized void instanceBump() {
        count++;
    }

    // 如果是静态方法，锁定的是关联这个class"类型对象"的monitor
    // If the method is static, it locks the monitor associated with the Class object that represents the class in which the method is defined.
    public static synchronized void classBump() {
        classCount++;
    }


    // After the lock action has been performed, the body of the synchronized statement is executed.
    // 如果lock action执行成功，则执行同步的语句块，结束后自动在同一个monitor上执行unlock action
    public void instanceBumpEqual() {
        synchronized (this) {
            count++;
        }
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
