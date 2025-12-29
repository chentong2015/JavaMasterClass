package nio.filesystem.file_storage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// 包含工作目录的文件系统上下文
public class FileSystemStorageContext extends FileStorageContext {

    private final Path workingDirectory;
    private final URI uri;

    public FileSystemStorageContext(Path workingDirectory, boolean cleanUpOnClose) {
        super(cleanUpOnClose);
        this.workingDirectory = Paths.get(normalizeDirectoryPath(workingDirectory.toString(), true));
        this.uri = this.workingDirectory.toUri();
    }

    protected void cleanup() {
        if (this.isCleanUpOnClose() && this.workingDirectory != null) {
            try {
                Files.deleteIfExists(this.workingDirectory.toAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String normalizeDirectoryPath(String path, boolean createIfNotExists) {
        String var10000 = normalizeFilePath(path);
        String dir = var10000 + File.separator;
        if (createIfNotExists) {
            createDirectoryIfNotExists(dir);
        }
        return dir;
    }

    public static String normalizeFilePath(String path) {
        if (path.startsWith("classpath:")) {
            return path;
        }
        if (path.startsWith("file:")) {
            path = path.replaceFirst("file:", "");
        }
        Path normalize = Paths.get(path).toAbsolutePath().normalize();
        try {
            normalize = normalize.toRealPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return normalize.toString();
    }

    private static void createDirectoryIfNotExists(String path) {
        if (path.startsWith("classpath:")) {
            return;
        }
        try {
            File directory = new File(path);
            directory.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public URI getURI() {
        return this.uri;
    }

    public String getWorkingDirectory() {
        return this.workingDirectory.toString();
    }
}
