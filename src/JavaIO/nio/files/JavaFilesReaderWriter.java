package JavaIO.nio.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

// TODO. Files创建BufferedReader和BufferedWriter来读写文件
public class JavaFilesReaderWriter {

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

    // 使用BufferWriter来写入数据
    private static void testJavaNIOWriter() throws IOException {
        Path locPath = FileSystems.getDefault().getPath("demo.txt");
        try (BufferedWriter locFile = Files.newBufferedWriter(locPath)) {
            locFile.write("write new line");
        }
    }
}
