package src.com.dassault.systemes.MultiThreading;

class Printer3 {
    public synchronized void printMethod(String message) {
        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println(line);
        }
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public void printBlock(String message) {
        String[] lines = message.split("\n");

        synchronized (System.out) {
            for (String line : lines) {
                System.out.println(line);
            }
        }

        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}

public class PerformanceTest {
    public static void main(String[] args) throws InterruptedException {
        Printer3 printer = new Printer3();

        // Test synchronized method
        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(() -> printer.printMethod("Hello from Thread 1"));
        Thread t2 = new Thread(() -> printer.printMethod("Hello from Thread 2"));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long endTime = System.currentTimeMillis();
        System.out.println("Synchronized Method Time: " + (endTime - startTime) + " ms");

        System.out.println();

        // Test synchronized block
        startTime = System.currentTimeMillis();
        t1 = new Thread(() -> printer.printBlock("Hello from Thread 1"));
        t2 = new Thread(() -> printer.printBlock("Hello from Thread 2"));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        endTime = System.currentTimeMillis();
        System.out.println("Synchronized Block Time: " + (endTime - startTime) + " ms");
    }
}

