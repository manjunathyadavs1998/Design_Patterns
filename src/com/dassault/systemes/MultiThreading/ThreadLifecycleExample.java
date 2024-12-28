package src.com.dassault.systemes.MultiThreading;

public class ThreadLifecycleExample {

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Thread running: " + Thread.currentThread().getName());
                Thread.sleep(1000); // Simulate some work, go to Timed Waiting state
                System.out.println("Thread waking up: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread1 = new Thread(task, "Thread 1");
        thread1.start();  // The thread enters Runnable state

        System.out.println("Thread 1 is alive: " + thread1.isAlive());  // It should be alive

        thread1.join();  // Main thread waits for thread1 to finish

        System.out.println("Thread 1 is alive: " + thread1.isAlive());  // It should be dead after join()
    }
}

