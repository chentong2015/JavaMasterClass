package concurrent_thread.ThreadsProject;

import java.io.IOException;

public class CsvOutputThread implements Runnable {

    private int count;

    public CsvOutputThread(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("Thread number: " + count);
        try {
            // CsvOutputStream.getInstance().writeLine();
            CsvOutputStream.getInstance().writeLongLine(count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
