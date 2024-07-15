package jvm_runtime_api.system_properties;

import java.nio.charset.Charset;

public class JavaVMOptions {

    // 系统属性参数设置, 支持参数序列
    // Edit Configuration > VM Options > -Dproperty1=test -Dproperty2="chen"
    private static void testCustomProperties() {
        System.out.println(System.getProperty("property1"));
        System.out.println(System.getProperty("property2"));

        // -Dfile.encoding="UTF-8" 特殊的VM参数属性
        // 可以设置到OS系统的环境变量，在程序执行时自动判断
        if (Charset.defaultCharset().toString().equals("UTF-8")) {
            System.out.println("Set encoding UTF-8");
        }
    }
}
