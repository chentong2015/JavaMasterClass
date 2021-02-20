package JavaThreadsConcurrency.Concurrency.ReentrantProConsumer;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantConsumer implements Runnable {

    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public ReentrantConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        while (true) {
            bufferLock.lock();
            if (buffer.isEmpty()) {
                bufferLock.unlock(); // 必须释放，否则没有办法执行到后面的unlock();
                continue;  // 返回whine继续执行时，没有将bufferLock释放掉，导致producer线程没有办法获得lock, list状态不会改变 !!
            }
            if (buffer.get(0).equals("EOF")) {
                System.out.printf(color + "Existing..");
                bufferLock.unlock(); // 必须释放，否则没有办法执行到后面的unlock();
                break;
            } else {
                System.out.println(color + "removed " + buffer.remove(0));
            }
            bufferLock.unlock();
        }
    }
}
