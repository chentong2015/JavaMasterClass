package base_thread_concurrency;

// 守护线程: 后台持续运行的线程
// setDaemon(true) /ˈdiːmən/ 在线程池中创建出来的是守护线程，不会阻止JVM的退出
// 在用户线程结束的时候JVM退出，后台的守护线程也自动结束
public class DemoJavaDaemonThread {

    public static void main(String[] args) {
        new WorkerThread().start();
        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread ending");
    }

    static class WorkerThread extends Thread {

        public WorkerThread() {
            // false(non daemon thread), the WorkerThread continues to run.
            // true(daemon thread), the WorkerThread terminates when the main thread or/and user defined thread(non daemon) terminates.
            setDaemon(false);
        }

        public void run() {
            int count = 0;
            while (true) {
                System.out.println("Hello from Worker " + count++);
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
