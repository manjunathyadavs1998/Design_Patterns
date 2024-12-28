package src.com.dassault.systemes.MultiThreading;
/*
In this case, the reader thread may not see the updated value of flag set by the writer thread because the flag variable is not marked as volatile.
The reader thread might keep reading the cached value of flag in its local memory instead of fetching the latest value from main memory.
 */
class NonVolatileExample {
    private boolean flag = false;

    public void writer() {
        flag = true; // Write operation
    }

    public void reader() {
        while (!flag) {
            // Busy wait
        }
        System.out.println("Flag is true!");
    }

    public static void main(String[] args) {
        NonVolatileExample example = new NonVolatileExample();

        // Writer thread
        Thread writerThread = new Thread(example::writer);
        writerThread.start();

        // Reader thread
        Thread readerThread = new Thread(example::reader);
        readerThread.start();
    }
}

