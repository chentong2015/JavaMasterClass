package JavaIO.io.output_stream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JavaOutputStream {

    // 在OutputStream中写入字节或者字节数组
    public void writeOutputStream(OutputStream outputStream) throws IOException {
        byte byteVal = 100;
        outputStream.write(byteVal);

        byte[] bytes = {0, 5, 19};
        outputStream.write(bytes);

    }

    // TODO. .flush()将内存中的缓存数据全部"冲刷"到文件中，数据的最终持久化
    public static void testFileOutputStream() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("test6.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(10);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        fileOutputStream.close();
    }
}
