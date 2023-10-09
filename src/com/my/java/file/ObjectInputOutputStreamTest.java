package com.my.java.file;

import org.junit.Test;

import java.io.*;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class ObjectInputOutputStreamTest {
    @Test
    public void test1() {
        // 序列化
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            oos.writeObject(new Student(16, "Jone"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert oos != null;
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2(){
        // 反序列化
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));
            Object o = ois.readObject();
            Student student = (Student)o;
            System.out.println(student);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ois != null;
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

class Student implements Serializable {

    public static final long serialVersionUID = 421611535535L;

    private int age;
    private String name;

    public Student() {
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
