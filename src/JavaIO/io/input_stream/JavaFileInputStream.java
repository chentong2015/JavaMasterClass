package JavaIO.io.input_stream;

import java.io.*;
import java.nio.charset.StandardCharsets;

// 以File作为输入流
public class JavaFileInputStream {

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
}
