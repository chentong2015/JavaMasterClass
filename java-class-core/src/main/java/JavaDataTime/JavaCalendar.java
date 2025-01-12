package JavaDataTime;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

// Calendar: 提供时刻点到"日历信息属性"的转换
// Converting specific instant in time and a set of calendar fields.
//
// Calendar fields:
// YEAR, MONTH, WEEK, HOUR 年月周时
// MONDAY, TUESDAY, WEDNESDAY 星期几
// JANUARY, FEBRUARY, MARCH 月份信息
public class JavaCalendar {

    public static void main(String[] args) {
        Calendar.Builder builder = new Calendar.Builder();

        // TODO. 使用瞬时值构建Calendar日历
        Instant instant = Instant.now();
        // Calendar calendar = builder.setInstant(System.currentTimeMillis()).build();

        // 构建日历，需要基于Epoch的毫秒偏移量数据作为参数
        // millisecond offset from the Epoch
        Calendar calendar = builder.setInstant(instant.toEpochMilli()).build();

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(dayOfWeek);


        // TODO. 使用Date日期构建Calendar日历
        Calendar calendar1 = builder.setInstant(new Date()) .build();
        int dayOfWeek1 = calendar1.get(Calendar.DAY_OF_WEEK);
        System.out.println(dayOfWeek1);


        Calendar rightNow = Calendar.getInstance();
        long timeInMillis = rightNow.getTimeInMillis();

        // TODO. 直接获取日历的某个属性
        // Returns the value of the given calendar field.
        int month = rightNow.get(Calendar.MONTH);
        int dayWeek = rightNow.get(Calendar.DAY_OF_WEEK);

        int weekOfYear = rightNow.getWeekYear();
    }
}
