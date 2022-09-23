package com.hznu.network;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author LIN
 * @date 2022/9/23 16:51
 */
public class TCPTest2 {

    @Test
    public void client() throws IOException{
        Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
        OutputStream os = socket.getOutputStream();
        FileInputStream fis = new FileInputStream("beauty.jpg");
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            os.write(buffer, 0, len);
        }
        System.out.println("client input over");
        // 关闭数据的输出
        socket.shutdownOutput();

        // 接收来自于服务器端的数据，并显示到控制台上
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bufferr = new byte[20];
        int len1;
        while((len1 = is.read(buffer)) != -1){
            baos.write(buffer,0,len1);
        }
        System.out.println(baos);

        fis.close();
        os.close();
        socket.close();
        baos.close();
    }

    @Test
    public void server() throws IOException{
        ServerSocket ss = new ServerSocket(9090);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream("beautyCopy.jpg");
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }
        System.out.println("server receive over");

        OutputStream os = socket.getOutputStream();
        os.write("你好，美女，照片我已收到，非常漂亮！".getBytes());

        fos.close();
        is.close();
        socket.close();
        ss.close();
        os.close();
    }

}
