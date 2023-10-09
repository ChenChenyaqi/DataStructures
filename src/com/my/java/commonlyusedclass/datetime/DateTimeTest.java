package com.my.java.commonlyusedclass.datetime;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class DateTimeTest {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            if (i == 20){
                System.exit(0);
            }
            System.out.println(i);
        }
    }

    @Test
    public void dateTest(){
        // 两个构造器的使用
        // 两个方法的使用
        // 空参构造器，创建当前的时间
        Date date = new Date();
        // 传入毫秒书，创建指定时间
        Date date1 = new Date(System.currentTimeMillis());
        // Date重写了toString方法，显示当前的年月日时分秒
        System.out.println("date = " + date);
        System.out.println("date1 = " + date1);

        // 返回毫秒数，获取当前date对象的时间戳
        long time = date.getTime();
        System.out.println("time = " + time);

        // 创建sqlDate
        java.sql.Date date2 = new java.sql.Date(time);
        System.out.println(date2.toString());

        // sql date -> util date
        // 多态
        Date date3 = date2;
        // util date -> sql date
        // 调用getTime()方法
        java.sql.Date date4 = new java.sql.Date(new Date().getTime());
    }

    @Test
    public void dateTest2(){
        // 1.格式化：日期 -> 字符串
        // 2.解析：字符串 -> 日期

        // 使用默认格式构造器（不好）
        SimpleDateFormat sdf = new SimpleDateFormat();
        // 格式化
        Date date = new Date();
        // 默认中午模式下
        String format = sdf.format(date);
        System.out.println("date = " + date);
        System.out.println("format = " + format);

        // 解析
        try {
            Date date2 = sdf.parse(format);
            System.out.println("date2 = " + date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // 指定格式进行构造
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = sdf1.format(date);
        System.out.println("format1 = " + format1);
        try {
            Date date1 = sdf1.parse("2016-12-31 12:05:00");
            System.out.println("date1 = " + date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // string -> sql date
        String str = "2000-4-23";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf2.parse(str);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            System.out.println(sqlDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void dateTest3(){
        // 构造
        Calendar calendar = Calendar.getInstance();

        // 常用方法
        // get()
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("dayOfMonth = " + dayOfMonth);
        System.out.println("dayOfWeek = " + dayOfWeek);
        // set()
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("dayOfMonth = " + dayOfMonth);
        // add()
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("dayOfMonth = " + dayOfMonth);
        // getTime()  calendar -> date
        Date date = calendar.getTime();
        System.out.println("date = " + date);
        // setTime()  date -> calendar
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

    }
}
