package JavaIOResources.IOPackage.input_output_stream;

import JavaIOSerialization.model.BaseObjectSerializable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Buffered Streams can improve efficiency
// 1. buffer contents in memory
// 2. Perform reads/writes in large chunks
// 3. reduces underlying stream interaction
public class BufferedInputOutputStream {

    private static Map<Integer, BaseObjectSerializable> objects = new HashMap<>();

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

    public static void testOutputStream() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("test6.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(10);

        // TODO. .flush()将内存中的缓存数据全部"冲刷"到文件中，数据的最终持久化
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        fileOutputStream.close();
    }
}
