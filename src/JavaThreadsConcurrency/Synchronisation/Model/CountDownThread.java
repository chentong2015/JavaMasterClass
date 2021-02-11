package JavaThreadsConcurrency.Synchronisation.Model;

public class CountDownThread extends Thread {

    private CountDown countDown;

    public CountDownThread(CountDown countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() {
        this.countDown.doCountDown();
    }
}
