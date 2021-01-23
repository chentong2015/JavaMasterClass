package JavaInputOutput.JavaNIOPackage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

// File's Attributes or metadata 拿到文件的元数据信息和参数         ====> C#区别: FileAttributes fileAttributes = File.GetAttributes("path")
public class JavaNIOFileClassAttributes {

    private void testFileAttributes() {
        Path filepath = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        try {
            long fileSize = Files.size(filepath);
            FileTime lastModifiedTime = Files.getLastModifiedTime(filepath); // 2020-01-02T02:06:02Z 与时区有关

            // 一次性将文件的信息读取出来
            BasicFileAttributes attributes = Files.readAttributes(filepath, BasicFileAttributes.class);
            FileTime creationTime = attributes.creationTime();
            boolean isFolder = attributes.isDirectory();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
