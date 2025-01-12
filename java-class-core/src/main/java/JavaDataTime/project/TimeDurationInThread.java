package JavaDataTime.project;

// TODO. 约束在单一线程中的Duration时间间隔统计
public class TimeDurationInThread {

    private final ThreadLocal<Long> localStorage = new ThreadLocal<>();

    public void start() {
        localStorage.set(System.currentTimeMillis());
    }

    public void finish(String methodName) {
        long startTime = localStorage.get();
        long finishTime = System.currentTimeMillis();
        long duration = finishTime - startTime;
        System.out.println(methodName + " takes " + duration + "ms");
    }
}
