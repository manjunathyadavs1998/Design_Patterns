package src.com.dassault.systemes.MultiThreading;


class Printer {
    public void print(String message) {
       for(int i=0; i<message.length(); i++){
           System.out.println(message.charAt(i));
       }
    }
}

class PrintJob implements Runnable {
    private final Printer printer;
    private final String message;

    public PrintJob(Printer printer, String message) {
        this.printer = printer;
        this.message = message;
    }

    @Override
    public void run() {
        printer.print(message);  // No synchronization
    }
}
public class PrinterDemoWithoutSync {

    public static void main(String[] args) {
        Printer printer = new Printer();

        // Two threads trying to print simultaneously
        Thread thread1 = new Thread(new PrintJob(printer, "Hello from Thread 1"));

        Thread thread2 = new Thread(new PrintJob(printer, "Hello from Thread 2"));

        thread1.start();
        thread2.start();
    }
}
