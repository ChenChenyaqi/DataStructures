package com.my.java.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class GenericTest {
    public static void main(String[] args) {
        Order<String,Integer> order = new Order<>("Jone", 18, "阿西吧",88);
        System.out.println(order);
    }


    @Test
    public void test(){
        List<Object> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add(123);
        list1.add("abc");
        list2.add("a123");
        list2.add("b456");

        List<?> list = null;
        list = list1;
        print(list);
        list = list2;
        print(list);

        print(list1);
        print(list2);

    }

    public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}

class Order<T,K> {
    private String name;
    private int age;
    private T orderT;
    private K orderK;

    public Order(String name, int age, T orderT, K orderK) {
        this.name = name;
        this.age = age;
        this.orderT = orderT;
        this.orderK = orderK;
    }

    public Order() {
    }

    public static  <E> void method1(E n){
        System.out.println(n);
    }

    public static  <E> void method2(E n){
        System.out.println(n);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    public K getOrderK() {
        return orderK;
    }

    public void setOrderK(K orderK) {
        this.orderK = orderK;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", orderT=" + orderT +
                '}';
    }
}
