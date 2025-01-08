package src.com.dassault.systemes.Java8.FunctionalInterface;

import java.util.*;

public class Func1{
    public static void main(String[] args) {
        IFunc1 square=(x,y)->x*y;
        IFunc1 rectangle=(x,y)-> x*y;
        IFunc1 triangle=(x,y)-> (int) (0.5*x*y);

        System.out.println(square.area(10,20));
        System.out.println(square.area(20,30));
        System.out.println(square.area(12,15));

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie","Alex", "Anan");
        names.forEach(name->{
            if(name.startsWith("A"))
                System.out.println(name);
        });
        names.forEach(System.out::println);
    //***************************************
        List<Integer>numberList=Arrays.asList(10,2,3,4,5,6,81,23,13,87);
        Collections.sort(numberList,(o1,o2)->{
            if (o1>o2) return -1;
            if (o1.equals(o2)) return 0;
            else return 1;
        });

        //********************************************************
        numberList=Arrays.asList(10,2,3,4,5,6,81,23,13,87);
        System.out.println(numberList);
        Collections.sort(numberList, (o1, o2) -> (o1 > o2) ? -1 : (o1.equals(o2) ? 0 : 1));

        System.out.println(numberList);

        System.out.println("**************************************************");
        TreeMap<Integer, String> m =
                new TreeMap<Integer, String>((o1, o2) -> o2.compareTo(o1));
        m.put(1, "Apple");
        m.put(4, "Mango");
        m.put(5, "Orange");
        m.put(2, "Banana");
        m.put(3, "Grapes");
        System.out.println("Elements of the TreeMap " +
                "after sorting are : " + m);


        System.out.println("***************************");
        Runnable r=()-> {

            try {
                System.out.println("Current Thread was "+Thread.currentThread().getName());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };
        Thread t1=new Thread(r);
        Thread t2=new Thread(r);
        Thread t3=new Thread(r);
        t1.start();
        t2.start();
        t3.start();
    }
}
