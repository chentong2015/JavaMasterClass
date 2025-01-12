package JavaDataTime.timezone;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

// ZoneOffset 关于时区偏移量的信息
// A time-zone offset from Greenwich/UTC, such as +02:00.
public class JavaZoneOffset {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();

        localDateTime.toEpochSecond(ZoneOffset.ofHours(1));
    }
}
