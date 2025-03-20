package JavaIO.io;

import java.io.*;

// TODO. IO文件路径系统: 基于文件相对位置和绝对位置来加载
// - 相对路径: 以非/开头的路径，是项目根目录的相对路径
// - 绝对路径: 以/开头的路径，或系统磁盘的全路径表示
public class JavaFileLocation {

    // The / at the start will make the path absolute instead of relative.
    private static String fileLocation = "WorkFolder/location.txt";
    private static String fileLocationAbsolute = "/WorkFolder/location.txt";
    private static String fileLocationFull = "C:/WorkFolder/location.txt";

    public static void main(String[] args) throws IOException {
        try (FileReader fileReader = new FileReader(fileLocation)) {
            // to do
        }
        try (FileWriter fileWriter = new FileWriter(fileLocation)) {
            // to do
        }

        try(FileInputStream fileInputStream = new FileInputStream(fileLocation)) {
            // to do
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileLocation)) {
            // to do
        }
    }
}
