package com.my.java.file;

import org.junit.Test;

import java.io.*;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class OtherStreamTest {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            // 把System.in这个 字节输入流 转换为 字符输入流
            InputStreamReader isr = new InputStreamReader(System.in);
            // 套上缓冲流，为了调用BufferedReader里的readLine()方法
            br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("e") || line.equals("exit")){
                    break;
                }
                System.out.println(line.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void test2(){
        PrintStream ps = null;
        try {
            // 字节流
            FileOutputStream fos = new FileOutputStream("123.txt");
            // 创建打印流，自动刷新
            ps = new PrintStream(fos, true);
            if (ps != null){
                // 把标准输出流改为文件
                System.setOut(ps);
            }
            for (int i = 0; i <= 255; i++) {
                System.out.print((char)i);
                if (i % 50 == 0){
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert ps != null;
            ps.close();
        }
    }

}
