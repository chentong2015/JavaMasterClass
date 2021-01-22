package JavaInputOutput.JavaNIOPackage;

// FileSystem 文件系统：针对于系统文件和目录的操作 > java.noi.file    ====> C#区别: 通过File, Directory两种类型处理系统文件 !!
// Path file 该路径指定系统文件 File
// Path dir 该路径指定系统目录 Directory
// Delimiter 路径中的分割字符 windows -> \ 反斜杠; MacOS, Unix -> /
public class BaseFileSystems {

    // FileSystems.getDefault() 返回当前的工作目录(项目主目录)       ====> C#区别: Directory.GetCurrentDirectory(); 主工作目录
    // FileSystems.getDefault().getPath("file.txt"); 目录路径再结合文件相对路径
    // Paths.get("C\\test\\test.txt"); 直接获取绝对路径            ====> C#区别:  FileStream file = File.Open(path); 使用文件绝对路径

    private static void test() {


    }
}
