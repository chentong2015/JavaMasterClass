package JavaDateTime.timestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;

// Timestamp <--> Instant 时间戳和瞬时点的相互转换
public class JdbcTimestampInstant {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());
        System.out.println(timestamp);

        Instant instantBack = timestamp.toInstant();
        Timestamp timestamp1 = new Timestamp(instantBack.toEpochMilli());
        System.out.println(timestamp1);

        // 获取时间戳对应的Calendar日历的相关属性信息
        Calendar.Builder builder = new Calendar.Builder();
        Calendar calendar = builder.setInstant(instantBack.toEpochMilli()).build();
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }
}
