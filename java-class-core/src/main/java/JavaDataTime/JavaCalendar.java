package JavaDataTime;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// TODO. Calendar提供时刻点到"日历信息属性"的转换
// Converting specific instant in time and a set of calendar fields.
//
// Calendar fields:
// YEAR, MONTH, WEEK, HOUR 年月周时
// MONDAY, TUESDAY, WEDNESDAY 星期几
// JANUARY, FEBRUARY, MARCH 月份信息
public class JavaCalendar {

    // 构建日历，基于Epoch的毫秒偏移量数据作为参数
    // millisecond offset from the Epoch
    public static void main(String[] args) {
        Calendar.Builder builder = new Calendar.Builder();
        Calendar calendar1 = builder.setInstant(System.currentTimeMillis()).build();
        int month = calendar1.get(Calendar.MONTH);
        int dayWeek = calendar1.get(Calendar.DAY_OF_WEEK);
        int weekOfYear = calendar1.getWeekYear();

        // TODO. 使用瞬时值构建Calendar日历
        Instant instant = Instant.now();
        Calendar calendar2 = builder.setInstant(instant.toEpochMilli()).build();
        int dayOfWeek = calendar2.get(Calendar.DAY_OF_WEEK);

        // 完全使用默认的参数来创建日历
        // Gets a calendar using the default time zone and locale.
        // The Calendar returned is based on the current time in the default time zone
        // with the default FORMAT locale.
        Calendar rightNow = Calendar.getInstance();
        long timeInMillis = rightNow.getTimeInMillis();
    }

    // TODO. 使用Calendar Field日历的Year类型来获取日期
    public static List<LocalDate> getAllDaysInOneYear(int year) {
        Year thisYear = Year.of(year);
        System.out.println(thisYear.atDay(1));
        System.out.println(thisYear.atDay(thisYear.length()));

        List<LocalDate> localDateList = new ArrayList<>();
        for (int index = 1; index <= thisYear.length(); index++) {
            localDateList.add(thisYear.atDay(index));
        }
        return localDateList;
    }

    // YearMonth 表示某年某月
    private void testYearMonth() {
        YearMonth month = YearMonth.of(2021, Month.JANUARY);
        LocalDate firstOfMonth = month.atDay(1);
        LocalDate firstOfFollowingMonth = month.plusMonths(1).atDay(1);
        System.out.println(firstOfMonth);
        System.out.println(firstOfFollowingMonth);
    }
}