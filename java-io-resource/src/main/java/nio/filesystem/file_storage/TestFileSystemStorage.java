package nio.filesystem.file_storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFileSystemStorage {

    public static void main(String[] args) throws IOException {
        String exportFolder = "C:\\Work\\Work My Projects\\Java Master\\JavaMasterClass\\WorkFolder";
        Path exportPath = Paths.get(exportFolder, "exportFolder");
        FileStorageContext fileStorageContext = createStorageContext(exportPath);

        Path outputFilePath = Paths.get(Paths.get(fileStorageContext.getURI()).toString(), "fileName.txt");
        System.out.println("outputFilePath: " + outputFilePath);

        String outputFileName = outputFilePath.toAbsolutePath().toString();
        try (FileOutputStream outputStream = new FileOutputStream(outputFileName)) {
             byte[] bytes = "this is new line".getBytes();
             outputStream.write(bytes);
        }
    }

    // 创建特定目录作为文件系统上下文
    public static FileSystemStorageContext createStorageContext(Path path) {
        try {
            Path dir = Files.createDirectories(path);
            return new FileSystemStorageContext(dir, false);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create directory");
        }
    }
}
