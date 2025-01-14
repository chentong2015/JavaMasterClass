package JavaDataTime.timezone;

import java.time.*;

// TODO. Clock时钟: 包含时区+对应的时刻
// A clock providing access to the current instant, date and time using a time-zone.
public class JavaClock {

    public static void main(String[] args) {
        // This returns the millisecond-based instant, measured from 1970-01-01T00:00Z (UTC).
        // This is equivalent to the definition of System.currentTimeMillis().
        Clock clock = Clock.systemDefaultZone();
        long epochMilliSeconds2 = clock.millis();
        String zoneId = clock.getZone().getId();
        System.out.println(zoneId); // Europe/Paris

        Clock clock1 = Clock.system(ZoneId.of("Europe/London"));
        System.out.println(clock1); // SystemClock[Europe/London]

        // TODO. 获取UTC+0时区的时钟(包含时刻和时区)
        Clock clock2 = Clock.systemUTC();
        System.out.println(clock2); // SystemClock[Z]

        LocalDateTime localDateTime = LocalDateTime.ofInstant(clock2.instant(), clock2.getZone());
        System.out.println(localDateTime);
    }
}
