package JavaIO.io.input_stream;

import java.io.*;

// TODO. InputStream <-> Bytes 操作
public class JavaInputStream {

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


    private void testBufferInputStream() {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("test.dat"))) {
            byte[] buff = new byte[16];
            int length;
            while ((length = bufferedInputStream.read(buff)) >= 0) {
                // do something with buff
                System.out.println(buff[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
