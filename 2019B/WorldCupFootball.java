package chap08;

import java.util.*;
import java.util.Map.Entry;

public class WorldCupFootball {

    public static void main(String... args) {
        String[] years = { "1958", "1962", "1966", "1970", "1974", "1978", "1982", "1986", "1990", "1994", "1998",
                "2002", "2006", "2010", "2014" };
        String[] countries = { "Switzerland", "Sweden", "Chile", "England", "Mexico", "Germany", "Argentina", "Spain",
                "Mexico", "Italy", "United States", "France", "Germany", "South Africa", "Brazil" };
        Map<String, String> dictionary = new TreeMap<String, String>();
        for (int i = 0; i < countries.length; i++) {
            dictionary.put(years[i], countries[i]);
        }
        Iterator<Entry<String, String>> iter;
        Entry<String, String> temporary_entry;
        iter = dictionary.entrySet().iterator();
        while (iter.hasNext()) {
            temporary_entry = iter.next();
            System.out.println(temporary_entry.getKey() + "\t" + temporary_entry.getValue());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a year: ");
        String year_input = scanner.nextLine();
        System.out.println(dictionary.containsKey(year_input) ? dictionary.get(year_input) : "Invalid input.");
        System.out.println("Please input a country: ");
        String country_input = scanner.nextLine();
        int flag = 0;
        iter = dictionary.entrySet().iterator();
        while (iter.hasNext()) {
            temporary_entry = iter.next();
            if (country_input.equals(temporary_entry.getValue())) {
                System.out.print(temporary_entry.getKey() + ", ");
                flag++;
            }
        }
        System.out.println(flag == 0 ? "Invalid input." : "");
        scanner.close();
    }

}
