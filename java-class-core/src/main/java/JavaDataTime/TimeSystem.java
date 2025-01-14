package JavaDataTime;

import java.time.Clock;

// 获取系统默认的时间/时刻
public class TimeSystem {


    // System.currentTimeMillis()


    // TODO. 获取系统默认的瞬时点 + 基于某个时区下的瞬时点
    private void testInstantTime() {
        // 拿到系统当前的精确时间 + 获取当前的日期
        //
        // the difference, measured in milliseconds,
        // between the current time and midnight, January 1, 1970 UTC.
        System.currentTimeMillis();

        // 对于间歇式的时间，优先使用System.nanoTime更精确
        System.nanoTime();

        Clock.systemDefaultZone().millis();
    }
}
