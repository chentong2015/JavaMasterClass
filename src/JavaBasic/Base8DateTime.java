package JavaBasic;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

// Java 8时间日期API:
// LocalDate, LocalTime, LocalDateTime, Clock, Instant等类
public class Base8DateTime {

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

    // .datesUntil()
    public List<LocalDate> getDatesBetweenUsingJava9(LocalDate startDate, LocalDate endDate) {
        // 提取指定一个月中的所有天数
        YearMonth month = YearMonth.of(2021, Month.JANUARY);
        LocalDate firstOfMonth = month.atDay(1);
        LocalDate firstOfFollowingMonth = month.plusMonths(1).atDay(1);
        firstOfMonth.datesUntil(firstOfFollowingMonth).forEach(System.out::println);

        // 提取两个Date之间的所有天数
        return startDate.datesUntil(endDate).collect(Collectors.toList());
    }
}
