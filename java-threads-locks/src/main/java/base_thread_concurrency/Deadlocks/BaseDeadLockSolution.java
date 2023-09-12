package base_thread_concurrency.Deadlocks;

// DeadLocks 基本解决方案:
// 1. 避免程序在逻辑上造成的死锁问题，缩小同步语句块
// 2. 让所有的线程获取锁的顺序一致 lock1 -> lock2 -> lock3
//    在wait()时必须释放掉已经拿到的锁，避免其他线程只能按照顺序拿到其中一部分锁 !!
public class BaseDeadLockSolution {

    private void testDeadlock() {
        final Teacher tutor = new Teacher();
        final Student student = new Student();
        student.setTeacher(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(() -> tutor.studyTime());
        Thread studentThread = new Thread(() -> student.handInAssignment());
        tutorThread.start();
        studentThread.start();
    }

    public class Teacher {

        private Student student;

        public void setStudent(Student student) {
            this.student = student;
        }

        public void studyTime() {
            synchronized (this) {
                synchronized (student) {
                    try {
                        this.wait();
                        student.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Study together");
                }
            }
        }
    }

    public class Student {

        private Teacher teacher;

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        public void handInAssignment() {
            synchronized (teacher) {
                synchronized (this) {
                    teacher.notifyAll();
                    this.notify();
                }
            }
        }
    }
}
