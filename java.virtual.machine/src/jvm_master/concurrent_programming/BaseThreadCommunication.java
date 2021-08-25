package jvm_master.concurrent_programming;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

// Pipes
// 1. 半双工的通信方式，数据只能单向流动
// 2. 可以使用Pipes来实现线程之间的通讯, 但通常不是最佳方案
public class BaseThreadCommunication {

    private Pipe pipe;

    private void testPipesChannel() throws IOException {
        pipe = Pipe.open();
        new Thread(getWriter()).start();
        new Thread(getReader()).start();
    }

    // SinkChannel: threads write to the SinkChannel
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

    // SourceChannel: other threads read from the SourceChannel
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
