package JavaIO.path_security;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOPathTraversal {

    // TODO. Path Traversal Vulnerability 路径遍历漏洞
    // 由于Path路径可以通过".."遍历到上层目录，导致遍历到系统中敏感文件(/etc/passwd)
    // 1. 清除Path路径中无效的".."
    // 2. 判断Path的CanonicalPath绝对路径是否以特定的目录开头
    public static void main(String[] args) throws URISyntaxException, IOException {
        Path path = Paths.get(".", "WorkFolder", "SubFolder", "test1.txt");
        Path pathWrong = Paths.get(".", "..",  ".", ".", "..", "work_password", "password.txt");

        String result = Files.readString(path);
        System.out.println(result);

        Path pathFormatted = Paths.get(cleanPath(path.toString()));
        byte[] fileBytes = Files.readAllBytes(pathFormatted.toAbsolutePath());
        String result1 = new String(fileBytes, StandardCharsets.UTF_8);
        System.out.println(result1);

        final String filepath = "./../../WorkFolder/SubFolder/test1.txt";
        String cleanFilepath = cleanPath(filepath);
        System.out.println(cleanFilepath);
    }

    // Removes the path traversing elements from the given path (../)
    public static String cleanPath(String path) {
        path = Stream.of(path.split("[/\\\\]"))
                .filter(str -> !"..".equals(str))
                .collect(Collectors.joining(File.separator));
        System.out.println("Cleaned relative path: " + path);
        return path;
    }
}
