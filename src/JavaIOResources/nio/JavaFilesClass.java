package JavaIOResources.nio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

// Files类型: 提供文件交互的静态方法
public class JavaFilesClass {

    // TODO. Files.readXX() 读取数据到memory内存中: 打开文件，写入数据，关闭文件
    // 1. 在数据操作上面可能比java.io缓慢，可以尝试使用别的数据文件
    // 2. 默认读取数据的解码方案是UTF_8.INSTANCE
    private static void testFilesReading() throws IOException {
        Path dataPath = FileSystems.getDefault().getPath("file.txt");
        List<String> lines = Files.readAllLines(dataPath);
        String fileStr = Files.readString(dataPath);
    }

    // TODO. Files.write()执行操作相互独立: 打开文件，写入数据，关闭文件
    // Open file streams: newBufferedReader, newBufferedWriter, newInputStream, newOutputStream
    private static void testFilesWriting() throws IOException {
        Path dataPath = FileSystems.getDefault().getPath("file.txt");

        // 将string转成写入文件的字节，指定编码的格式UTF_8
        // 指定写入的模式: 默认是没有会创建，然后刷新其中的数据内容
        Files.writeString(dataPath, "this data to write");
        Files.writeString(dataPath, "new line", StandardOpenOption.APPEND);

        try (BufferedWriter writer = Files.newBufferedWriter(dataPath)) {
            writer.write("data");
            writer.newLine();
        }

        List<String> listData = Arrays.asList("data", "data2");
        Files.write(dataPath, listData, Charset.defaultCharset(), StandardOpenOption.CREATE);
    }

    /**
     * TODO. 创建程序需要的默认文件, 不同OS的默认目录不同
     * Create temporary file in OS's default temporary file directory
     * C:\Users\Username\AppData\Local\Temp\myapp15961596656467916.appext Java自动生成临时ID
     * C:\Users\CHEN%20Tong\AppData\Local\Temp\myApp_f6a44001-3674-4140-8521-36e30af51b9a.log 实际生成临时日志文件
     * 1. 上面路径始终和File System相关联，可通过提供(Path dir,,,)参数创建在不同位置
     * 2. 也可以直接创建临时目录Files.createTempDirectory()
     */
    private void createTemporaryFileInOS() throws IOException {
        Path tempFile = Files.createTempFile("myapp", ".appext");
        Path absoluteFilepath = tempFile.toAbsolutePath();
    }
}
