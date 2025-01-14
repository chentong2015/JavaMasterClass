package JavaDataTime;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO. Timestamp时间戳 => 默认关联到JVM的当地时区 ！
// - 本质上Date类型的保证类型，等效于LocalDateTime时间
// - 本质上表示一个时刻，用于JDBC标识为SQL TIMESTAMP(JDBC Type)值
//
// TODO. JDBC 时区配置, 使用正确的时区转换Timestamp时间戳
// JDBC driver will use default timezone (TimeZone.getDefault()) of JVM
// to transform timestamp before storing in DB
// 1. JVM Level
//    Change the TimeZone of the JVM will impact other processes running in same JVM
// 2. JPA API level
//    Override the JPA engine's timestamp setters in order not to use the default timezone
public class JdbcTimestamp {

    // 时间戳基于1970年后的毫秒时间值来创建
    // time – milliseconds since January 1, 1970, 00:00:00 GMT
    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp); // 2025-01-14 10:54:45.436

        Instant instant = timestamp.toInstant();
        System.out.println(instant);


        // 使用瞬时值恢复时间戳，采用JVM所在的当前时区
        Timestamp timestamp1 = new Timestamp(instant.toEpochMilli());
        System.out.println(timestamp1);

        // 转换成LocalDateTime时也是基于JVM所在的当前时区
        // The conversion creates a LocalDateTime
        // that represents the same values as this Timestamp in the local time zone.
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        System.out.println(localDateTime);
    }

    // TODO. 从DB中查询特定条件的时间戳数据
    public static List<Timestamp> getTimestampsFromDB(LocalDateTime dateTime) throws Exception {
        String query = "Select * from t_calendar where kind = 'opening' and timestamp >= ? and timestamp < ?";
        List<Timestamp> slotsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("url");
             PreparedStatement statement = connection.prepareStatement(query)) {
            // statement.setDate(0, new Date()); 注意Java类型映射的关系
            statement.setTimestamp(1, Timestamp.valueOf(dateTime));
            statement.setTimestamp(2, Timestamp.valueOf(dateTime.plusDays(8)));

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Timestamp timestamp = results.getTimestamp("col_timestamp");
                slotsList.add(timestamp);
            }
        }
        return slotsList;
    }
}
