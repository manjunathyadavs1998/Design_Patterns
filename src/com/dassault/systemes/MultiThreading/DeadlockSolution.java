package src.com.dassault.systemes.MultiThreading;

class Resource1 {
    private String name;

    public Resource1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class DeadlockSolution {

    private static final Resource1 resource1 = new Resource1("Resource1");
    private static final Resource1 resource2 = new Resource1("Resource2");

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            // Lock resources in the same order: resource1 first, then resource2
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + " locked " + resource1.getName());
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resource2.getName());
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            // Lock resources in the same order: resource1 first, then resource2
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + " locked " + resource1.getName());
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resource2.getName());
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

/*
Explanation:
Lock Ordering: Both threads now acquire resource1 before resource2, preventing circular wait and thereby avoiding deadlock.
This guarantees that no circular dependencies are formed. The threads acquire resources in the same order, and there’s no possibility of a deadlock.
Timeout (Deadlock Detection):
Another approach is to try to acquire locks with a timeout. If a thread cannot acquire all the necessary locks within a certain period, it releases the locks it has and retries.
 */

