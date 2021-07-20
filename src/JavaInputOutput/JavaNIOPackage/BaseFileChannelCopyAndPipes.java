package JavaInputOutput.JavaNIOPackage;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

/**
 * Pipes 管道：transfer data between threads, one way connection  使用Pipes来实现不同线程之间的通讯, 通常不是最佳方案
 * 1. SinkChannel: threads write to the SinkChannel
 * 2. SourceChannel: other threads read from the SourceChannel
 */
public class BaseFileChannelCopyAndPipes {

    private Pipe pipe;

    private void testFileChannelCopy() throws IOException {
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

    private void testPipesChannel() throws IOException {
        pipe = Pipe.open();
        new Thread(getWriter()).start();
        new Thread(getReader()).start();
    }

    private Runnable getWriter() {
        return () -> {
            Pipe.SinkChannel sinkChannel = pipe.sink();
            ByteBuffer writeBuffer = ByteBuffer.allocate(56);
            for (int i = 0; i < 10; i++) {
                writeBuffer.flip();
                writeBuffer.put("Write data".getBytes(StandardCharsets.UTF_8));
                writeBuffer.flip();
                while (writeBuffer.hasRemaining()) {
                    try {
                        sinkChannel.write(writeBuffer);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        };
    }

    private Runnable getReader() {
        return () -> {
            Pipe.SourceChannel sourceChannel = pipe.source();
            ByteBuffer readBuffer = ByteBuffer.allocate(56);
            for (int i = 0; i < 10; i++) {
                readBuffer.flip();
                try {
                    int numBytesRead = sourceChannel.read(readBuffer);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                readBuffer.flip();
                System.out.println("Read data" + new String(readBuffer.array()));
            }
        };
    }
}
