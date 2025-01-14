package JavaDataTime.timezone;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

// TODO. TimeZone 表示时区的信息，包括偏移时间offset
// ZoneInfo[id="Europe/Paris",offset=3600000,dstSavings=3600000,,,]
public class JavaTimeZone {

    public static void main(String[] args) {
        // 当前系统所在的默认时区
        TimeZone timezone = TimeZone.getDefault();
        System.out.println(timezone);

        TimeZone timeZone1 = TimeZone.getTimeZone(ZoneId.of("Europe/London"));
        System.out.println(timeZone1);
        int offset = timeZone1.getRawOffset() + timeZone1.getDSTSavings();
    }

    // TODO. Date时间: 去掉当前时区的偏移量，返回UTC时间
    public static void testDateUtc() {
        TimeZone timezone = TimeZone.getDefault();

        Instant instantDefault = Instant.now(Clock.systemUTC()).minusMillis(timezone.getRawOffset());
        Date dateUTC = Date.from(instantDefault);
        System.out.println(dateUTC);
    }

    // Calculate a shiftedTimestamp in order to get a correct UTC timestamp in the DB
    private static Date getShiftedUtcTimestamp() {
        TimeZone timezone = TimeZone.getDefault();
        TimeZone.getTimeZone("1");
        int timezoneOffset = timezone.getRawOffset() + timezone.getDSTSavings(); // in ms

        Instant shiftedInstant = Instant.now(Clock.systemUTC()).minusMillis(timezoneOffset);
        return Date.from(shiftedInstant);
    }
}
