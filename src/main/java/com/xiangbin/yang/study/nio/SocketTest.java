package com.xiangbin.yang.study.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

/**
 * @author xiangbin.yang
 * @since 2017/11/3
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        try (Socket s = new Socket("whois.internic.net", 43)) {
            OutputStream out = s.getOutputStream();
            InputStream in = s.getInputStream();

            IOUtils.write("baidu.com\n", out, "utf-8");
            System.out.println(IOUtils.toString(in, "utf-8"));

            // Read and display response.
            /*
            int c = 0;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
            */
        }
    }
}
