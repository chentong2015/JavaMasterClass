package nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

// Paths类型: 提供Path路径的静态方法
public class JavaPathsClass {

    public static void main(String[] args) throws IOException {
        // 获取绝对路径下的文件Path
        Path outsideFile = Paths.get("C:\\Test\\JavaUnitTestExceptions.txt");
        System.out.println(outsideFile);

        // 获取当前项目路径下的文件，自动适配不同OS的separator
        Path currentFolder = Paths.get(".");
        Path txtFile = Paths.get("WorkFolder/SubFolder/text1.txt");
        Path txtFile1 = Paths.get(".", "WorkFolder", "SubFolder", "test1.txt");

        // normalize()去掉路径中无效的"."目录
        String folder ="WorkFolder\\.\\";
        String fullPath = Paths.get(folder).toAbsolutePath().normalize().toString();
        System.out.println(fullPath);

        // 获取文件的绝对完整路径
        // A canonical pathname is both absolute and unique.
        String filePath = new File(currentFolder.toAbsolutePath().toString()).getCanonicalPath();
        Path absolutedPathFilePath = Paths.get(filePath);
    }

    // TODO. 组合解析完整的Path路径
    private static void combinePath() {
        Path parentPath = Paths.get("\\Examples");
        Path childPath = Paths.get("dir\\file.txt");
        Path fullFilePath = parentPath.resolve(childPath);
        System.out.println(fullFilePath);
    }
}
