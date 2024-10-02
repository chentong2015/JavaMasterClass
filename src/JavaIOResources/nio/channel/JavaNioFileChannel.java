package JavaIOResources.nio.channel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

// 使用纯Java.nio读取文件数据
// 1. Channel is the DataSource (file, network, socket...) reading from or writing to
//    必须实现Java.noi.channels.channel interface 用于连接
//    不同于 read or write bytes or characters at a time
//    构建一个Channel的实例，即可实现读写操作 read() & write()
// 2. A buffer is the container for block of data to read or write 数据块的容器
//    buffer只容纳指定类型的数据，处理的是"一块数据"
//    buffer中的数据出现变动，或者是将数据写入到文件后，Index Position会变化
//    注意使用.flip()
// 3. Selectors allow single thread to manage the I/O for multiple channels
//    该技术主要针对大型的企业级软件
public class JavaNioFileChannel {

    private static void testWritingStringToBinaryFile() throws IOException {
        try (FileOutputStream binFile = new FileOutputStream("file.dat");
             FileChannel binChannel = binFile.getChannel()) {
            String writeStr = "Hello";
            // 1. 使用UTF_8, Hello会被编码成5个bytes
            byte[] writeBytes = writeStr.getBytes(StandardCharsets.UTF_8);
            // 2. Wraps the bytes to the buffer 自动填充，两者之间相互影响
            // 3. ByteBuffer 容纳的字节大小，就是包装的字符串的(字节)大小
            // 4. wrap() 方法会自动的flip，将byte缓冲流的Position重新设置到起始位置，在write()写入进文件的时候，需要从起始位置开始读取 !!
            // 5. The buffer's mark will be undefined 使用buffer.reset()方法，可见将position重置为之前标记的位置  ==> 先标记，再重置
            ByteBuffer buffer = ByteBuffer.wrap(writeBytes);
            // 6. 最终写入的字节是5个bytes
            int numBytes = binChannel.write(buffer);
        }
    }

    private static void testWritingIntegerToBinaryFile() throws IOException {
        try (FileOutputStream binFile = new FileOutputStream("file.dat");
             FileChannel binChannel = binFile.getChannel()) {
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES); // 给ByteBuffer分配4个bytes的大小空间
            intBuffer.putInt(245);  // buffer's position会自动的往后移动
            intBuffer.flip();            // 重置position到0的位置
            binChannel.write(intBuffer); // 返回写入的数据的字节大小，也即自定义的allocate()的4个字节
            intBuffer.flip();
            intBuffer.putInt(-6542);
            intBuffer.flip();
            binChannel.write(intBuffer);
        }
    }

    private static void testReadDataFromBinaryFile() throws IOException {
        String writeStr = "Hello";
        byte[] writeBytes = writeStr.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.wrap(writeBytes);

        RandomAccessFile file = new RandomAccessFile("file.dat", "rwd");
        FileChannel channel = file.getChannel();

        // 读取到buffer所包装的字节数组，注意buffer的position的位置
        long numBytesRead = channel.read(buffer);
        if (buffer.hasArray()) {
            System.out.println("Bytes buffer is = " + new String(buffer.array()));
        }

        ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
        numBytesRead = channel.read(intBuffer);
        intBuffer.flip();

        // getInt(0) "absolute read"不使用.flip() ==> 不会设置index position=0的位置
        int readInt = intBuffer.getInt();

        channel.close();
        file.close();
    }

    /**
     * FileChannel实现了SeekableByteChannel (6个方法)
     * 可指定在文件读写的位置 SeekableByteChannel position(long newPosition)
     */
    private static void testByteBuffer() throws IOException {
        String writeStr = "Hello";
        byte[] writeBytes = writeStr.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.wrap(writeBytes);

        ByteBuffer buffer1 = ByteBuffer.allocate(writeBytes.length);
        buffer1.put(writeBytes); // 将字节数组中的数据复制到buffer中
        buffer1.flip();          // put()不会自动的将position设置成0，需要重置

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.put(writeBytes).putInt(100).putInt(-200); // 支持连续性写入Buffer
        long int1Position = writeBytes.length; // 标记每一个添加数据的Position位置 ===> 通过位置，按照指定的顺序读取Sequentially
        long int2Position = int1Position + Integer.BYTES;
        long lastPosition = int2Position + Integer.BYTES;

        RandomAccessFile file = new RandomAccessFile("file.dat", "rwd");
        FileChannel channel = file.getChannel();
        ByteBuffer readBuffer = ByteBuffer.allocate(Integer.BYTES);
        channel.position(int2Position);  // 指定读取最后一个Int值
        channel.read(readBuffer); // Buffer的position已经改变，需要重置
        readBuffer.flip();
        int int2Value = readBuffer.getInt();

        channel.position(int1Position);
        readBuffer.flip();
        channel.read(readBuffer);
        readBuffer.flip();
        int int1Value = readBuffer.getInt();

        channel.close();
        file.close();
    }

    private void testFileChannelCopy() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile("file.dat", "rwd");
             FileChannel channel = file.getChannel();
             RandomAccessFile copyFile = new RandomAccessFile("fileCopy.dat", "rw");
             FileChannel copyFileChannel = copyFile.getChannel()) {
            // write data to file using channel

            channel.position(0); // 前面对于channel的操作，可能导致它的position改变 !!
            // transferFrom(): position is relative value of the source FileChannel
            long numTransferred = copyFileChannel.transferFrom(channel, 0, channel.size());
            // transferTo():
            long num = channel.transferTo(0, channel.size(), copyFileChannel);
        }
    }
}
