package JavaThreadsConcurrency.DeadlocksSolution.example02;

/**
 * DeadLock解决方案：约定获得锁的顺序
 * 即使约束获得lock顺序，由于调用wait()释放掉其中一个锁，别的线程仍然没有办法按照同样的顺序，获得所有需要的锁 !!
 */
public class BaseDeadLocks02 {

    private void testDeadlock() {
        final Teacher tutor = new Teacher();
        final Student student = new Student(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });
        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });
        tutorThread.start();
        studentThread.start();
    }
}