package multi_threading.thread;

// TODO. Live Threads = User Threads + Daemon Threads 所有活跃线程
// User Threads: 1     用户自创建的工作线程
// Daemon Threads: 10  守护线程，后台持续运行的线程，默认创建的辅助线程
//
// TODO. 当用户线程全部执行完毕，只剩守护线程池，JVM自动退出(守护线程也自动结束)
// The Java Virtual Machine exits when the only threads running are all daemon threads.
public class JavaDaemonThread {

    static class UserThread extends Thread {
        // TODO. boolean daemon = false;
        //  默认创建出来的线程(包括线程池中线程)不是守护线程，需要执行到结束
        // setDaemon(false): non daemon thread, it continues to run until the end
        // setDaemon(true): daemon thread, it terminates when user defined thread(non daemon) terminates.
        public UserThread() {
            setDaemon(false);
        }

        public void run() {
            int count = 0;
            while (true) {
                System.out.println("Worker " + count++);
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
