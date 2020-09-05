/**
 * Program 1
 * Program that manipulates and derives information from 12 total passwords
 * between 3 students.
 * CS108-3
 * 2/1/2020
 * @author Anthony Norderhaug
 */

import java.util.Scanner;


public class Password {
    // class level variables
    String[][] passwords2D = new String[3][4]; // passwords organized per each student

    /**
     * Default constructor.
     * Initializes class level variable passwords2D with String array. Organizes each
     * password by student
     *
     * @param pws       String array argument used to initialize passwords2D
     */
    public Password (String[] pws) {
        Scanner scnr = new Scanner(System.in);
        int index = 0;  // index for later assigning passwords to passwords2D

        for (int i = 0; i < passwords2D.length; ++i) {
            for (int j = 0; j < passwords2D[i].length; ++j) {
                passwords2D[i][j] = pws[index++];
            }
        }

    }

    /**
     * hasWord() checks if any passwords are equal to the String argument.
     * Returns true once identical strings are found, otherwise returns false.
     *
     * @param password  String argument used for comparison
     * @return          boolean identifying if equivalent String is found
     */
    public boolean hasWord(String password) {
        for (int i = 0; i < passwords2D.length; i++) {
            for (int j = 0; j < passwords2D[i].length; j++) {
                if (password.equals(passwords2D[i][j])) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * unlockAll() changes all values within passwords2D to "unlock".
     */
    public void unlockAll() {
        for (int i = 0; i < passwords2D.length; i++) {
            for (int j = 0; j < passwords2D[i].length; j++) {
                passwords2D[i][j] = "unlock";
            }
        }
    }

    /**
     * longest() finds longest password in passwords2D and returns as String.
     *
     * @return      String containing longest password
     */
    public String longest() {
        String longestPW = "";

        for (int i = 0; i < passwords2D.length; i++) {
            for (int j = 0; j < passwords2D[i].length; j++) {
                if (passwords2D[i][j].length() > longestPW.length()) {
                    longestPW = passwords2D[i][j];
                }
            }
        }

        return longestPW;
    }

    /**
     * mostFrequent() records each student's total password changes and compares them
     * together. The student with the most changes is identified by index.
     *
     * @return      index of student with most password changes
     */
    public int mostFrequent() {

        int mostPWchanges = 0;      // highest record PW changes
        int PWchanges;              // current student's PW changes
        int studentIndex = 0;       // student with most PW changes's index

        for (int i = 0; i < passwords2D.length; i++) {
            PWchanges = 0;

            for (int j = 1; j < passwords2D[i].length; j++) {
                // starts out at index 1 for index 0 is comparison starting point
                if (!(passwords2D[i][j].equals(passwords2D[i][j-1]))) {
                    ++PWchanges;
                }
            }

            if (PWchanges > mostPWchanges) {
                mostPWchanges = PWchanges;
                studentIndex = i;
            }
        }

        return studentIndex;
    }

    /**
     * getIdentificationString() returns author's information.
     *
     * @return      "Program 1, Anthony Norderhaug"
     */
    public String getIdentificationString() {
        return "Program 1, Anthony Norderhaug";
    }

}
