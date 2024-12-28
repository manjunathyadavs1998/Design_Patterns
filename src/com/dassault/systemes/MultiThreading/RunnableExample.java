package src.com.dassault.systemes.MultiThreading;


public class RunnableExample implements Runnable {

    // Implement the run() method from Runnable interface
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Number: " + i);
            try {
                Thread.sleep(500); // Simulate some work by sleeping for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        // Create an instance of RunnableExample
        RunnableExample task = new RunnableExample();

        // Create threads and pass the Runnable task to each thread
        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Main thread continues to execute its own task
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main Thread - Number: " + i);
            try {
                Thread.sleep(300); // Simulate work in main thread
            } catch (InterruptedException e) {
                System.out.println("Main thread was interrupted.");
            }
        }

        System.out.println("All threads are running concurrently!");
    }
}
