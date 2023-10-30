package JavaIOResources.resources;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class JavaResourceBundle {

    // PropertyResourceBundle从classpath路径下或者指定名称的.properties文件
    // 属性配置文件可以位于当前项目Classpath或者依赖项目的Classpath路径下
    public void testPropertyResourceBundle() {
        ResourceBundle resourceBundle = PropertyResourceBundle.getBundle("filepath");
        String server = resourceBundle.getString("SERVER.NAME");
        int port = Integer.parseInt(resourceBundle.getString("PORT"));
    }

    // TODO. 使用ResourceBundle替代PropertyResourceBundle
    //  .getBundle()是ResourceBundle中定义的Static方法，应该由其调用
    public void testResourceBundle() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("filename");
        String value = resourceBundle.getString("key");
        System.out.println(value);
    }

}
