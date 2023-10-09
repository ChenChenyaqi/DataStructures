package com.my.java.file;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
// InputStreamReader: 将一个字节输入流转为字符输入流
// OutputStreamWriter: 将一个字符输出流转为字节输出流

// 解码：字节 -> 字符
// 编码: 字符 -> 字节

// 字符集
public class InputStreamReaderTest {
    @Test
    public void test1() {
        // 字节流
        FileInputStream fis = null;
        FileOutputStream fos = null;
        // 缓冲流
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            fis = new FileInputStream("dbcp.txt");
            fos = new FileOutputStream("dbcp_gbk.txt");
            // 当初文件用什么字符集保存的，现在就用什么字符集读取
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            osw = new OutputStreamWriter(fos, "gbk");

            char[] ch = new char[16];
            int len;
            while ((len = isr.read(ch)) != -1) {
                System.out.print(new String(ch, 0, len));
                osw.write(ch,0,len);
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
                assert osw != null;
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
