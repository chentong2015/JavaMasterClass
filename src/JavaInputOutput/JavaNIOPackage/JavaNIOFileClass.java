package JavaInputOutput.JavaNIOPackage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * java.noi.File如何解决java.io.File类型的问题和限制 ?
 * 1. import java.nio.file.Files; 包含了文件系统处理文件的一些基本方法
 * 2. exists, copy, move, rename, delete  全是静态方法
 */
public class JavaNIOFileClass {

    private static void testFileMethods() {
        Path checkFolder = FileSystems.getDefault().getPath("WorkFolder");
        boolean isExists = Files.exists(checkFolder);
        boolean isNotExists = Files.notExists(checkFolder);

        // By default symbolic links are followed, 可以自定义成LinkOption.NOFOLLOW_LINKS ==> 指定验证Link链接到的文件
        // public static boolean exists(Path path, LinkOption... options)

        boolean isReadable = Files.isReadable(checkFolder);
        boolean isExecutable = Files.isExecutable(checkFolder);
    }

    private static void testCopyFolderAndFile() {
        Path sourceFile = FileSystems.getDefault().getPath("WorkFolder", "text.txt");
        Path copyFile = FileSystems.getDefault().getPath("WorkFolder", "textCopy.txt");
        try {
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING); // 如果文件已经存在，则覆盖

            // TODO: 复制文件夹，需要将文件夹中的所有文件全部拷贝一份 ==> File tree的问题
            sourceFile = FileSystems.getDefault().getPath("WorkFolder");
            copyFile = FileSystems.getDefault().getPath("CopyWorkFolder");
            Files.copy(sourceFile, copyFile);
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

    // 1. 可先判断再删除
    // 2. 如果是删除目录，这需要目录为空
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
