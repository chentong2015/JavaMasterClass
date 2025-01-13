package JavaDataTime.timestamp;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO. Timestamp本质上是Date日期的保证类型
// A thin wrapper around java.util.Date that
// allows the JDBC API to identify this as an SQL TIMESTAMP value.

// TODO. 处理时间戳在数据库中的存储
// JDBC driver will use default timezone (TimeZone.getDefault()) of JVM
// to transform timestamp before storing in DB
public class JavaJdbcTimestamp {


    // TODO. Timestamp时间戳和LocalDateTime时间相当
    // 2024-12-16 19:43:51.834134 时间戳的数据格式，包含完整时间点
    // 2024-12-18 03:43:51.834146

    public static void main(String[] args) {
        // TODO. Timestamp是Date类型的一个包装类型，方便映射到SQL Type的时间戳类型
        // A thin wrapper around java.util.Date that allows the JDBC API
        // to identify this as an SQL TIMESTAMP value.

        // TODO. Timestamp本质上代表DateTime(具体时刻)
        long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);

        // APP部署在法国，提取时间的显示 UI界面显示的是 法国时刻
        // 在英国看到的是法国时间，需要让它们看到自己的时间，根据它们当地的时间来预定booking

        // 在英国的用户将会预定英国的医生，给出的Slot是它们当地的时间
        // 这些信息在被存储到法国的DB Server上时应该包含Zone
        //


        // Timestamp 光是一个时间戳，无法表示一个完整的时间
        // 这个时间戳表示当时时间，定义时区才能转化成本地时间

        //

        // DB存储时间的瞬时点   -- long
        //
        // + DB存储瞬时点所对应的时区ZoneID  -- string
        //
        // 2025-01-12 18:00:00
        timestamp.toInstant().getEpochSecond();

        timestamp.toInstant().toEpochMilli();

        timestamp.toLocalDateTime().toLocalDate();


    }

    // TODO. 从DB中查询特定条件的时间戳数据
    public static List<Timestamp> getTimestampsFromDB(LocalDateTime dateTime) throws Exception {
        // The first date is inclusive, but the second is exclusive
        String query = "Select * from t_calendar where kind = 'opening' and timestamp >= ? and timestamp < ?";

        List<Timestamp> slotsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("url");
             PreparedStatement statement = connection.prepareStatement(query)) {

            // 注意java类型和java.sql类型映射
            // statement.setDate(0, new Date());
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
