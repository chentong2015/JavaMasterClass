package JavaThreadsConcurrency.LiveLocksSolution;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 解决方案2: 将一直hold的锁给释放掉，并且确保一定能释放掉，避免对需要锁的线程造成影响
public class LiveLocksExample02 {

    /**
     * account1和account2之间的转账会不停的进行，由于无法转账成功，会退回到原来的账户
     * 然后在尝试转账，再次退回到原来的账户，如此持续Live lock状态: Transfer1 and Transfer2 never release the locks they are holding, keep looping
     */
    private void testLiveLocks() {
        BankAccount account1 = new BankAccount("12345-678", 500.00);
        BankAccount account2 = new BankAccount("98765-432", 1000.00);
        new Thread(new Transfer(account1, account2, 10.00), "Transfer1").start();
        new Thread(new Transfer(account2, account1, 55.88), "Transfer2").start();
    }
}

class BankAccount {
    private double balance;
    private String accountNumber;
    private Lock lock = new ReentrantLock();

    BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public boolean withdraw(double amount) {
        if (lock.tryLock()) {
            try {
                try {
                    Thread.sleep(100);  // Simulate database access
                } catch (InterruptedException ignored) {
                }
                balance -= amount;
                System.out.printf("%s: Withdrew %f\n", Thread.currentThread().getName(), amount);
            } finally {
                lock.unlock();
            }
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (lock.tryLock()) {
            try {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
                balance += amount;
                System.out.printf("%s: Deposited %f\n", Thread.currentThread().getName(), amount);
            } finally {
                lock.unlock(); // 一定要释放已经获得的lock !!
            }
            return true;
        }
        return false;
    }

    public boolean transfer(BankAccount destinationAccount, double amount) {
        if (withdraw(amount)) {
            if (destinationAccount.deposit(amount)) {
                return true;
            } else {
                // The deposit failed. Refund the money back into the account.
                System.out.printf("%s: Destination account busy. Refunding money\n", Thread.currentThread().getName());
                deposit(amount);
            }
        }
        return false;
    }
}

class Transfer implements Runnable {
    private BankAccount sourceAccount, destinationAccount;
    private double amount;

    Transfer(BankAccount sourceAccount, BankAccount destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public void run() {
        while (!sourceAccount.transfer(destinationAccount, amount)) {
            continue;
        }
        System.out.printf("%s completed\n", Thread.currentThread().getName());
    }
}