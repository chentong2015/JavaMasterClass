package JavaIO.IOPackage.byte_stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputOutputStream {

    // 1. 将InputStream读取到Byte[]数组中，通过ByteArray缓存
    // 2. 调用apache common io IOUtils.toByteArray(is)来实现
    public void toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        byte[] data = new byte[100];
        while (inputStream.read(data) != -1) {
            buffer.write(data);
        }

        byte[] result = buffer.toByteArray();
    }

    // 从InputStream中逐个读取字节
    public void readInputStream(InputStream inputStream) throws IOException {
        int intVal;
        while ((intVal = inputStream.read()) >= 0) {
            byte byteVal = (byte) intVal;
        }
    }

    // 在OutputStream中写入字节或者字节数组
    public void writeOutputStream(OutputStream outputStream) throws IOException {
        byte byteVal = 100;
        outputStream.write(byteVal);

        byte[] bytes = {0, 5, 19};
        outputStream.write(bytes);
    }
}
