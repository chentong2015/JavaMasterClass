package nio;

import java.nio.file.*;

// TODO. FileSystem文件系统：针对于系统文件和目录的操作
// FileSystem identified by URIs
//  Path file 该路径指定系统文件 File
//  Path dir  该路径指定系统目录 Directory
//  Delimiter 路径中的分割字符 windows -> \; Mac OS, Linux, Unix -> /
public class JavaFilesystems {

    // TODO. FileSystems获取OS系统默认分隔符
    private static String separator = FileSystems.getDefault().getSeparator();

    // TODO. FileSystems.getDefault() 返回当前工作目录(项目主目录)
    public static void main(String[] args) {
        // 获取Root顶级目录(C:\, D:\)
        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for (Path path : rootPaths) {
            System.out.println(path);
        }

        Path folder = FileSystems.getDefault().getPath("java-io-resource/folder" + separator + "SubFolder");
        System.out.println(Files.exists(folder));

        Path filePath1 = FileSystems.getDefault().getPath("java-io-resource/folder", "SubFolder", "test1.txt");
        System.out.println(Files.exists(filePath1));

        // 结合'.'和'..'共同定位文件的Path路径
        Path filePath2 = FileSystems.getDefault().getPath(".", "java-io-resource/folder", "..", "java-io-resource/folder", "text.txt");
        System.out.println(Files.exists(filePath2));

        Path filepath3 = FileSystems.getDefault().getPath("java-io-resource/folder/locations.txt");
        Path filePath4 = FileSystems.getDefault().getPath("java-io-resource\\folder\\SubFolder\\text.txt");
    }
}
