package JavaIO.NIOPackage;

import JavaIOSerialization.JavaSerializableObject;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BufferedWReaderObjectStream {

    private static Map<Integer, JavaSerializableObject> objects = new HashMap<>();

    private static void testJavaNIOWriter() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("demo.txt");
        try (BufferedWriter locFile = Files.newBufferedWriter(locPath)) {
            for (JavaSerializableObject object : objects.values()) {
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
            for (JavaSerializableObject object : objects.values()) {
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
                    JavaSerializableObject object = (JavaSerializableObject) localFile.readObject();
                } catch (EOFException | ClassNotFoundException exception) {
                    eof = true;
                }
            }
        }
    }
}
