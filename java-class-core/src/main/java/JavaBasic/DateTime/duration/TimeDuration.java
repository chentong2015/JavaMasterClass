package JavaBasic.DateTime.duration;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// 统计两点时刻的Duration间隔
public class TimeDuration {

    private final DateTimeFormatter dateTimeFormatter;
    private final DateTimeFormatter timeFormatter;

    private LocalDateTime before;
    private LocalDateTime after;

    private TimeDuration() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    private TimeDuration(String dateTimeFormat, String timeFormat) {
        dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
        timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
    }

    public static TimeDuration create() {
        return new TimeDuration();
    }

    public static TimeDuration create(String dateTimeFormat, String timeFormat) {
        return new TimeDuration(dateTimeFormat, timeFormat);
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

    public String getDuration() {
        return LocalTime.ofSecondOfDay(getSeconds()).format(timeFormatter);
    }

    public final long getSeconds() {
        return Duration.between(before, after).getSeconds();
    }

    public long getItemPerSecond(long itemCount) {
        long seconds = getSeconds();
        double itemPerSecond = ((double) itemCount / (double) (seconds != 0 ? seconds : 1));
        return Math.round(itemPerSecond);
    }
}