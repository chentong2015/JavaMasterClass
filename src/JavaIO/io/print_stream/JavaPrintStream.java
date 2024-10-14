package JavaIO.io.print_stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

// TODO. PrintStream 针对Output Stream提供格式格式输出API
public class JavaPrintStream {

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream consoleOut = System.out;
        consoleOut.println("console out");

        try (PrintStream locFile = new PrintStream(new FileOutputStream("WorkFolder/location.dat"))) {
            locFile.print("print line");
            locFile.println("println line");
            locFile.write(10);
        }
    }
}