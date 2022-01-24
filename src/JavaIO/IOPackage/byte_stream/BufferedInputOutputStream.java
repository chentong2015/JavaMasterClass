package JavaIO.IOPackage.byte_stream;

import JavaIOSerialization.JavaSerializableObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Buffered Streams can improve efficiency
// 1. buffer contents in memory
// 2. Perform reads/writes in large chunks
// 3. reduces underlying stream interaction
public class BufferedInputOutputStream {

    private static Map<Integer, JavaSerializableObject> objects = new HashMap<>();

    private void testBufferInputStream() {
        // BufferedInputStream
        try (BufferedInputStream bufferedInputStream =
                     new BufferedInputStream(new FileInputStream("test.dat"))) {
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

    private void testBufferOutputStream() {
        // BufferedOutputStream
    }
}
