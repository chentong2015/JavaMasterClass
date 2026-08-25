package io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaFileClass {

    // TODO. File获取OS系统默认分隔符
    // On UNIX systems the value of this field is '/';
    // On Microsoft Windows systems it is '\\'
    private static String systemSeparator = File.separator;

    // TODO. "" 当前工作目录, 获取目录完整路径
    public static void main(String[] args) throws IOException {
        File workDirectory = new File("").getAbsoluteFile();
        System.out.println(workDirectory);
        for (File file : workDirectory.listFiles()) {
            System.out.println(file.getName());
            Path convertedPath = file.toPath();  // Map File to java NIO Path !!
        }

        File subDirectory = new File(workDirectory, "java-io-resource/folder");
        for (String file : subDirectory.list()) {
            System.out.println(file);
        }

        // 获取文件的绝对完整路径
        // A canonical pathname is both absolute and unique.
        String filePath = new File(workDirectory.getAbsolutePath()).getCanonicalPath();
        System.out.println(filePath);
    }
}
