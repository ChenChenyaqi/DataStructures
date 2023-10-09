package com.my.java.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class FileInputOutputStreamTest {
    @Test
    public void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File("迎新晚会.png");
            File desFile = new File("迎新晚会（1）.png");

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(desFile);

            byte[] bytes = new byte[16];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
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
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
