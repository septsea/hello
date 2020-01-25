import java.util.LinkedList;
import java.util.Iterator;

public class MyOwnList {

    public static void main(String... args) {
        String[] sixElements = { "1", "2", "3", "4", "5", "6" };
        LinkedList<String> myList = new LinkedList<String>();
        for (String element : sixElements) {
            myList.add(element);
        }
        Iterator<String> listIterator;
        listIterator = myList.iterator();
        System.out.println("The elements of the list are: ");
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();
        System.out.println("The element with index = 3 is " + myList.get(3));
        System.out.println("The index of the element \"1\" is " + myList.indexOf("1") + ".");
        System.out.println("The index of the element \"6\" is " + myList.indexOf("6") + ".");
        System.out.println("Removing the element with index = 4...");
        myList.remove(4);
        listIterator = myList.iterator();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();
        System.out.println("The element with index = 4 has been removed.");
    }

}
