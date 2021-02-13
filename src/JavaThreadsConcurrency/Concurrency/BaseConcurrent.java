package JavaThreadsConcurrency.Concurrency;

/**
 * Concurrency并发：支持"并发使用"设计的类型
 * 当多个线程在操该类型的同一个实例对象时，同一时刻，只有一个Thread能够在运行get()或者put()方法
 * 反之，如果两个并发的线程同时执行put()方法，会造成object对象的丢失
 */
public class BaseConcurrent {

    private Object boxContents;

    public synchronized Object get() {
        Object contents = boxContents;
        boxContents = null;
        return contents;
    }

    public synchronized boolean put(Object contents) {
        if (boxContents != null) {
            return false;
        }
        boxContents = contents;
        return true;
    }
    

}
