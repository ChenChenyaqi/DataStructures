package com.my.java.networkProgramming;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class InetAddressTest {
    public static void main(String[] args) {
        try {
            // 通过ip地址
            InetAddress inet = InetAddress.getByName("192.168.10.14");
            System.out.println(inet);

            // 通过域名
            InetAddress inet1 = InetAddress.getByName("www.atguigu.com");
            System.out.println(inet1);
            InetAddress inet2 = InetAddress.getByName("www.wxcyq.love");
            System.out.println(inet2);

            // 得到本机ip
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);

            // 得到域名 字符串
            String hostName = inet1.getHostName();
            System.out.println(hostName);

            // 得到ip 字符串
            String hostAddress = inet1.getHostAddress();
            System.out.println(hostAddress);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
