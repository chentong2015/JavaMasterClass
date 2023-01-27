package JavaIO.IOPackage.file_reader_writer;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class JavaPrintWriter {

    /**
     * 1. PrintWriter继承自Writer, 同时实现了class PrintStream中的所有的输出方法
     * 2. PrintWriter将写入各种数据格式化的功能(能力)提供给FileWriter
     * 3. 它不包含用于写入原始字节的方法，为此程序应使用未编码的字节流  ==> 区别于PrintStream
     */
    private static void testPrintWriter() throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("locations.txt"))) {
            printWriter.print("print line");
            printWriter.println("println line");
            printWriter.write("write line");
        }
    }

    public static void main(String[] args) {
        String lineSeparator = System.getProperty("line.separator");
        try (PrintWriter writer = new PrintWriter("test.csv")) {
            StringBuilder sb = new StringBuilder();
            // add csv title and data
            sb.append("id").append(',').append("Name").append(lineSeparator);
            sb.append("100").append(',').append("Victor").append(lineSeparator);
            sb.append("50 errors have been detected on this table").append(lineSeparator);
            writer.write(sb.toString());
            // Flushes the stream
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
