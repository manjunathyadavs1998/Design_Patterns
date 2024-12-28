package src.com.dassault.systemes.MultiThreading;



public class Volatile {

    // A shared variable that will be modified and read by multiple threads
    private volatile boolean flag = false;

    // Thread that will write the flag value
    public void writer() {
        try {
            Thread.sleep(2000); // Simulating some delay before writing the flag
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true; // Write operation
        System.out.println("Writer thread: flag set to true.");
    }

    // Thread that will keep reading the flag value
    public void reader() {
        while (!flag) {
            // Busy waiting (could be a task waiting for flag to become true)
        }
        System.out.println("Reader thread: flag is true!");
    }

    public static void main(String[] args) {
        Volatile example = new Volatile();

        // Create writer and reader threads
        Thread writerThread = new Thread(example::writer);
        Thread readerThread = new Thread(example::reader);

        // Start the threads
        writerThread.start();
        readerThread.start();
    }
}
