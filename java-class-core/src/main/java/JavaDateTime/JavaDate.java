package JavaDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// TODO. 已经过时，其中大部分的APIs都被废弃 > 不建议使用
// 1. Date 日期最简单表示的class，提供基础的API
// 2. Date 类型本身是可变的，其余几乎都是不可变类型
public class JavaDate {

    // 默认使用“系统时区对应的毫秒时间”来初始化Date日期
    public static void main(String[] args) {
        // Allocates a Date object and initializes it.
        // it represents the time at which it was allocated, measured to the nearest millisecond.
        Date date = new Date(); // System.currentTimeMillis()
        System.out.println(date); // Fri Dec 29 15:58:36 CET 2023
        System.out.println(date.getYear());  // year from 1900.
        System.out.println(date.getMonth()); // 0 = January
        System.out.println(date.getDay());   // 2 = Tuesday

        // 设置日期，基于1970年起的毫秒时间
        // Time milliseconds after January 1, 1970 00:00:00 GMT.
        date.setTime(1000000);
        System.out.println(date); // Thu Jan 01 01:16:40 CET 1970
    }

    // TODO. Date类型的格式化
    public static void testDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateFormatted = dateFormat.format(date);
        System.out.println(dateFormatted); // 2023-12-29_15-58-36
    }
}
