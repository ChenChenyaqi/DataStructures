package com.my.java.file;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class IOTest {
    @Test
    public void test() {
        File file = new File("hello.txt");
        FileReader fr = null;

        try {
            // 字符输入流
            fr = new FileReader(file);
            // 每次读入一个字符，读到末尾返回-1
            int data;
            while ((data = fr.read()) != -1) {
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test1() {
        File file = new File("hello.txt");
        FileReader fr = null;
        try {
            // 字符输入流
            fr = new FileReader(file);
            char[] ch = new char[5];
            // 每次把文件里的数据读入到ch中，len表示一次读入的数据的长度，读到末尾返回-1;
            int len;
            while ((len = fr.read(ch)) != -1) {
                // 为避免重复输出，限定0到len只输出本次读到的长度
                System.out.print(new String(ch, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() {
        File file = new File("hello1.txt");
        FileWriter fw = null;
        try {
            // 向file中写入文本，第二个参数true表示追加不覆盖
            fw = new FileWriter(file, true);
            fw.write("I have a dream!");
            fw.write("I have a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fw != null;
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test3() {
        File src = new File("hello.txt");
        File des = new File("hello1.txt");
        FileReader fr = null;
        FileWriter fw = null;
        try {
            // 读入
            fr = new FileReader(src);
            // 写出
            fw = new FileWriter(des);

            char[] ch = new char[8];
            int len;
            while ((len = fr.read(ch)) != -1) {
                fw.write(ch, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert fw != null;
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
