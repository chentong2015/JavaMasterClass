package JavaIO.io.output_stream;

import java.io.*;

public class JavaFileOutputStream {

    // .dat 数据流(二进制格式)文件, 通常指给程序使用, 不是常规文件
    // DataOutputStream将功能添加到另一个输出流, 能够提供更加丰富的数据格式写入
    public static void testOutputStream() throws IOException {
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("location.dat"));

        try (DataOutputStream locFile = new DataOutputStream(outputStream)) {
            // for (Map.Entry<Integer, String> entry : locations.entrySet()) {
            //     // Writes an int to the underlying output stream as four bytes, high byte first
            //     locFile.writeInt(entry.getKey());   // 从高位往低位依次，每次write一个字节
            //     locFile.writeUTF(entry.getValue());  // 使用UTF8的编码格式来写入文件 !!
            // }
        }
    }
}
