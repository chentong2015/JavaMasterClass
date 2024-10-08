package JavaIOResources.buffered;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// BufferedReader:
// 1. Read texts from input stream and buffers the characters into a character array
// 2. 相比于在disk drive硬盘频繁的寻址，单次读取少量的数据
//    使用buffers能够一次读取更多数据 reduce the disk access time
// 3. 默认缓冲区8K，一次性读取的数据量(如果数据量小于8K，则一次性读取到buffer中，提供读取使用)
// 4. The data if only read from the disk when the buffer is empty;
//    otherwise FileReader continues to take data ...

// BufferedWriter:
// 1. The file writer puts the data to the buffered,
//    and the data is only written to disk when the buffer is full ...
public class FileBufferedReaderWriter {

    private static Map<Integer, String> locations = new HashMap<>();

    // 1. 通过Scanner+BufferedReader来读取
    public static void testBufferReader() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("locations.txt")));
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                int locID = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String direction = scanner.nextLine();
                locations.put(locID, direction);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    // 2. 直接通过BufferedReader来读取，使用行分割符号来读取一行的数据
    private static void testBufferReaderWithoutScanner() {
        try (BufferedReader locFile = new BufferedReader(new FileReader("location.txt"))) {
            String inputLine = locFile.readLine();
            while (inputLine != null) {
                // Read the data to map
                inputLine = locFile.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void testBufferedStreams() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("file1.txt"))) {
            int intVal;
            while ((intVal = bufferedReader.read()) > 0) {
                char charVal = (char) intVal;
                // do something with charVal
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 3. 直接通过BufferedWriter来写文件
    private static void testBufferedWriter() {
        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("location.txt"))) {
            for (String location : locations.values()) {
                locFile.write(location);
                // TODO: 自动使用system系统的行分割符号，添加换行符
                locFile.newLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
