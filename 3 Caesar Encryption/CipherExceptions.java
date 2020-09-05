/**
 * Program 3b
 * Program that encodes and decodes messages written with a Caesar cypher.
 * This specific implementation features exceptions handled specifically in
 * each method as opposed to being thrown.
 * CS108-3
 * 2/22/2020
 * @author Anthony Norderhaug
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.lang.Character;
import java.io.File;

public class CipherExceptions {

    public static final int NUM_LETTERS = 26;
    public static final int ENCODE = 1;
    public static final int DECODE = 2;
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static File outputFile;

    public static void main(String[] args) {
        // variables
        Scanner inputS = null;
        PrintWriter outputPW = null;
        String outputFilename;

        // validating and extracting input arguments
        checkArgs(args);
        String inputFilename = args[0];
        String key = args[1];
        int action = Integer.parseInt(args[2]);

        try {
            // methods initializing and accessing input & output streams
            outputFilename = getOutputFilename(inputFilename, action);
            inputS = openInput(inputFilename);
            outputPW = openOutput(outputFilename);

            // encoding or decoding begins
            processMessage(inputS, outputPW, key, action);

        } catch (NullPointerException excpt) {
            System.out.print("Option " + action + " is not valid");
        } finally{
            // if outputPW or inputS created, files are closed
            if (inputS != null) {
                inputS.close();
            }
            if (outputPW != null) {
                System.out.print(outputFile);
                outputPW.close();
            }
        }
    }

    /**
     * Method that records input file's contents, identifies Caesar cypher encryption,
     * then encodes(shiftUpByK) or decodes(shiftDownByK) according to action.
     *
     * @param input             Scanner created from input file
     * @param output            PrintWriter created for output file
     * @param key               User-specified String for manipulating message
     * @param action            User-specified int indicating encryption or decryption
     */
    public static void processMessage(Scanner input, PrintWriter output,
                                      String key, int action) {
        try{
            int offset;
            char selectedChar;
            String inputLine = input.nextLine();

            for (int i = 0; i < inputLine.length(); i++) {
                selectedChar = inputLine.charAt(i);
                    /* finding relative index of key for input, pulling char,
                       then finding char's distance from 'a' in alphabet */
                offset = alphabet.indexOf(key.charAt(i % key.length()));

                if (action == ENCODE) {
                    output.print(shiftUpByK(selectedChar, offset));
                } else if (action == DECODE) {
                    output.print(shiftDownByK(selectedChar, offset));
                }
            }
        } catch (InputMismatchException excpt) {
            System.out.print("Usage: Cipher inputFileName cipherKey (1)encode (2)decode");
        }
    }

    /**
     * Checks if 3 args are present. Exits JVM if not satisfied
     *
     * @param args              Input command-line arguments
     */
    public static void checkArgs(String[] args) {
        if (args.length != 3) {
            System.out.print("Invalid quantity of arguments");
            System.exit(0);
        }
    }

    /**
     * Creates input stream from file and subsequent scanner. Prints usage
     * statement if FileNotFoundException thrown and exits JVM
     *
     * @param filename          String containing file name
     * @return Scanner          Created scanner specified by input stream
     */
    public static Scanner openInput(String filename)  {
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            return new Scanner(inputStream);

        } catch (FileNotFoundException excpt) {
            System.out.print("Usage: Cipher inputFileName cipherKey (1)encode (2)decode");
            System.exit(0);
        }

        return null;
    }

    /**
     * Creates output file, output stream, and subsequent PrintWriter object.
     * Prints usage statement if FileNotFoundException thrown and exits JVM
     *
     * @param filename          String specifying file name
     * @return PrintWriter      PrintWriter specified by output stream
     */
    public static PrintWriter openOutput(String filename)  {
        try {
            outputFile = new File(filename);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            return new PrintWriter(outputStream);

        } catch (FileNotFoundException excpt) {
            System.out.print("Usage: Cipher inputFileName cipherKey (1)encode (2)decode");
        }

        return null;
    }

    /**
     * Encode letter by some offset d. Finds char diff from 'a'(or 'A') then
     * uses modulo by NUM_LETTERS of diff to wrap around alphabet. 'a'(or 'A')
     * is added to retrieve char ASCII value. Uppercase characters are converted
     * to lowercase.
     *
     * @param c                 input character
     * @param offset            amount to shift character value
     * @return char             encoded character
     */
    public static char shiftUpByK(char c, int offset) {
        char uppercaseChar = 0;

        if ('a' <= c && c <= 'z')
            return (char) ('a' + (c - 'a' + offset) % NUM_LETTERS);
        if ('A' <= c && c <= 'Z') {
            uppercaseChar = (char) ('A' + (c - 'A' + offset) % NUM_LETTERS);
            return Character.toLowerCase(uppercaseChar);
        }
        return c; // don't encrypt if not an ic character
    }

    /**
     * Decode letter by some offset d. Finds char diff from 'a'(or 'A') then
     * subtracts by offset. Modulo of NUM_LETTERS to allow wrap around. If
     * final displacement is negative, it is applied to 'z'. Uppercase chars
     * are converted to lowercase.
     *
     * @param c                 input character
     * @param offset            amount to shift character value
     * @return char             decoded character
     */

    public static char shiftDownByK(char c, int offset) {
        char uppercaseChar = 0;
        int displacement;

        if ('a' <= c && c <= 'z') {
            displacement = (c - 'a' - offset) % NUM_LETTERS;

            if (displacement < 0) {
                return (char)('z' + (displacement+1)); // +1 since -1 from 'a' is 'z'

            }
            return (char) (displacement + 'a');
        }
        if ('A' <= c && c <= 'Z') {
            displacement = (c - 'A' - offset) % NUM_LETTERS;

            if (displacement < 0) {
                return (char)Character.toLowerCase('Z' + (displacement+1));
            }
            return (char) Character.toLowerCase(displacement + 'A');
        }
        return c; // don't encrypt if not an ic character
    }

    /**
     * Changes file extension to ".coded" or ".decoded"
     *
     * @param filename
     * @return String           new filename or null if action is illegal
     */
    public static String getOutputFilename(String filename, int action) {
        if (action == ENCODE) {
            return filename + ".coded";
        }
        else if (action == DECODE) {
            return filename + ".decoded";
        }
        return null;
    }

    /**
     * Identification string
     *
     * @return String           "Program 3b, Anthony Norderhaug"
     */
    public String getInfo() {
        return "Program 3b, Anthony Norderhaug";
    }

}