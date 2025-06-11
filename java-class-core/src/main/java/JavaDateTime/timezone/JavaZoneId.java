package JavaDateTime.timezone;

import java.time.ZoneId;
import java.util.Set;

public class JavaZoneId {

    // ZoneId标识一个特定的时区(使用字符串Key来指定)
    public static void main(String[] args) {
       ZoneId zoneIdDefault = ZoneId.systemDefault();
       ZoneId zoneId1 = ZoneId.of("Europe/London");
       ZoneId zoneId2 = ZoneId.of("America/Los_Angeles");

       // 返回所有可用的时区ID
       Set<String> zoneIdSet = ZoneId.getAvailableZoneIds();
       for (String zoneId: zoneIdSet) {
           System.out.println(zoneId);
       }
    }
}
