package JavaIO.io.output_stream;

import java.io.*;

// 以File文件作为IO输出流，Write写入其中
public class JavaFileOutputStream {

    private static String filepath = "WorkFolder/location.dat";

    public static void main(String[] args) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(filepath)) {
            fileOutputStream.write(100);

            byte[] bytes = "test".getBytes();
            fileOutputStream.write(bytes);
        }
    }
}
