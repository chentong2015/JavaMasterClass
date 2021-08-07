package JavaIO.IOPackage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Byte Stream 字节流 / Binary Data 二进制数据：可以直接操作
 * + abstract class InputStream 抽象类
 * + FilterInputStream 子类, FileInputStream 子类 (From file)
 * --> DataInputStream & BufferedInputStream extends FilterInputStream 过滤器输入流
 * -----------------------------------
 * + abstract class OutputStream 抽象类
 * + FilterOutputStream 子类，FileOutputStream 子类 (To File)
 * --> DataOutputStream & BufferedOutputStream extends FilterOutputStream 过滤器输出流 / PrintStream extends FilterOutputStream
 */
public class BaseByteSteamBinaryData {

    private static Map<Integer, String> locations = new HashMap<>();

    /**
     * EOFException: This exception is mainly used by data input streams to signal end of stream.
     * Signals that an end of file or end of stream has been reached 判断是否到达字节流的末尾的一个标识
     */
    private static void testInputStream() throws IOException {
        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("location.dat")))) {
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
    // DataOutputStream将功能添加到另一个输出流, 能够提供更加丰富的数据格式写入 !!!
    public static void testOutputStream() throws IOException {
        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("location.dat")))) {
            for (Map.Entry<Integer, String> entry : locations.entrySet()) {
                // Writes an int to the underlying output stream as four bytes, high byte first
                locFile.writeInt(entry.getKey());   // 从高位往低位依次，每次write一个字节
                locFile.writeUTF(entry.getValue());  // 使用UTF8的编码格式来写入文件 !!
            }
        }
    }

    // PrintStream将功能添加到另一个输出流，即可以方便地打印各种数据值的表示形式的功能 !!!
    private static void testPrintStream() throws IOException {
        try (PrintStream locFile = new PrintStream(new FileOutputStream("location.dat"))) {
            locFile.print("print line");
            locFile.println("println line");
            locFile.write(10);
            byte[] writeBytes = new byte[10];  // 写入字节数组的时候, 需要编码方案 !!
            locFile.write(writeBytes, 0, writeBytes.length);
        }
    }

}
