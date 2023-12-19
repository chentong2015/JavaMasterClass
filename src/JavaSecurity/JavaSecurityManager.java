package JavaSecurity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

// Java程序在运行的时候，可以从系统配置(参数)中获取安全管理器
// 通过判断是否满足权限(是否自定义设置)来判断程序是正常运行还是报错
// https://docs.oracle.com/en/java/javase/16/security/java-security-overview1.html
public class JavaSecurityManager {

    // TODO: 配置JVM参数，开启安全管理机制
    // -Djava.security.manager
    // -Djava.security.policy="D:/Software Senior/Java/JavaBasic/src/JavaSecurity/protect.policy"

    // 1. 在IO读取文件的时候，会加载安全管理器，判断文件是否有"read"权限
    //    public FileInputStream(File file) throws FileNotFoundException {
    //      String name = (file != null ? file.getPath() : null);
    //      SecurityManager security = System.getSecurityManager();
    //      if (security != null) {
    //         security.checkRead(name);
    //    }
    //
    // 2. 调用System.getProperty()方法，会获取安全管理器，判断对指定的全局属性是否有权限
    //    public static String getProperty(String key) {
    //        checkKey(key);
    //        SecurityManager sm = getSecurityManager();
    //        if (sm != null) {
    //            sm.checkPropertyAccess(key);
    //        }
    //        return props.getProperty(key);
    //    }
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("WorkFolder\\text.txt");
        InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        reader.close();

        System.out.println(System.getProperty("file.encoding"));
    }
}
