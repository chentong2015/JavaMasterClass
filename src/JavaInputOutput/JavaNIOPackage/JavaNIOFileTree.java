package JavaInputOutput.JavaNIOPackage;

import JavaInputOutput.JavaNIOPackage.FileTree.CopyFileTree;
import JavaInputOutput.JavaNIOPackage.FileTree.SimpleFileTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class JavaNIOFileTree {

    private final Path directory = FileSystems.getDefault().getPath("FileTree\\SubFolder");

    /**
     * 1. DirectoryStream extends Closeable, Iterable<T> 这里返回的Stream是可以被迭代的
     * 2. glob参数：正则表达式的匹配 ==> 针对文件的名称，而非file attributes
     * TODO: 正则表达式
     * https://docs.oracle.com/javase/7/docs/api/java/nio/file/FileSystem.html#getPathMatcher(java.lang.String)
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

    private void testFilterDirectoryContents() throws IOException, DirectoryIteratorException {
        // TODO: 直接使用lambda表达式 !!!
        DirectoryStream.Filter<Path> filter1 = Files::isRegularFile;
        DirectoryStream.Filter<Path> filter2 = path -> Files.isRegularFile(path);

        // 自定义过滤的条件, 返回什么类型的文件: 使用匿名的类型, 只需要实现Filter接口的accept()方法
        DirectoryStream.Filter<Path> myFilter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isRegularFile(entry);
            }
        };
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, myFilter)) {
            // Work with the contents
        }
    }

    /**
     * 在不同的OS，文件path路径的分割符是不同的，需要区别处理  ===> 避免硬编码，实现程序的跨平台
     */
    private void testFileSystemSeparators() {
        String ioSeparator = File.separator; // java.io.File
        String noiSeparator = FileSystems.getDefault().getSeparator(); // java.noi.file
        Path directory = FileSystems.getDefault().getPath("FileTree" + noiSeparator + "SubFolder");
    }

    /**
     * 1. 遍历文件夹，从文件夹的所有文件中找到需要的文件
     * 2. 复制文件夹，需要将文件夹中的所有文件全部拷贝一份
     * 3. 以下重载的方法支持访问目录的最大层级，以及FileVisitOption.FOLLOW_LINKS是否追踪文件链接
     * Path walkFileTree(Path start,Set<FileVisitOption> options, int maxDepth, FileVisitor<? super Path> visitor)
     */
    private void testWalkFileTree() {
        Path rootFolder = FileSystems.getDefault().getPath("WorkFolder");
        try {
            Files.walkFileTree(rootFolder, new SimpleFileTree());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 使用Path类型的两个方法实现File Tree的完整拷贝 ==> 相对的路径始终是一致的  ==> 提供信息提示，是否目标的文件需要被覆盖 !!
     * sourceRoot = "FileTree\Dir1"
     * sourcePath = "FileTree\Dir1\Dir2\Test.txt"
     * targetRoot = "FileTree\Dir3\"
     * relativePath = sourceRoot.relative(sourcePath) => "\Dir2\Test.txt" 相对于root路径的相对路径
     * targetRoot.resolve(relativePath) => "FileTree\Dir3\\Dir2\Test.txt" 在目标root路径的基础上补充相对路径
     */
    private void testCopyFileTree() {
        Path sourceDir = FileSystems.getDefault().getPath("WorkFolder");
        Path targetDir = FileSystems.getDefault().getPath("WorkFolderCopy"); // 结果的总文件夹不能已经存在 !!
        try {
            Files.walkFileTree(sourceDir, new CopyFileTree(sourceDir, targetDir));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
