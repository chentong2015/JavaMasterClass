package JavaInputOutput.JavaNIOPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// FileSystem 文件系统：针对于系统文件和目录的操作 > java.noi.file       ====> C#区别: 通过File, Directory两种类型处理系统文件 !!
// Path file 该路径指定系统文件 File
// Path dir 该路径指定系统目录 Directory
// Delimiter 路径中的分割字符 windows -> \ 反斜杠; MacOS, Unix -> /
public class BaseFileSystems {

    // FileSystems.getDefault() 返回当前的工作目录(项目主目录)          ====> C#区别: Directory.GetCurrentDirectory(); 主工作目录
    // FileSystems.getDefault().getPath("file.txt"); 目录路径再结合文件相对路径
    // Paths.get("C:\\test\\test.txt"); 直接获取绝对路径              ====> C#区别: FileStream file = File.Open(path); 使用文件绝对路径
    private static void testFileSystems() {
        Path path = FileSystems.getDefault().getPath("locations.txt");

        // 1. 注意传递多个folder做为路径时 .和..的作用
        Path filePath = FileSystems.getDefault().getPath("WorkFolder", "SubFolder", "text.txt");
        Path filePath2 = FileSystems.getDefault().getPath(".", "WorkFolder", "..", "WorkFolder", "text.txt");
        Path filePath3 = FileSystems.getDefault().getPath("WorkFolder\\SubFolder\\text.txt");

        // 2. 对于项目路径外的文件，需要使用绝对路径
        Path outsideFile = Paths.get("C:\\Test\\test.txt");

        // 3. 通过Paths拿到当前的工作路径
        Path currentFolder = Paths.get(".");
        // 4. normalize() 在转换成绝对路径的时候，去掉 \.\ 目录 z
        Path absolutePath = currentFolder.normalize().toAbsolutePath();
        Path insideFile = Paths.get(".", "WorkFolder", "text.txt");
    }

    // path参数是file文件的路径
    private static void printFile(Path path) {
        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
