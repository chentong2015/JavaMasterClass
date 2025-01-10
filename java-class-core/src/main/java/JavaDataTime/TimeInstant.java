package JavaDataTime;

import java.time.*;

// Instant: 时间线上的瞬时点
// An instantaneous point on the time-line
public class TimeInstant {

    // TODO. 基于不同的国家，存储DateTime时应该包含Zone时区信息
    // UK UTC+0
    // France UTC+1
    public static void main(String[] args) {
        // TODO. 从特定的LocalDateTime转换成instant数据存储

        // 获取特定UTC时区的Now瞬时时间
        Instant shiftedInstant0 = Instant.now(Clock.system(ZoneId.of("Europe/London")));
        System.out.println(shiftedInstant0.getEpochSecond());

        // 更加Instant瞬时时间值，恢复它原本的当地时间
        LocalDateTime localDateTime3 = LocalDateTime.ofInstant(shiftedInstant0, ZoneId.of("Europe/London"));
        System.out.println(localDateTime3);


        // 获取系统UTC时区的Now瞬时时间 1736518960
        Instant shiftedInstant = Instant.now(Clock.systemUTC());
        System.out.println(shiftedInstant.getEpochSecond());


        // TODO. Instant瞬时时间的处理, 可获取瞬时时间并将其转换成Date日期

        // 2025-01-10 基于系统默认时区的Date
        LocalDate localDate = LocalDate.ofInstant(shiftedInstant, ZoneId.systemDefault());
        System.out.println(localDate);

        // 2025-01-10T15:23:34.570858 基于系统默认时区的DateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(shiftedInstant, ZoneId.systemDefault());
        System.out.println(localDateTime);

        // 2025-01-10T22:23:34.570858 "换算"瞬时时间到不同时区的DateTime
        // LocalDateTime localDateTime2 = LocalDateTime.ofInstant(shiftedInstant, ZoneId.of("Asia/Shanghai"));
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(shiftedInstant, ZoneId.of("Europe/London"));
        System.out.println(localDateTime2);
    }

    // TODO. 获取系统默认的瞬时点 + 基于某个时区下的瞬时点
    private void testInstantTime() {
        // 拿到系统当前的精确时间 + 获取当前的日期
        System.currentTimeMillis();

        // 对于间歇式的时间，优先使用System.nanoTime更精确
        System.nanoTime();

        Clock.systemDefaultZone().millis();
    }
}
