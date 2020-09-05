
import java.util.ArrayList;


public class RecursionP2Debug {

    public static void main(String[] args) {
        // declaring/initializing input ArrayList and varying arrays
        ArrayList<Integer> testValues = new ArrayList<Integer>();
        int[] values = {2, 5, 1, 4, 0};
        int[] values2 = {7, 9, 4, 0, 23};
        int index = 1;

        // initializing testValues
        for( int i = 0; i < values.length; ++i) {
            testValues.add(values[i]);
        }

        // creating instance of Recursion P2
        RecursionP2 practiceInstance = new RecursionP2(values2);


        // original values
        System.out.println("Original testValues: " + testValues);
        System.out.print("Original class values: ");
        for( int i = 0; i < values2.length; ++i) {
            System.out.print(values2[i] + " ");
        }

        System.out.println("\n");

        // calling reverseList, using testValues as parameter
        System.out.println("reverseList with testValues: " + practiceInstance.reverseList(testValues));

        // calling reverseList, using instance's class variable
        System.out.println("reverseList with class values: " + practiceInstance.reverseList());

        // calling toOddList with testValues as parameter
        System.out.println("toOddList with testValues: " + practiceInstance.toOddList(testValues));

        // calling toOddList, using instance's class variables
        System.out.println("toOddList with class values: " + practiceInstance.toOddList());

        // calling toEvenRevList, with testValues as parameter
        System.out.println("toEvenRevList with testValues: " + practiceInstance.toEvenRevList(testValues));

        // calling toEvenRevList, using instance's class variables
        System.out.println("toEvenRevList with class values: " + practiceInstance.toEvenRevList());

        // calling retPenultimate, using testValues as parameter
        System.out.println("retPenultimate with testValues: " + practiceInstance.retPenultimate(testValues));

        // calling getList, returning instance's class variables
        System.out.println("getList, returning original class values: " + practiceInstance.getList());

        // trial getInfo
        System.out.print("\n" + practiceInstance.getInfo());

    }
}
