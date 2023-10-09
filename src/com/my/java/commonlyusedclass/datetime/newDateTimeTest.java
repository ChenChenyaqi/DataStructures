package com.my.java.commonlyusedclass.datetime;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class newDateTimeTest {
    @Test
    public void testDate(){
        // LocalDate、LocalTime、LocalDateTime的使用
        // 获取当前本地的日期,通过now()
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("localDate = " + localDate);
        System.out.println("localTime = " + localTime);
        System.out.println("localDateTime = " + localDateTime);

        // 通过of()获取
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 8, 16, 18, 53);
        System.out.println("localDateTime1 = " + localDateTime1);

        // getXXX()
        int dayOfMonth = localDateTime1.getDayOfMonth();
        DayOfWeek dayOfWeek = localDateTime1.getDayOfWeek();
        int value = dayOfWeek.getValue();
        int hour = localDateTime1.getHour();
        System.out.println("dayOfMonth = " + dayOfMonth);
        System.out.println("dayOfWeek = " + dayOfWeek);
        System.out.println("value = " + value);
        System.out.println("hour = " + hour);

        // withXXX()
        LocalDateTime localDateTime2 = localDateTime1.withDayOfMonth(20);
        System.out.println("localDateTime2 = " + localDateTime2);

        // plusXXX() 加多少天，小时...
        LocalDateTime localDateTime3 = localDateTime1.plusHours(5);
        System.out.println("localDateTime3 = " + localDateTime3);

        // minusXXX() 减
        LocalDateTime localDateTime4 = localDateTime1.minusDays(6);
        System.out.println("localDateTime4 = " + localDateTime4);
    }

    @Test
    public void testDate1(){
        // UTC同一时间，和伦敦一样
        Instant instant = Instant.now();
        System.out.println("instant = " + instant);

        // 调整到本地时间，东八区，+8小时
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println("offsetDateTime = " + offsetDateTime);

        // 获取距离1970年开始的毫秒数
        long epochMilli = instant.toEpochMilli();
        System.out.println("epochMilli = " + epochMilli);

        // 通过毫秒数造对象
        Instant instant1 = Instant.ofEpochMilli(epochMilli);
        System.out.println("instant1 = " + instant1);

    }

    @Test
    public void testDate2(){
        // 构造自定义格式对象
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        // localDateTime -> string
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = dtf.format(localDateTime);
        System.out.println("localDateTime = " + localDateTime);
        System.out.println("format = " + format);

        // string -> TemporalAccessor
        TemporalAccessor accessor = dtf.parse(format);
        System.out.println(accessor);
    }
}
