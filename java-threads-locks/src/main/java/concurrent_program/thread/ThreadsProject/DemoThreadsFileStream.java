package concurrent_program.thread.ThreadsProject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DemoThreadsFileStream {

    private static void testMultiThreadsFileStreams() throws InterruptedException, IOException {
        for (int index = 0; index < 2; index++) {
            new Thread(() -> {
                try {
                    CsvOutputStream.getInstance().writeLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        // 在.close()关闭输出流之前，设置一段时间的休眠
        Thread.sleep(5000);
        CsvOutputStream.getInstance().close();
    }

    private static void testThreadPoolFileStreams() throws IOException {
        ExecutorService worker = Executors.newFixedThreadPool(2);
        for (int index = 1; index < 1000; index++) {
            worker.execute(new CsvOutputThread(index));
        }
        worker.shutdown();
        try {
            worker.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        CsvOutputStream.getInstance().close();
    }
}
