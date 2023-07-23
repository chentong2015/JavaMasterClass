package JavaIOResources.IOPackage.file_reader_writer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaFileReaderWriter {

    private static Map<Integer, String> locations = new HashMap<>();

    /**
     * 第一种：标准的读取文件内容: 需要解码方案
     * Scanner在close()时会同时关闭任何使用的Stream流, 只要对应的类型(FileReader)实现了Closeable接口
     * if (source instanceof Closeable) { }
     */
    static {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations.txt", StandardCharsets.UTF_8));
            scanner.useDelimiter(","); // 设置每一行的分割符; 也可以直接使用String的切割Split;
            while (scanner.hasNext()) {
                int locID = scanner.nextInt();
                scanner.skip(scanner.delimiter()); // 移动到分割符的下一个数据
                locations.put(locID, scanner.nextLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            // 如果在创建scanner的时候出错，则需要判断是否为null
            if (scanner != null) {
                // TODO: 调用.close()方法的时候，任然可能抛出异常，应该使用try语句
                scanner.close();
            }
        }
    }

    /**
     * 第一种：标准处理IO机制
     * 1. Write data to a local file 打开文件，写入所有数据，然后关闭文件: 需要编码方案
     * 2. IOException 是一种checked exception，无法忽略 !!
     * 3. 必须要关闭文件的写入Stream流，否则文件会处于Locked状态，别的process无法操作
     */
    public static void testBasicIOProcess() {
        FileWriter localFile = null;
        try {
            // 提供的是相对路径; 重复操作将重写文件中的数据
            localFile = new FileWriter("locations.txt", StandardCharsets.UTF_8, true);
            for (String location : locations.values()) {
                localFile.write(location + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (localFile != null) {
                    localFile.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * 第二种：直接选择抛出异常，则在方法的内部无需再catch该异常
     */
    private static void testThrowIOException() throws IOException {
        FileWriter localFile = new FileWriter("locations.txt");
        for (String location : locations.values()) {
            localFile.write(location + "\n");
        }
        localFile.close();
    }

    /**
     * TODO: Try-With-Resources-Statement: automating clean up
     * 1. 支持多个Resources的同时声明, 使用;分隔
     * 2. 自动调用Closeable的close方法，确定写入的流会被关闭(无论catch异常与否)
     * 3. 只在该Statement才能不写finally语句块 !!
     */
    private static void testTryWithResourcesStatement() throws IOException {
        try (FileWriter localFile = new FileWriter("locations.txt")) {
            for (String location : locations.values()) {
                localFile.write(location + "\n");
            }
        }
    }

    // 在读取数据的时候同时写入，注意读取的长度和写入的参数
    public static void testTryWithMultiResources() {
        int length;
        char[] buff = new char[8];
        try (Reader reader = new FileReader("file1.txt");
             Writer writer = new FileWriter("file2.txt")) {
            while ((length = reader.read(buff)) >= 0) {
                writer.write(buff, 0, length);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
