package project_designs.throttle;

public class JvmThrottleLimitTest {

    public static void main(String[] args) throws InterruptedException {
        CustomThrottleLimitImpl throttleLimit = new CustomThrottleLimitImpl(3);

        for (int n=0; n<10; n++) {
            throttleLimit.put(new CustomTaskRunnable());
        }
        System.out.println(throttleLimit.getPermitNum());

        for (int n=0; n<5; n++) {
            try {
                throttleLimit.runTask();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
