package JavaThreadsConcurrency.Deadlocks;

public class DeadlockExample02 {

    /**
     * DeadLocks 死锁的场景 02：
     * 两个线程互相拿到两个对象monitor上的其中一锁，然后执行另一个对象上的同步方法，
     * 由于对象的monitor已经被另一个线程获取，并锁住，
     * 所以两个线程所调用的方法都无法执行，造成死锁
     */
    private void testDeadlockExample1() {
        Data data = new Data();
        Display display = new Display();
        data.setDisplay(display);
        display.setData(data);
        data.updateDisplay(); // For Thread 1
        display.updateData(); // For Thread 2
    }

    private static class Data {

        private Display display;

        private void setDisplay(Display display) {
            this.display = display;
        }

        public synchronized void updateDisplay() {
            display.testDisplay();
        }

        private synchronized void testData() {
            System.out.println("Test data ...");
        }
    }

    private static class Display {

        private Data data;

        public void setData(Data data) {
            this.data = data;
        }

        public synchronized void updateData() {
            data.testData();
        }

        public synchronized void testDisplay() {
            System.out.println("Test display ...");
        }
    }
}
