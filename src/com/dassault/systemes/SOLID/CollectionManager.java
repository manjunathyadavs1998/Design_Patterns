import java.util.*;

public class CollectionManager {

    /**
     * Adds three predefined integers (10, 20, 30) to the provided list.
     *
     * @param list A List of integers where the numbers will be added.
     */
    public void addNumbers(List<Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);
    }

    /**
     * Prints all the elements of the provided list to the console.
     *
     * @param list A List of integers whose elements will be printed.
     */
    public void printNumbers(List<Integer> list) {
        for (Integer number : list) {
            System.out.println(number);
        }
    }

    /**
     * Main method to demonstrate the usage of CollectionManager.
     * It creates instances of ArrayList and LinkedList, adds numbers to them, and prints them.
     *
     * @param args Command-line arguments (not used).
     */
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
