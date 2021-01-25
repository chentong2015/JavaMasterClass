package JavaInputOutput.JavaNIOPackage.FileTree;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 1. 想要遍历File Tree下面的所有文件，必须实现FileVisitor<T>接口
 * 2. Java提供了对FileVisitor<T>的一个默认实现SimpleFileVisitor<T>
 * ---- preVisitDirectory(T dir, BasicFileAttributes attrs) : 在访问目录之前调用
 * ---- visitFile(T file, BasicFileAttributes attrs) : It's only called for files 可以访问所有的文件，也可以显式跳过指定的文件
 * ---- visitFileFailed(T file, IOException exc)     : Can be called for files and directories 文件的访问失败, 必要的时候抛出异常
 * ---- postVisitDirectory(T dir, IOException exc)   : 在访问目录之后调用(因为异常中断), 必要的时候抛出异常 (在遍历过程中可能出现的异常)
 * 3. 通过对SimpleFileVisitor<Path>的继承，重写方法，可自定义遍历file的逻辑
 */
public class SimpleFileTree extends SimpleFileVisitor<Path> {

    // 按照迭代的访问层次，该方法的调用会优先于visitFile()的调用
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println(dir.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("Visiting... " + file.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        // 自定义处理文件访问的异常 ==> 日志输出
        System.out.println("Error accessing file " + file.toAbsolutePath() + " " + exc.getMessage());
        return FileVisitResult.CONTINUE;
    }
}