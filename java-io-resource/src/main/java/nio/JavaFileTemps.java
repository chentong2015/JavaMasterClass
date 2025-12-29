package nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// TODO. 在OS系统上创建临时文件, 不同OS的默认目录不同
// Create temporary file in OS's default temporary file directory
public class JavaFileTemps {

    // C:\Users\Username\AppData\Local\Temp\ Windows默认临时文件路径
    public static void main(String[] args) throws IOException {
        // TODO. 通过系统属性获取OS默认Temp目录
        String tempFolder = System.getProperty("java.io.tmpdir");
        System.out.println(tempFolder);

        // 默认使用前缀加随机码组成临时文件名称
        // C:\Users\chent5\AppData\Local\Temp\prefix_7776846736296699162
        Path tempDir = Files.createTempDirectory(Paths.get(tempFolder), "prefix_");
        System.out.println(tempDir.toAbsolutePath());

        // Creates an empty file in the default temporary-file directory,
        // using the given prefix and suffix to generate its name
        Path tempFile = Files.createTempFile("myapp", ".appext");
        Path tempFilepath = tempFile.toAbsolutePath();
        System.out.println(tempFilepath);
    }
}
