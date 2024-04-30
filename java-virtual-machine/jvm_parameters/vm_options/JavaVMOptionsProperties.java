import java.nio.charset.Charset;

// 获取Java启动配置的全局变量值(参数), 解耦程序运行时的参数
public class JavaVMOptionsProperties {

    private static final String FILE_ENCODING_UTF_8 = "UTF-8";

    // 系统属性参数设置, 支持参数序列
    // Edit Configuration > VM Options > -Dproperty1=test -Dproperty2="chen tong"
    public static void main(String[] args) {
        System.out.println(System.getProperty("property1"));
        System.out.println(System.getProperty("property2"));

        // -Dfile.encoding="UTF-8" 特殊的VM参数属性
        // 可以设置到OS系统的环境变量，在程序执行时自动判断
        if (Charset.defaultCharset().toString().equals(FILE_ENCODING_UTF_8)) {
            System.out.println("Set encoding UTF-8");
        }
    }
}
