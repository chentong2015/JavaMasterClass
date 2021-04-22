package JavaInputOutput.Properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseProperties {

    // 这里取的路径是包含package下的完整文件路径
    private static final String PROPERTIES_FILE = "JavaInputOutput/Properties/config.properties";

    /**
     * Properties类表示一组持久的属性,
     * 可以将属性保存到流或从流加载(InputStream), 属性列表中的每个键及其对应的值都是一个字符串
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
}
