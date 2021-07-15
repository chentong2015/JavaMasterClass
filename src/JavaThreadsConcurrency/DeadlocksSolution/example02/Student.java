package JavaThreadsConcurrency.DeadlocksSolution.example02;

public class Student {

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
