package base_thread_concurrency.ThreadsFileStream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class CsvOutputStream {

    private final FileOutputStream fileOutputStream;
    private final BufferedOutputStream bufferedOutputStream;
    private final String lineSeparator = System.getProperty("line.separator");
    private static CsvOutputStream instance = null;

    private CsvOutputStream() throws IOException {
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create instance");
        }
        fileOutputStream = new FileOutputStream("checkOutPut2.csv");
        bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        initCsvHeaders();
    }

    public static CsvOutputStream getInstance() throws IOException {
        if (instance == null) {
            synchronized (CsvOutputStream.class) {
                if (instance == null) {
                    instance = new CsvOutputStream();
                }
            }
        }
        return instance;
    }

    private void initCsvHeaders() throws IOException {
        String headers = "message";
        bufferedOutputStream.write(headers.concat(lineSeparator).getBytes());
    }

    // 1. 多个线程同时调用bufferedOutputStream插入，数据存储的顺序没有保证
    // 2. 如果添加synchronized线程同步锁，则保证了数据的完整插入
    public synchronized void writeLine() throws IOException {
        // 3. 方法内部的局部变量不被线程共享，所以值的增长不会被扰乱
        for (int index = 0; index < 1000; index++) {
            String line = "line" + index + lineSeparator;
            bufferedOutputStream.write(line.getBytes());
        }
    }

    // 4. 局部变量在创建过程中不会受到多线程的影响，写入的数据是没有问题
    public void writeLongLine(int threadId) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 1; index < 100; index++) {
            stringBuilder.append("line " + index);
        }
        String line = threadId + stringBuilder.toString().concat(lineSeparator);
        bufferedOutputStream.write(line.getBytes());
    }

    public void close() throws IOException {
        bufferedOutputStream.flush();
        bufferedOutputStream.close();

        fileOutputStream.close();
    }
}
