package JavaBasic.DateTime;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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
}
