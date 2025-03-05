package JavaIOResource;

import java.io.*;
import java.net.URL;

// TODO. 通过ClassLoader加载/resources资源目录下的文件
public class JavaResourcesLoader {

    public static void main(String[] args) {
        // TODO. 返回.class文件被加载的完整路径
        // file:/target/classes/JavaIOResource/JavaResourcesLoader.class
        URL url = JavaResourcesLoader.class.getClassLoader().getResource("JavaIOResource/JavaResourcesLoader.class");
        System.out.println(url);

        // TODO. 返回.class文件被加载的根目录, 从jar中加载则会加上压缩文件路径
        // /target/JavaMasterClass/
        // /target/JavaMasterClass/file:...Demo.jar
        String filePath = JavaResourcesLoader.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();
        System.out.println(filePath);
    }

    // TODO. 1. 实例方法 -> get Resource / get Stream
    // 获取classpath资源路径下文件, 包括依赖模块classpath路径
    // 资源文件路径和this.getClass()当前类路径package加载路径一致
    public void getResourceFileFromClasspath() throws IOException {
        URL resource = this.getClass().getClassLoader().getResource("filepath");
        InputStream inputStream1 = this.getClass().getClassLoader().getResourceAsStream("filepath");
        InputStream inputStream2 = this.getClass().getClassLoader().getResourceAsStream("filepath");
        inputStream1.close();
        inputStream2.close();
    }

    // TODO. 2. 静态方法 -> get Stream: 获取classpath资源路径下文件
    public static void getResourcesByStatic() {
        InputStream inputStream = JavaResourcesLoader.class.getClassLoader().getResourceAsStream("filepath");
        System.out.println(inputStream);
    }

    // TODO. 3. 实例方法 + 静态方法 -> get Stream: 使用Thread ContextClassLoader获取classpath资源文件
    public void getResourcesByObjectMethod(String fileName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        System.out.println(inputStream);
    }
}
