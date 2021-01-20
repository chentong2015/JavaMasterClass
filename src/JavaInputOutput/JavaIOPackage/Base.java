package JavaInputOutput.JavaIOPackage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <<Java IO works with Streams (bytes & binary), data is read one byte or character at a time or buffered  !!!!>>
 * IO操作：内存和硬盘之间的沟通 (带宽瓶颈)
 * The source and destination of I/O
 * 1. files on the PC disk drives 磁盘驱动器中存储的数据 + SSD固态硬盘数据 <-> 机械硬盘写入方式是覆盖
 * 2. Networking 网络流数据
 * 3. Pips 管道, WebSocket
 * 4. Computer's keyboard and screen 鼠标和键盘的输入
 * ------------------------------------------------
 * Format格式:
 * 1. binary format 二进制
 * 2. XML
 * 3. JSON
 * 4. Serial & Sequential files 序列化数据 ==> A Steam of data, each piece of data following in sequence
 * 5. Random access files 随机访问和修改(位置)上的数据
 */
public class Base {

    private static Map<Integer, String> locations = new HashMap<>();

    /**
     * 第一种：标准的读取文件内容  ===> 需要解码方案
     * Scanner在close()时会同时关闭任何使用的Stream流, 只要对应的类型(FileReader)实现了Closeable接口   ====> C#对比：类型实现了IDispose接口 -> Dispose()
     * if (source instanceof Closeable) { }
     */
    static {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations.txt", StandardCharsets.UTF_8)); // ====> C#对比：StreamReader
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
    public static void main(String[] args) {
        FileWriter localFile = null;
        try {
            // 提供的是相对路径; 重复操作将重写文件中的数据
            localFile = new FileWriter("locations.txt", StandardCharsets.UTF_8, true); // ====> C#对比：StreamWriter
            for (String location : locations.values()) {
                localFile.write(location + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (localFile != null) {
                    localFile.close();  //  close()关闭的时候，也可能抛出异常IOException
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * 第二种：直接选择抛出异常，则在方法的内部无需再catch该异常          =====> C#对比: 没有这种异常处理的机制
     */
    private static void testThrowIOException() throws IOException {
        FileWriter localFile = new FileWriter("locations.txt");
        for (String location : locations.values()) {
            localFile.write(location + "\n");
        }
        localFile.close();
    }

    /**
     * Try-With-Resources-Statement                               =====> C#对比: 等效于使用using语句操作IO; 自动生成try-finally语句块，同时完成释放
     * 1. 支持两个Resources的声明
     * 2. Ensure the writer stream is closed 确定写入的流会被关闭 (无论catch异常与否)
     * 3. 只在该Statement才能不写finally语句块 !!!!
     */
    private static void testTryWithResourcesStatement() throws IOException {
        try (FileWriter localFile = new FileWriter("locations.txt");
             FileWriter dirFile = new FileWriter("directions.txt")) {
            for (String location : locations.values()) {
                localFile.write(location + "\n");
            }
        }
    }
}
