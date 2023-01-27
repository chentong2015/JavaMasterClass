package JavaIO.Properties;

import java.io.InputStream;

// 1. FileSystem路径: 包含package的完整文件路径
// 2. Classpath路径: /resources下的标准路径
public class JavaResourcesLoader {

    // TODO. 从项目的资源目录下加载文件，拿到InputStream做为读取
    // - new BufferedInputStream(inputStream)
    // - new BufferedOutputStream(outputStream)
    //
    // - new InputStreamReader(inputStream)
    // - new OutputStreamWriter(outputStream)
    public void getResourcesByObjectMethod(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);

        // 注意: 资源文件路径和this.getClass()当前类的路径一致(package)
        ClassLoader classLoader1 = getClass().getClassLoader();
        InputStream inputStream = classLoader1.getResourceAsStream(fileName);
    }

    // 区别于在实例方法中从资源路径下载文件
    public static void getResourcesByStatic() {
        ClassLoader classLoader = JavaResourcesLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("folder_path");
    }
}
