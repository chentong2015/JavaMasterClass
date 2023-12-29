package JavaBasic.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// 1. Date 日期最简单的表示class，提供基础的API
// 2. Date 类型本身是可变的，其余几乎都是不可变类型
// 3. Date 已经过时，不应该使用 !!
public class JavaDate {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date); // Fri Dec 29 15:58:36 CET 2023

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateFormatted = dateFormat.format(date);
        System.out.println(dateFormatted); // 2023-12-29_15-58-36
    }
}
