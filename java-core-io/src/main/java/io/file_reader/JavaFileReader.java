package io.file_reader;

import java.io.FileReader;
import java.io.IOException;

public class JavaFileReader {

    // 从FileSystem文件系统读取
    private static String filepath = "java-core-io/folder/locations.txt";

    // 基于Byte依次读取指定字节长度的数据
    public static void main(String[] args) throws IOException {
       try (FileReader fileReader = new FileReader(filepath)) {
           int length;
           char[] buff = new char[8];
           while ((length = fileReader.read(buff)) >= 0) {
               System.out.println(new String(buff, 0, length));
           }
       }
    }
}
