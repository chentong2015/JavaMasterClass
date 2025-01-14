package JavaDataTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO. LocalDate 当前日期(哪一天)，依赖于某个特定的时区
public class JavaLocalDate {

    // TODO. 默认使用系统所在的默认时区来确定LocalDate日期
    public static void main(String[] args) {
        // Obtains the current date from the system clock in the default time-zone.
        // Query system clock in the default time-zone to obtain the current date.
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        // TODO. 获取特定时区的Date时间
        LocalDate.now(Clock.systemDefaultZone());
        LocalDate.now(ZoneId.systemDefault());

        // 基于瞬时值来构建LocalDate，并指定时区
        LocalDate localDate1 = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println(localDate1);
    }

    // TODO. LocalDate的格式化
    public static void testLocalDateFormat(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String localDateFormatted1 = dateTimeFormatter.format(localDate);
        System.out.println(localDateFormatted1);

        String localDateFormatted2 = localDate.format(dateTimeFormatter);
        System.out.println(localDateFormatted2);
    }

    // .datesUntil() 获取两个LocalDate之间的天数, 左闭右开
    public List<LocalDate> getIntervalDates(LocalDate startDate, LocalDate endDate) {
        // The returned stream starts from this date (inclusive) and
        // goes to endExclusive (exclusive) by an incremental step of 1 day.
        return startDate.datesUntil(endDate).collect(Collectors.toList());
    }
}
