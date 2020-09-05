/**
 * Program Extra Credit - BogoSort
 * Implementation of a "BogoSort" sorting algorithm, which continuously swaps random elements within a list until sorted
 * @author Anthony Norderhaug
 * @date 4/28/2020
 * CS 108 Section - 3
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Random;
import java.util.Collections;


public class BogoSort {
    public ArrayList<Integer> input = new ArrayList<Integer>();
    public long randomSeed;
    public int noOfRuns = 0;
    public boolean sorted = false; // class-level boolean variable identifying if ArrayList has been sorted or not

    /**
     * Parameterized constructor which uses String argument to identify a txt file. Then proceeds to print, sort, then
     * print again class-level ArrayList
     *
     * @param filename                      String specifying desired file's name
     */
    BogoSort(String filename) {
        readFromFile(filename);
        printList();
        sort();
        printList();
    }

    /**
     * readFromFile() identifies seed to be used for Random object and populates class-level ArrayList with identified
     * file's remaining values. File identification dependant on input stream and Scanner generated through String
     * parameter. Exceptions thrown if no file is identified or if file's data is incompatible with Integer.
     *
     * @param filename                      String specifying desired file's name
     */
    private void readFromFile(String filename) {
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            Scanner scan = new Scanner(inputStream);

            randomSeed = Integer.parseInt(scan.nextLine());
            String[] inputValues = scan.nextLine().split(",");

            for (String inputV: inputValues) {
                input.add(Integer.parseInt(inputV));
            }
        } catch (FileNotFoundException except) {
            System.out.println("File does not exist!");
            System.exit(0);
        } catch (NumberFormatException except) {
            System.out.println("Error in reading from file!");
            System.exit(0);
        }
    }

    /**
     * printList() prints out class-level ArrayList's values in specific format. Preceding strings determined by
     * class-level boolean identifying whether ArrayList has been sorted or not.
     */
    private void printList() {
        if (!sorted) {
            System.out.print("Initial List: ");
        }
        else {
            System.out.print("Sorted List in " + noOfRuns + " attempt(s): ");
        }
        input.forEach((i) -> System.out.print(i + ", "));
        System.out.println();
    }

    /**
     * sort() declares and initializes Random object w/ randomSeed int and swaps elements within class-level ArrayList
     * at random indices until sorted. sortCheck() used to identify current sorting.
     */
    private void sort() {
        Random randGen = new Random(randomSeed);

        while (!sorted) {
            if (sortCheck(input)) {
                sorted = true;
            } else {
                for (int i = 0; i < input.size(); i++) {
                    Collections.swap(input, i, randGen.nextInt(input.size()));
                }
                noOfRuns++;
            }
        }
    }

    /**
     * sortCheck() takes in argument ArrayList<Integer> and ensures all elements are greater than their predecessor,
     * verifying that the elements are in ascending order.
     *
     * @param input                         ArrayList<Integer> checked for ascending order
     * @return                              boolean indicating if input is sorted or not
     */
    private boolean sortCheck(ArrayList<Integer> input) {
        for (int i = 0; i < input.size() - 1; i++) {
            if (input.get(i) > input.get(i+1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Identification string
     *
     * @return                          "Program Extra Credit, Anthony Norderhaug"
     */
    public String getIdentificationString() {
        return "Program Extra Credit, Anthony Norderhaug";
    }

}
