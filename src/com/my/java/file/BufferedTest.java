package com.my.java.file;

import org.junit.Test;

import java.io.*;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class BufferedTest {
    @Test
    public void test1() {
        File srcFile = new File("迎新晚会.png");
        File destFile = new File("迎新晚会（2）.png");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 造节点流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            // 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] bytes = new byte[16];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 先关外层，再关内层
            // 关闭外层后，会自定关闭内层
            try {
                assert bis != null;
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert bos != null;
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
