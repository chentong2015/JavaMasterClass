package JavaDataTime.timezone;

import java.time.ZoneId;

// ZoneId用于标识一个特定的时区(使用字符串Key来指定)
public class JavaZoneId {

    public static void main(String[] args) {
       ZoneId zoneId1 = ZoneId.of("Europe/London");
       ZoneId zoneId2 = ZoneId.of("America/Los_Angeles");
    }
}
