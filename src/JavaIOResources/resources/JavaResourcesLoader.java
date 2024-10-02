package JavaIOResources.resources;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

// TODO. Classpath路径
// /src 项目的源资源路径
// /resources下资源文件将会自动生成到项目output目录中
public abstract class JavaResourcesLoader {

    // TODO. 实例方法获取classpath资源路径下的文件
    public static void getResourcesByStatic() {
        InputStream inputStream = JavaResourcesLoader.class.getClassLoader().getResourceAsStream("filepath");
        System.out.println(inputStream);
    }

    // TODO. 静态方法获取classpath资源路径下的文件, 包括依赖模块的classpath路径
    // 资源文件路径和this.getClass()当前类的路径一致(package)
    public File getFileFromClasspath(String filename) {
        URL resource = this.getClass().getClassLoader().getResource(filename);
        assert resource != null;
        return new File(resource.getFile());
    }

    // TODO. 直接使用Thread ContextClassLoader获取项目资源文件
    public void getResourcesByObjectMethod(String fileName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        System.out.println(inputStream);
    }

    // TODO. 设计抽象ResourceLoader API, 根据资源的不同位置进行加载
    // Classpath资源路径使用文件的相对位置
    protected abstract boolean isResourceInClasspath();

    protected abstract String getFilepath();

    protected List<String> readFileData() throws Exception {
        if (isResourceInClasspath()) {
            InputStream in = getClass().getResourceAsStream(getFilepath());
            assert in != null;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {
                return bufferedReader.lines().collect(Collectors.toList());
            }
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getFilepath()))) {
                return bufferedReader.lines().collect(Collectors.toList());
            }
        }
    }
}
