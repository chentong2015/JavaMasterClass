package JavaIOResources.nio.fileTree;

import java.io.IOException;
import java.nio.file.*;

// TODO. 通过DirectoryStream来遍历执行目录下的数据
public class DirectoryStreamLoop {

    // glob参数：正则表达式的匹配, 针对文件的名称
    private void readExistingDirectoryContents() throws IOException, DirectoryIteratorException {
        Path directory = FileSystems.getDefault().getPath("FileTree\\SubFolder");
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
        Path directory = FileSystems.getDefault().getPath("FileTree\\SubFolder");
        DirectoryStream.Filter<Path> myFilter = Files::isRegularFile;
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, myFilter)) {
        }
    }


}
