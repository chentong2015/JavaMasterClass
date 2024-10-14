package JavaIO.io.file_writer;

import java.io.FileWriter;
import java.io.IOException;

// 标准IO写入机制: 打开文件，写入所有数据，然后关闭文件
public class JavaFileWriter {

    private static String filepath = "WorkFolder/locations.txt";

    public static void main(String[] args) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.write("new line");
            fileWriter.append("add new line");
        }
    }
}
