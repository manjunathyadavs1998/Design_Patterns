package src.com.dassault.systemes.MultiThreading;

public class MyClass extends Thread{

    public void run(){
        for(int i=1; i<=10; i++){
            System.out.println(i*2);
            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void printData(String str){
        for(int i=0; i<str.length(); i++){
            System.out.println(str.charAt(i));
        }
    }


}
