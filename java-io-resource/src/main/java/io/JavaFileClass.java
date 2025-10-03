package io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaFileClass {

    // TODO. 基于OS系统的默认文件分隔符
    // On UNIX systems the value of this field is '/';
    // On Microsoft Windows systems it is '\\'
    private static String systemSeparator = File.separator;

    public static void main(String[] args) {
        File workDirectory = new File("").getAbsoluteFile(); // "" 表示传递当前工作目录
        File workingDirectory = workDirectory.getAbsoluteFile();

        File workFolder = new File(workingDirectory, "WorkFolder");
        String[] contents = workFolder.list();
        for (String content : contents) {
            System.out.println(content);
        }

        File[] listFiles = workDirectory.listFiles();
        for (File file : listFiles) {
            System.out.println(file.getName());
            Path convertedPath = file.toPath();  // Map File to java NIO Path !!
        }
    }
}
