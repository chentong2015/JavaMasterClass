package JavaThreadsConcurrency.Deadlocks;

public class DeadlockExample03 {

    /**
     * DeadLocks 死锁的场景 03：
     * 一个线程调用一个对象的方法时，这个对象上的monitor被另外一个线程锁住了，导致无法执行
     * 另一线程同理, 无法调用另外一个对象的方法
     */
    private static void testDeadlockExample2() {
        Person chen = new Person("chen");
        Person tong = new Person("tong");
        new Thread(() -> chen.sayHelloToPerson(tong)).start();
        new Thread(() -> tong.sayHelloToPerson(chen)).start();
    }

    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void sayHelloToPerson(Person person) {
            System.out.println(this.name + " say hello to " + person.getName());
            person.sayHelloBackToPerson(this);
        }

        public synchronized void sayHelloBackToPerson(Person person) {
            System.out.println(this.name + " say hello back to " + person.getName());
        }
    }
}
