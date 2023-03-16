package JavaIO.resources;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

// 1. FileSystem路径: 包含package的完整文件路径
// 2. Classpath路径: /resources下资源文件将会自动生成到项目output目录中
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

    // TODO. 从classpath路径下获取指定的文件, 包括依赖的模块下的classpath路径
    public File getFileFromClasspath(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found !");
        } else {
            return new File(resource.getFile());
        }
    }
}
