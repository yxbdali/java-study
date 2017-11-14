package com.xiangbin.yang.study.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;
import org.apache.commons.io.IOUtils;

/**
 * @author xiangbin.yang
 * @since 2017/11/3
 */
public class ClientSocket {
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("localhost", 81)) {
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            //IOUtils.writeLines(Lists.newArrayList("echo from client"), "utf-8", out);
            IOUtils.write("echo from client!\r\n", out, "utf-8");
            System.out.println(IOUtils.toString(in, "utf-8"));
        }
    }
}
