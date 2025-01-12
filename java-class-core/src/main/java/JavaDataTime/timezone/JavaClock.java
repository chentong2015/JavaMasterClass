package JavaDataTime.timezone;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

// TODO. 本质上Clock时钟和时区对应
//  利用时钟来访问不同的时区的瞬时值，日期和时间
public class JavaClock {

    // A clock providing access to the current instant, date and time using a time-zone.
    public static void main(String[] args) {
        Instant instant = Instant.now(Clock.systemDefaultZone());

        LocalDate.now(Clock.systemDefaultZone());

        LocalDateTime localDateTime = LocalDateTime.now(Clock.systemDefaultZone());
    }
}
