package JavaDateTime.timezone;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

// ZoneOffset 关于时区偏移量的信息
// A time-zone offset from Greenwich/UTC, such as +02:00.
public class JavaZoneOffset {

    public static void main(String[] args) {
        // TODO. 获取基于某个特定时区的现在时间
        LocalDateTime importDate = LocalDateTime.now(ZoneOffset.UTC);
        System.out.println(importDate);

        // Combines this local date-time and specified offset to calculate the epoch-second value
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);


        // 设定时区偏移量的大小
        ZoneOffset zoneOffset = ZoneOffset.ofHoursMinutes(1, 2);
        System.out.println(zoneOffset.getTotalSeconds());

        long epochSeconds = localDateTime.toEpochSecond(ZoneOffset.ofHours(0));
        System.out.println(epochSeconds); // 1736878989 +3600s

        epochSeconds = localDateTime.toEpochSecond(ZoneOffset.ofHours(1));
        System.out.println(epochSeconds); // 1736875389 +0
    }
}
