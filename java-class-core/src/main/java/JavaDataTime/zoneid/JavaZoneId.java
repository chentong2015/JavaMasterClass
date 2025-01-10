package JavaDataTime.zoneid;

import java.time.ZoneId;

// ZoneId用于标识一个特定的时区(使用字符串Key来指定)
public class JavaZoneId {

    public static void main(String[] args) {
       ZoneId zoneId = ZoneId.of("Europe/London");
    }
}
