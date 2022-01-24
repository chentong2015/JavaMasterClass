package JavaIO.IOPackage.byte_stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class JavaPrintStream {

    // PrintStream将功能添加到另一个输出流，方便地打印各种数据值的表示形式的功能
    // 使用常规输入到字节流文件
    private static void testPrintStream() throws IOException {
        try (PrintStream locFile = new PrintStream(new FileOutputStream("location.dat"))) {
            locFile.print("print line");
            locFile.println("println line");
            locFile.write(10);

            // 写入字节数组的时候, 需要编码方案
            byte[] writeBytes = new byte[10];
            locFile.write(writeBytes, 0, writeBytes.length);
        }
    }
}
