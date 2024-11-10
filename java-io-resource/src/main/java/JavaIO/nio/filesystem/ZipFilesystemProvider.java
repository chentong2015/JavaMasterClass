package JavaIO.nio.filesystem;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

// 自定义的文件系统
// Zip File System: .zip or .jar
// Zip File system uses "jar:file" scheme
// https://docs.oracle.com/javase/8/docs/technotes/guides/io/fsp/zipfilesystemprovider.html
public class ZipFilesystemProvider {

    // Zip文件系统的根目录
    private static final String ROOT_FOLDER = "/";

    // TODO. 自定义创建新的空Zip文件系统，在指定的Path路径中创建
    public static void createZipFilesystems() throws IOException {
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");

        Path zipPath = FileSystems.getDefault().getPath("WorkFolder", "ZipFolder", "test_new.zip");
        try (FileSystem fs = FileSystems.newFileSystem(zipPath, env)) {
            Path externalFile = Paths.get("./WorkFolder/SubFolder/new.txt");
            Path pathInZip = fs.getPath("/new.txt");
            Files.copy(externalFile, pathInZip, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // TODO. 打开已经存在的Zip文件，遍历文件并读取文件中的内容
    public static void openZipFilesystems1() throws IOException {
        Path zipPath = FileSystems.getDefault().getPath("WorkFolder", "ZipFolder", "test.zip");
        try (FileSystem fs = FileSystems.newFileSystem(zipPath)) {
            System.out.println(fs.isOpen());
            try (Stream<Path> pathList = Files.walk(fs.getPath(ROOT_FOLDER)).map(Path::getFileName)) {
                for (Path path : pathList.toList()) {
                    System.out.println(path.toString());
                }
            }
            Path pathInZip = fs.getPath("/test1.txt");
            String content = Files.readString(pathInZip);
            System.out.println(content);
        }
    }

    // TODO. 打开已经存在的Zip文件，Copy并添加新的文件
    public static void openZipFilesystems2() throws IOException {
        Path zipPath = FileSystems.getDefault().getPath("WorkFolder", "ZipFolder", "test.zip");
        Path filePathOutside = FileSystems.getDefault().getPath("WorkFolder", "SubFolder", "new.txt");
        try (FileSystem fs = FileSystems.newFileSystem(zipPath)) {
            Path folderInZip = fs.getPath("/folder");
            Files.createDirectory(folderInZip);

            Path filePathInZip = fs.getPath("/folder/new.txt");
            Files.copy(filePathOutside, filePathInZip, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
