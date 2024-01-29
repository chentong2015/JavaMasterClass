package JavaIOResources.Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertiesHelper {

    private static final String ENABLE_LOG = "enable_log";

    // 获取到Properties中定义的键值，判断是否合规或者存在无效数据
    public static void verifyProperties(Properties properties) throws Exception {
        Set<String> keys = properties.stringPropertyNames();
        if (!keys.contains("source_url")) {
            parserException("source_url");
        }
        checkSizeValue("min_size", properties, keys);
        checkSizeValue("max_size", properties, keys);
        if (keys.contains(ENABLE_LOG)) {
            System.setProperty(ENABLE_LOG, properties.getProperty(ENABLE_LOG));
        } else {
            System.setProperty("enable_log", "false");
        }
    }

    private static void checkSizeValue(String property, Properties properties, Set<String> keys) throws Exception {
        if (keys.contains(property) && (Integer.parseInt(String.valueOf(properties.get(property))) <= 0)) {
            parserException(property);
        } else if (!keys.contains(property)) {
            properties.setProperty(property, "100");
        }
    }

    private static void parserException(String property) throws Exception {
        throw new Exception(property + " is missing or wrong");
    }
}
