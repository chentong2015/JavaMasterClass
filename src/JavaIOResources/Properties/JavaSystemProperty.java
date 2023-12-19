package JavaIOResources.Properties;

// 获取Java启动配置的全局变量值(参数), 解耦程序运行时的参数
public class JavaSystemProperty {

    // 系统属性参数设置, 支持参数序列
    // Edit Configuration > VM Options > -Dproperty1=test -Dproperty2="chen tong"
    public static void main(String[] args) {
        System.out.println(System.getProperty("property1"));
        System.out.println(System.getProperty("property2"));
    }
}
