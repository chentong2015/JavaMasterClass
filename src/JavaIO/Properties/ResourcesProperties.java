package JavaIO.Properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourcesProperties {

    // 1. FileSystem路径: 包含package的完整文件路径
    // 2. Classpath路径: /resources下的标准路径
    private static final String PROPERTIES_FILE = "JavaIO/Properties/config.properties";

    /**
     * Properties类表示一组持久的属性, 列表中的每个键及其对应的值都是一个字符串
     * 可以将属性保存到流或从流加载(InputStream)
     */
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);
        try {
            Properties properties = new Properties();
            properties.load(propertiesFile);
            System.out.println(properties.getProperty("url"));
            System.out.println(properties.getProperty("driver"));
            System.out.println(properties.getProperty("username"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO. 资源文件路径需要和this.getClass()当前类的路径一致(package)
    public void testGetResources() {
        InputStream inputStream = this.getClass().getResourceAsStream("test.json");
    }
}