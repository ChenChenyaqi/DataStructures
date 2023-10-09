package com.my.java.networkProgramming;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class TCPTest2 {
    // 客户端发送文件给服务端，服务端受到文件并保存到本地
    // 服务端返回“收到文件”给客户端
    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);
            os = socket.getOutputStream();
            fis = new FileInputStream("迎新晚会.png");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }

            // 关闭传输
            socket.shutdownOutput();

            // 客户端收到反馈并显示到控制台
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            char[] ch = new char[3];
            while((len = isr.read(ch)) != -1){
                System.out.print(new String(ch,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fis != null;
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        }
    }

    @Test
    public void server() {
        Socket socket = null;
        InputStream is = null;
        FileOutputStream fos = null;
        OutputStream os = null;
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            socket = serverSocket.accept();
            is = socket.getInputStream();
            fos = new FileOutputStream("迎新晚会（3）.png");
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }

            // 服务器端给客户端反馈
            os = socket.getOutputStream();
            os.write("我已收到文件！".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert is != null;
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
            try {
                assert os != null;
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
