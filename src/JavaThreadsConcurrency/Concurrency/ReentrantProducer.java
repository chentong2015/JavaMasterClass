package JavaThreadsConcurrency.Concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantProducer implements Runnable {

    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public ReentrantProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] numbers = {"1", "2", "3", "4"};
        for (String num : numbers) {
            try {
                bufferLock.lock();
                buffer.add(num);
                bufferLock.unlock();
                System.out.println(color + "Adding.." + num);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bufferLock.lock();
        buffer.add("EOF"); // "EOF": End of file String
        bufferLock.unlock();
    }
}
