package JavaDateTime.instant;

public class TimeSystem {

    public static void main(String[] args) {
        // Return system default milliseconds difference,
        // between the current time and midnight, January 1, 1970 UTC.
        long epochMilliSeconds = System.currentTimeMillis();
        System.out.println(epochMilliSeconds);

        // TODO. 关于间歇式时间的精确测量，使用System.nanoTime更精确
        // 返回正在运行的Java虚拟机的高精度时间源的当前值(以纳秒为单位)
        // 此方法只能用于测量经过的时间，与任何其他系统或挂钟时间概念无关
        long startTime = System.nanoTime();
        // ... the code being measured ...
        long elapsedNanos = System.nanoTime() - startTime;
    }
}
