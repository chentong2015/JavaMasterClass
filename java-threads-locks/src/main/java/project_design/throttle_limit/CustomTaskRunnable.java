package project_design.throttle_limit;

public class CustomTaskRunnable implements Runnable {

    private final CustomThrottleLimitImpl throttleLimitImplCopy;

    public CustomTaskRunnable(CustomThrottleLimitImpl implCopy) {
        this.throttleLimitImplCopy = implCopy;
    }

    @Override
    public void run() {
       try {
           System.out.println("Run CustomTaskRunnable");
           Thread.sleep(5000);

           this.throttleLimitImplCopy.releaseExecution();
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
    }
}
