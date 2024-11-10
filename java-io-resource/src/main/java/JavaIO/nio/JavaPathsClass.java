package JavaIO.nio;

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
        Path normalizedPath = currentFolder.normalize();

        // 获取文件的绝对完整路径
        Path absolutedPath = normalizedPath.toAbsolutePath();
        Path absolutedPathFilePath = Paths.get(new File(normalizedPath.toString()).getCanonicalPath());
    }
}
