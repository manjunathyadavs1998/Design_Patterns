package src.com.dassault.systemes.MultiThreading;
//Extending using Thread Class
public class TestClass {
    public static void main(String[] args) {
        MyClass c=new MyClass();
        c.start();
        c.printData("Minal Bitne");
    }
}
