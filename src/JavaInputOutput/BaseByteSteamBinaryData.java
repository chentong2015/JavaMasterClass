package JavaInputOutput;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Byte Stream 字节流 / Binary Data 二进制数据：可以直接操作
 * + abstract class InputStream 抽象类
 * + FilterInputStream 子类, FileInputStream 子类(From file)
 * --> DataInputStream & BufferedInputStream extends FilterInputStream 过滤器输入流
 * -----------------------------------------------
 * + abstract class OutputStream 抽象类
 * + FilterOutputStream 子类，FileOutputStream 子类(To File)
 * --> DataOutputStream & BufferedOutputStream extends FilterOutputStream 过滤器输出流
 */
public class BaseByteSteamBinaryData {

    private static Map<Integer, String> locations = new HashMap<>();

    /**
     * EOFException: This exception is mainly used by data input streams to signal end of stream.
     * Signals that an end of file or end of stream has been reached
     * 判断是否到达字节流的末尾的一个标识
     */
    private static void testInputStream() {
        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("location.bat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    int locID = locFile.readInt();
                    String description = locFile.readUTF();
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // .dat 输出成二进制格式的文件，不是常规文件
        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("location.dat")));) {
            for (Map.Entry<Integer, String> entry : locations.entrySet()) {
                // Writes an int to the underlying output stream as four bytes, high byte first
                // 从高位往低位依次，每次write一个字节
                locFile.writeInt(entry.getKey());
                locFile.writeUTF(entry.getValue());  // 使用UTF8的编码格式来写入文件 !!
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
