import java.util.HashSet;
import java.util.Iterator;

public class DoyoulikeJavaornot {

    public static void main(String... args) {
        HashSet<String> set = new HashSet<String>();
        HashSet<String> duplicateElements = new HashSet<String>();
        int counter = 0;
        for (String element : args) {
            if (!set.add(element)) {
                if (!duplicateElements.contains(element)) {
                    duplicateElements.add(element);
                    System.out.println("The " + num2ord(++counter) + " word occurring more than once: " + element);
                }
            }
        }
        Iterator<String> setiterator = set.iterator();
        System.out.println("The elements of the set are: ");
        while (setiterator.hasNext()) {
            System.out.print(setiterator.next() + " ");
        }
        System.out.println();
    }

    public static String num2ord(int x) {
        if (x < 0) {
            return "";
        } else if (x % 100 == 11 || x % 100 == 12 || x % 100 == 13) {
            return x + "th";
        } else if (x % 10 == 1) {
            return x + "st";
        } else if (x % 10 == 2) {
            return x + "nd";
        } else if (x % 10 == 3) {
            return x + "rd";
        } else {
            return x + "th";
        }
    }

}
