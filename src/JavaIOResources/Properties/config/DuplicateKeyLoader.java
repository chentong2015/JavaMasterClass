package JavaIOResources.Properties.config;

import java.io.InputStream;
import java.util.Properties;

// TODO. Properties无法加载相同key的不同值, 属性值会被覆盖
public class DuplicateKeyLoader {

    public static void main(String[] args) throws Exception {
        String fileName = "duplicatedKeys.cfg";
        InputStream in = DuplicateKeyLoader.class.getClassLoader().getResourceAsStream(fileName);

        Properties prop = new Properties();
        prop.load(in);

        // file2 后面的key会覆盖前面的
        System.out.println(prop.getProperty("provider"));
        System.out.println(prop.getProperty("origin"));
        System.out.println(prop.getProperty("file"));
    }

}
