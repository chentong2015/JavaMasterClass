package JavaBasic.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 1. Date 日期最简单的表示class，提供基础的API
// 2. Date 类型本身是可变的，其余几乎都是不可变类型
// 3. Date 已经过时，不应该使用，其中大部分的APIs都被废弃
public class JavaDate {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date); // Fri Dec 29 15:58:36 CET 2023

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateFormatted = dateFormat.format(date);
        System.out.println(dateFormatted); // 2023-12-29_15-58-36

        // time milliseconds after January 1, 1970 00:00:00 GMT.
        // 一旦设置时间便会从1970年开始计算
        date.setTime(10000);
    }

    // TODO. Date -> LocalDate
    // java.util.Date日期中包含具体的Time时刻/Instant瞬时，可转成LocalDate
    // java.sql.Date 同名类型，直接转成LocalDate
    public void convertDateToLocalDate() {
        Date date = new Date();

        // TODO. Date和Instant没有Time Zone的概念，需要添加时区
        LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
