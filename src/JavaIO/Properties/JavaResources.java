package JavaIO.Properties;

import java.io.InputStream;

// 1. FileSystem路径: 包含package的完整文件路径
// 2. Classpath路径: /resources下的标准路径
public class JavaResources {

    // TODO. 在实例方法和静态方法中使用不同方式获取ClassLoader
    // 资源文件路径需要和this.getClass()当前类的路径一致(package)
    public void getResourcesByObjectMethod(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        ClassLoader classLoader1 = getClass().getClassLoader();
        InputStream inputStream = classLoader1.getResourceAsStream(fileName);
    }

    public static void getResourcesByStatic() {
        ClassLoader classLoader = JavaResources.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("folder_path");
    }
}
