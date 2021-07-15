package JavaThreadsConcurrency.ConcurrencyPatterns.BlockingQueueProConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingProducer implements Runnable {

    private String color;
    private ArrayBlockingQueue<String> queue;

    public BlockingProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.queue = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] numbers = {"1", "2", "3", "4"};
        for (String num : numbers) {
            try {
                queue.put(num);
                System.out.println(color + "Adding.." + num);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            queue.put("EOF");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
