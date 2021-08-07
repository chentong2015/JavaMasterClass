package JavaIO.NIOPackage;

import JavaIOSerialization.BaseSerializableObject;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Java NOI deals with data in blocks at a time, use channels and buffers 处理数据块
 * Java NOI Non-blocking 非阻塞
 * Threads will not block 线程将会继续执行，有助于性能的提升
 * 1. Path + Files + "Java.io.*" 混合使用    : BufferedWriter/BufferedReader; ObjectOutputStream/ObjectInputStream
 * 2. File Channel + Buffers (ByteBuffer)   : 调用Channel read() & write()方法
 * 3. Path + Files + File Systems文件系统交互 :
 */
// NIO: 基于通道Channel和缓冲区Buffer的I/O方式
// 可以使用Native函数直接分配栈外内存，通过DirectByteBuffer对象作为这块内存的引用进行操作
// TODO: https://www.tabnine.com/code/java/classes/java.nio.DirectByteBuffer
// 不受到Java堆大小的限制，但是受到本地的总内存和处理器寻址空间的限制
public class Base {

    private static Map<Integer, BaseSerializableObject> objects = new HashMap<>();

    private static void testJavaNIOWriter() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("demo.txt");
        try (BufferedWriter locFile = Files.newBufferedWriter(locPath)) {
            for (BaseSerializableObject object : objects.values()) {
                locFile.write(object.getID() + ": " + object.getName());
            }
        }
    }

    // 直接使用BufferedReader来读取数据
    private static void testJavaNOIReader() throws IOException {
        String input;
        Path locPath = FileSystems.getDefault().getPath("demo.txt");
        try (BufferedReader dirFile = Files.newBufferedReader(locPath)) {
            while ((input = dirFile.readLine()) != null) {
                System.out.println("Line data: " + input);
            }
        }

        try (Scanner scanner = new Scanner(Files.newBufferedReader(locPath))) {
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                System.out.println("Line data: " + inputLine);
            }
        }
    }

    // 对于Object对象的读写，Files没有明确的声明创建ObjectStream实例, 但可以用创建newOutputStream + newInputStream
    private static void testJavaNIOObjectOutput() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("local.dat");
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))) {
            for (BaseSerializableObject object : objects.values()) {
                locFile.writeObject(object);
            }
        }
    }

    private static void testJavaNOIObjectInput() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("local.dat");
        try (ObjectInputStream localFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    BaseSerializableObject object = (BaseSerializableObject) localFile.readObject();
                } catch (EOFException | ClassNotFoundException exception) {
                    eof = true;
                }
            }
        }
    }
}
