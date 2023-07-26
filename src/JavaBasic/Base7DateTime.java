package JavaBasic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

// TODO. 时间的使用规范
//  - Date已经过时，不应该使用 => Date类本身是可变的
//  - 对于间歇式的时间，优先使用System.nanoTime(更加精确)，而非currentTimeMillis
// Java 8 API: 不可变类型
//  - LocalDate, LocalTime, LocalDateTime, OffsetDateTime, Clock, Instant等类
public class Base7DateTime {

    public void getDateTime() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        Calendar.getInstance().getTimeInMillis();
        Clock.systemDefaultZone().millis();

        // 拿到系统当前的精确时间 + 获取当前的日期
        System.currentTimeMillis();
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        offsetDateTime.minus(1, ChronoUnit.DAYS);
        offsetDateTime.minusDays(1);
        offsetDateTime.plusDays(10);
        offsetDateTime.isAfter(OffsetDateTime.now());
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

    // Calculate a shiftedTimestamp in order to get a correct UTC timestamp in the DB
    // JDBC driver will use default timezone (TimeZone.getDefault()) of JVM to transform timestamp before storing in DB
    //
    // 1. Change the TimeZone of the JVM which is very impacting (impacts other processes running in same JVM)
    // 2. Override the JPA engine's timestamp setters in order not to use the default timezone
    //    however this should be done at the JPA API level (coretech)
    private static Date getShiftedUtcTimestamp() {
        TimeZone timezone = TimeZone.getDefault();
        int timezoneOffset = timezone.getRawOffset() + timezone.getDSTSavings(); // in ms

        Instant shiftedInstant = Instant.now(Clock.systemUTC()).minusMillis(timezoneOffset);
        return Date.from(shiftedInstant);
    }
}
