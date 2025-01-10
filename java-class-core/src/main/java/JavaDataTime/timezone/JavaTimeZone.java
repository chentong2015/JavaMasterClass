package JavaDataTime.timezone;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;


public class JavaTimeZone {

    public static void main(String[] args) {
        TimeZone timeZone0 = TimeZone.getTimeZone(ZoneId.of("Europe/London"));
        System.out.println(timeZone0);


        TimeZone timezone = TimeZone.getDefault(); // 当前系统所在的默认时区
        System.out.println(timezone); // ZoneInfo[id="Europe/Paris",offset=3600000,dstSavings=3600000,,,]

        Instant instantDefault = Instant.now(Clock.systemUTC()).minusMillis(timezone.getRawOffset());
        Date dateUTC = Date.from(instantDefault);
        System.out.println(dateUTC); // 去掉当前时区的偏移量，返回UTC时间

        TimeZone timezoneLos = TimeZone.getTimeZone("America/Los_Angeles");
        System.out.println(timezoneLos);
        int offset = timezoneLos.getRawOffset() + timezoneLos.getDSTSavings();
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
