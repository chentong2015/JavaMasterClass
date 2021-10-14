package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class BaseNIOClient {

    public void testClientSocketChannel() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 9000));

        String newData = "Send message: " + System.currentTimeMillis();
        ByteBuffer bufWrite = ByteBuffer.allocate(48);
        bufWrite.clear();
        bufWrite.put(newData.getBytes());
        bufWrite.flip();
        while (bufWrite.hasRemaining()) {
            socketChannel.write(bufWrite);
        }

        ByteBuffer bufRead = ByteBuffer.allocate(48);
        int bytesReceived = socketChannel.read(bufRead);
        socketChannel.close();
    }
}
