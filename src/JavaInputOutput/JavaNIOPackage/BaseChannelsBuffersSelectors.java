package JavaInputOutput.JavaNIOPackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

// 使用纯Java.nio读取文件数据
// 1. Channel is the DataSource (file, network, socket...) reading from or writing to : 必须实现Java.noi.channels.channel interface 用于连接
// 2. A buffer is the container for block of data to read or write 数据块的容器: buffer只容纳指定类型的数据
// 3. Selectors allow single thread to manage the I/O for multiple channels  : 该技术主要针对大型的企业级软件
public class BaseChannelsBuffersSelectors {

    /**
     * Files.readAllLines() 会将所有的数据读取到memory内存中     ==> 在数据操作上面可能比java.io缓慢，可以尝试使用别的数据文件 !!!
     * 操作Stream，可住指定对数据的解码方案
     */
    private static void testFilesReadingTextFile() throws IOException {
        Path dataPath = FileSystems.getDefault().getPath("data.txt");
        List<String> lines = Files.readAllLines(dataPath); // 默认的读取数据，解码的方案是UTF_8.INSTANCE
    }

    /**
     * 1. Files.write()方法所执行的操作是相互独立的：打开文件，写入数据，关闭文件  ====> 可以使用StringBuilder来一次性写入更多的数据
     * 2. 需要对数据进行编码，然后写入文件中
     */
    private static void testFilesWritingTextFile() throws IOException {
        Path dataPath = FileSystems.getDefault().getPath("data.txt");
        String appendData = "\nline 4";
        byte[] appendBytes = appendData.getBytes(StandardCharsets.UTF_8); // 将string转成写入文件的字节; 指定编码的格式UTF_8
        Files.write(dataPath, appendBytes, StandardOpenOption.APPEND); // 指定写入的模式: 默认是没有会创建，然后刷新其中的数据内容
    }

    /**
     * 1. 处理block of data, 而不是bytes or characters at a time
     * 2. 是需要构建一个Channel的实例，即可实现读写操作 read() & write()
     */
    private static void testReadDataFromBinaryFile() {
        try {
            FileInputStream file = new FileInputStream("data.txt");
            FileChannel channel = file.getChannel(); // 使用FileInputStream获取的Channel只能用于读取reading  !!!
// TODO : reading ...
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void testWritingStringToBinaryFile() throws IOException {
        try (FileOutputStream binFile = new FileOutputStream("file.dat");
             FileChannel binChannel = binFile.getChannel()) {
            String writeStr = "Hello";
            // 1. 使用UTF_8, Hello会被编码成5个bytes !!!
            byte[] writeBytes = writeStr.getBytes(StandardCharsets.UTF_8);
            // 2. Wraps 包装 the bytes to the buffer 自动填充 !!! ==>  两者之间相互影响
            // 3. ByteBuffer 容纳的字节大小，就是包装的字符串的(字节)大小
            // 4. wrap() 方法会自动的flip，将byte缓冲流的位置重新设置到起始位置，在write()写入进文件的时候，需要从起始位置开始读取
            ByteBuffer buffer = ByteBuffer.wrap(writeBytes);
            // 5. 最终写入的字节是5个bytes
            int numBytes = binChannel.write(buffer);
        }
    }

    private static void testWritingIntegerToBinaryFile() throws IOException {
        try (FileOutputStream binFile = new FileOutputStream("file.dat");
             FileChannel binChannel = binFile.getChannel()) {
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES); // 给ByteBuffer分配4个bytes的大小空间
            intBuffer.putInt(245); // buffer's position会自动的往后移动
            intBuffer.flip(); // 重置position到0的位置
            binChannel.write(intBuffer); // 返回写入的数据的字节大小，也即自定义的allocate()的4个字节
            intBuffer.flip();
            intBuffer.putInt(-6542);
            intBuffer.flip();
            binChannel.write(intBuffer);
        }
    }

}
