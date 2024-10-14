package JavaIO.io.file_writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// TODO. PrintWriter 针对Writer提供各种格式化数据的读写API
public class JavaPrintWriter {
    
    public static void main(String[] args) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("WorkFolder/locations.txt"))) {
            printWriter.print("print line");
            printWriter.println("println line");
            printWriter.write("write line");
        }

        try (PrintWriter printWriter = new PrintWriter("WorkFolder/test.csv")) {
            printWriter.write(getCsvString());
            // Flushes the stream
            printWriter.flush();
        }
    }

    private static String getCsvString() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        // add csv title and data
        sb.append("id").append(',').append("Name").append(lineSeparator);
        sb.append("10").append(',').append("Chen").append(lineSeparator);
        sb.append("100").append(',').append("Victor").append(lineSeparator);
        return sb.toString();
    }
}
