package JavaInputOutput.JavaNIOPackage;

import JavaInputOutput.DataModel.SerializableObjectModel;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <<Java NOI deal with data in blocks at a time, use channels and buffers >> 处理数据块
 * Java NOI (Non-blocking非阻塞): Java IO的延伸版
 * 1. File Systems reading and writing data 文件系统的交互               ======> C#区别： FileStream file = File.OpenRead(path); File.Create(path);
 * 2. Files new Instance 构建具体的实例
 * 3. Threads will not block 线程将会继续执行，有助于性能的提升
 */
public class BaseNIO {

    private static Map<Integer, SerializableObjectModel> objects = new HashMap<>();

    // BufferedWriter
    private static void testJavaNIOWriter() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("test.txt");
        try (BufferedWriter locFile = Files.newBufferedWriter(locPath)) {
            for (SerializableObjectModel object : objects.values()) {
                locFile.write(object.getID() + ": " + object.getName());
            }
        }
    }

    // BufferedReader
    private static void testJavaNOIReader() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("test.txt");
        try (Scanner scanner = new Scanner(Files.newBufferedReader(locPath))) {
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                System.out.println("Line data: " + inputLine);
            }
        }

        // 直接使用BufferedReader来读取数据
        try (BufferedReader dirFile = Files.newBufferedReader(locPath)) {
            String input;
            while ((input = dirFile.readLine()) != null) {
                System.out.println("Line data: " + input);
            }
        }
    }

    // ObjectOutputStream
    // 对于Object对象的读写，Files没有明确的声明创建ObjectStream实例, 但可以用创建newOutputStream + newInputStream
    private static void testJavaNIOObjectOutput() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("local.dat");
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))) {
            for (SerializableObjectModel object : objects.values()) {
                locFile.writeObject(object);
            }
        }
    }

    // ObjectInputStream
    private static void testJavaNOIObjectInput() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("local.dat");
        try (ObjectInputStream localFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    SerializableObjectModel object = (SerializableObjectModel) localFile.readObject();
                } catch (EOFException | ClassNotFoundException exception) {
                    eof = true;
                }
            }
        }
    }

}
