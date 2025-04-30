package nio;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Paths类型: 提供Path路径的静态方法
public class JavaPathsClass {

    public static void main(String[] args) throws IOException {
        // 获取绝对路径下的文件Path
        Path outsideFile = Paths.get("C:\\Test\\JavaUnitTestExceptions.txt");

        // 获取当前项目路径下的文件，自动适配不同OS的separator
        Path currentFolder = Paths.get(".");
        Path txtFile = Paths.get("WorkFolder/SubFolder/text1.txt");
        Path txtFile1 = Paths.get(".", "WorkFolder", "SubFolder", "test1.txt");

        // normalize()去掉路径中无效的"."目录
        String folder ="WorkFolder\\.\\";
        String fullPath = Paths.get(folder).toAbsolutePath().normalize().toString();
        System.out.println(fullPath);

        // 获取文件的绝对完整路径
        Path absolutedPath = currentFolder.toAbsolutePath();

        // A canonical pathname is both absolute and unique.
        String filePath = new File(absolutedPath.toString()).getCanonicalPath();
        Path absolutedPathFilePath = Paths.get(filePath);
    }
}
