package serialization.object_stream;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO: 关于对象的IO Stream => 关于对象的序列化
public class ObjectIOStream {

    // 将对象进行序列化后写入到指定文件
    private static void writeObjectStream() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("local.dat");
        BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(locPath));

        try (ObjectOutputStream locFile = new ObjectOutputStream(outputStream)) {
            locFile.writeObject(new ObjectIOStream());
        }
    }

    // 从指定文件中读取序列化后的Object对象
    private static void readObjectStream() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("local.dat");
        BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(locPath));

        try (ObjectInputStream localFile = new ObjectInputStream(inputStream)) {
            boolean eof = false;
            while (!eof) {
                try {
                    Object object = localFile.readObject();
                } catch (EOFException | ClassNotFoundException exception) {
                    eof = true;
                }
            }
        }
    }
}
