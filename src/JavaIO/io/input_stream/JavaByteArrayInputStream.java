package JavaIO.io.input_stream;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

// 以ByteArray字节数组作为输入流
public class JavaByteArrayInputStream {

    public static void main(String[] args) {
        byte[] bytes = "test test".getBytes(Charset.defaultCharset());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        byte[] temp = new byte[3];
        byteArrayInputStream.read(temp, 0, temp.length);
        System.out.println(new String(temp));
    }
}
