package JavaIOResource.properties;

import java.util.Properties;
import java.util.Set;

// Property辅助类用于判断和检测自定义的属性配置是否合规
public class PropertiesValidator {

    private static final String ENABLE_LOG = "enable_log";

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
