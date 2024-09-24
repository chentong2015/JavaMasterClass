package JavaIOResources.nio.filesystem;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

// 根据文件的URI路径在本地创建FileSystem文件系统
// 通过文件系统来操作文件，通过系统Path路径来读取文件数据
public class FilesystemsTest {

    private static final String DELIMITER = "/";

    public String testLocalFileSystem() throws URISyntaxException {
        String cleanPath = "/file.txt";
        URL resource = this.getClass().getClassLoader().getResource(cleanPath);
        if (resource != null) {
            URI uri = resource.toURI();

            try (FileSystem zipFs = FileSystems.newFileSystem(uri, Collections.singletonMap("create", "true"))) {
                Path path = zipFs.getPath(DELIMITER + cleanPath);
                System.out.println("attempting to read {} in {}" + path + zipFs);
                return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
