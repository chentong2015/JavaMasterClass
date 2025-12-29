package io.output_stream;

import java.io.*;

// 以File文件作为IO输出流，Write写入其中
public class JavaFileOutputStream {

    private static String filepath = "WorkFolder/location.dat";

    public static void main(String[] args) throws IOException {
        try(FileOutputStream outputStream = new FileOutputStream(filepath)) {
            outputStream.write(100);

            byte byteVal = 100;
            outputStream.write(byteVal);
            byte[] bytes = {0, 5, 19};
            outputStream.write(bytes);

            byte[] bytes2 = "test".getBytes();
            outputStream.write(bytes2);
        }
    }
}
