package com.my.java.networkProgramming;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class URLTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/examples/beauty.jpg?username=Tom");
            // 获取URL的协议名
            System.out.println(url.getProtocol());
            // 获取URL的主机名
            System.out.println(url.getHost());
            // 获取URL的端口号
            System.out.println(url.getPort());
            // 获取URL的文件路径
            System.out.println(url.getPath());
            // 获取URL的文件名
            System.out.println(url.getFile());
            // 获取URL的查询名
            System.out.println(url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        HttpURLConnection urlConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            // 获取资源URL地址
            URL url = new URL("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180814%2Faa7d7eda9e6b43eab3ff121c3db367ab.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1632544545&t=9b278c42429957ade6f530b9670ef8a7");
            System.out.println("文件名 ： " + url.getFile());
            // 获取连接
            urlConnection = (HttpURLConnection)url.openConnection();
            // 建立连接
            urlConnection.connect();
            // 获取输入流
            is = urlConnection.getInputStream();
            // 写出文件
            fos = new FileOutputStream("王雨纯.png");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.disconnect();
        }
    }
}
