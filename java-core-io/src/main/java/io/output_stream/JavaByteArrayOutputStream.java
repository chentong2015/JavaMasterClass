package io.output_stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// 以ByteArray字节数组作为IO输出流, Write写入其中
public class JavaByteArrayOutputStream {

    private static String str = "testing";

    public static void main(String[] args) throws IOException {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byteArrayOutputStream.write(50);
            byteArrayOutputStream.write(60);
            byteArrayOutputStream.write(40);
            byteArrayOutputStream.write(str.getBytes());

            byte[] bytes = byteArrayOutputStream.toByteArray();
            System.out.println(new String(bytes));
        }
    }
}
