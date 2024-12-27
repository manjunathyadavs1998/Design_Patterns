package src.com.dassault.systemes.creational.singleton;

public class Singleton_2 {
    // Private constructor to prevent instantiation
    private Singleton_2() {}

    // The instance is created when the inner class is loaded
    private static class SingletonHelper {
        private static final Singleton_2 INSTANCE = new Singleton_2();
    }

    // Public method to provide access to the instance
    public static Singleton_2 getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

