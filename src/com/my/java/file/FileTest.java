package com.my.java.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class FileTest {
    @Test
    public void test1(){
        // 此时只是在内存里的对象，并不是真正的文件
        // 相对路径，默认在当前module下
        // 构造器1
        File file1 = new File("hello.txt");
        // 绝对路径
        File file2 = new File("E:\\Java\\Java_IDEA\\DataStructures\\src\\com\\my\\java\\file\\hello.txt");
        // 跨平台
        File file3 = new File("E:" + File.separator + "Java" + File.separator + "Java_IDEA");

        System.out.println(file1);
        System.out.println(file2);

        // 构造器2
        File file4 = new File("E:\\Java\\Java_IDEA","DataStructures");
        System.out.println(file4);

        // 构造器3
        File file5 = new File(file4,"src\\com\\my\\java\\file\\hello.txt");
        System.out.println(file5);
    }

    @Test
    public void test2(){
        File file1 = new File("hello.txt");
        File file2 = new File("E:\\Java\\Java_IDEA\\DataStructures\\src\\world.txt");

        System.out.println("绝对路径File2：");
        // 获取绝对路径
        System.out.println(file2.getAbsolutePath());
        // 获取路径
        System.out.println(file2.getPath());
        // 获取名称
        System.out.println(file2.getName());
        // 获取上层文件目录路径，若无，返回null
        System.out.println(file2.getParent());
        // 获取文件长度（即：字节数）不能获取目录的长度
        System.out.println(file2.length());
        // 获取最后一次修改的时间，毫秒值
        System.out.println(file2.lastModified());
        // 获取指定目录下的所有文件或者文件目录的名称数组
        System.out.println(Arrays.toString(new File(file2.getParent()).list()));
        // 获取指定目录下的所有文件或文件目录组成的File数组
        System.out.println(Arrays.toString(new File(file2.getParent()).listFiles()));


        System.out.println("相对路径File1：");
        // 获取绝对路径
        System.out.println(file1.getAbsolutePath());
        // 获取路径
        System.out.println(file1.getPath());
        // 获取名称
        System.out.println(file1.getName());
        // 获取上层文件目录路径，若无，返回null
        System.out.println(file1.getParent());
        // 获取文件长度（即：字节数）不能获取目录的长度
        System.out.println(file1.length());
        // 获取最后一次修改的时间，毫秒值
        System.out.println(file1.lastModified());
        // 获取指定目录下的所有文件或者文件目录的名称数组
        // System.out.println(Arrays.toString(new File(file1.getParent()).list()));  空指针异常
        // 获取指定目录下的所有文件或文件目录组成的File数组
        // System.out.println(Arrays.toString(new File(file1.getParent()).listFiles()));  空指针异常
    }

    @Test
    public void test3(){
        File file = new File("hello.txt");
        // 判断是否是文件目录
        System.out.println(file.isDirectory());
        // 判断是否是文件
        System.out.println(file.isFile());
        // 判断是否存在
        System.out.println(file.exists());
        // 判断是否可读
        System.out.println(file.canRead());
        // 判断是否可写
        System.out.println(file.canWrite());
        // 判断是否隐藏
        System.out.println(file.isHidden());
    }

    @Test
    public void test4() throws IOException {
        File file = new File("world.txt");
        if (!file.exists()){
            // 创建文件，若存在则不创建，返回false
            file.createNewFile();
            System.out.println("创建成功！");
        } else {
            // 删除文件或文件夹
            file.delete();
            System.out.println("删除成功！");
        }

        // 创建文件目录，若此文件目录存在，就不创建；如果此文件目录的上层目录不存在，则不创建
        File file1 = new File("d:\\io\\io1\\io2");
        System.out.println(file1.mkdir());
        // 创建文件目录，如果上层文件目录不存在，则一并创建
        File file2 = new File("d:\\io\\io1\\io2");
        System.out.println(file2.mkdirs());
    }

    @Test
    public void test5() throws IOException {
        File file = new File("E:\\Java\\Java_IDEA\\DataStructures\\src\\com\\my\\java");
        show(file);
    }

    private void show(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()){
                show(files[i]);
            }
            if (files[i].isFile()){
                System.out.println(files[i].getName());
            }
        }
    }

    @Test
    public void test6(){
        File file = new File("D:\\ioio");
        delete(file);
        file.delete();
    }

    private void delete(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()){
                delete(files[i]);
            }
            files[i].delete();
        }
    }
}
