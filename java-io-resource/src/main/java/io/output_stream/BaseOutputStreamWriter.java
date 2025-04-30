package io.output_stream;

import java.io.*;

// 使用Writer能够方便以Char和Str格式的数据写入
public class BaseOutputStreamWriter {

    public static void main(String[] args) throws IOException {
        OutputStream outputStream = new FileOutputStream("WorkFolder/location.dat");
        testOutputStreamWriter(outputStream);
        outputStream.close();
    }

    private static void testOutputStreamWriter(OutputStream outputStream) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write("test line");
        outputStreamWriter.append("append");
    }

    // 使用BufferedWriter包装OutputStreamWriter能够有效提高效率
    private static void testBufferedWriter() throws IOException {
       try (BufferedWriter outWriter = new BufferedWriter(new OutputStreamWriter(System.out))) {
            outWriter.write("next line");
            outWriter.append("test", 0, 3);
        }
    }
}
