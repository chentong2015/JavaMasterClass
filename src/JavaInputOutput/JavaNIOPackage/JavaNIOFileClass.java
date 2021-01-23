package JavaInputOutput.JavaNIOPackage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * java.noi.File解决了java.io.File类型所出现的问题  ===> 封装在类型java.nio.file.Files包含处理系统文件的基本方法
 */
public class JavaNIOFileClass {

    private static void testCreateFileAndFolder() {
        try {
            Path newFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
            Files.createFile(newFile);
            Path createFolder = FileSystems.getDefault().getPath("TestFolder");
            Files.createDirectory(createFolder);
            Path createFolders = FileSystems.getDefault().getPath("Examples", "Dir2\\Dir3\\Dir4");
            Files.createDirectories(createFolders); // 同时创建多个目录，可以只提供一个path路径
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // public static boolean exists(Path path, LinkOption... options)
    // By default symbolic links are followed, 可以自定义成LinkOption.NOFOLLOW_LINKS ==> 指定验证Link链接到的文件
    private static void testFileMethods() {
        Path checkFolder = FileSystems.getDefault().getPath("WorkFolder");
        boolean isExists = Files.exists(checkFolder);
        boolean isNotExists = Files.notExists(checkFolder);
        boolean isReadable = Files.isReadable(checkFolder);
        boolean isExecutable = Files.isExecutable(checkFolder);
    }

    private static void testCopyFolderAndFile() {
        Path sourceFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        Path copyFile = FileSystems.getDefault().getPath("WorkFolder", "textCopy.txt");
        try {
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING); // 如果文件已经存在，则覆盖
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // 1. 使用move()重命名, 保证在同一个文件夹下面 !!
    private static void testMove() {
        Path sourceFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        Path renameFile = FileSystems.getDefault().getPath("WorkFolder", "text2.txt");
        // 目的文件必须提供完整路径 full path !!
        Path moveFile = FileSystems.getDefault().getPath("WorkFolder", "SubFolder", "textCopy.txt");
        try {
            Files.move(sourceFile, moveFile);
            Files.move(sourceFile, renameFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // 1. 先判断再删除, 只能删除空的目录
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
