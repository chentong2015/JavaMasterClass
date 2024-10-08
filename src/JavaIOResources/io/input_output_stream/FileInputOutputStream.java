package JavaIOResources.io.input_output_stream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

// 使用FileInputStream/FileOutputStream操作字节流(Byte Stream)或二进制数据(Binary Data)
public class FileInputOutputStream {

    private static Map<Integer, String> locations = new HashMap<>();

    public static void main(String[] args) {
        String lineSeparator = System.getProperty("line.separator");
        String fileName = "output.txt";
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileName))) {
            stream.write(("\"\"first item \"\"" + lineSeparator).getBytes(StandardCharsets.UTF_8));

            // Flush内存中缓存的字节，写入底层(持久化文件中)
            // Flushes this buffered output stream.
            // Forces any buffered output bytes to be written out to the underlying output stream
            stream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // EOFException: 判断是否到达字节流的末尾的一个标识
    // This exception is mainly used by data input streams to signal end of stream.
    // Signals that an end of file or end of stream has been reached
    private static void testInputStream() throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream("location.dat"));
        try (DataInputStream locFile = new DataInputStream(inputStream)) {
            boolean eof = false;
            while (!eof) {
                try {
                    int locID = locFile.readInt();
                    String description = locFile.readUTF();
                } catch (EOFException e) {
                    eof = true;
                }
            }
        }
    }

    // .dat 数据流(二进制格式)文件, 通常指给程序使用, 不是常规文件
    // DataOutputStream将功能添加到另一个输出流, 能够提供更加丰富的数据格式写入
    public static void testOutputStream() throws IOException {
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("location.dat"));
        try (DataOutputStream locFile = new DataOutputStream(outputStream)) {
            for (Map.Entry<Integer, String> entry : locations.entrySet()) {
                // Writes an int to the underlying output stream as four bytes, high byte first
                locFile.writeInt(entry.getKey());   // 从高位往低位依次，每次write一个字节
                locFile.writeUTF(entry.getValue());  // 使用UTF8的编码格式来写入文件 !!
            }
        }
    }
}
