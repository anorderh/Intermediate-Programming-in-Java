/**
 * (Program 8 - Create Your Own Program) IPhoneDirectoryUnitTests Class
 * Class which conducts several tests on a created IPhoneDirectory object
 * @author Anthony Norderhaug
 * @date 5/4/2020
 */
import java.util.Arrays;
import java.util.Random;

public class IPhoneDirectoryUnitTests {
    static Random testGen = new Random(377);
    static IPhoneDirectory IPhone;
    static String separation = "\n----------------------------------------------------------------\n";


    public static void main(String[] args) {
        IPhone = new IPhoneDirectory();

        /* NOTE: Tests B and C are considering randGen seed (not testGen, located in IPhoneDirectory.java) to be 235,
        if this seed is changed, consider reviewing tests B, C, & E and changing testing values accordingly */

        // Initializing an IPhoneDirectory with 10 photos & 10 videos, random resolutions and durations
        for (int i = 0; i < 10; i++) {
            int[] resolution = {testGen.nextInt(2500), testGen.nextInt(2500)};
            IPhone.takePicture(resolution, "Photo" + i, IPhone.directory, IPhone.date);
            IPhone.recordVideo(resolution, "Video" + i, testGen.nextInt(1500), IPhone.directory, IPhone.date);
        }

        System.out.println("\nPerforming Unit Tests\n");

        UnitATest();  // Formatting directory w/ merge sort and checking if every file is truly ordered
        UnitBTest();  // Using binary search in searching directory for media w/ given ID
        UnitCTest();  // Attempting to remove existing and imaginary media from directory
        UnitDTest();  // Advancing date at random, unorthodox time intervals and verifying accuracy
        UnitETest();  // Creating new IPhoneDirectory and checking sort/search for every element to 20
    }

    /**
     * UnitATest() formats directory and verifies that in active directory, each preceding value has a smaller ID
     * than its successor (note, equivalence is allowed)
     */

    public static void UnitATest() {

        if (IPhone.formatDirectory()) {
            int lastValue = IPhone.directory.get(0).getID();

            for (Media file: IPhone.directory) {
                if (file.getID() < lastValue) {
                    System.out.println("\nUnit Test A failed.");
                    return;
                }
                lastValue = file.getID();
            }
            IPhone.printDirectory();
            System.out.println("Unit Test A passed.");
        } else {
            System.out.println("Unit Test A failed.");
        }
        System.out.println(separation);
    }

    /**
     * UnitBTest() searches for two IDs, one that exists and one that doesn't. Determines success based on if derived Media's
     * ID is original ID and if imaginary ID warrants error
     */
    public static void UnitBTest() {
        int existingID = 155; // hard-coded ID I know exists
        int imaginaryID = 77; // hard-coded ID I know doesn't exist
        int mediaIndex;

        mediaIndex = IPhone.search(existingID);
        if (IPhone.sortedDirectory.get(mediaIndex).getID() != existingID) {
            System.out.println("Unit Test B failed");
            return;
        }

        mediaIndex = IPhone.search(imaginaryID);
        if (mediaIndex != -1) {                 // -1 in my implementation represents DNE
            System.out.println("\nUnit Test B failed");
            return;
        }
        System.out.println("\nID# " + existingID + " identified and ID 77 does not exist, refer to implementation\nUnit Test B Passed\n");
        System.out.println(separation);
    }

    /**
     * UnitCTest() removes 4 known IDs from directory and 4 fake IDs from directory. Keeps tabs on expected sizes before
     * and after each operation. In addition to output statements, if sizes are different than expected, test fials
     */
    public static void UnitCTest() {
        int[] existingIDs = {203, 123, 24, 409}; //hard-coded IDs I know exist
        int[] imaginaryIDs = {25214, 2318008, 17, 234141}; //hard-coded IDs I know eixst

        int expectedSizeAfter= IPhone.directory.size() - existingIDs.length;
        for (int ID: existingIDs) {
            IPhone.removeMedia(ID);
        }
        if (IPhone.directory.size() != expectedSizeAfter) {
            System.out.println("Unit Test C failed.");
            return;
        }

        int expectedSizeAfter2 = IPhone.directory.size();
        for (int ID: imaginaryIDs) {
            IPhone.removeMedia(ID);
        }
        if (IPhone.directory.size() != expectedSizeAfter2) {
            System.out.println("Unit Test C failed.");
            return;
        }

        IPhone.printDirectory();
        System.out.println("\nUnit Test C Passed. Genuine IDs removed and fictional ignored.\n");
        System.out.println(separation);
    }

    /**
     * UnitDTest advances the date by abnormal amounts of time and given verification from pre-existing date arrays,
     * determines if advanceDate() of IPhoneDirectory is functioning correctly
     */
    public static void UnitDTest() {
        boolean pass = true;
        final int[] initialDate = {1, 1, 2020};
        int[] date1 = {1, 1, 2045};             // add 25 years
        int[] date2 = {3, 10, 2047};            // add 799 days
        int[] date3 = {3, 10, 2049};            // add 24 months
        int[] date4 = {3, 23, 2049};            // add 13 days
        int[] date5 = {3, 23, 2053};            // add 4 years
        // dates confirmed through https://www.timeanddate.com/date/dateadded.html?m1=3&d1=10&y1=2049&type=add&ay=4&am=&aw=&ad=&rec=

        IPhone.advanceDate(25, "years");
        if (!(Arrays.equals(IPhone.date, date1))) {
            pass = false;
        }
        IPhone.advanceDate(799, "days");
        if (!(Arrays.equals(IPhone.date, date2))) {
            pass = false;
        }
        IPhone.advanceDate(24, "months");
        if (!(Arrays.equals(IPhone.date, date3))) {
            pass = false;
        }
        IPhone.advanceDate(13, "days");
        if (!(Arrays.equals(IPhone.date, date4))) {
            pass = false;
        }
        IPhone.advanceDate(4, "years");
        if (!(Arrays.equals(IPhone.date, date5))) {
            pass = false;
        }

        if (pass) {
            System.out.println("Unit Test D Passed.\nAll dates are accurate.\n");
        } else {
            System.out.println("Unit Test D Failed.\n");
        }
        System.out.println(separation);

    }

    /**
     * UnitETest() iterates 50 times, each time adding a new photo, formatting, ensuring format is correct, and then
     * searches for ID known to exist at all times (i.e the first ID generated given the seed).
     *
     * Checks that a Media object can be identified and formatted at all possible indices
     */
    public static void UnitETest() {
        IPhoneDirectory newIPhone = new IPhoneDirectory();
        int knownID = 266;
        int[] defaultRes = {1000,1000};
        String defaultStr = "stringy";

        for (int i = 1; i <= 50; i++) {
            newIPhone.takePicture(defaultRes, defaultStr, newIPhone.directory, newIPhone.date);
            newIPhone.formatDirectory();
            int lastValue = newIPhone.directory.get(0).getID();

            for (Media file: newIPhone.directory) {
                if (file.getID() < lastValue) {
                    System.out.println("\nUnit Test E failed.");
                    return;
                }
                lastValue = file.getID();
            }
            System.out.println("newIPhone directory successfully formatted for " + i + " element(s)");

            if (newIPhone.sortedDirectory.get(newIPhone.search(knownID)).getID() != knownID) {
                System.out.println("Media ID# " + knownID + " was unable to be correctly identified, exiting");
                System.out.println("Unit Test E failed");
                return;
            }
            System.out.println("Media ID# " + knownID + " successfully identified for " + i + " element(s)\n");
        }
        System.out.println("Unit Test E passed");
        System.out.println(separation);
    }


}
