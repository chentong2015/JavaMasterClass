package io.random_access;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

    public static final int EOF = 10; // 对应换行键

    public static void main(String[] args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("file.dat", "rwd");) {
            byte[] readBytes = new byte[10];
            randomAccessFile.read(readBytes);
            // 将读取的10个字节数据转换成字符串
            String readStr = new String(readBytes);

            // 读完指定的bytes之后，读取int(一个byte)
            long readInt = randomAccessFile.readInt();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // 删除RandomAccess文件的最后一行
    public static void removeLastLine(File file) throws IOException {
        if (file == null || file.length() == 0) {
            return;
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            long length = randomAccessFile.length() - 1;
            byte b;
            do {
                length -= 1;
                randomAccessFile.seek(length);
                b = randomAccessFile.readByte();
            } while (b != EOF && length > 0);

            randomAccessFile.setLength(length == 0 ? length : length + 1);
        }
    }
}
