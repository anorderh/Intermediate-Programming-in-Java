/**
 * Program 6 - GenMethods
 * Implements and tests several generic methods used for manipulating and accessing ArrayLists and arrays.
 * @author Anthony Norderhaug
 * @date 4/8/2020
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;

public class GenMethods {

    /**
     * removeDuplicates() (generic method) takes in an ArrayList of type E, removes any reoccurring elements, and
     * returns the processed list
     *
     * @param list                  input ArrayList of type E
     * @param <E>                   type parameter, allows application on varying data types
     * @return                      ArrayList of type E with duplicates removed
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> processedList = new ArrayList<E>();

        for (E input: list) {
            if (processedList.contains(input)) {
                continue;
            }
            processedList.add(input);
        }

        return processedList;
    }

    /**
     * shuffle() (generic method) takes in an ArrayList of type E, swaps 2 random elements, and repeats for 29 more
     * iterations.
     * @param list                  input ArrayList of type E
     * @param <E>                   type parameter, allows application on varying data types
     */

    public static <E> void shuffle(ArrayList<E> list) {
        Random rand = new Random(340L);

        for (int i = 0; i < 30; i++) {
            Collections.swap(list, rand.nextInt(list.size()),rand.nextInt(list.size()));
        }
    }

    /**
     * max() (generic method) takes in an ArrayList of type E, sorts in ascending order, and returns last element to
     * method call.
     *
     * @param list                  input ArrayList of type E
     * @param <E>                   type parameter, allows application on varying data types
     * @return                      element E that is largest within input ArrayList
     */
    public static <E extends Comparable<E>> E max(ArrayList<E> list) {
        if (list.size() == 0) {
            return null;
        }
        Collections.sort(list);

        return list.get(list.size() - 1);
    }

    /**
     * linearSearch() (generic method) takes in an array of E and a single element of E, to be used as a key. All of
     * the array's contents are compared with the key and if equivalent element is found, int representing element's
     * index is returned. If not found, -1 is returned.
     *
     * @param list                  input array of type E
     * @param key                   input element E, to be compared with array's contents
     * @param <E>                   type parameter, allows application on varying data types
     * @return                      int representing 1st equivalent element's index, -1 if not found
     */
    public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * max() (generic method) takes in an array of E, sorts in ascending order, and returns to method call.
     *
     * @param list                  input array of type E
     * @param <E>                   type parameter, allows application on varying data types
     * @return                      element E that is largest within input array
     */
    public static <E extends Comparable<E>> E max(E[] list) {
        if (list.length == 0) {
            return null;
        }
        Arrays.sort(list);
        return list[list.length - 1];
    }

    /**
     * max() (generic method) takes in a 2-dimensional array of E, identifies the largest element through nested loops,
     * and returns to method call.
     *
     * @param list                  input 2-dimensional array of type E
     * @param <E>                   type parameter, allows application on varying data types
     * @return                      element E that is largest within input 2-dimensional array
     */
    public static <E extends Comparable<E>> E max(E[][] list) {
        if (list.length == 0) {
            return null;
        }
        E largestElement = list[0][0];

        for (E[] sublist: list) {
            for (E element: sublist) {
                if (element.compareTo(largestElement) > 0) {
                    largestElement = element;
                }
            }
        }
        return largestElement;
    }

    /**
     * Main method tests implemented generic methods
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        // Read in 'n' to represent number of elements
        int n = scnr.nextInt();

        // Use n to initialize array of Integers named 'list'
        // Use same input to initialize linked list of Integers named 'linked'
        Integer[] list = new Integer[n];
        LinkedList<Integer> linked = new LinkedList<Integer>();
        Integer inputElement;
        for (int i = 0; i < n; i++) {
            inputElement = scnr.nextInt();
            list[i] = inputElement;
            linked.add(inputElement);
        }

        // print 'list' and print 'linked'
        System.out.println(Arrays.toString(list));
        System.out.println(linked);

        // read in k key value for search, search 'list' for k, and print result
        Integer k = scnr.nextInt();
        int positionResult = linearSearch(list, k);

        if (positionResult == -1) {
            System.out.println("Key " + k + " was not found");
        }
        else {
            System.out.println("Key " + k + " was found at position" + positionResult);
        }

        // Call max(list) and print result
        System.out.println(max(list) + " is the max element");

        // Read in ints 'm' and 'p'. Use to initialize 2-dimensional array 'list2'
        int m = scnr.nextInt();
        int p = scnr.nextInt();
        Integer[][] list2 = new Integer[m][p];

        // Initialize 'list2' with m*p number of elements
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                list2[i][j] = scnr.nextInt();
            }
        }

        // Print 'list' with nested loops in rows of data on separate lines
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                System.out.print(list2[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Call max(list2) and print result
        System.out.println(max(list2) + " is the max element");

        // Instantiate ArrayList<Integer> 'alist' from LinkedList 'linked', then print
        ArrayList<Integer> alist = new ArrayList<Integer>(linked);
        System.out.println(alist);

        // Remove duplicate elements of 'alist' and print
        alist = removeDuplicates(alist);
        System.out.println(alist);

        // Shuffle elements of 'alist' and print
        shuffle(alist);
        System.out.println(alist);

        // Call max(alist) and print result
        System.out.println(max(alist) + " is the max element");
    }

    /**
     * getIdentificationString() returns student info in String
     *
     * @return                  "Program 6, Anthony Norderhaug"
     */
    public static String getIdentificationString() {
        return "Program 6, Anthony Norderhaug";
    }

}
