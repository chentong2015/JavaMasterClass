package JavaThreadsConcurrency.TreatmentAtomic;

/**
 * Atomic Action: 原子操作
 * 如果statement的操作是atomic原子操作，则在操作的过程中，线程是不能中断的 ! ===> TODO 17.7. Non-Atomic Treatment
 * 1. Atomic Action :
 * ___ 1.1 读写引用变量 object obj1 = obj2;
 * ___ 1.2 读写primitive type变量 myInt = 10;
 * ___ 1.3 读写所有声明"volatile"的变量 !!!
 * 2. Not Atomic Action :
 * ___ 2.1 读写long, double类型的值, JVM需要两步操作去完成
 */

public class AtomicAction {

    private void testAtomicAction() {
        // println() 输出操作不是原子操作，可能出现中断
        System.out.println("This is not an atomic action");
    }

}
