package JavaBasic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// TODO. 时间的使用规范
//  - Date已经过时，不应该使用 => Date类本身是可变的
//  - 对于间歇式的时间，优先使用System.nanoTime(更加精确)，而非currentTimeMillis
// Java 8 API: LocalDate, LocalTime, LocalDateTime, Clock, Instant等类 => 不可变类型
public class Base7DateTime {

    public void getDateTime() {
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
    }

    public List<LocalDate> getDatesBetweenUsingJava9(LocalDate startDate, LocalDate endDate) {
        // 提取指定一个月中的所有天数
        YearMonth month = YearMonth.of(2021, Month.JANUARY);
        LocalDate firstOfMonth = month.atDay(1);
        LocalDate firstOfFollowingMonth = month.plusMonths(1).atDay(1);

        // .datesUntil() 获取两个LocalDate之间的天数，然后逐个处理日期
        firstOfMonth.datesUntil(firstOfFollowingMonth).forEach(System.out::println);

        // 提取两个Date之间的所有天数
        return startDate.datesUntil(endDate).collect(Collectors.toList());
    }

    public void testFormatDateTime() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(localDate.format(newFormatter));

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateFormatted = dateFormat.format(date);
    }
}
