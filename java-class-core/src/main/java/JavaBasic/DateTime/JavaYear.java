package JavaBasic.DateTime;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class JavaYear {

    public static void main(String[] args) {
        List<LocalDate> localDateList = getAllDaysInOneYear("2023");
        System.out.println(localDateList.get(0));
        System.out.println(localDateList.get(200));
    }

    // 输入一个日期字符串，返回这一年中的全部Date
    // 直接将字符串解析成Year类型的对象
    public static List<LocalDate> getAllDaysInOneYear(String year) {
        final Year thisYear = Year.of(Integer.parseInt(year));
        System.out.println(thisYear.atDay(1));
        System.out.println(thisYear.atDay(thisYear.length()));

        List<LocalDate> localDateList = new ArrayList<>();
        for (int index=1; index<=thisYear.length(); index++) {
            localDateList.add(thisYear.atDay(index));
        }
        return localDateList;
    }
}
