package JavaThreadsConcurrency.DeadlocksSolution.example01;

import java.util.Random;

public class ThreadReader implements Runnable {

    private DeadLockMessage message;

    public ThreadReader(DeadLockMessage message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (String str = message.readSync(); !str.equals("Done"); str = message.readSync()) {
            System.out.println(str);
            sleepRandomTimes();
        }
    }

    private void sleepRandomTimes() {
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
