package JavaBasic.DateTime;

import java.time.Clock;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class JavaTimeInstant {

    // Instant瞬时时间的处理, 可获取瞬时时间并将其转换成Date日期
    private void testInstantTime() {
        // 获取系统UTC时区的瞬时时间
        Instant shiftedInstant = Instant.now(Clock.systemUTC());
        Date date = Date.from(shiftedInstant);

        Clock.systemDefaultZone().millis();

        Calendar rightNow = Calendar.getInstance();
        long timeInMillis = rightNow.getTimeInMillis();
        int month = rightNow.get(Calendar.MONTH);
    }

    private void testSystemTime() {
        // 拿到系统当前的精确时间 + 获取当前的日期
        System.currentTimeMillis();

        // 对于间歇式的时间，优先使用System.nanoTime更精确
        System.nanoTime();
    }
}
