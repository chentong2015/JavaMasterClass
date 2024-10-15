package JavaIO.io.input_stream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

// 以ByteArray字节数组作为IO输入流, 从中Read读取
public class JavaByteArrayInputStream {

    private static String str = "testing";

    public static void main(String[] args) throws IOException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes())) {
            byte[] temp = new byte[3];
            int totalNumBytes = byteArrayInputStream.read(temp, 0, temp.length);

            System.out.println(totalNumBytes);
            System.out.println(new String(temp));
        }
    }
}
