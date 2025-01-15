package JavaDataTime.timestamp;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JdbcTimestamp {

    public static void main(String[] args) {
        // TODO. 使用格式化的字符串来创建Timestamp时间戳
        // s – timestamp in format yyyy-[m]m-[d]d hh:mm:ss[.f...].
        Timestamp timestamp2 = Timestamp.valueOf("2024-10-10 10:20:30");
        System.out.println(timestamp2);

        // TODO. 使用Epoch Milliseconds毫秒时长来构建
        // time – milliseconds since January 1, 1970, 00:00:00 GMT
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp); // 2025-01-14 10:54:45.436
        System.out.println(timestamp.getTime()); // Returns Epoch milliseconds
        System.out.println(timestamp.toLocalDateTime().toLocalDate());

        // 以下信息推荐使用Calendar来获取
        System.out.println(timestamp.getDate()); // DAY_OF_MONTH
        System.out.println(timestamp.getDay());  // DAY_OF_WEEK
        System.out.println(timestamp.getHours());  // 小时值
        System.out.println(timestamp.getMinutes());// 分钟值
    }

    // TODO. Timestamp时间戳的格式化
    private static void formatTimestamp(Timestamp timestamp) {
        String timestampFormat1 = new SimpleDateFormat("MM/dd/yyyy").format(timestamp);
        System.out.println(timestampFormat1);

        String timestampFormat2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
        System.out.println(timestampFormat2);
    }

    // TODO. Timestamp -> LocalDateTime 基于JVM所在的当前时区
    // Create a LocalDateTime that represents same values as this Timestamp in local time zone.
    private static void timestampToLocalDateTime(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        System.out.println(localDateTime);
        System.out.println(localDateTime.toLocalDate());
    }
}
