package com.my.java.reflection;

import com.my.java.animal.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class MoreReflectionTest {
    // 获取属性结构:
    @Test
    public void test1() {
        Class<Person> clazz = Person.class;
        // getFields()，会获取当前类以及父类的public属性
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }
        System.out.println();

        // getDeclaredFields(): 获取当前运行时类当中声明的所有属性（无视权限，但不包含父类）
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            System.out.println(f);
        }
    }

    // 可以进一步拿到：权限修饰符  数据类型  变量名
    @Test
    public void test2() {
        Class<Person> clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            // 1.权限修饰符
            int modifiers = f.getModifiers();
            System.out.print(Modifier.toString(modifiers) + "\t"); // Modifier类内部给各个权限赋予了一个数字
            // 2.数据类型
            Class<?> type = f.getType();
            System.out.print(type.getName() + "\t");
            // 3.变量名
            String fName = f.getName();
            System.out.print(fName);
            System.out.println();
        }
    }


    // 获取方法
    @Test
    public void test3() {
        Class<Person> clazz = Person.class;
        // getMethods(): 会获取当前类以及父类的public方法
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        System.out.println();

        // getDeclaredMethods(): 获取当前运行时类当中声明的所有属性（无视权限，但不包含父类）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m);
        }
    }

    // 可以进一步拿到：...
    @Test
    public void test4() {
        Class<Person> clazz = Person.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            // 0.注解
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a : annotations) {
                System.out.println(a);
            }
            // 1.权限修饰符
            int modifiers = m.getModifiers();
            System.out.print(Modifier.toString(modifiers) + "\t");
            // 2.返回值类型
            Class<?> returnType = m.getReturnType();
            System.out.print(returnType.getName() + "\t");
            // 3.方法名（形参1，形参2，...）
            System.out.print(m.getName());
            System.out.print("(");
            // 4.形参列表
            Class<?>[] parameterTypes = m.getParameterTypes();
            if (!(parameterTypes == null || parameterTypes.length == 0)) { // 如果有形参
                for (int i = 0; i < parameterTypes.length; i++) {
                    if (i == parameterTypes.length - 1) {
                        System.out.print(parameterTypes[i].getName() + " args_" + i);
                    } else {
                        System.out.print(parameterTypes[i].getName() + " args_" + i + ", ");
                    }
                }
            }
            System.out.print(")");
            // 5.throws XXXException
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if (!(exceptionTypes == null || exceptionTypes.length == 0)) {
                System.out.print(" throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (i == exceptionTypes.length - 1) {
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ", ");
                }
            }
            System.out.println();
            System.out.println();
        }
    }


    // 获取构造器
    @Test
    public void test5(){
        Class<Person> clazz = Person.class;
        // getConstructors() : 获取当前类的（public）构造器，不能拿到父类的构造器
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> c : constructors){
            System.out.println(c);
        }
        System.out.println();
        // getDeclaredConstructors(): 获取当前类的所有构造器（无视权限修饰符）
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> c : declaredConstructors){
            System.out.println(c);
        }
    }


    // 获取运行时类的父类及父类的泛型
    @Test
    public void test6(){
        Class<Person> clazz = Person.class;
        // getSuperclass()： 获取父类，但不带泛型
        Class<? super Person> superclass = clazz.getSuperclass();
        System.out.println(superclass);
        // getGenericSuperclass(): 获取带泛型的父类
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);

        // ParameterizedType类的对象的getActualTypeArguments()：获取带泛型的父类的泛型
        ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type t : actualTypeArguments){
            // Type本身也是Class
            Class<?> c = (Class<?>)t;
            System.out.println(c.getName());
        }
    }

    // 获取接口
    @Test
    public void test7(){
        Class<Person> clazz = Person.class;
        // getInterfaces()
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> c : interfaces){
            System.out.println(c);
        }
        System.out.println();
        // 获取运行时类的父类的接口
        Class<?>[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for (Class<?> c : interfaces1){
            System.out.println(c);
        }
    }

    // 获取所在的包
    @Test
    public void test8(){
        Class<Person> clazz = Person.class;
        // getPackage()
        Package aPackage = clazz.getPackage();
        System.out.println(aPackage);
    }

    // 获取注解
    @Test
    public void test9() {
        Class<Person> clazz = Person.class;
        // getAnnotations()
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation a : annotations){
            System.out.println(a);
        }
    }
}
