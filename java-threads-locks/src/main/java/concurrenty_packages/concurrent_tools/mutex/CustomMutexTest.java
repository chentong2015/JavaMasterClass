package concurrenty_packages.concurrent_tools.mutex;

public class CustomMutexTest {

    public static final CustomMutex mutex = new CustomMutex();

    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runner(), "Runner" + i).start();
        }
    }

    static class Runner implements Runnable {
        @Override
        public void run() {
            mutex.lock();
            try {
                System.out.println("Run thread: " + Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mutex.unlock();
        }
    }
}
