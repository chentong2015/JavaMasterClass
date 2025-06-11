package JavaDateTime.instant;

import java.time.*;
import java.util.Date;

// Instant: An instantaneous point on the time-line
// - 时间线上的瞬时点, 基于某个时区的一个计数数值
// - 时差换算: 瞬时点数值对应到不同时区的Time不同
public class TimeInstant {

    public static void main(String[] args) {
        // TODO. 获取特定时区所对应的瞬时点
        // Obtains the current instant from the system clock.
        // This will query the system UTC clock to obtain the current instant.
        Instant instant = Instant.now();
        Instant instant1 = Instant.now(Clock.systemUTC()); // "Europe/Paris"
        Instant instant2 = Instant.now(Clock.systemDefaultZone());
        Instant instant3 = Instant.now(Clock.system(ZoneId.of("Europe/London")));
        System.out.println(instant); // 2025-01-15T10:20:51.190469Z
        System.out.println(instant3);

        // Gets the number of seconds from the Java epoch of 1970-01-01T00:00:00Z.
        System.out.println(instant.getEpochSecond());
        System.out.println(instant3.getEpochSecond());

        // Converts this instant to the number of milliseconds from the epoch of 1970-01-01T00:00:00Z.
        System.out.println(instant.toEpochMilli());
    }

    // TODO. 将基于特定时区获取的Instant瞬时点转换成DateTime时刻
    public static void main2(String[] args) {
        Instant instant = Instant.now(Clock.system(ZoneId.of("Europe/Paris")));

        // Tue Jan 14 18:07:00 CET 2025
        Date date = Date.from(instant);
        System.out.println(date);

        // 基于系统默认时区的LocalDate 2025-01-14
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDate);

        // 基于系统默认时区的LocalDateTime 2025-01-14T18:07:00.305724
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime);

        // TODO. 时差换算: 计算瞬时值在其他时区的等效时间值
        // 2025-01-14T17:07:00.305724 (UTC+0 此刻时间慢1个小时)
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant, ZoneId.of("Europe/London"));
        System.out.println(localDateTime1);

        // 2025-01-15T01:07:00.305724 (亚洲 此刻时间快7个小时)
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        System.out.println(localDateTime2);
    }
}
