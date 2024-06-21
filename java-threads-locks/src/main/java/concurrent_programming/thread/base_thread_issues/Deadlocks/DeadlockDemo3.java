package concurrent_programming.thread.base_thread_issues.Deadlocks;

// 3. 两个对象调用自身方法时，都要调用到彼此对象的方法
//    由于在调用时对象已经获取到锁, 对方想要调用自身方法则无法拿到锁
public class DeadlockDemo3 {

    private void testDeadlock() {
        Data data = new Data();
        Display display = new Display();
        data.setDisplay(display);
        display.setData(data);

        data.updateDisplay();
        display.updateData();
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
