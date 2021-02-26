package OtherTech.JavaLogging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// 学习，测试第三方代码或者类库: 整洁的边界
// TODO: https://howtodoinjava.com/log4j2/log4j2-consoleappender-example/
public class BaseLogging {

    private static Logger logger = LogManager.getLogger(BaseLogging.class.getName());

    public static void main(String[] args) {
        // PropertyConfigurator.configure("log4j2.xml");
        logger.info("logging info");
        logger.debug("logging debug");
    }
}
