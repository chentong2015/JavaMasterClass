package io.output_stream;

import java.io.*;

// TODO. PrintStream 针对OutputStream提供格式格式输出API
public class BaseOutputStreamPrint {

    public static void main(String[] args) throws IOException {
        PrintStream consoleOut = System.out;
        consoleOut.println("console out");

        try (OutputStream outputStream = new FileOutputStream("WorkFolder/location.dat")) {
            testPrintStream(outputStream);
        }
    }

    private static void testPrintStream(OutputStream outputStream) {
        try (PrintStream locFile = new PrintStream(outputStream)) {
            locFile.print("print line");
            locFile.println("println line");
            locFile.write(10);
        }
    }
}