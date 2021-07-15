package JavaThreadsConcurrency.DeadlocksSolution.example02;

public class Teacher {

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
