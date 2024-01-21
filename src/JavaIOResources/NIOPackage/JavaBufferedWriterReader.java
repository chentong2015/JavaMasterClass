package JavaIOResources.NIOPackage;

import JavaIOSerialization.model.BaseObjectSerializable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaBufferedWriterReader {

    private static Map<Integer, BaseObjectSerializable> objects = new HashMap<>();

    // 使用BufferWriter来写入数据
    private static void testJavaNIOWriter() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("demo.txt");
        try (BufferedWriter locFile = Files.newBufferedWriter(locPath)) {
            for (BaseObjectSerializable object : objects.values()) {
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
}
