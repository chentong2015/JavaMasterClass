package project_designs.throttle;

public class CustomTaskRunnable implements Runnable {

   @Override
   public void run() {
       System.out.println("Run holder thread");
       try {
           Thread.sleep(3000);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
   }
}
