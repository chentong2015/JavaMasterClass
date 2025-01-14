package JavaDataTime.timestamp;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JdbcTimestamp {

    public static void main(String[] args) {
        // TODO. 使用Epoch Milliseconds毫秒时长来构建
        // time – milliseconds since January 1, 1970, 00:00:00 GMT
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp); // 2025-01-14 10:54:45.436
        System.out.println(timestamp.getTime()); // Returns Epoch milliseconds

        Instant instant = timestamp.toInstant();
        Timestamp timestamp1 = new Timestamp(instant.toEpochMilli());
        System.out.println(timestamp1);

        // 以特定格式的数据来构建Timestamp
        // s – timestamp in format yyyy-[m]m-[d]d hh:mm:ss[.f...].
        Timestamp timestamp2 = Timestamp.valueOf("2024-10-10");
        System.out.println(timestamp2);
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
    }
}
