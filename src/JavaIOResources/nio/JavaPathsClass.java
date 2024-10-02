package JavaIOResources.nio;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Paths类型: 提供Path路径的静态方法
public class JavaPathsClass {

    private static void testFileSystems() throws IOException {
        // 获取绝对路径下的项目外的文件路径
        Path outsideFile = Paths.get("C:\\Test\\JavaUnitTestExceptions.txt");

        // 获取拿到当前工作路径(项目主目录)，自动适配不同OS的separator
        Path currentFolder = Paths.get(".");
        Path insideFile = Paths.get(".", "WorkFolder", "SubFolder", "test1.txt");

        // normalize()去掉路径中无效的"."目录
        Path normalizedPath = currentFolder.normalize();

        // 获取文件的绝对完整路径
        Path absolutedPath = normalizedPath.toAbsolutePath();
        Path absolutedPathFilePath = Paths.get(new File(normalizedPath.toString()).getCanonicalPath());
    }

    // TODO. Path Traversal Vulnerability 路径遍历漏洞
    // 由于Path路径可以通过".."遍历到上层目录，导致遍历到系统中敏感文件(/etc/passwd)
    // 1. 清除Path路径中无效的".."
    // 2. 判断Path的CanonicalPath绝对路径是否以特定的目录开头
    public static void main(String[] args) throws URISyntaxException, IOException {
        Path path = Paths.get(".", "WorkFolder", "SubFolder", "test1.txt");
        String result = Files.readString(path);
        System.out.println(result);

        Path path1 = Paths.get(".", "..",  ".", ".", "..", "work_password", "password.txt");
        Path pathFormatted = Paths.get(cleanPath(path1.toString()));
        pathFormatted = pathFormatted.toAbsolutePath();

        byte[] fileBytes = Files.readAllBytes(pathFormatted);
        String result1 = new String(fileBytes, StandardCharsets.UTF_8);
        System.out.println(result1);
    }

    // Removes the path traversing elements from the given path (../)
    public static String cleanPath(String path) throws IOException {
        path = Stream.of(path.split("[/\\\\]"))
                .filter(str -> !"..".equals(str))
                .collect(Collectors.joining(File.separator));
        System.out.println("Cleaned relative path: " + path);
        return path;
    }
}
