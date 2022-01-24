package JavaIO.NIOPackage.zip_file_system;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1. Zip file is a file in the default file system
// 2. The content of zip is separate file system, a zip file system
public class BaseZipFileSystem {

    // zipPath = Paths.get("myData.zip")
    // 这里创建处理的ZipFS下项目的主路径下面
    private static FileSystem openZip(Path zipPath) throws IOException, URISyntaxException {
        // 提供File System的配置参数
        Map<String, String> env = new HashMap<>();
        env.put("capacity", "16G");
        env.put("blockSize", "4k");
        URI uriFs = URI.create("memory:///?name=logfs");
        FileSystem fs = FileSystems.newFileSystem(uriFs, env);

        Map<String, String> providerProps = new HashMap<>();
        providerProps.put("create", "true");
        URI uri = new URI("jar:file", zipPath.toUri().getPath(), null);
        FileSystem zipFileSystem = FileSystems.newFileSystem(uri, providerProps);
        return zipFileSystem;
    }

    private static void copyToZip(FileSystem zipFileSystem) throws IOException {
        Path sourceFile = Paths.get("file1.txt");
        Path desFile = zipFileSystem.getPath("/file1_copied.txt");
        Files.copy(sourceFile, desFile, StandardCopyOption.REPLACE_EXISTING);
    }

    private static void writeToFileInZip1(FileSystem zipFileSystem, String[] data) throws IOException {
        Path filePath = zipFileSystem.getPath("newFile1.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (String d : data) {
                writer.write(d);
                writer.newLine();
            }
        }
    }

    // 直接通过Files.write()往Zip File System中写入指定的文件
    private static void writeToFileInZip2(FileSystem zipFileSystem, String[] data) throws IOException {
        List<String> listData = Arrays.asList(data);
        Path filePath = zipFileSystem.getPath("newFile2.txt");
        Files.write(filePath, listData, Charset.defaultCharset(), StandardOpenOption.CREATE);
    }
}
