package io.file_writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// BufferedWriter:
// 1. The file writer puts the data to the buffered
// 2. When the buffer is full, the buffer data will be written to disk
public class JavaFileWriterBuffered {

    private static String filepath = "WorkFolder/location.txt";

    public static void main(String[] args) throws IOException {
        try (BufferedWriter locFile = new BufferedWriter(new FileWriter(filepath))) {
            locFile.write("first line");
            locFile.newLine(); // 自动使用system系统的行分割符号
            locFile.write("next line");
        }
    }
}
