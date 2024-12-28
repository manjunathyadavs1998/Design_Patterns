package src.com.dassault.systemes.creational.singleton;

import java.io.FileWriter;
import java.io.IOException;

class LoggerSingleton {
    private static LoggerSingleton instance;
    private String fileName;

    // Private constructor to prevent instantiation
    private LoggerSingleton(String fileName) {
        this.fileName = fileName;
    }

    // Public method to provide access to the instance
    public static LoggerSingleton getInstance(String fileName) {
        if (instance == null) {
            synchronized (LoggerSingleton.class) { // Ensuring thread-safety
                if (instance == null) {
                    instance = new LoggerSingleton(fileName);  // Correct instantiation of LoggerSingleton
                }
            }
        }
        return instance;
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

public class MultiLoggerDemoSingleton {
    public static void main(String[] args) {
        // Using Singleton pattern to get a single LoggerSingleton instance
        LoggerSingleton loggerSingleton = LoggerSingleton.getInstance("app.log");

        // Simulate multiple threads writing to the same log file
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                loggerSingleton.log("Logger1: Message " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                loggerSingleton.log("Logger2: Message " + i);
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

        System.out.println("Logs written to app.log. Check for correct entries.");
    }
}
