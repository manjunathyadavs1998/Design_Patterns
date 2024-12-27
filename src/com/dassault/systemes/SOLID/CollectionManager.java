package src.com.dassault.systemes.SOLID;

import java.util.*;

public class CollectionManager {

    public void addNumbers(List<Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);
    }

    public void printNumbers(List<Integer> list) {
        for (Integer number : list) {
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        CollectionManager manager = new CollectionManager();

        // Using ArrayList
        List<Integer> list1 = new ArrayList<>();
        manager.addNumbers(list1);
        manager.printNumbers(list1);

        // Using LinkedList (Changing implementation without modifying other code)
        List<Integer> list2 = new LinkedList<>();
        manager.addNumbers(list2);
        manager.printNumbers(list2);
    }
}

