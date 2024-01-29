package JavaIOResources.Properties;

import JavaIOResources.resources.JavaResourcesLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesDemo {

    // TODO. 加载xml成properties必须满足properties.dtd xml文件定义和约束
    private static final String PROPERTIES_FILE = "JavaIOResources/Properties/config.properties";
    private static final String XML_FILE = "JavaIOResources/Properties/config.xml";

    // 支持从文件路径加载配置属性properties
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        if (args != null && args.length > 0) {
            try (final FileInputStream fis = new FileInputStream(args[0])) {
                properties.load(fis);
            }
            if (args.length > 1) {
                System.out.println("Warning: Some command line arguments were ignored. " +
                        "This demo only accepts an optional configuration file.");
            }
        }

        properties.put("user", "victor");
        properties.put("password", "test");
        System.out.println(properties.getProperty("user"));

        // 如果Properties没有找到指定的key，则提供默认值作为返回
        System.out.println(properties.getProperty("label") == null);
        System.out.println(properties.getProperty("label", "my label"));
    }

    // Properties类表示一组持久的属性, 列表中的每个键及其对应的值都是一个字符串
    // 可以将属性保存到流或从流加载(InputStream)
    public static void loadPropertiesFile() throws IOException {
        ClassLoader classLoader = JavaResourcesLoader.class.getClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(XML_FILE);
        Properties properties = new Properties();
        // properties.load(propertiesFile);
        properties.loadFromXML(propertiesFile);

        System.out.println(properties.getProperty("url"));
        System.out.println(properties.getProperty("driver"));
        System.out.println(properties.getProperty("username"));
    }
}
