package JavaInputOutput.JavaIOPackage;

public class JavaIOFileClass {

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

}
