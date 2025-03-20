package JavaIO.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

// TODO. java.io FileXXX 将被废弃
// java.io.File 类型的问题 => 在java.noi.File类型中被解决
// 1. File.delete() 没有异常的抛出，也没有具体的错误原因
// 2. File.rename() 在不同的平台结果不同
// 3. No support for symbolic links
// 4. Cannot get metadata about a file 不能获取文件权限和安全信息
// 5. Don't perform well when working with lots of data
public class JavaFileClass {

    public static void main(String[] args) {
        File workDirectory = new File("").getAbsoluteFile(); // "" 表示传递当前工作目录
        File workingDirectory = workDirectory.getAbsoluteFile();

        File workFolder = new File(workingDirectory, "WorkFolder");
        String[] contents = workFolder.list();
        for (String content : contents) {
            System.out.println(content);
        }

        File[] listFiles = workDirectory.listFiles();
        for (File file : listFiles) {
            System.out.println(file.getName());
        }
    }

    private void mapJavaIOToJavaNIO() {
        File file = new File("\\Example\\file.txt"); // 指向的相对文件如果不存在，则创建
        Path convertedPath = file.toPath();  // Map File to java NIO Path !!

        File parent = new File("\\Examples");
        File resolvedFile = new File(parent, "dir\\file.txt");  // 等效于FileSystems.getDefault().getPath()中的组合路径
        File fullFile = new File("\\Examples", "dir\\file.txt"); // 同时等效于Paths.get("\\Examples", "dir\\file.txt")

        Path parentPath = Paths.get("\\Examples");
        Path childPath = Paths.get("dir\\file.txt");
        Path fullPath = parentPath.resolve(childPath);
    }

    // TODO. 获取基于OS系统的文件分隔符
    // The system-dependent default name-separator character.
    // On UNIX systems the value of this field is '/'; on Microsoft Windows systems it is '\\'
    public void getOsSeparator() {
        System.out.println(File.separator);
    }
}
