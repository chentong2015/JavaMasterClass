package JavaIOResources.NIOPackage.fileTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class NioFileTree {

    private final Path directory = FileSystems.getDefault().getPath("FileTree\\SubFolder");

    /**
     * 1. DirectoryStream extends Closeable, Iterable<T> 这里返回的Stream是可以被迭代的
     * 2. glob参数：正则表达式的匹配, 针对文件的名称，而非file attributes
     */
    private void readExistingDirectoryContents() throws IOException, DirectoryIteratorException {
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, "*.java")) {
            for (Path file : contents) {
                boolean isDirectory = Files.isDirectory(file);
                boolean isRegularFile = Files.isRegularFile(file);
                System.out.println(file.getFileName());
            }
        }
    }

    // 自定义DirectoryStream.Filter过滤的规则和条件
    private void testFilterDirectoryContents() throws IOException, DirectoryIteratorException {
        DirectoryStream.Filter<Path> myFilter = Files::isRegularFile;
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, myFilter)) {
        }
    }

    // getSeparator() 兼容不同OS的path路径的分割符
    private void testFileSystemSeparators() {
        String ioSeparator = File.separator; // java.io.File
        String noiSeparator = FileSystems.getDefault().getSeparator(); // java.noi.file
        Path directory = FileSystems.getDefault().getPath("FileTree" + noiSeparator + "SubFolder");
    }
}
