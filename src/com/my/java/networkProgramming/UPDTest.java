package com.my.java.networkProgramming;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class UPDTest {
    // UPD：数据和服务器地址、端口号一起封装在数据报中
    // TCP：先通过服务器地址和端口号进行连接，建立连接后，再传输数据

    @Test
    public void client() throws IOException {
        DatagramSocket socket = new DatagramSocket();

        String str = "我是UDP方式发送的导弹";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        // 封装成一个  数据报
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,8899);
        // socket发送数据报
        socket.send(packet);

        socket.close();
    }

    @Test
    public void server() throws IOException {
        // 指明端口号
        DatagramSocket socket = new DatagramSocket(8899);
        byte[] data = new byte[100];
        // 创建packet进行接收数据
        DatagramPacket packet = new DatagramPacket(data, 0, data.length);
        // 接收数据报
        socket.receive(packet);

        System.out.println(new String(packet.getData(),0, packet.getLength()));
    }
}
