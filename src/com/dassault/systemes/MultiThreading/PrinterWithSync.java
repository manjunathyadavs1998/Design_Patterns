package src.com.dassault.systemes.MultiThreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;


/**
 * import java.util.concurrent.locks.Lock;
 * import java.util.concurrent.locks.ReentrantLock;
 *
 * class Printer {
 *     private final Lock lock = new ReentrantLock();
 *
 *     public void print(String message) {
 *         lock.lock(); // Acquire the lock before printing
 *         try {
 *             // Simulate time taken to print
 *             System.out.println("Printing: " + message);
 *             try {
 *                 Thread.sleep(1000);  // Simulate time taken to print
 *             } catch (InterruptedException e) {
 *                 Thread.currentThread().interrupt();
 *             }
 *         } finally {
 *             lock.unlock(); // Ensure the lock is released after printing
 *         }
 *     }
 * }
 *
 * class PrintJob implements Runnable {
 *     private Printer printer;
 *     private String message;
 *
 *     public PrintJob(String message) {
 *         this.message = message;
 *         this.printer = PrinterSingleton.getInstance().getPrinter();
 *     }
 *
 *     @Override
 *     public void run() {
 *         printer.print(message);
 *     }
 * }
 *
 * public class SingletonPrinterDemo {
 *     public static void main(String[] args) {
 *         PrinterSingleton printerSingleton = PrinterSingleton.getInstance();
 *
 *         // Two threads trying to print simultaneously
 *         Thread thread1 = new Thread(new PrintJob("Hello from Thread 1"));
 *         Thread thread2 = new Thread(new PrintJob("Hello from Thread 2"));
 *
 *         thread1.start();
 *         thread2.start();
 *     }
 * }
 */

/**
 * public void print(String message) {
 *     // Split the message into lines using the newline character
 *     String[] lines = message.split("\n");
 *
 *     // Synchronize only the critical section (printing to console)
 *     for (String line : lines) {
 *         synchronized (System.out) {
 *             System.out.println(line);
 *         }
 *     }
 *
 *     // Simulate time taken to print
 *     try {
 *         Thread.sleep(1000);  // Simulate time taken to print
 *     } catch (InterruptedException e) {
 *         // Properly handle interruption and reset the interrupt flag
 *         Thread.currentThread().interrupt();
 *     }
 * }
 */
class Printer1 {
    public  synchronized void print(String message) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(message));
        String line;
        // Read the string line by line
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        System.out.println();

        try {
            Thread.sleep(1000);  // Simulate time taken to print
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class PrintJob1 implements Runnable {
    private final Printer1 printer;
    private final String message;

    public PrintJob1(Printer1 printer, String message) {
        this.printer = printer;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            printer.print(message);  // Synchronized method to avoid simultaneous access
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
public class PrinterWithSync {
    public static void main(String[] args) {
        Printer1 printer = new Printer1();

        // Two threads trying to print simultaneously
        Thread thread1 = new Thread(new PrintJob1(printer, "Hello from Thread 1.\nprinting the paper1 in Thread 1.\nprinting the paper2 in Thread 1."));
        Thread thread2 = new Thread(new PrintJob1(printer, "Hello from Thread 2.\nprinting the paper1 in Thread 2.\nprinting the paper2 in Thread 2."));

        thread1.start();
        thread2.start();
    }
}
