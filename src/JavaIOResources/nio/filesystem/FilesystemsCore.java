package JavaIOResources.nio.filesystem;

import java.nio.file.*;

// FileSystem 文件系统：针对于系统文件和目录的操作
// FileSystem identified by URIs
//  Path file 该路径指定系统文件 File
//  Path dir  该路径指定系统目录 Directory
//  Delimiter 路径中的分割字符 windows -> \; Mac OS, Linux, Unix -> /
public class FilesystemsCore {

    // TODO. FileSystems.getDefault() 返回当前工作目录(项目主目录)
    public static void testFileSystems() {
        Path folder = FileSystems.getDefault().getPath("FileTree" + getOsSeparator() + "SubFolder");
        Path filepath = FileSystems.getDefault().getPath("locations.txt");

        // 传递多个folder做为路径时注意.和..的作用
        Path filePath1 = FileSystems.getDefault().getPath("WorkFolder", "SubFolder", "text.txt");
        Path filePath2 = FileSystems.getDefault().getPath(".", "WorkFolder", "..", "WorkFolder", "text.txt");
        Path filePath3 = FileSystems.getDefault().getPath("WorkFolder\\SubFolder\\text.txt");

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
