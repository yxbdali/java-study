package com.xiangbin.yang.study.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

import static java.lang.System.out;

/**
 * @author xiangbin.yang
 * @since 2017/10/31
 */
public class FileCh {
    public static void main(String[] args) throws IOException {
        readFromFileChannel();
    }

    private static void readFromFileChannel() throws IOException {
        FileChannel fh = FileChannel.open(Paths.get("pom.xml"));
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int read = fh.read(buffer);
        do {
            buffer.flip();
            while (buffer.remaining() > 0) {
                out.print((char)buffer.get());
            }
            buffer.clear();
            read = fh.read(buffer);
        }while (read != -1);
    }
}
