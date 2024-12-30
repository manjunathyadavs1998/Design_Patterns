package src.com.dassault.systemes.MultiThreading;

class Resource {
    private final String name;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class DeadlockExample {

    private static final Resource resource1 = new Resource("Resource1");
    private static final Resource resource2 = new Resource("Resource2");

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + " locked " + resource1.getName());
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resource2.getName());
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + " locked " + resource2.getName());
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resource1.getName());
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}

/*
Explanation of the Code:
In the example above:

Thread 1 locks resource1, then waits for resource2 to be released.
Thread 2 locks resource2, then waits for resource1 to be released.
Both threads are in a state where they are holding one resource and waiting for the other, but neither can proceed because the other is also waiting for the resources.
What Happens?
Thread 1 locks resource1 and tries to lock resource2, but resource2 is already locked by Thread 2.
Thread 2 locks resource2 and tries to lock resource1, but resource1 is already locked by Thread 1.
Both threads are waiting for each other to release the lock, causing a deadlock. They will never proceed, and the program will hang indefinitely.

 */

