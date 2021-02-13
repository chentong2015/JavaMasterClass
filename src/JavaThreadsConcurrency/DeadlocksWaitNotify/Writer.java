package JavaThreadsConcurrency.DeadlocksWaitNotify;

import java.util.Random;

public class Writer implements Runnable {

    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String[] messages = {"message 01", "message 02", "message 03", "message 04"};
        for (int i = 0; i < messages.length; i++) {
            message.writeSync(messages[i]);
            sleepRandomTimes();
        }
        message.writeSync("Done");
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
