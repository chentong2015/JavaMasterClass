package io;

import java.io.*;

// TODO. IO文件路径系统: 基于文件相对位置和绝对位置来加载
// - 相对路径: 以非/开头的路径，是项目根目录的相对路径
// - 绝对路径: 以/开头的路径，或系统磁盘的全路径表示
public class JavaFileLocation {

    // TODO. 定义文件的相对路径(相对项目根目录下的路径)
    private static String relativePath = "java-io-resource/folder/location.txt";

    // TODO. 定义文件的绝对路径 -> "C:/java-io-resource/folder/location.txt"
    private static String absolutePath = "/java-io-resource/folder/location.txt";
    private static String absolutePathFull = "C:/Work/Work My Projects/Java Master/JavaMasterClass/java-io-resource/folder/location.txt";

    public static void main(String[] args) throws IOException {
        File file = new File("\\Example\\file.txt");
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath()); // "C:\Example\file.txt"

        File parent = new File("\\Examples");
        File resolvedFile = new File(parent, "dir\\file.txt");
        System.out.println(resolvedFile.getAbsoluteFile()); // C:\Examples\dir\file.txt

        // 同时等效于Paths.get("\\Examples", "dir\\file.txt")
        File fullFile = new File("\\Examples", "dir\\file.txt");
        System.out.println(fullFile.getAbsoluteFile()); // C:\Examples\dir\file.txt
    }
}
