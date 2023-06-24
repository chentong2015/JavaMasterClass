package jvm_basics.chapter13_Lock_ThreadSafety.JavaLock.ReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JavaReentrantReadWriteLock {

    Map<String, String> syncHashMap = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();

    // 写锁要互斥，必须等到写锁unlock之后才能获取锁
    Lock writeLock = lock.writeLock();

    public void put(String key, String value) {
        try {
            writeLock.lock();
            syncHashMap.put(key, value);
        } finally {
            // 写锁放在finally语句块中，确保一定能够释放
            writeLock.unlock();
        }
    }

    // 读锁不互斥
    Lock readLock = lock.readLock();

    public String get(String key) {
        try {
            readLock.lock();
            return syncHashMap.get(key);
        } finally {
            readLock.unlock();
        }
    }
}
