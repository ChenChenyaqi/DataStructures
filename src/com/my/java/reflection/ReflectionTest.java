package com.my.java.reflection;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class ReflectionTest {

    // 反射之前 对于Person类的操作
    @Test
    public void test1(){
        // 创建Person类的对象
        Person person = new Person("Jone", 18);
        // 调属性
        person.age = 20;
        System.out.println(person);
        // 调方法
        person.show();
    }

    // 运用反射 对Person类的操作
    @Test
    public void test2() throws Exception {
        // 1.通过反射，创建Person类的对象
        // 创建Class类的对象
        Class<Person> clazz = Person.class;
        // 创建构造器
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);
        // 用构造器造Person类的对象
        Person person = constructor.newInstance("Jone", 18);
        System.out.println(person);

        // 2.通过反射，调用对象指定的属性，方法
        // 获取属性age
        Field age = clazz.getDeclaredField("age");
        // 设置age的值
        age.set(person, 10);
        System.out.println(person);
        // 获取方法
        Method show = clazz.getDeclaredMethod("show");
        // 执行方法
        show.invoke(person);

        // 3.通过反射，可以调用私有方法、属性、构造器
        // 调私有构造器
        Constructor<Person> constructor1 = clazz.getDeclaredConstructor(String.class);
        // 设置可访问性，可以访问私有的东西
        constructor1.setAccessible(true);
        Person person1 = constructor1.newInstance("Jerry");
        System.out.println(person1);
        // 调私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person1, "Tom");
        System.out.println(person1);
        // 调私有方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        // 得到返回值
        String nation = (String)showNation.invoke(person1, "China");
        System.out.println("nation = " + nation);

    }

    @Test
    public void test3() throws ClassNotFoundException {
        // 方式一：调用运行时类的属性: .class
        Class<Person> clazz1 = Person.class;
        System.out.println("clazz1 = " + clazz1);
        // 方式二：通过运行时类的对象调用getClass()
        Person person = new Person("Jon", 20);
        Class<? extends Person> clazz2 = person.getClass();
        System.out.println("clazz2 = " + clazz2);
        // 方式三：调用Class的静态方法：forName(String 类的全路径)(用的多)
        Class<?> clazz3 = Class.forName("com.my.java.reflection.Person");
        System.out.println("clazz3 = " + clazz3);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);

        // 方式四：使用类的加载器：ClassLoader(了解)
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("com.my.java.reflection.Person");
        System.out.println("clazz4 = " + clazz4);

        System.out.println(clazz1 == clazz4);
    }

    @Test
    public void test4(){
        // Object类
        Class<Object> c1 = Object.class;
        // 接口
        Class<Comparable> c2 = Comparable.class;
        // 字符串数组
        Class<String[]> c3 = String[].class;
        // int数组
        Class<int[][]> c4 = int[][].class;
        // 枚举类
        Class<ElementType> c5 = ElementType.class;
        // 注解
        Class<Override> c6 = Override.class;
        // 基本数据类型int
        Class<Integer> c7 = int.class;
        // void
        Class<Void> c8 = void.class;
        // Class本身
        Class<Class> c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class<? extends int[]> c10 = a.getClass();
        Class<? extends int[]> c11 = b.getClass();
        // 只要元素类型和维度一样，就是一个Class
        System.out.println(c10 == c11);  // true
        int[][] c = new int[10][50];
        Class c12 = c.getClass();
        System.out.println(c10 == c12); // false
    }
}