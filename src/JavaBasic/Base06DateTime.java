package JavaBasic;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

// Java 8时间日期API
// LocalDate、LocalTime、LocalDateTime、Clock、Instant等类
// 使用"不变类型"的设计模式
public class Base06DateTime {

    public void testDateTime() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.MONTH)); // 0 - 11

        // 获取毫秒数
        Calendar.getInstance().getTimeInMillis();
        // 拿到系统当前的精确时间
        System.currentTimeMillis();
        Clock.systemDefaultZone().millis();

        // 获取当前时刻
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        System.out.println(today.getYear());

        // 格式日期的类型
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(localDate.format(newFormatter));
    }
}
