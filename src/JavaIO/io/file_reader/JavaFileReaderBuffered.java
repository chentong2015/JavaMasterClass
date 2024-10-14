package JavaIO.io.file_reader;

import java.io.*;

public class JavaFileReaderBuffered {

    private static String filepath = "WorkFolder/locations.txt";

    public static void main(String[] args) throws IOException {
       try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {
           String line = bufferedReader.readLine();
           while (line != null) {
               System.out.println(line);
               line = bufferedReader.readLine();
           }
       }

       // 每次只读取一个字节长度
       try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {
           int intVal;
           // Reads a single character.
           while ((intVal = bufferedReader.read()) > 0) {
               char charVal = (char) intVal;
               System.out.println(charVal);
           }
       }
    }
}
