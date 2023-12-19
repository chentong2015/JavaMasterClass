package base_thread_concurrency;

// Java 守护线程，后台持续运行的线程
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
