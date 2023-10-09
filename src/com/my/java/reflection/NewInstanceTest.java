package com.my.java.reflection;

import org.junit.Test;

import java.util.Random;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class NewInstanceTest {

    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class<Person> personClass = Person.class;
        // newInstance()：创建对应的运行时类的对象
        // 其实是调了类的内部空参构造器（注意不能是private）
        Person person = personClass.newInstance();
        System.out.println(person);
    }

    @Test
    public void test2() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int num = new Random().nextInt(3); // 0, 1, 2
        String classPath = "";
        switch(num){
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.String";
                break;
            case 2:
                classPath = "com.my.java.reflection.Person";
                break;
            default:
                break;
        }
        Object obj = getInstance(classPath);
        System.out.println(obj);
    }

    public Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}
