package JavaIO.NIOPackage.byte_stream;

import JavaIOSerialization.JavaSerializableObject;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// Object的序列化和反序列化
// ObjectOutputStream & ObjectInputStream
public class JavaObjectIOStream {

    private static Map<Integer, JavaSerializableObject> objects = new HashMap<>();

    // 对于Object对象的读写，Files没有明确的声明创建ObjectStream实例
    // 但可以用创建newOutputStream + newInputStream
    private static void testJavaNIOObjectOutput() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("local.dat");
        try (ObjectOutputStream locFile =
                     new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))) {
            for (JavaSerializableObject object : objects.values()) {
                locFile.writeObject(object);
            }
        }
    }

    private static void testJavaNOIObjectInput() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("local.dat");
        try (ObjectInputStream localFile =
                     new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    JavaSerializableObject object = (JavaSerializableObject) localFile.readObject();
                } catch (EOFException | ClassNotFoundException exception) {
                    eof = true;
                }
            }
        }
    }
}
