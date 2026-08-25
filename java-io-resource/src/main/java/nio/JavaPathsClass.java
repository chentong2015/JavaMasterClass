package nio;

import java.io.IOException;
import java.nio.file.*;

// Paths类型: 提供Path路径的静态方法
public class JavaPathsClass {

    public static void main(String[] args) throws IOException {
        // TODO. Paths.get("") 获取当前项目路径
        Path currentFolder = Paths.get("");
        System.out.println(currentFolder.toAbsolutePath()); // 获取绝对路径

        Path txtFile = Paths.get("java-io-resource/folder/SubFolder/text1.txt");
        Path txtFile1 = Paths.get("java-io-resource", "folder", "SubFolder", "test1.txt");
        System.out.println(txtFile.toAbsolutePath());
        System.out.println(txtFile1.toAbsolutePath());

        // TODO. 定位当前项目路径下的相对目录
        Path baseFolder = Path.of("java-io-resource/folder");
        System.out.println(Files.exists(baseFolder));

        // normalize()去掉路径中无效的"."目录
        String folder ="java-io-resource\\folder\\.\\";
        Path fullPath = Paths.get(folder).toAbsolutePath().normalize();
        System.out.println(fullPath);

        // 获取绝对路径下的文件Path
        Path outsideFile = Paths.get("C:\\Test\\JavaUnitTestExceptions.txt");
        System.out.println(outsideFile);

        // TODO. resolve构建完整路径 -> "C:\Examples\dir\file.txt"
        Path parentPath = Paths.get("\\Examples");
        Path childPath = Paths.get("dir\\file.txt");
        Path fullFilePath = parentPath.resolve(childPath);
        System.out.println(fullFilePath.toAbsolutePath());
    }
}