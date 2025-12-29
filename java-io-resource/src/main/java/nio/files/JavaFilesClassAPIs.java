package nio.files;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;

// TODO. 测试文件的相关属性，文件的创建，复制，移动，删除
public class JavaFilesClassAPIs {

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

    // TODO. createDirectories()当目录存在时不会抛出异常
    private static void testCreateFileAndFolder() throws IOException {
        Path newFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        Files.createFile(newFile);

        // 如果指定路径的目录存在则会抛出异常
        Path createFolder = FileSystems.getDefault().getPath("TestFolder");
        Files.createDirectory(createFolder);

        Path createFolders = FileSystems.getDefault().getPath("Examples", "Dir2\\Dir3\\Dir4");
        Files.createDirectories(createFolders);
    }

    private static void testCopyFolderAndFile() throws IOException {
        Path sourceFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        Path copyFile = FileSystems.getDefault().getPath("WorkFolder", "textCopy.txt");

        // REPLACE_EXISTING 如果文件已经存在，则覆盖
        Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
    }

    // TODO. 使用move()重命名, 保证在同一个文件夹下面
    private static void testMove() throws IOException {
        Path sourceFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        Path renameFile = FileSystems.getDefault().getPath("WorkFolder", "text2.txt");
        // 目标文件必须提供完整路径
        Path moveFile = FileSystems.getDefault().getPath("WorkFolder", "SubFolder", "textCopy.txt");
        Files.move(sourceFile, moveFile);
        Files.move(sourceFile, renameFile);
    }

    // TODO. 先判断再删除, 只能删除空的目录
    private static void testDelete() throws IOException {
        Path sourceFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        Files.delete(sourceFile);
        Files.deleteIfExists(sourceFile);
    }

    // TODO. 递归删除非空目录中所有文件
    private static void testDeleteFolder(Path path) throws IOException {
        if (!Files.exists(path)) {
            return;
        }
        Files.walk(path)
            .sorted(Comparator.reverseOrder()) // 先删子文件
            .forEach(p -> {
                try {
                    Files.delete(p);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }
}
