package JavaThreadsConcurrency.ThreadsInOrder.base;

public class DemoWaitNotify {

    private static Object myLock1 = new Object();
    private static Object myLock2 = new Object();

    /**
     * 为什么要加这两个标识状态?
     * 如果没有状态标识，当t1已经运行完了t2才运行，t2在等待t1唤醒导致t2永远处于等待状态
     */
    private static Boolean t1Run = false;
    private static Boolean t2Run = false;

    public static void main(String[] args) {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock1) {
                    System.out.println("产品经理规划新需求...");
                    t1Run = true;
                    myLock1.notify();
                }
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock1) {
                    try {
                        if (!t1Run) {        // 可能由thread2先拿到锁，但是不能执行，必须等待.wait()
                            System.out.println("开发人员先休息会...");
                            myLock1.wait();  // 释放到获取的myLock1锁，等待thread1执行完毕之后的通知.notify();
                        }
                        synchronized (myLock2) {
                            System.out.println("开发人员开发新需求功能");
                            myLock2.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock2) {
                    try {
                        if (!t2Run) {
                            System.out.println("测试人员先休息会...");
                            myLock2.wait();
                        }
                        System.out.println("测试人员测试新功能");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println("早上：");
        thread1.start();
        thread3.start();
        thread2.start();
    }
}
