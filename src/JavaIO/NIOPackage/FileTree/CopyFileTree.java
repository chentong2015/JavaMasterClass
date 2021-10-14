package JavaIO.NIOPackage.FileTree;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFileTree extends SimpleFileVisitor<Path> {

    private Path sourceRoot;
    private Path targetRoot;

    public CopyFileTree(Path sourceRoot, Path targetRoot) {
        this.sourceRoot = sourceRoot;
        this.targetRoot = targetRoot;
    }

    // 1. 目录的拷贝, 创建新的文件夹, 注意结果目录是否已经存在
    // 2. FileVisitResult.SKIP_SUBTREE:
    //    Continue without visiting the entries in this directory 只在该方法下有效 !!
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        Path relativePath = sourceRoot.relativize(dir);
        System.out.println("Relative path is = " + relativePath.toAbsolutePath());
        Path copyDir = targetRoot.resolve(relativePath);
        try {
            Files.copy(dir, copyDir);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        Path relativePath = sourceRoot.relativize(file);
        Path copyFile = targetRoot.resolve(relativePath);
        try {
            Files.copy(file, copyFile);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error accessing file:" + file.toAbsolutePath() + " " + exc.getMessage());
        return FileVisitResult.CONTINUE;
    }
}
