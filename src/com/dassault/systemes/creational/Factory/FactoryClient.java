package src.com.dassault.systemes.creational.Factory;

interface Logger{
    void log(String message);
}

class ConsoleLogger implements  Logger{

    @Override
    public void log(String message) {
        System.out.println("Writing log to console "+message);
    }
}
class FileLogger implements  Logger{

    @Override
    public void log(String message) {
        System.out.println("Writing log to file "+message);
    }
}
class LoggerFactory {
    public  Logger createLogger(String loggerType) {
        return switch (loggerType.toLowerCase()) {
            case "console" -> new ConsoleLogger();
            case "file" -> new FileLogger();
            default -> throw new IllegalArgumentException("Unknown logger type: " + loggerType);
        };
    }
}
public class FactoryClient {
    public static void main(String[] args) {
        LoggerFactory loggerFactory = new LoggerFactory();
        loggerFactory.createLogger("console").log("some data to console....");
        loggerFactory.createLogger("file").log("some data to file");
        // loggerFactory.createLogger("invalid").log("some data to file");
    }



}

/*******
 * // Enum to handle Logger creation
 * enum LoggerType {
 *     CONSOLE {
 *         @Override
 *         public Logger createLogger() {
 *             return new ConsoleLogger();
 *         }
 *     },
 *     FILE {
 *         @Override
 *         public Logger createLogger() {
 *             return new FileLogger();
 *         }
 *     },
 *     DATABASE {
 *         @Override
 *         public Logger createLogger() {
 *             return new DatabaseLogger();
 *         }
 *     };
 *
 *     // Abstract method that must be implemented by each enum constant
 *     public abstract Logger createLogger();
 * }
 *
 * public class Main {
 *     public static void main(String[] args) {
 *         // Client code
 *         String loggerType = "database"; // It could be "console", "file", etc.
 *
 *         // Get the appropriate logger from the enum
 *         Logger logger = LoggerType.valueOf(loggerType.toUpperCase()).createLogger();
 *
 *         // Log a message using the logger
 *         logger.log("This is a log message.");
 *     }
 * }
 */
