package JavaIO.IOPackage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * import java.io.File  ==> Points to files on the file system
 * public class File implements Serializable, Comparable<File>
 * 该包下面的File类型主要存在以下的问题
 * 1. 没有异常的抛出，也没有具体的错误原因 File.delete() => boolean
 * 2. File.rename() 在不同的平台结果不同
 * 3. No support for symbolic links
 * 4. Cannot get metadata about a file 不能获取文件的权限和一些安全信息
 * 5. Don't perform well when working with lots of data
 */
public class JavaIOFileClass {
    
    private void testWorkDirectory() {
        File workDirectory = new File("").getAbsoluteFile(); // "" ==> 表示传递当前工作目录
        File workingDirectory = workDirectory.getAbsoluteFile();

        File workFolder = new File(workingDirectory, "WorkFolder");
        String[] contents = workFolder.list();
        for (int i = 0; i < contents.length; i++) {
            System.out.println(contents[i]);
        }

        File[] list = workDirectory.listFiles();
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i].getName()); // 返回文件的名称，不是全路径
        }
    }

    private void testMapJavaIOToJavaNIO() {
        File file = new File("\\Example\\file.txt"); // 指向的相对文件如果不存在，则创建
        Path convertedPath = file.toPath();  // Map File to java NIO Path !!

        File parent = new File("\\Examples");
        File resolvedFile = new File(parent, "dir\\file.txt");  // 等效于FileSystems.getDefault().getPath()中的组合路径
        File fullFile = new File("\\Examples", "dir\\file.txt"); // 同时等效于Paths.get("\\Examples", "dir\\file.txt")

        Path parentPath = Paths.get("\\Examples");
        Path childPath = Paths.get("dir\\file.txt");
        Path fullPath = parentPath.resolve(childPath);

    }
}
