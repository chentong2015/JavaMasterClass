package io.input_stream;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

// 使用Reader能够方便读取Char和Str格式的数据
public class BaseInputStreamReader {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("WorkFolder/location.dat");
        testInputStreamReader(inputStream);
        inputStream.close();
    }

    // 读取指定字符长度的数据
    public static void testInputStreamReader(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] charBuff = new char[10];
        while (inputStreamReader.read(charBuff) >= 0) {
            // do something with charBuff
            System.out.print(charBuff[0]);
            System.out.print(charBuff[1]);
        }
    }

    // 支持读取line整行的数据
    public static void testBufferedReader(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            Stream<String> streams = bufferedReader.lines().skip(1);

            List<String> lines = streams.toList();
            System.out.println(lines.get(0));
            System.out.println(lines.get(1));
        }
    }
}
