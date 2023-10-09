package com.my.java.enumAndAnnotations;

import java.util.Arrays;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class EnumTest {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring.getSeasonName());

        // ****************************************************

        Season1 autumn = Season1.AUTUMN;
        System.out.println(autumn);
        System.out.println(autumn.getClass());
        // 定义的枚举类默认继承于Enum
        System.out.println(Season1.class.getSuperclass());
        System.out.println(autumn.getSeasonName());

        Season1[] values = Season1.values();
        System.out.println(Arrays.toString(values));

        System.out.println(Season1.valueOf("WINTER").getSeasonName());

        autumn.show();
        Season1.SPRING.show();
    }
}

interface Info{
    void show();
}

// 使用enum关键字
enum Season1 implements Info{
    // 枚举多个对象
    SPRING("春天","春暖花开"){
        @Override
        public void show() {
            System.out.println("春天在哪里~");
        }
    },
    SUMMER("夏天","夏日炎炎"){
        @Override
        public void show() {
            System.out.println("夏天夏天~~");
        }
    },
    AUTUMN("秋天","秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天在哪里~~·");
        }
    },
    WINTER("冬天","白雪皑皑"){
        @Override
        public void show() {
            System.out.println("冬天在哪里");
        }
    };

    // private final 属性
    private final String seasonName;
    private final String seasonDesc;

    // 私有化构造器
    private Season1(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }


    // 获取枚举对象属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public void show() {
        System.out.println("这是一个季节");
    }

//    @Override
//    public String toString() {
//        return "Season{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }
}

// jdk5.0之前的自定义枚举类
class Season{

    // private final 属性
    private final String seasonName;
    private final String seasonDesc;

    // 私有化构造器
    private Season(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    // 枚举多个public static final对象
    public static final Season SPRING = new Season("春天","春暖花开");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","白雪皑皑");

    // 获取枚举对象属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
