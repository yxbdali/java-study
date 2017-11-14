package com.xiangbin.yang.study.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;

/**
 * @author xiangbin.yang
 * @since 2017/11/3
 */
public class ServerSocketTest {
    private static final ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(81);
        System.out.println("Server start...");
        Socket client = serverSocket.accept();
        pool.execute(() -> {
            try {
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                System.out.println(IOUtils.toString(in, "utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
