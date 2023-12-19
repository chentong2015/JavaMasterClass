package JavaIOSerialization;

import JavaIOSerialization.model.BaseObjectSerializable;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ObjectIOStream {

    private static Map<Integer, BaseObjectSerializable> objects = new HashMap<>();

    // 对于Object对象的读写，Files没有明确的声明创建ObjectStream实例
    // 但可以用创建newOutputStream + newInputStream
    private static void testJavaNIOObjectOutput() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("local.dat");
        try (ObjectOutputStream locFile =
                     new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))) {
            for (BaseObjectSerializable object : objects.values()) {
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
                    BaseObjectSerializable object = (BaseObjectSerializable) localFile.readObject();
                } catch (EOFException | ClassNotFoundException exception) {
                    eof = true;
                }
            }
        }
    }
}
