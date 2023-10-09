package com.my.java.reflection;

import com.my.java.animal.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class GetFieldMethodConstructorTest {
    // 获取指定的属性: getField() 要求运行时类中属性声明为public
    @Test
    public void testField1() throws Exception{
        Class<Person> clazz = Person.class;
        // 创建运行时类的对象
        Person person = clazz.newInstance();
        // 获取id属性
        Field id = clazz.getField("id");
        // 设置值
        id.set(person,1001);
        // 获取值
        int pId = (int)id.get(person);
        System.out.println("pId = " + pId);
    }

    // 获取指定的属性: getDeclaredField() 可以获取私有属性,但要设置允许访问setAccessible(true);
    @Test
    public void testField2() throws Exception {
        Class<Person> clazz = Person.class;
        // 创建运行时类的对象
        Person person = clazz.newInstance();
        // 获取name私有属性
        Field name = clazz.getDeclaredField("name");
        // 保证当前属性可访问
        name.setAccessible(true);
        // 设置值
        name.set(person,"Tom");
        // 获取值
        String pName = (String)name.get(person);
        System.out.println("pName = " + pName);
    }


    // 获取指定的方法
    @Test
    public void test2() throws Exception{
        Class<Person> clazz = Person.class;
        // 创建运行时类的对象
        Person person = clazz.newInstance();
        // 获取show方法, 第二个参数表示要用哪个类的形参
        Method show = clazz.getDeclaredMethod("show", String.class);
        // 设置可访问性
        show.setAccessible(true);
        // 调用, 返回值默认为Object
        String nation = (String)show.invoke(person, "China");
        System.out.println("nation = " + nation);

        System.out.println("==============如何调用静态方法===============");
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        Object returnVal = showDesc.invoke(Person.class);
        System.out.println("returnVal = " + returnVal); // null
    }



    // 获取指定的构造器(用得少)
    @Test
    public void test3() throws Exception{
        Class<Person> clazz = Person.class;
        // 获取指定的构造器，参数指明构造器的参数列表
        Constructor<Person> constructor = clazz.getDeclaredConstructor(String.class);
        // 保证访问
        constructor.setAccessible(true);
        // 调构造器
        Person person = constructor.newInstance("Tom");
        System.out.println(person);
    }
}
