package JavaDataTime;

import java.time.OffsetDateTime;

// TODO. 带有UTC/Greenwich偏移量的DateTime时刻(存储Offset值信息)
// "2nd October 2007 at 13:45:30.123456789 +02:00"
public class JavaOffsetDateTime {

    public static void main(String[] args) {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime.minusDays(2));
        System.out.println(offsetDateTime.plusDays(10));
        System.out.println(offsetDateTime.isAfter(OffsetDateTime.now()));
    }
}
