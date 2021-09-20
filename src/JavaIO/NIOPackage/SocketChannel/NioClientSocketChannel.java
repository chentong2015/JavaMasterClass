package JavaIO.NIOPackage.SocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

// 使用NOI来构建Socket网络连接的端口监听
public class NioClientSocketChannel {

    public void testClientSocketChannel() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 80));

        String newData = "Write to file..." + System.currentTimeMillis();
        ByteBuffer bufWrite = ByteBuffer.allocate(48);
        bufWrite.clear();
        bufWrite.put(newData.getBytes());
        bufWrite.flip();
        while (bufWrite.hasRemaining()) {
            socketChannel.write(bufWrite);
        }

        ByteBuffer bufRead = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(bufRead);

        socketChannel.close();
    }
}
