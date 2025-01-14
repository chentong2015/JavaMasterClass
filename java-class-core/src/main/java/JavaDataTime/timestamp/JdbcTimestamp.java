package JavaDataTime.timestamp;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JdbcTimestamp {

    // TODO. 使用Epoch Milliseconds毫秒时长来构建
    public static void main(String[] args) {
        // time – milliseconds since January 1, 1970, 00:00:00 GMT
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp); // 2025-01-14 10:54:45.436

        Instant instant = timestamp.toInstant();
        Timestamp timestamp1 = new Timestamp(instant.toEpochMilli());
        System.out.println(timestamp1);
    }

    // TODO. Timestamp时间戳的格式化
    private static void formatTimestamp(Timestamp timestamp) {


    }

    // TODO. Timestamp -> LocalDateTime 基于JVM所在的当前时区
    // Create a LocalDateTime that represents same values as this Timestamp in local time zone.
    private static void convertTimestampToLocalDateTime(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        System.out.println(localDateTime);
    }
}
