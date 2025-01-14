package JavaDataTime;

import java.time.*;
import java.time.format.DateTimeFormatter;

// TODO. LocalDateTime 当地具体的时间(时刻)，依赖于某个特定的时区
// LocalDatetime = LocalDate + LocalTime
public class JavaLocalDateTime {

    public static void main(String[] args) {
        // TODO. 默认使用系统所在的时区来获取时刻信息
        LocalDateTime today = LocalDateTime.now(); // now(Clock.systemDefaultZone());
        LocalDateTime yesterday = today.minusDays(1);
        System.out.println(yesterday); // 2023-12-28T16:30:36.889356
        System.out.println(yesterday.toLocalDate()); // 2023-12-28

        // TODO. 返回特定时区的此刻Now时间
        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Europe/London")));
        LocalDateTime now1 = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(now);
        System.out.println(now1);


        // TODO. LocalDateTime = LocalDate + LocalTime
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        localDateTime.toLocalDate();
        localDateTime.toLocalTime();

        // 指定一个日期中的特定某个时刻
        LocalDateTime startDateTime = localDate.atStartOfDay();
        LocalDateTime localDateTime2 = localDate.atTime(20,16);
    }

    // TODO. 格式化LocalDateTime的两种方式
    // Locale.FRANCE Pattern支持设置不同的Locale语言环境
    private void testLocalDateTimeFormatter(LocalDateTime localDateTime) {
        String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        String localDateTimeFormatted1 = dateTimeFormatter.format(localDateTime);
        System.out.println(localDateTimeFormatted1);

        String localDateTimeFormatted2 =  localDateTime.format(dateTimeFormatter);
        System.out.println(localDateTimeFormatted2);
    }

    // TODO. 带有UTC/Greenwich偏移量的DateTime时刻(存储Offset值信息)
    // "2nd October 2007 at 13:45:30.123456789 +02:00"
    private static void testOffsetDateTime() {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime.minusDays(2));
        System.out.println(offsetDateTime.plusDays(10));
        System.out.println(offsetDateTime.isAfter(OffsetDateTime.now()));
    }
}
