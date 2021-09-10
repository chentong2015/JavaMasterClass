package JavaProxy.Java.DynamicProxy.DemoCase;

public class TimeMonitorUtil {

    private static ThreadLocal<Long> localStorage = new ThreadLocal<>();

    public static void start() {
        localStorage.set(System.currentTimeMillis());
    }

    public static void finish(String methodName) {
        long finishTime = System.currentTimeMillis();
        long duration = finishTime - localStorage.get();
        System.out.println(methodName + "takes " + duration + "ms");
    }
}
