import java.util.*;

class MainClass {
    public static void main(String[] args) {
        String[] relatives = {"Father", "Mother", "Uncle", "Son", "Daughter", "Grandmother", "GrandFather", "Son", "Cousins", "Cousins",};
        HashMap<String, Integer> relat = new HashMap<>();
        for (String x : relatives) {
            relat.put(x, relat.getOrDefault(x,0)+1);
        }
        System.out.println(relat);

        Phonebook book = new Phonebook();
        book.add("Ivanov", "+798012312311");
        book.add("Sidorov", "+798012312322");
        book.add("Petrov", "+798012312333");
        book.add("Smirnov", "+798012312344");
        book.add("Olenov", "+798012312355");
        book.add("Sidorov", "+798012312366");


        System.out.println(book.get("Ivanov"));
        System.out.println(book.get("Smirnov"));
        System.out.println(book.get("Sidorov"));
        System.out.println(book.get("Olenov"));

    }
}