package JavaIO.IOPackage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileReaderWriter {

    private static Map<Integer, String> locations = new HashMap<>();

    /**
     * 第一种：标准的读取文件内容  ===> 需要解码方案
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
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * 第一种：标准处理IO机制
     * 1. Write data to a local file 打开文件，写入所有数据，然后关闭文件  ===> 需要编码方案
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
     * Try-With-Resources-Statement
     * 1. 支持多个Resources的同时声明
     * 2. Ensure the writer stream is closed 确定写入的流会被关闭 (无论catch异常与否)
     * 3. 只在该Statement才能不写finally语句块 !!!!
     */
    private static void testTryWithResourcesStatement() throws IOException {
        try (FileWriter localFile = new FileWriter("locations.txt")) {
            for (String location : locations.values()) {
                localFile.write(location + "\n");
            }
        }
    }

    /**
     * 1. PrintWriter继承自Writer, 同时实现了class PrintStream中的所有的输出方法
     * 2. PrintWriter将写入各种数据格式化的功能(能力)提供给FileWriter
     * 3. 它不包含用于写入原始字节的方法，为此程序应使用未编码的字节流  ==> 区别于PrintStream
     */
    private static void testPrintWriter() throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("locations.txt"))) {
            printWriter.print("print line");
            printWriter.println("println line");
            printWriter.write("write line");
        }
    }
}
