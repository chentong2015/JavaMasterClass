package io;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;

// TODO. 从项目的/src/main/resources目录下加载File文件
public class JavaFileResources {

    public static void main(String[] args) throws URISyntaxException {
        // 1. 获取文件的全路径后再构建File文件
        Path path = FileSystems.getDefault().getPath("java-io-resource",  "src", "main", "resources", "entity.xml");
        File file1 = new File(path.toAbsolutePath().toString());

        // 2. 通过Resource URI来创建File文件 => 推荐方式(避免模块变化造成异常)
        URL resourceUrl = JavaFileResources.class.getClassLoader().getResource("entity.xml");
        File file2 = new File(resourceUrl.toURI());

        // 3. 直接通过文件的相对路径来获取
        File file3 = new File("java-io-resource/src/main/resources/entity.xml");
    }
}
