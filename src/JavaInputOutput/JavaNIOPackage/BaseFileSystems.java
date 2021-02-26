package JavaInputOutput.JavaNIOPackage;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

// FileSystem 文件系统：针对于系统文件和目录的操作 > java.noi.file       ====> C#区别: 通过File, Directory两种类型处理系统文件 !!
// Path file 该路径指定系统文件 File
// Path dir 该路径指定系统目录 Directory
// Delimiter 路径中的分割字符 windows -> \ 反斜杠; MacOS (based in Unix), Linux, Unix -> /
public class BaseFileSystems {

    // FileSystems.getDefault() 返回当前的工作目录(项目主目录)          ====> C#区别: Directory.GetCurrentDirectory(); 主工作目录
    // FileSystems.getDefault().getPath("file.txt"); 目录路径再结合文件相对路径
    // Paths.get("C:\\JavaUnitTestExceptions.test\\JavaUnitTestExceptions.test.txt"); 直接获取绝对路径              ====> C#区别: FileStream file = File.Open(path); 使用文件绝对路径
    private static void testFileSystems() {
        Path path = FileSystems.getDefault().getPath("locations.txt");

        // 1. 注意传递多个folder做为路径时 .和..的作用
        Path filePath = FileSystems.getDefault().getPath("WorkFolder", "SubFolder", "text.txt");
        Path filePath2 = FileSystems.getDefault().getPath(".", "WorkFolder", "..", "WorkFolder", "text.txt");
        Path filePath3 = FileSystems.getDefault().getPath("WorkFolder\\SubFolder\\text.txt");

        // 2. 对于项目路径外的文件，需要使用绝对路径
        Path outsideFile = Paths.get("C:\\Test\\JavaUnitTestExceptions.test.txt");

        // 3. 通过Paths拿到当前的工作路径
        Path currentFolder = Paths.get(".");
        // 4. normalize() 在转换成绝对路径的时候，去掉 \.\ 目录 z
        Path absolutePath = currentFolder.normalize().toAbsolutePath();
        Path insideFile = Paths.get(".", "WorkFolder", "text.txt");
    }

    /**
     * Files.readAllLines() 会将所有的数据读取到memory内存中      ====> 在数据操作上面可能比java.io缓慢，可以尝试使用别的数据文件 !!!
     * 操作Stream，可住指定对数据的解码方案
     */
    private static void testFilesReadingTextFile() throws IOException {
        Path dataPath = FileSystems.getDefault().getPath("file.txt");
        List<String> lines = Files.readAllLines(dataPath); // 默认的读取数据，解码方案是UTF_8.INSTANCE !!
    }

    /**
     * 1. Files.write()方法所执行的操作是相互独立的：打开文件，写入数据，关闭文件  ====> 可以使用StringBuilder来一次性写入更多的数据
     * 2. 需要对数据进行编码，然后写入文件中
     */
    private static void testFilesWritingTextFile() throws IOException {
        Path dataPath = FileSystems.getDefault().getPath("file.txt");
        String appendData = "\nline 4";
        // 将string转成写入文件的字节; 指定编码的格式UTF_8
        Files.writeString(dataPath, appendData, StandardOpenOption.APPEND); // 指定写入的模式: 默认是没有会创建，然后刷新其中的数据内容
    }
}
