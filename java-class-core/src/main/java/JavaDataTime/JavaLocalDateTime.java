package JavaDataTime;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// DateTime提供比Date更加丰富的时间线处理APIs
public class JavaLocalDateTime {

    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);

        System.out.println(yesterday); // 2023-12-28T16:30:36.889356
        System.out.println(yesterday.toLocalDate()); // 2023-12-28

        // 不能使用算数的方式来比较DateTime
        // System.out.println(today > yesterday);

        // 格式化DateTime, 设置不同的Locale语言环境
        String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN, Locale.FRANCE);
        String yesterdayFormat = dateTimeFormatter.format(yesterday);
        System.out.println(yesterdayFormat);

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime.minusDays(2));
        System.out.println(offsetDateTime.plusDays(10));
        System.out.println(offsetDateTime.isAfter(OffsetDateTime.now()));


    }
}
