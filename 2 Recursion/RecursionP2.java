/**
 * Program 2: Recursion
 * Program that manipulates and returns ArrayLists according to input ArrayLists or
 * a class-level ArrayList, initialized by a 1D array
 * CS108-3
 * 2/11/2020
 * @author Anthony Norderhaug
 */

import java.util.ArrayList;


public class RecursionP2 {
    // class-level ArrayList declaration
    ArrayList<Integer> classNumbers = new ArrayList<Integer>();

    /**
     * Default constructor.
     * Takes a 1D array as parameter and initializes the class-level ArrayList
     *
     * @param numbers 1D Integer array holding various int values
     */
    public RecursionP2(int[] numbers) {

        for (int i = 0; i < numbers.length; ++i) {
            classNumbers.add(numbers[i]);
        }
    }

    /**
     * Method that stores and removes each element of an input ArrayList until size is 0.
     * Once base case reached, recursion begins backtracking, returning each stored value
     * to an ArrayList's copy. Through backtracking, the ArrayList's values are formatted
     * in reverse.
     *
     * @param numbers ArrayList to base values off of (copied into local  variable)
     * @return        ArrayList based off param input but in reverse order
     */
    public ArrayList<Integer> reverseList(ArrayList<Integer> numbers) {
        ArrayList<Integer> numbersCopy =  new ArrayList<Integer>(numbers);
        int storedValue;

        if (numbersCopy.size() == 0) {
            return numbersCopy;
        }
        else {
            storedValue = numbersCopy.get(0);
            numbersCopy.remove(0);

            numbersCopy = reverseList(numbersCopy);

            numbersCopy.add(storedValue);
        }

        return numbersCopy;
    }

    /**
     * Method that creates an ArrayList copy of the class-level ArrayList. This copy
     * is then used as a param for reverseList(ArrayList<Integer>).
     *
     * @return      reverseList's return value given class-level ArrayList copy
     */
    public ArrayList<Integer> reverseList() {
        ArrayList<Integer> classCopy =  new ArrayList<Integer>(classNumbers);

        return reverseList(classCopy);
    }

    /**
     * Method that stores 2nd element of an ArrayList(odd index), removes the first and
     * second elements, then calls itself with the modified ArrayList. In doing so, all
     * odd-indexed values are stored. Once backtracking occurs, each stored odd value is
     * added to ArrayList's beginning to counteract the inherent reversal tendency.
     *
     * @param numbers   ArrayList to base values off of (copied into local variable)
     * @return          ArrayList based off param input but only odd indexed elements
     */
    public ArrayList<Integer> toOddList(ArrayList<Integer> numbers) {
        ArrayList<Integer> numbersCopy =  new ArrayList<Integer>(numbers);
        int storedValue;

        if (numbersCopy.size() == 0) {
            return numbersCopy;
        }
        else if (numbersCopy.size() == 1) {
            numbersCopy.remove(0);
            return numbersCopy;
        }
        else {
            storedValue = numbersCopy.get(1);
            numbersCopy.subList(0, 2).clear();

            numbersCopy = toOddList(numbersCopy);

            numbersCopy.add(0, storedValue);
        }

        return numbersCopy;
    }

    /**
     * Method that creates an ArrayList copy of the class-level ArrayList. This copy
     * is then used as a param for toOddList(ArrayList<Integer>).
     *
     * @return      toOddList's return value given class-level ArrayList copy
     */
    public ArrayList<Integer> toOddList() {
        ArrayList<Integer> classCopy =  new ArrayList<Integer>(classNumbers);

        return toOddList(classCopy);
    }

    /**
     * Method that stores 1st element of an ArrayList(even index), removes the first and
     * second elements, then calls itself with the modified ArrayList. In doing so, all
     * even-indexed values are stored. Once backtracking occurs, each stored even value
     * is added to the ArrayList normally, leading the values to also be reversed.
     *
     * @param numbers   ArrayList to base values off of (copied into local variable)
     * @return          ArrayList based off param input but only even indexed values in reverse order
     */
    public ArrayList<Integer> toEvenRevList(ArrayList<Integer> numbers) {
        ArrayList<Integer> numbersCopy =  new ArrayList<Integer>(numbers);
        int storedValue;

        if (numbersCopy.size() == 0 || numbersCopy.size() == 1) {
            return numbersCopy;
        }
        else {
            storedValue = numbersCopy.get(0);
            numbersCopy.subList(0, 2).clear();

            numbersCopy = toEvenRevList(numbersCopy);

            numbersCopy.add(storedValue);
        }

        return numbersCopy;
    }

    /**
     * Method that creates an ArrayList copy of the class-level ArrayList. This copy
     * is then used as a param for toEvenRevList(ArrayList<Integer>).
     *
     * @return      toEvenRevList's return value given class-level ArrayList copy
     */
    public ArrayList<Integer> toEvenRevList() {
        ArrayList<Integer> classCopy =  new ArrayList<Integer>(classNumbers);

        return toEvenRevList(classCopy);
    }

    /**
     * Method that removes initial value of ArrayList copy and recurses until
     * only 1 element is present. Then, this element is returned. If ArrayList
     * has no elements, the value "-1" is returned.
     *
     * @param numbers   ArrayList to base values off of (copied into local variable)
     * @return          Integer that is param ArrayList's last element
     */
    public int retPenultimate(ArrayList<Integer> numbers) {
        ArrayList<Integer> numbersCopy = new ArrayList<Integer>(numbers);

        if (numbersCopy.size() == 0) {
            return -1;
        }
        else if (numbersCopy.size() == 1) {
            return numbersCopy.get(0);
        }
        else {
            numbersCopy.remove(0);
        }

        return retPenultimate(numbersCopy);
    }

    /**
     * Method that returns class-level ArrayList
     *
     * @return  class-level ArrayList
     */
    public ArrayList<Integer> getList() {
        return classNumbers;
    }

    /**
     * Method that returns student information
     *
     * @return      Program number, student name
     */

    public String getInfo() {
        return "Program 2, Anthony Norderhaug";
    }

}
