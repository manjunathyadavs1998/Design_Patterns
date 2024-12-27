package src.com.dassault.systemes.creational.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Program_1 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // Accessing Singleton instance through normal method call
        System.out.println(Singleton_1.getInstance().hashCode());

        // Breaking Singleton by creating a new instance using reflection
        Constructor<Singleton_1> constructor = Singleton_1.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton_1 newInstance = constructor.newInstance();

        // The new instance obtained via reflection is not the same as the original instance
        System.out.println(newInstance.hashCode());

        // Accessing Singleton again through normal method call
        System.out.println(Singleton_1.getInstance().hashCode());


    }
}
