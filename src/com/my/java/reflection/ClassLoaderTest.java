package com.my.java.reflection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class ClassLoaderTest {

    @Test
    public void test1(){
        // 对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        // 调用系统类加载器的getParent(): 获取扩展类加载器
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);
        // 调用扩展类加载器的getParent(): 无法获取引导类加载器
        // 引导类加载器主要负责加载java的核心类库，无法加载自定义类
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
        // 加载String类也是引导类加载器进行加载
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);
    }

    @Test
    public void test2() throws Exception{
        Properties pros = new Properties();
        // 加载配置文件方式一：
        FileInputStream fis = new FileInputStream("src\\jdbc.properties");
        pros.load(fis);

        // 加载配置文件方式二：使用ClassLoader
        // 配置文件默认识别为：当前Module下的src里
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + " ," + "password = " + password);
    }
}
