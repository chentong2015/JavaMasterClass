package JavaDataTime.timestamp;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcTimestampQuery {

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
