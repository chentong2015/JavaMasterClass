package concurrent_program.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaExecutorsDemo {

    // Main Thread + 6 Threads
    // 多个线程并行执行，多个子线程中任务将确保被完成
    public static void main(String[] args) {
        List<String> recordList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int index=0; index<1000; index++) {
            int finalIndex = index;
            executorService.execute(() -> {
                recordList.add("string" + finalIndex);
                System.out.println("string " + finalIndex);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();

        System.out.println(recordList.size());
        System.out.println("Finish Main Thread");
    }
}