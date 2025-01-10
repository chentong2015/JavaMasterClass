package JavaDataTime.calendar;

import java.util.Calendar;

// Calendar: 提供瞬时点的转换器
// Converting specific instant in time and a set of calendar fields
// (such as YEAR, MONTH, DAY_OF_MONTH, HOUR)
public class JavaCalendar {

    public static void main(String[] args) {
        Calendar rightNow = Calendar.getInstance();
        long timeInMillis = rightNow.getTimeInMillis();
        int month = rightNow.get(Calendar.MONTH);
    }
}
