package JavaBasic.DateTime;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class JavaTimeZone {

    public static void main(String[] args) {
        TimeZone timezone = TimeZone.getDefault(); // 当前系统所在的默认时区
        System.out.println(timezone); // ZoneInfo[id="Europe/Paris",offset=3600000,dstSavings=3600000,,,]

        Instant instantDefault = Instant.now(Clock.systemUTC()).minusMillis(timezone.getRawOffset());
        Date dateUTC = Date.from(instantDefault);
        System.out.println(dateUTC); // 去掉当前时区的偏移量，返回UTC时间

        TimeZone timezoneLos = TimeZone.getTimeZone("America/Los_Angeles");
        System.out.println(timezoneLos);
        int offset = timezoneLos.getRawOffset() + timezoneLos.getDSTSavings();
    }
}
