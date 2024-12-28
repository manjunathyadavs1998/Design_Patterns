package src.com.dassault.systemes.creational.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        // Thread 1 trying to access the singleton instance
        Thread thread1 = new Thread(() -> {
            Singleton_3 instance1 = Singleton_3.getInstance();
            System.out.println("Thread 1: " + instance1);
        });

        // Thread 2 trying to access the singleton instance
        Thread thread2 = new Thread(() -> {
            Singleton_3 instance2 = Singleton_3.getInstance();
            System.out.println("Thread 2: " + instance2);
        });

        thread1.start();
        thread2.start();
    }
}

