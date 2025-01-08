package src.com.dassault.systemes.Java8.Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    public static void main(String[] args) {
        List<Integer> inputList= Arrays.asList(2,4,6,7,9,10,13,19,23);
        List<Integer>outputList1=inputList
                .stream()
                .filter(n->n%2==0)
                .toList();
        System.out.println(outputList1);
        //Map Function
        System.out.println("***************************");
        List<Integer>outputList2=inputList
                .stream()
                .filter(n->n%2==1)
                .map(i->i+10)
                .toList();
        System.out.println(outputList2);

        //Reduce Function
        System.out.println("************************");
        Integer sum=inputList
                .stream()
                .reduce(0, Integer::sum);
        System.out.println(sum);



    }
}
