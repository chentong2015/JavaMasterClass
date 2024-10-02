package JavaIOResources.resources.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TODO. 设置或从文件中加载程序运行相关的属性
// - Property表示一组属性，键和值为字符串
// - Properties无法加载相同key的不同值，属性值会被后者覆盖
// - 加载xml成properties必须满足properties.dtd xml文件约束
public class PropertiesResources {

    private static final String PROPERTIES_FILE = "JavaIOResources/resources/properties/config.properties";
    private static final String XML_FILE = "JavaIOResources/resources/properties/config.xml";
    private static final String DUPLICATED_FILE = "JavaIOResources/resources/properties/duplicated.cfg";

    // TODO. ClassLoader会从项目的SRC路径下获取资源文件加载
    public static void main(String[] args) throws Exception {
        InputStream inputStream = PropertiesResources.class.getClassLoader().getResourceAsStream(DUPLICATED_FILE);
        Properties properties = new Properties();
        properties.load(inputStream);

        // file2 后面的key会覆盖前面的
        System.out.println(properties.getProperty("provider"));
        System.out.println(properties.getProperty("origin"));
        System.out.println(properties.getProperty("file"));

        properties.put("user", "victor");
        properties.put("password", "test");
        System.out.println(properties.getProperty("user"));

        // 如果Properties没有找到指定的key，则提供默认值作为返回
        System.out.println(properties.getProperty("label") == null);
        System.out.println(properties.getProperty("label", "my label"));
    }

    public static void loadPropertiesFile() throws IOException {
        InputStream propertiesFile = PropertiesResources.class.getClassLoader().getResourceAsStream(XML_FILE);
        Properties properties = new Properties();
        properties.load(propertiesFile);
        properties.loadFromXML(propertiesFile);

        System.out.println(properties.getProperty("url"));
        System.out.println(properties.getProperty("driver"));
        System.out.println(properties.getProperty("username"));
    }
}
