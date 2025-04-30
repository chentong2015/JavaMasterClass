package io.input_stream;

import java.io.*;

// TODO. InputStream <-> Bytes 操作
public class BaseInputStream {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("WorkFolder/location.dat");
        testInputStream(inputStream);
        inputStream.close();
    }

    private static void testInputStream(InputStream inputStream) throws IOException {
        // 逐个读取单独的Byte字节
        // Reads the next byte of data from the input stream.
        // The value byte is returned as an int in the range 0 to 255.
        // Return -1 if end of the stream has been reached.
        int byteVal = inputStream.read();
        System.out.println(byteVal);

        // 读取指定Byte长度的数据
        byte[] buffByte = new byte[16];
        while (inputStream.read(buffByte) != -1) {
            System.out.println(new String(buffByte));
        }
    }

    // TODO. 通过Buffer提高输入流的读取效率，并不改变读取的方式
    private static void testInputStreamBuffer(InputStream inputStream) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
            int length;
            byte[] buff = new byte[16];
            while ((length = bufferedInputStream.read(buff)) >= 0) {
                // do something with buff
                System.out.println(buff[0]);
            }
        }
    }

    // TODO. 通过DataInputStream来读取指定数据类型的数据(int, string, long, boolean..)
    // End Of File EOFException: Signals that an end of file or end of stream has been reached
    private static void testDataInputStream(InputStream inputStream) throws IOException {
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
