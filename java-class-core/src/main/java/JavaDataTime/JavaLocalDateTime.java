package JavaDataTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// DateTime提供比Date更加丰富的时间线处理APIs
public class JavaLocalDateTime {

    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);

        // 2023-12-28T16:30:36.889356
        System.out.println(yesterday);

        // 2023-12-28 只取DateTime的Date日期
        System.out.println(yesterday.toLocalDate());

        // 不能使用算数的方式来比较DateTime
        // System.out.println(today > yesterday);



        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime.minusDays(2));
        System.out.println(offsetDateTime.plusDays(10));
        System.out.println(offsetDateTime.isAfter(OffsetDateTime.now()));
    }

    // Locale.FRANCE Pattern支持设置不同的Locale语言环境
    private void testLocalDateTimeFormatter(LocalDateTime localDateTime) {
        String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        // TODO. 两种方式来调用格式化的API
        String localDateTimeFormatted1 = dateTimeFormatter.format(localDateTime);
        System.out.println(localDateTimeFormatted1);

        String localDateTimeFormatted2 =  localDateTime.format(dateTimeFormatter);
        System.out.println(localDateTimeFormatted2);
    }
}
