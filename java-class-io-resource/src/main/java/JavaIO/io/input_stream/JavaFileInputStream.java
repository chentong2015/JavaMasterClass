package JavaIO.io.input_stream;

import java.io.*;

// 以File文件作为IO输入流，从中Read读取数据
public class JavaFileInputStream {

    // 非常规文本文件
    private static String filepath = "WorkFolder/location.dat";

    public static void main(String[] args) throws IOException {
        try(FileInputStream fileInputStream = new FileInputStream(filepath)) {
            byte[] temp = new byte[5];
            int totalBytes = fileInputStream.read(temp);
            if (totalBytes == -1) {
                System.out.println("no more data, end of the file has been reached.");
            }

            System.out.println(totalBytes);
            System.out.println(new String(temp));
        }
    }
}
