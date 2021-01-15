package JavaInputOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Buffered缓冲读写器: 在操作大量数据时的高效性 !!!! 提供数据缓冲区 8K
 */
public class BaseBufferedReaderWriter {

    private static Map<Integer, String> locations = new HashMap<>();

    public static void main(String[] args) {
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

}
