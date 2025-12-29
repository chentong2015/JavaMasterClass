package io.output_stream;

import java.io.*;

public class BaseOutputStream {

    // TODO. .flush()将内存中缓存数据全部"冲刷"到持久化端
    // Flushes this buffered output stream.
    // Forces any buffered output bytes to be written out to the underlying output stream
    private static void testBufferedOutputStream(OutputStream outputStream) throws IOException {
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
            bufferedOutputStream.write(10);

            byte[] bytes = "test".getBytes();
            bufferedOutputStream.write(bytes);

            bufferedOutputStream.flush();
        }
    }

    // TODO. 使用DataOutputStream来写入各种类型的数据
    private static void testDataOutputStream(OutputStream outputStream) throws IOException {
        try(DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeByte(100);
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeChars("values");
        }
    }
}
