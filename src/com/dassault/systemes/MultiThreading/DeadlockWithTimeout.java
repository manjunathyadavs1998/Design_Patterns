package src.com.dassault.systemes.MultiThreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource2 {
    private final Lock lock = new ReentrantLock();
    private String name;

    public Resource2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Lock getLock() {
        return lock;
    }
}

public class DeadlockWithTimeout {

    private static final Resource2 resource1 = new Resource2("Resource1");
    private static final Resource2 resource2 = new Resource2("Resource2");

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            boolean lockedResource1 = false;
            boolean lockedResource2 = false;

            try {
                // Try to acquire the first resource
                lockedResource1 = resource1.getLock().tryLock();
                if (lockedResource1) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resource1.getName());

                    // Try to acquire the second resource
                    lockedResource2 = resource2.getLock().tryLock();
                    if (lockedResource2) {
                        System.out.println(Thread.currentThread().getName() + " locked " + resource2.getName());
                    } else {
                        System.out.println(Thread.currentThread().getName() + " could not acquire " + resource2.getName());
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not acquire " + resource1.getName());
                }
            } finally {
                // Release the locks only if they were acquired
                if (lockedResource1) {
                    resource1.getLock().unlock();
                    System.out.println(Thread.currentThread().getName() + " released " + resource1.getName());
                }
                if (lockedResource2) {
                    resource2.getLock().unlock();
                    System.out.println(Thread.currentThread().getName() + " released " + resource2.getName());
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            boolean lockedResource1 = false;
            boolean lockedResource2 = false;

            try {
                // Try to acquire the second resource
                lockedResource2 = resource2.getLock().tryLock();
                if (lockedResource2) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resource2.getName());

                    // Try to acquire the first resource
                    lockedResource1 = resource1.getLock().tryLock();
                    if (lockedResource1) {
                        System.out.println(Thread.currentThread().getName() + " locked " + resource1.getName());
                    } else {
                        System.out.println(Thread.currentThread().getName() + " could not acquire " + resource1.getName());
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not acquire " + resource2.getName());
                }
            } finally {
                // Release the locks only if they were acquired
                if (lockedResource2) {
                    resource2.getLock().unlock();
                    System.out.println(Thread.currentThread().getName() + " released " + resource2.getName());
                }
                if (lockedResource1) {
                    resource1.getLock().unlock();
                    System.out.println(Thread.currentThread().getName() + " released " + resource1.getName());
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}


/*
Explanation:
ReentrantLock: The tryLock() method is used to attempt to acquire a lock. If the lock is not available, the thread doesn’t block; instead, it returns false, and the thread can try again or move on.
This method avoids deadlock by allowing the thread to proceed if it cannot acquire all the necessary locks within a certain time.
Avoiding Nested Locks:
One of the best strategies to prevent deadlock is to minimize the use of nested locks. If possible, design your program so that only one lock is needed at a time, or if multiple locks are needed, acquire them in a consistent order.
Conclusion:
Deadlock occurs when threads are in a circular wait, each waiting on a resource held by another, creating a standstill.
The Lock Ordering method ensures that threads always acquire locks in the same order, preventing circular waits and deadlocks.
Using ReentrantLock with timeout or try-lock also provides an elegant solution by avoiding indefinite blocking and allowing threads to try to acquire locks within a certain period.
Minimizing nested locks and structuring the code to reduce dependencies on multiple resources helps avoid deadlock scenarios.
 */
