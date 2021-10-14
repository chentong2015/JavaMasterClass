package io.nio;

import io.util.CharsetHelper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class BaseNIOServerSelector {

    // 创建epoll Selector多路复用器
    private static Selector selector;

    public static void main(String[] args) throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9000));
        serverSocketChannel.configureBlocking(false);
        // 将serverSocketChannel注册到多路复用器，监听的事件是"客户端连接事件"
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server started. 成功");
        while (true) {
            // 阻塞: 直到有任何监听的事件发生 ==> 没有任何事件则不会占用CPU
            selector.select();
            // 获取selector中注册的全部事件 ==> 只是关注有效地注册事件，避免多余的处理
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            // 遍历SelectionKey，逐个对事件进行处理，之后删除(避免重复处理)
            while (iterator.hasNext()) {
                handleSelectionKeyEvents(iterator.next());
                iterator.remove();
            }
        }
    }

    // TODO: 通过SelectionKey获取到相应的Socket Server，根据监测的不同事件做相应的处理
    private static void handleSelectionKeyEvents(SelectionKey key) throws IOException {
        // 如果是serverSocketChannel上的连接事件
        if (key.isAcceptable()) {
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("Connection OK");
        }
        // 如果是SocketChannel上的Read事件(接受信息，读取到客户端的信息)
        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(4);
            int len = socketChannel.read(byteBuffer);
            if (len > 0) {
                byteBuffer.flip(); // 重置position到0的位置
                String message = new String(byteBuffer.array());
                if (message.equals("quit")) {
                    System.out.println("close connection");
                    socketChannel.close();
                } else {
                    System.out.println("Receive: " + message);
                    System.out.println("Send back message to client: ");
                    // 发送信息，写回到客户端的信息
                    socketChannel.write(ByteBuffer.wrap("Response".getBytes()));
                }
            }
        }
    }

    // 补充接收message信息的编码和解码方式
    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    private static void handleReadableEvent(SelectionKey key) throws IOException {
        if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            buffer.clear();
            if (channel.read(buffer) > 0) {
                buffer.flip();
                CharBuffer charBuffer = CharsetHelper.decode(buffer);
                String msg = charBuffer.toString();
                System.out.println("Receive" + channel.getRemoteAddress() + " message：" + msg);
                channel.write(CharsetHelper.encode(CharBuffer.wrap(msg)));
            } else {
                channel.close();
            }
        }
    }
}
