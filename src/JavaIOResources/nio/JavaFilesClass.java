package JavaIOResources.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

// Files类型: 提供文件交互的静态方法
// 1. static methods for interaction with files
// 2. Open file streams: newBufferedReader, newBufferedWriter, newInputStream, newOutputStream
// 3. Read/Write file contents: readAllLines, write 直接一行语句读取文件数据
public class JavaFilesClass {

    /**
     * Files.readAllLines() 会将所有的数据读取到memory内存中
     * 1. 在数据操作上面可能比java.io缓慢，可以尝试使用别的数据文件
     * 2. 操作Stream，可指定对数据的解码方案
     */
    private static void testFilesReadingTextFile() throws IOException {
        Path dataPath = FileSystems.getDefault().getPath("file.txt");
        // 默认的读取数据，解码方案是UTF_8.INSTANCE
        // 1. open the file for the path
        // 2. read all lines in it
        // 3. close the file
        List<String> lines = Files.readAllLines(dataPath);
        String fileStr = Files.readString(dataPath);

        // 1. open the file for the path
        // 2. write data to the file
        // 3. close the file
        Files.writeString(dataPath, "this data to write");
    }

    /**
     * 1. Files.write()方法所执行的操作是相互独立的：打开文件，写入数据，关闭文件
     * .  可以使用StringBuilder(单线程)来一次性写入更多的数据
     * 2. 需要对数据进行编码，然后写入文件中
     */
    private static void testFilesWritingTextFile() throws IOException {
        Path dataPath = FileSystems.getDefault().getPath("file.txt");
        String appendData = "\nline 4";
        // 将string转成写入文件的字节; 指定编码的格式UTF_8
        // 指定写入的模式: 默认是没有会创建，然后刷新其中的数据内容
        Files.writeString(dataPath, appendData, StandardOpenOption.APPEND);
    }

    /**
     * TODO. 创建程序需要的默认文件
     * Create temporary file in OS's default temporary file directory
     * 在不同的OS，默认的目录不同
     * C:\Users\Username\AppData\Local\Temp\myapp15961596656467916.appext Java会自动生成临时ID到文件的名称中
     * C:\Users\CHEN%20Tong\AppData\Local\Temp\myApp_f6a44001-3674-4140-8521-36e30af51b9a.log 实际生成临时日志文件
     * 1. 上面路径始终和File System相关联，可通过提供(Path dir,,,)参数创建在不同位置
     * 2. 也可以直接创建临时目录 Files.createTempDirectory()
     */
    private void createTemporaryFileInOS() {
        try {
            Path tempFile = Files.createTempFile("myapp", ".appext");
            Path absoluteFilepath = tempFile.toAbsolutePath();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
