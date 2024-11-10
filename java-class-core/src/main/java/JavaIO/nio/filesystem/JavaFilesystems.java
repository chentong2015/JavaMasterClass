package JavaIO.nio.filesystem;

import java.nio.file.*;

// FileSystem 文件系统：针对于系统文件和目录的操作
// FileSystem identified by URIs
//  Path file 该路径指定系统文件 File
//  Path dir  该路径指定系统目录 Directory
//  Delimiter 路径中的分割字符 windows -> \; Mac OS, Linux, Unix -> /
public class JavaFilesystems {

    // TODO. FileSystems.getDefault() 返回当前工作目录(项目主目录)
    // 结合'.'和'..'共同定位文件的Path路径
    public static void main(String[] args) {
        Path folder = FileSystems.getDefault().getPath("WorkFolder" + getOsSeparator() + "SubFolder");
        System.out.println(Files.exists(folder));

        Path filePath1 = FileSystems.getDefault().getPath("WorkFolder", "SubFolder", "test1.txt");
        System.out.println(Files.exists(filePath1));

        Path filePath2 = FileSystems.getDefault().getPath(".", "WorkFolder", "..", "WorkFolder", "text.txt");
        System.out.println(Files.exists(filePath2));

        Path filepath3 = FileSystems.getDefault().getPath("WorkFolder/locations.txt");
        Path filePath4 = FileSystems.getDefault().getPath("WorkFolder\\SubFolder\\text.txt");

        // 获取Root顶级目录
        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for (Path path : rootPaths) {
            System.out.println(path);
        }
    }

    // TODO. 获取基于OS系统的文件分隔符
    public static String getOsSeparator() {
        String separator = FileSystems.getDefault().getSeparator();
        System.out.println(separator);
        return separator;
    }
}
