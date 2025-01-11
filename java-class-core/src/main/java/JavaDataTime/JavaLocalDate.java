package JavaDataTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// LocalDate 关于日期的处理，提供日期操作的API
public class JavaLocalDate {

    // TODO. 通过LocalDate + LocalTime来构建LocalDateTime
    // public static LocalDateTime of(LocalDate date, LocalTime time) {
    //    Objects.requireNonNull(date, "date");
    //    Objects.requireNonNull(time, "time");
    //    return new LocalDateTime(date, time);
    // }
    public void convertLocalDateToLocalDateTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(date, time);

        // Date 日期 + Time时间组合成 DateTime
        LocalDate localDate = LocalDate.now();
        LocalDateTime startDateTime = localDate.atStartOfDay();
        LocalDateTime localDateTime = localDate.atTime(20,16);
    }

    // TODO. 测试LocalDate的格式化
    public static void main1(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate); // 2023-12-29

        String pattern = "yyyy/MM/dd";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

        // TODO. 两种方式来调用格式化的API
        String localDateFormatted1 = dateTimeFormatter.format(localDate);
        System.out.println(localDateFormatted1);

        String localDateFormatted2 = localDate.format(dateTimeFormatter);
        System.out.println(localDateFormatted2);
    }
}
