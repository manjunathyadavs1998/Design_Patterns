package src.com.dassault.systemes.creational.singleton;

public class Singleton_3 {
    private static volatile Singleton_3 instance;  // Singleton instance (volatile ensures visibility in a multi-threaded environment)

    // Private constructor to prevent instantiation from outside the class
    private Singleton_3() {}

    // Public method to get the singleton instance
    public static Singleton_3 getInstance() {
        // First check (outside the synchronized block)
        if (instance == null) {
            synchronized (Singleton_3.class) {  // Locking to ensure thread-safety during initialization
                // Second check (inside the synchronized block)
                if (instance == null) {
                    instance = new Singleton_3();  // Create the singleton instance
                }
            }
        }
        return instance;
    }
}
