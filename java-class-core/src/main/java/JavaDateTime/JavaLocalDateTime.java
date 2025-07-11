package JavaDateTime;

import java.sql.Timestamp;
import java.time.*;

// TODO. LocalDateTime 当地具体的时间(时刻)，依赖于某个特定的时区
public class JavaLocalDateTime {

    public static void main(String[] args) {
        // TODO. 默认使用系统所在的时区来获取时刻信息
        LocalDateTime today = LocalDateTime.now(); // now(Clock.systemDefaultZone());
        LocalDateTime yesterday = today.minusDays(1);
        System.out.println(yesterday); // 2023-12-28T16:30:36.889356
        System.out.println(yesterday.toLocalDate()); // 2023-12-28

        // TODO. 返回特定时区的此刻Now时间
        LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());
        LocalDateTime now1 = LocalDateTime.now(Clock.system(ZoneId.of("Europe/London")));
        LocalDateTime now2 = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(now);

        LocalDateTime startDateTime = LocalDateTime.of(2024, 6, 10, 12, 0);
        LocalDateTime localDateTime = LocalDateTime.of(2024, 6, 10, 12, 1);
        // 必须是完全相同的时刻才会返回True
        System.out.println(localDateTime.isEqual(startDateTime));
    }

    // TODO. LocalDateTime = LocalDate + LocalTime
    private void buildLocalDateTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        localDateTime.toLocalDate();
        localDateTime.toLocalTime();

        // 指定一个日期中的特定某个时刻
        LocalDateTime startDateTime = localDate.atStartOfDay();
        LocalDateTime localDateTime2 = localDate.atTime(20,16);
    }

    // TODO. LocalDateTime -> Timestamp 基于Instant瞬时点来转换
    private void localDateTimeToTimestamp(LocalDateTime localDateTime) {
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        LocalDateTime now = LocalDateTime.now();
        Instant instant = Instant.ofEpochSecond(now.toEpochSecond(ZoneOffset.ofHours(1)));
        Timestamp timestamp2 = new Timestamp(instant.toEpochMilli());
    }
}
