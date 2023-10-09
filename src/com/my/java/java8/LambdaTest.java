package com.my.java.java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class LambdaTest {

    @Test
    public void test1() {
        Runnable r1 = new Runnable() {

            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();

        System.out.println("=============================");

        // Lambda表达式
        Runnable r2 = () -> System.out.println("我爱北京故宫");
        r2.run();
    }


    @Test
    public void test2() {
        Comparator<Integer> t1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        System.out.println(t1.compare(5, 6));

        System.out.println("===========================");

        // Lambda
        Comparator<Integer> t2 = (o1, o2) -> Integer.compare(o1,o2);
        System.out.println(t2.compare(9, 8));
    }

    @Test
    public void test3() {
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("谎言和誓言的区别是什么呢？");

        System.out.println("====================================");

        Consumer<String> con2 = (s) -> System.out.println(s);
        con2.accept("一个是听得人当真了，一个是说的人当真了");
    }

    @Test
    public void test4() {
        Consumer<String> con2 = s -> System.out.println(s);
        con2.accept("一个是听得人当真了，一个是说的人当真了");
    }


    @Test
    public void test6() {

    }
}
