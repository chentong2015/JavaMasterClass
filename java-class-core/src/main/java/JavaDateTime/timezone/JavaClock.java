package JavaDateTime.timezone;

import java.time.*;
import java.util.Date;
import java.util.TimeZone;

// TODO. Clock时钟: 包含时刻 + 时区
// A clock providing access to the current instant, date and time using a time-zone.
public class JavaClock {

    public static void main(String[] args) {
        // This returns the millisecond-based instant, measured from 1970-01-01T00:00Z (UTC).
        // This is equivalent to the definition of System.currentTimeMillis().
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        System.out.println(clock.getZone().getId()); // Europe/Paris

        Clock clock1 = Clock.system(ZoneId.of("Europe/London"));
        System.out.println(clock1); // SystemClock[Europe/London]
    }

    // TODO. 使用最佳可用的系统时钟(当前系统UTC时间)，返回当前的瞬时点
    //  Obtains a clock that returns the current instant using the best available system clock,
    //  converting to date and time using the UTC time-zone.
    private static void testClockSystemUtc() {
        Clock clock2 = Clock.systemUTC();
        System.out.println(clock2); // SystemClock[Z]
        System.out.println(clock2.millis());

        // 使用时区偏移，恢复成UTC+0的时间
        TimeZone timezone = TimeZone.getDefault();
        Date dateUTC = Date.from(clock2.instant().minusMillis(timezone.getRawOffset()));
        System.out.println(dateUTC);

        // 转化成系统所在时区的UTC的时间
        dateUTC = Date.from(clock2.instant());
        System.out.println(dateUTC);


        // 转换成UTC+0的基准时间
        LocalDateTime localDateTime = LocalDateTime.ofInstant(clock2.instant(), clock2.getZone());
        System.out.println(localDateTime);

        // 转换成系统所在时区的UTC时间
        localDateTime = LocalDateTime.ofInstant(clock2.instant(), ZoneId.systemDefault());
        System.out.println(localDateTime);
    }
}
