package JavaIO.IOPackage.text_stream;

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
}
