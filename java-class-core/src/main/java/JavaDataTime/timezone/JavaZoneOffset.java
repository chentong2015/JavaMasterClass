package JavaDataTime.timezone;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

// ZoneOffset 关于时区偏移量的信息
// A time-zone offset from Greenwich/UTC, such as +02:00.
public class JavaZoneOffset {

    // TODO. LocalDateTime在返回Epoch Seconds时间时
    //  必须提供时区的偏移量信息，用于计算数值的转换
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();

        // Converts this date-time to the number of seconds from the epoch of 1970-01-01T00:00:00Z.
        // This combines this local date-time and the specified offset to calculate the epoch-second value
        long epochSeconds = localDateTime.toEpochSecond(ZoneOffset.ofHours(1));
        System.out.println(epochSeconds);
    }
}
