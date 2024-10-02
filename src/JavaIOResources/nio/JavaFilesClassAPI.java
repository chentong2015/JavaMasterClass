package JavaIOResources.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

// TODO. 测试文件的相关属性，文件的创建，复制，移动，删除
public class JavaFilesClassAPI {

    public static void main(String[] args) throws IOException {
        Path checkFolder = FileSystems.getDefault().getPath("WorkFolder");
        boolean isExists = Files.exists(checkFolder);
        boolean isNotExists = Files.notExists(checkFolder);

        Path filepath = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        long fileSize = Files.size(filepath);
        boolean isReadable = Files.isReadable(filepath);
        boolean isExecutable = Files.isExecutable(filepath);

        // 2020-01-02T02:06:02Z 与时区有关
        FileTime lastModifiedTime = Files.getLastModifiedTime(filepath);

        // 拿到文件的元数据信息和参数metadata
        BasicFileAttributes attributes = Files.readAttributes(filepath, BasicFileAttributes.class);
        FileTime creationTime = attributes.creationTime();
        boolean isFolder = attributes.isDirectory();
    }

    private static void testCreateFileAndFolder() throws IOException {
        Path newFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        Files.createFile(newFile);

        Path createFolder = FileSystems.getDefault().getPath("TestFolder");
        Files.createDirectory(createFolder);

        // 提供一个Path路径同时创建多个目录
        Path createFolders = FileSystems.getDefault().getPath("Examples", "Dir2\\Dir3\\Dir4");
        Files.createDirectories(createFolders);
    }

    private static void testCopyFolderAndFile() {
        Path sourceFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        Path copyFile = FileSystems.getDefault().getPath("WorkFolder", "textCopy.txt");
        try {
            // REPLACE_EXISTING 如果文件已经存在，则覆盖
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // 使用move()重命名, 保证在同一个文件夹下面
    private static void testMove() {
        Path sourceFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        Path renameFile = FileSystems.getDefault().getPath("WorkFolder", "text2.txt");
        // 目标文件必须提供完整路径
        Path moveFile = FileSystems.getDefault().getPath("WorkFolder", "SubFolder", "textCopy.txt");
        try {
            Files.move(sourceFile, moveFile);
            Files.move(sourceFile, renameFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // 先判断再删除, 只能删除空的目录
    private static void testDelete() {
        Path sourceFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        try {
            Files.delete(sourceFile);
            Files.deleteIfExists(sourceFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
