package system_properties;

import java.net.URL;

// 通过系统变量全局参数的配置, 解耦程序运行时的参数
// - 获取自定义属性设置
// - 获取系统定义的配置属性
// https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
public class JavaSystemProperties {

    public static void main(String[] args) {
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(System.getProperty("java.version"));

        // TODO. 返回包含class的所有路径
        // Path used to find directories and JAR archives containing class files.
        System.out.println(System.getProperty("java.class.path"));

        // TODO. 返回class被加载的完整路径
        // file:working directory/out/production/JavaMasterClass/runtime/VMOptionSystemProperties.class
        URL url = JavaSystemProperties.class.getClassLoader()
                .getResource("runtime_api/VMOptionSystemProperties.class");
        System.out.println(url);

        // TODO. 返回class被加载的根目录位置, 如果从jar中加载则会加上jar文件的路径
        // working directory/out/production/JavaMasterClass/
        // working directory/out/production/JavaMasterClass/file:...Demo.jar
        String filePath = JavaSystemProperties.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();
        System.out.println(filePath);
    }
}
