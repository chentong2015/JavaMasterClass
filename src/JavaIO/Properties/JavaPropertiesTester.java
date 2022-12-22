package JavaIO.Properties;

import java.util.Properties;

public class JavaPropertiesTester {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("user", "victor");
        properties.put("password", "test");
        System.out.println(properties.getProperty("user"));

        // 如果Properties没有找到指定的key，则提供默认值作为返回
        System.out.println(properties.getProperty("label"));
        System.out.println(properties.getProperty("label", "my label"));
    }
}
