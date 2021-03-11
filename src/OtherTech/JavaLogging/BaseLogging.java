package OtherTech.JavaLogging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TODO: 核心类库官方文档, https://howtodoinjava.com/log4j2/log4j2-consoleappender-example/
 * Logging:
 * 1. Record of specific information from a programs execution, for develop and debug
 * 2. Logging should be detailed, configurable, reliable
 * .
 * Two aspects of increasing visibility into programs 提高程序代码可视化的两个方面
 * 1. Behavior of shipped code during development
 * 2. Visibility after running the application
 * .
 * Logging use cases
 * 1. Debugging during dev
 * 2. Diagnose bugs during production
 * 3. Create data for statistical use
 * 4. Tracing access for security use
 */
public class BaseLogging {

    // 基本输出信息的方式
    // 在生产代码中，System.err可以写入到log文件中
    private void testLogging() {
        System.out.println("logging");
        System.err.println("error");
        // e.printStackTrace();
    }

    private static Logger logger = LogManager.getLogger(BaseLogging.class.getName());

    public static void main(String[] args) {
        // PropertyConfigurator.configure("log4j2.xml");
        logger.info("logging info");
        logger.debug("logging debug");
    }
}
