package src.com.dassault.systemes.creational.singleton;

import java.io.FileWriter;
import java.io.IOException;

class Logger {
    private String fileName;

    // Constructor to specify log file
    public Logger(String fileName) {
        this.fileName = fileName;
    }

    // Log method to write messages to the file
    public void log(String message) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class MultiLoggerDemo {
    public static void main(String[] args) {
        // Two different logger objects pointing to the same file
        Logger logger1 = new Logger("app.log");
        Logger logger2 = new Logger("app.log");

        // Simulate multiple threads writing to the same log file
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                logger1.log("Logger1: Message " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                logger2.log("Logger2: Message " + i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Logs written to app.log. Check for mixed-up entries.");
    }
}
