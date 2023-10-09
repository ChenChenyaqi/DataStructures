package com.my.java;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class CodeBlockTest {
    public static void main(String[] args) {
        // 调用静态属性，则静态代码块被加载到内存中并执行(只执行一次)
        // 静态代码块作用：1.可以初始化类的信息
        String desc = Person.desc;
        String desc2 = Person.desc;

        // 调用非静态资源，非静态代码块执行(多次执行)
        // 非静态代码块作用: 1.可以对对象属性进行初始化
        Person person = new Person();
        System.out.println("person.age = " + person.age);
        new Person();
    }
}

class Person{
    String name;
    int age;
    static  String desc = "I am a person!";

    public Person(){
    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    // 静态代码块
    static {
        System.out.println("Hello, static block");

    }

    // 非静态代码块
    {
        System.out.println("hello, block");
        age = 1;
    }

    public void eat(){
        System.out.println("Eating!");
    }

    public static void info(){
        System.out.println("I'm a so happy boy!");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
