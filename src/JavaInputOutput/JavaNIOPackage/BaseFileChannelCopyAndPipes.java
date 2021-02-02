package JavaInputOutput.JavaNIOPackage;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

/**
 * Pipes 管道：transfer data between threads, one way connection 在线程之间传输数据, 数据只能以一种方式流动
 * 1. SinkChannel: threads write to the SinkChannel
 * 2. SourceChannel: other threads read from the SourceChannel
 * 使用Pipes来实现不同线程之间的通讯，可能不是最优方案 !!!
 */
public class BaseFileChannelCopyAndPipes {

    private static void testFileChannelCopy() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile("file.dat", "rwd");
             FileChannel channel = file.getChannel();
             RandomAccessFile copyFile = new RandomAccessFile("fileCopy.dat", "rw");
             FileChannel copyFileChannel = copyFile.getChannel()) {
            // write data to file using channel

            channel.position(0); // 前面对于channel的操作，可能导致它的position改变 !!
            // transferFrom(): position is relative value of the source FileChannel
            long numTransferred = copyFileChannel.transferFrom(channel, 0, channel.size()); // channel.size() dataSource的大小

            // transferTo():
            long num = channel.transferTo(0, channel.size(), copyFileChannel);
        }
    }

    private static void testPipesChannel() {
        try {
            Pipe pipe = Pipe.open();

            Runnable writer = new Runnable() { // 实现Runnable接口的匿名类型
                @Override
                public void run() {
                    try {
                        Pipe.SinkChannel sinkChannel = pipe.sink(); // 拿到pipe的sink channel
                        ByteBuffer writeBuffer = ByteBuffer.allocate(56);
                        for (int i = 0; i < 10; i++) {
                            String currentTime = "The time is: " + System.currentTimeMillis();
                            writeBuffer.flip();
                            writeBuffer.put(currentTime.getBytes(StandardCharsets.UTF_8));
                            writeBuffer.flip();
                            while (writeBuffer.hasRemaining()) { // whether any elements between the current position and the limit.
                                sinkChannel.write(writeBuffer);
                            }
                            Thread.sleep(500);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };

            Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SourceChannel sourceChannel = pipe.source();
                        ByteBuffer readBuffer = ByteBuffer.allocate(56);
                        for (int i = 0; i < 10; i++) {
                            readBuffer.flip();
                            int numReadBytes = sourceChannel.read(readBuffer);
                            readBuffer.flip();
                            byte[] readBytes = readBuffer.array();
                            System.out.println("Reader thread : " + new String(readBytes));
                            Thread.sleep(500);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

            };

            // TODO: Thread(Runnable target) the object whose run() method is invoked when this thread is started
            // 线程启动之后所要触发对象
            new Thread(writer).start();
            new Thread(reader).start();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
