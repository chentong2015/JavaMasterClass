package JavaDataTime.timezone;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

// ZoneOffset 关于时区偏移量的信息
// A time-zone offset from Greenwich/UTC, such as +02:00.
public class JavaZoneOffset {

    public static void main(String[] args) {
        // 设定时区偏移量的大小
        ZoneOffset zoneOffset = ZoneOffset.ofHoursMinutes(1, 2);
        System.out.println(zoneOffset.getTotalSeconds());

        // TODO. 计算LocalDateTime的Epoch Seconds，考虑它相对时区的偏移量
        // Combines this local date-time and specified offset to calculate the epoch-second value
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        long epochSeconds = localDateTime.toEpochSecond(ZoneOffset.ofHours(0));
        System.out.println(epochSeconds); // 1736878989 +3600s

        epochSeconds = localDateTime.toEpochSecond(ZoneOffset.ofHours(1));
        System.out.println(epochSeconds); // 1736875389 +0
    }
}
