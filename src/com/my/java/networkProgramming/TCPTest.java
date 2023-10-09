package com.my.java.networkProgramming;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class TCPTest {
    // 实现TCP的网络编程
    // 客户端发送信息给服务端，服务端显示信息到控制台上

    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        try {
            // 服务器的地址
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            // 创建发送给该服务器上端口号为8899的程序
            socket = new Socket(inet, 8899);
            // 创建输出流
            os = socket.getOutputStream();
            // 输出数据，传输数据
            os.write("你好，卧榻西瓦扣你妈撒，152Hello".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert os != null;
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void server() {
        Socket socket = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            // 表明自己的端口号
            ServerSocket serverSocket = new ServerSocket(8899);
            // 调用accept()表示接收到来自客户端的socket
            socket = serverSocket.accept();
            // 获取输入流
            is = socket.getInputStream();
            isr = new InputStreamReader(is);

            // 查看客户端的地址
            System.out.println(socket.getInetAddress().getHostAddress() + " : ");

            char[] ch = new char[5];
            int len;
            while ((len = isr.read(ch)) != -1) {
                System.out.print(new String(ch, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert isr != null;
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
