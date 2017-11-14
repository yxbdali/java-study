package com.xiangbin.yang.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

import static java.lang.System.out;

/**
 * @author xiangbin.yang
 * @since 2017/10/31
 */
public class SocketCh {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(81));
        out.println("Start nio server...");

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        for (;;) {
            int select = selector.select();
            if (select > 0) {
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel)key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE);
                    }
                    else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(10);
                        int read = client.read(buffer);
                        do {
                            buffer.flip();
                            while (buffer.remaining() > 0) {
                                char c = (char)buffer.get();
                                out.print(c);
                            }
                            buffer.clear();
                            read = client.read(buffer);
                        }while (read != -1);
                    }
                    else if (key.isWritable()) {
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(30);
                        buffer.put("Hello! from NIO Server\r\n".getBytes(Charset.forName("utf-8")));
                        out.println("Echo to client");
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                        //client.close();
                    }
                }
            }
        }
    }
}
