package JavaDataTime.timezone;

import java.time.Clock;
import java.time.ZoneId;
import java.util.TimeZone;

// TODO. TimeZone 表示时区的信息
//  offset偏移时间: 表示当前时间与UTC+0时区的偏移时间
//  UTC+0基准时区时间 + offset偏移时间 = 某时区的时间
public class JavaTimeZone {

    // ZoneInfo[id="Europe/Paris",offset=3600000,dstSavings=3600000,,,]
    public static void main1(String[] args) {
        // 当前系统所在的默认时区
        TimeZone timezone = TimeZone.getDefault();
        System.out.println(timezone); // 3600000ms
        System.out.println(timezone.getRawOffset());

        TimeZone timeZone1 = TimeZone.getTimeZone(Clock.systemDefaultZone().getZone());
        System.out.println(timeZone1);

        // 获取特定ZoneID所在的时区
        TimeZone timeZone2 = TimeZone.getTimeZone(ZoneId.of("Europe/London"));
        System.out.println(timeZone1);
    }
}
