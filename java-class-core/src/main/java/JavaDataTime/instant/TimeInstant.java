package JavaDataTime.instant;

import java.time.*;
import java.util.Date;
import java.util.TimeZone;

// Instant: An instantaneous point on the time-line
// - 时间线上的瞬时点, 一个计数的数值
// - 这个数值就基于不同的时区，对应的时刻有所不同
public class TimeInstant {

    // TODO. 基于不同的国家，存储DateTime时应该包含Zone时区信息
    // UK UTC+0
    // France UTC+1
    public static void main(String[] args) {

        // Instant instant = Instant.now(Clock.systemDefaultZone());

        // TODO. 从特定的LocalDateTime转换成instant数据存储

        // 获取特定UTC时区的Now瞬时时间
        Instant shiftedInstant0 = Instant.now(Clock.system(ZoneId.of("Europe/London")));
        System.out.println(shiftedInstant0.getEpochSecond());

        // 更加Instant瞬时时间值，恢复它原本的当地时间
        LocalDateTime localDateTime3 = LocalDateTime.ofInstant(shiftedInstant0, ZoneId.of("Europe/London"));
        System.out.println(localDateTime3);


        // TODO. 获取系统默认的时刻点
        // Obtains the current instant from the system clock.
        // This will query the system UTC clock to obtain the current instant.
        Instant instant = Instant.now();


        // TODO. 获取瞬时值偏移秒数的时间
        // Gets the number of seconds from the Java epoch of 1970-01-01T00:00:00Z.
        instant.getEpochSecond();
        // Converts this instant to the number of milliseconds from the epoch of 1970-01-01T00:00:00Z.
        instant.toEpochMilli();






        // Converts this instant to the number of milliseconds from the epoch of 1970-01-01T00:00:00Z.
        long millisecondOffsetEpoch = instant.toEpochMilli();

        // 获取系统UTC时区的Now瞬时时间 1736518960
        Instant shiftedInstant = Instant.now(Clock.systemUTC()); // "Europe/Paris"
        System.out.println(shiftedInstant.getEpochSecond());


        // TODO. Instant瞬时时间的处理, 可获取瞬时时间并将其转换成Date日期

        // 将顺时值转换成对应的Date日期
        Date date = Date.from(instant);

        // 2025-01-10 基于系统默认时区的Date
        LocalDate localDate = LocalDate.ofInstant(shiftedInstant, ZoneId.systemDefault());
        System.out.println(localDate);

        // 2025-01-10T15:23:34.570858 基于系统默认时区的DateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(shiftedInstant, ZoneId.systemDefault());
        System.out.println(localDateTime);


        // TODO. 瞬时值换算成不同的时区，所对应的时刻有所不同

        // 2025-01-10T22:23:34.570858 "换算"瞬时时间到不同时区的DateTime
        // LocalDateTime localDateTime2 = LocalDateTime.ofInstant(shiftedInstant, ZoneId.of("Asia/Shanghai"));
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(shiftedInstant, ZoneId.of("Europe/London"));
        System.out.println(localDateTime2);
    }

    public static void main1(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();

        Instant instant = Instant.ofEpochSecond(localDateTime.toEpochSecond(ZoneOffset.ofHours(1)));


    }


    // Calculate a shiftedTimestamp in order to get a correct UTC timestamp in the DB
    public static void main111(String[] args) {
        TimeZone timezone = TimeZone.getDefault();

        Instant instant = Instant.now(Clock.systemUTC());
        // System.out.println(instant);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, Clock.systemUTC().getZone());
        System.out.println(localDateTime);


        // Instant instantDefault = Instant.now(Clock.systemUTC()).minusMillis(timezone.getRawOffset());

        // Instant instantDefault = Instant.now(Clock.systemDefaultZone());
        Instant instantDefault = Instant.now(Clock.systemUTC());
        // System.out.println(instantDefault);
        Date dateUTC = Date.from(instantDefault);
        System.out.println(dateUTC);
    }

}
