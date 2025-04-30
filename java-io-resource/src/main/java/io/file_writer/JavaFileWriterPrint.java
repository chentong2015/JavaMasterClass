package io.file_writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// TODO. PrintWriter 针对Writer提供各种格式化数据的读写API
public class JavaFileWriterPrint {

    private static String filepath = "WorkFolder/locations.txt";

    public static void main(String[] args) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filepath))) {
            printWriter.print("print line");
            printWriter.println("println line");
            printWriter.write("write line");
        }
    }
}
