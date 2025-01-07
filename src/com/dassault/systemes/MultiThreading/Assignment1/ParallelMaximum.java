package src.com.dassault.systemes.MultiThreading.Assignment1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ParallelMaximum {
    public static void main(String[] args) {
        int max = 1000000000;  // Total number of elements
        int threadCount = 100; // Number of threads
        Random random = new Random();
        List<Integer> list = new ArrayList<>(max);
        long startTime = System.currentTimeMillis();
        int res=Integer.MIN_VALUE;
        for(int i=0; i<max; i++){
            list.add(random.nextInt());
            res=Math.max(res,list.get(i));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Synchronized Method Time: " + (endTime - startTime) + " ms");
        System.out.println(res);
    }
}
