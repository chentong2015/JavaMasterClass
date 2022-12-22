package jvm_basics.chapter12_JavaMemoryModel.Daemon;

public class WorkerThread extends Thread {

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
