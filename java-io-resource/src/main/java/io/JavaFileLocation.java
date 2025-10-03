package io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

// TODO. IO文件路径系统: 基于文件相对位置和绝对位置来加载
// - 相对路径: 以非/开头的路径，是项目根目录的相对路径
// - 绝对路径: 以/开头的路径，或系统磁盘的全路径表示
public class JavaFileLocation {

    // TODO. 下面定义文件的相对路径(相对项目根目录下的路径)
    private static String fileLocation = "WorkFolder/location.txt";

    // TODO. 下面定义文件的绝对路径
    private static String fileLocationAbsolute = "/WorkFolder/location.txt";
    private static String fileLocationFull = "C:/WorkFolder/location.txt";

    // 绝对路径使用效果
    // "\\Example\\file.txt" -> "C:\Example\file.txt"
    public static void main(String[] args) throws IOException {
        // 指向的相对文件如果不存在，则创建
        File file = new File("\\Example\\file.txt");
        System.out.println(file.getAbsolutePath());

        File parent = new File("\\Examples");

        // 同时等效于Paths.get("\\Examples", "dir\\file.txt")
        File fullFile = new File("\\Examples", "dir\\file.txt");

        // 等效于FileSystems.getDefault().getPath()中的组合路径
        File resolvedFile = new File(parent, "dir\\file.txt");
    }
}
