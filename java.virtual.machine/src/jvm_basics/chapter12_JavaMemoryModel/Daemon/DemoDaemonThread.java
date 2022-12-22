package jvm_basics.chapter12_JavaMemoryModel.Daemon;

public class DemoDaemonThread {

    public static void main(String[] args) {
        new WorkerThread().start();
        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread ending");
    }
}
