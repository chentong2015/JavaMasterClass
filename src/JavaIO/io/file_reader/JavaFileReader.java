package JavaIO.io.file_reader;

import java.io.FileReader;
import java.io.IOException;

// TODO: Try-With-Resources-Statement: 保证IO流的关闭
// 1. 支持多个Resources的同时声明，使用;分隔
// 2. 自动调用Closeable的close方法，确定写入的流会被关闭
public class JavaFileReader {

    private static String filepath = "WorkFolder/locations.txt";

    // 基于Byte依次读取指定字节长度的数据
    public static void main(String[] args) throws IOException {
       try (FileReader fileReader = new FileReader(filepath)) {
           int length;
           char[] buff = new char[8];
           while ((length = fileReader.read(buff)) >= 0) {
               // 只输出指定字节长度的数据
               System.out.println(new String(buff, 0, length));
           }
       }
    }
}
