package JavaDataTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// LocalDate 关于日期的处理，提供日期操作的API
public class JavaLocalDate {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate); // 2023-12-29

        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(localDate.format(newFormatter));
    }

    // .datesUntil() 获取两个LocalDate之间的天数
    public List<LocalDate> getIntervalDatesWithJava9(LocalDate startDate, LocalDate endDate) {
        YearMonth month = YearMonth.of(2021, Month.JANUARY);
        LocalDate firstOfMonth = month.atDay(1);
        LocalDate firstOfFollowingMonth = month.plusMonths(1).atDay(1);
        firstOfMonth.datesUntil(firstOfFollowingMonth).forEach(System.out::println);

        return startDate.datesUntil(endDate).collect(Collectors.toList());
    }

    public static void main22(String[] args) {
        List<LocalDate> localDateList = getAllDaysInOneYear("2023");
        System.out.println(localDateList.get(0));
        System.out.println(localDateList.get(200));
    }

    // 输入一个日期字符串，返回这一年中的全部Date
    // 直接将字符串解析成Year类型的对象
    public static List<LocalDate> getAllDaysInOneYear(String year) {
        Year thisYear = Year.of(Integer.parseInt(year));
        System.out.println(thisYear.atDay(1));
        System.out.println(thisYear.atDay(thisYear.length()));

        List<LocalDate> localDateList = new ArrayList<>();
        for (int index=1; index<=thisYear.length(); index++) {
            localDateList.add(thisYear.atDay(index));
        }
        return localDateList;
    }

    // TODO. 通过LocalDate + LocalTime来构建LocalDateTime
    // public static LocalDateTime of(LocalDate date, LocalTime time) {
    //    Objects.requireNonNull(date, "date");
    //    Objects.requireNonNull(time, "time");
    //    return new LocalDateTime(date, time);
    // }
    public void convertLocalDateToLocalDateTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(date, time);

        LocalDate localDate = LocalDate.now();
        LocalDateTime startDateTime = localDate.atStartOfDay();
        LocalDateTime localDateTime = localDate.atTime(20,16);
    }
}
