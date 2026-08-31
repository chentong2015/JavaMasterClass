package random_access;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

    public static void main(String[] args) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("java-core-io/folder/location.dat", "rwd");) {
            byte[] readBytes10 = new byte[10];
            randomAccessFile.read(readBytes10); // 读取10个字节
            System.out.println(new String(readBytes10));

            randomAccessFile.skipBytes(2); // 跳过字节数量

            byte[] readBytes4 = new byte[4];  // 再读取4个字节
            randomAccessFile.read(readBytes4);
            System.out.println(new String(readBytes4));

            System.out.println(randomAccessFile.length()); // 总共16个字节
        }
    }

    // TODO. 删除RandomAccess文件的最后一行
    // seek(long pos): Sets the file-pointer offset, measured from the beginning of this file, at which the next read or write occurs.
    // readByte()!=10: 控制字符10表示换行符字节
    public static void removeLastLine(File file) throws IOException {
        if (file == null || file.length() == 0) {
            return;
        }
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            long length = randomAccessFile.length() - 1;
            byte b;
            do {
                length -= 1;                      // 倒退一个字节
                randomAccessFile.seek(length);    // 设置偏移定位
                b = randomAccessFile.readByte();  // 往后读取一个字节
            } while (b != 10 && length > 0);

            // 截断文件长度, 字符长度+1
            randomAccessFile.setLength(length == 0 ? length : length + 1);
        }
    }
}
