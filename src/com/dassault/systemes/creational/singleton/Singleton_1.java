package src.com.dassault.systemes.creational.singleton;

public class Singleton_1 {
    private static final Singleton_1 instance=new Singleton_1();

    //Cannot be part of inheritance due to private constructor
    private Singleton_1() {
    }

    public static Singleton_1 getInstance(){
       return instance;
    }
}
