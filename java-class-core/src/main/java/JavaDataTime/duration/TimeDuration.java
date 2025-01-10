package JavaDataTime.duration;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeDuration {

    private final DateTimeFormatter dateTimeFormatter;
    private final DateTimeFormatter durationFormatter;

    private LocalDateTime before;
    private LocalDateTime after;

    public TimeDuration() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.durationFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    // TODO. 获取两个DateTime之间的时间间隔，特定格式输出
    public String getDuration() {
        long seconds = Duration.between(before, after).getSeconds();
        return LocalTime.ofSecondOfDay(seconds).format(durationFormatter);
    }

    public void before() {
        before = LocalDateTime.now();
    }

    public void after() {
        after = LocalDateTime.now();
    }

    public String getBefore() {
        return before.format(dateTimeFormatter);
    }

    public String getAfter() {
        return after.format(dateTimeFormatter);
    }
}