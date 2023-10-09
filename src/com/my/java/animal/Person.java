package com.my.java.animal;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
@MyAnnotation(value = "hi")
public class Person extends Creature<String> implements Comparable<String>, MyInterface {

    private String name;
    int age;
    public int id;

    public Person() {
    }

    @MyAnnotation(value = "abc")
    private Person(String name) {
        this.name = name;
    }

    Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @MyAnnotation
    private String show(String nation){
        System.out.println("我的国籍是：" + nation);
        return nation;
    }

    public String display(String interest, int age) throws NullPointerException, ClassCastException{
        return interest + age;
    }

    private static void showDesc(){
        System.out.println("我是一个可爱的人");
    }
    @Override
    public void info() {
        System.out.println("我是一个人");
    }


    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
