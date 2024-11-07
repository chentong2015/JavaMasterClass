package JavaBasic.DateTime.duration;

// 统计起始到结束的时间间隙，约束在单一单线程中共享统计
public class TimeDurationUtil {

    private static ThreadLocal<Long> localStorage = new ThreadLocal<>();

    public static void start() {
        localStorage.set(System.currentTimeMillis());
    }

    public static void finish(String methodName) {
        long startTime = localStorage.get();
        long finishTime = System.currentTimeMillis();
        long duration = finishTime - startTime;
        System.out.println(methodName + " takes " + duration + "ms");
    }
}
