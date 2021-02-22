package JavaThreadsConcurrency.DeadlocksWaitNotify;

/**
 * 即使约束获得object lock的顺序，但由于调用wait()释放掉其中一个锁
 * 别的线程仍然没有办法按照同样的顺序，获得所有需要的锁 !!
 */
public class DeadLocksExample02 {

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

class Teacher {
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void studyTime() {
        synchronized (this) {
            System.out.println("Teacher has arrived");
            synchronized (student) {
                try {
                    this.wait();  // 这里只是释放掉了Teacher对象上的lock锁 !!!
                } catch (InterruptedException e) {
                }
                student.startStudy(); // Teacher is studying with student
            }
        }
    }

    public void getProgressReport() {
        System.out.println("Teacher gave progress report");
    }
}

class Student {

    private Teacher teacher;

    public Student(Teacher teacher) {
        this.teacher = teacher;
    }

    public void startStudy() {
        System.out.println("Student is studying");
    }

    public void handInAssignment() {
        synchronized (teacher) {
            teacher.getProgressReport();
            synchronized (this) {
                System.out.println("Student handed in assignment");
                teacher.notifyAll(); // 没有办法通知teacher object, 因为student's lock还被teacher object获取着
            }
        }
    }
}