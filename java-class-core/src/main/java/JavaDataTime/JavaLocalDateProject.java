package JavaDataTime;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 项目API案例：
// 1. 输入一个整型的年份，返回对应年份的所有LocalDate日期
// 2. 输入两个LocalDate日期，返回它们之间的LocalDate日期
public class JavaLocalDateProject {

    public static void main22(String[] args) {
        List<LocalDate> localDateList = getAllDaysInOneYear(2023);
        System.out.println(localDateList.get(0));
        System.out.println(localDateList.get(200));
    }

    // 输入一个日期字符串，返回这一年中的全部Date
    // 直接将字符串解析成Year类型的对象
    public static List<LocalDate> getAllDaysInOneYear(int year) {
        // TODO. 直接使用Year Class类型
        Year thisYear = Year.of(year);

        System.out.println(thisYear.atDay(1));
        System.out.println(thisYear.atDay(thisYear.length()));

        List<LocalDate> localDateList = new ArrayList<>();
        for (int index=1; index<=thisYear.length(); index++) {
            localDateList.add(thisYear.atDay(index));
        }
        return localDateList;
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
