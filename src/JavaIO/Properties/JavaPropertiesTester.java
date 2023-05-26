package JavaIO.Properties;

import JavaIO.resources.JavaResourcesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class JavaPropertiesTester {

    // TODO. 加载xml成properties必须满足properties.dtd xml文件定义和约束
    private static final String PROPERTIES_FILE = "JavaIO/Properties/config.properties";
    private static final String XML_FILE = "JavaIO/Properties/config.xml";

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
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

    // TODO. PropertyResourceBundle从classpath路径下或者指定名称的.properties文件
    // 属性配置文件可以位于当前项目Classpath或者依赖项目的Classpath路径下
    public void testPropertyResourceBundle() {
        ResourceBundle resourceBundle = PropertyResourceBundle.getBundle("property file path");
        String server = resourceBundle.getString("SERVER.NAME");
        int port = Integer.parseInt(resourceBundle.getString("PORT"));
    }
}
