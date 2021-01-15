package JavaInputOutput;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * IO操作：内存和硬盘之间的沟通 (带宽瓶颈)
 * The source and destination of I/O
 * 1. files on the PC disk drives 磁盘驱动器中存储的数据 + SSD固态硬盘数据 <-> 机械硬盘写入方式是覆盖
 * 2. Networking 网络流数据
 * 3. Pips 管道, WebSocket
 * 4. Computer's keyboard and screen 鼠标和键盘的输入
 * Format格式:
 * 1. binary format 二进制
 * 2. XML
 * 3. JSON
 * 4. Serial & Sequential files 序列化数据 ==> A Steam of data, each piece of data following in sequence
 * 5. Random access files 随机访问和修改(位置)上的数据
 */
// TODO: 回顾C#关于IO操作和Stream流的基础处理
public class BaseIO implements Map<Integer, String> {

    private static Map<Integer, String> locations = new HashMap<>();

    /**
     * 第一种：标准的读取文件内容
     * Scanner在close()时会同时关闭任何使用的Stream流, 只要对应的类型(FileReader)实现了Closeable接口   ====> C#对比：类型实现了IDispose接口 -> Dispose()
     * if (source instanceof Closeable) {
     *    try {
     *       ((Closeable)source).close();
     *     } catch (IOException ioe) { }
     *  }
     */
    static {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations.txt"));
            scanner.useDelimiter(","); // 设置每一行的分割符; 也可以直接使用String的切割Split
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
     * 1. Write data to a steam (to a local file)
     * 2. IOException 是一种checked exception，无法忽略 !!
     * 3. 必须要关闭文件的写入Stream流，否则文件会处于Locked状态，别的process无法操作
     */
    public static void main(String[] args) {
        FileWriter localFile = null;
        try {
            localFile = new FileWriter("locations.txt"); // 提供的是相对路径; 重复操作将重写文件中的数据
            for (String location : locations.values()) {
                localFile.write(location + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (localFile != null) {
                    localFile.close();  // 关闭文件的时候，也可能抛出IO异常
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
     */
    private static void testTryWithResourcesStatement() throws IOException {
        try (FileWriter localFile = new FileWriter("locations.txt");
             FileWriter dirFile = new FileWriter("directions.txt");
        ) {
            for (String location : locations.values()) {
                localFile.write(location + "\n");
            }
        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return locations.get(key);
    }

    @Override
    public String put(Integer key, String value) {
        return locations.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {
        // 将一个Map完全put到当前类型的Map Field成员中
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<String> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, String>> entrySet() {
        return locations.entrySet();
    }
}
