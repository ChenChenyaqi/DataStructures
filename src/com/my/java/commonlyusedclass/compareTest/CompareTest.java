package com.my.java.commonlyusedclass.compareTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class CompareTest {
    public static void main(String[] args) {
        // 自然排序

        // Comparable接口使用举例
        String[] arr = new String[]{"AA", "bb", "abc", "dac"};
        // String实现了Comparable接口，重写了compareTo方法
        // 重写规则：如果this大于obj，返回正数，小于则返回负数，等于返回0
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        // 自定义类实现排序
        Goods[] goods = new Goods[4];
        goods[0] = new Goods("lenovoMouse", 34);
        goods[1] = new Goods("dellMouse", 43);
        goods[2] = new Goods("xiaomiMouse", 12);
        goods[3] = new Goods("huaweiMouse", 65);

        // sort排序
        Arrays.sort(goods);
        System.out.println("goods = " + Arrays.toString(goods));


        // 定制排序
        Arrays.sort(goods, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return Double.compare(o1.getPrice(),o2.getPrice());
            }
        });

        System.out.println("goods = " + Arrays.toString(goods));

    }

    @Test
    public void test1(){
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
    }
}

class Goods implements Comparable {

    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj instanceof Goods) {
            Goods goods = (Goods)obj;
            if (this.price > goods.price) {
                return 1;
            } else if (this.price < goods.price) {
                return -1;
            } else {
                return 0;
            }
        } else {
            throw new RuntimeException("传进来的不是Goods");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

