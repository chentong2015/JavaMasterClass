package JavaIOResources.IOPackage.input_output_stream;

import java.io.*;

// TODO. java.io.InputStream 输入流两个核心操作
// 1. InputStream -> String 最佳实践
//    https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
// 2. InputStream -> Byte[]
public class BaseInputOutputStream {

    // 两种转成字节数组的方式:
    // 1. 将InputStream读取到Byte[]数组中, 通过ByteArray缓存
    // 2. 调用IOUtils.toByteArray(is), Apache Common IO类库API
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

    // InputStreamReader从指定的inputStream中读取指定的字节长度
    public void doChain(InputStream inputStream) throws IOException {
        char[] charBuff = new char[128];
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            while (reader.read(charBuff) >= 0) {
                // do something with charBuff
                System.out.print(charBuff[0]);
            }
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
