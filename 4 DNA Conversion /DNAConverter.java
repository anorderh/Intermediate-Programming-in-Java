/**
 * Program 4 - DNAConverter
 * Takes in DNA.txt and creates new RNA.txt file, consisting of DNA's antisense strand. Then uses RNA.txt to create
 * new Protein.txt file, consisting of proteins translated according to genetic code table provided by RNA2Protein.txt
 * @author Anthony Norderhaug
 * @date 3/5/2020
 * CS 108 Section - 3
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.File;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.lang.StringBuilder;


public class DNAConverter {

    /**
     * Creates and returns a Scanner given filename
     *
     * @param filename                  String representing path for pre-standing file
     * @return                          Scanner linked with pre-standing file's input stream
     * @throws FileNotFoundException    Thrown if given path does not identify a file
     */

    private Scanner createInput(String filename) throws FileNotFoundException {
            FileInputStream inputS = new FileInputStream(filename);
            return new Scanner(inputS);
    }

    /**
     * Creates new file RNA (given pathname RNAFile) that contains the antisense strand of pre-standing DNA file with
     * pathname DNAFile. Transcribes DNA's contents line-by-line into RNA.
     *
     * @param DNAFile                   String representing DNA file's pathname
     * @param RNAFile                   String representing created RNA file's pathname
     * @throws NoSuchElementException   Thrown if any of DNAFile's contents do not correlate with DNA's 4 nucleotides
     */

    public void DNA2RNA(String DNAFile, String RNAFile) throws NoSuchElementException {
        try {
            // DNA scanner declared & initialized, same with DNASequence but no values for line-by-line conversion
            Scanner inputDNA = createInput(DNAFile);
            StringBuilder DNASequence = new StringBuilder();

            // RNA File and RNA PrintWriter declared & initialized for writing into file
            File RNA = new File(RNAFile);
            PrintWriter outputRNA = new PrintWriter(RNA);

            // do-while loop contingent on DNA file containing another line of data
            do {
                // appending to empty DNASequence DNA file's subsequent line of data
                DNASequence.append(inputDNA.nextLine());

                // while loop iterating until DNASequence is empty
                while (DNASequence.length() > 0) {
                    // switch statement examining DNASequence first char, outputs translated nucleotides to RNA
                    switch (DNASequence.charAt(0)) {
                        case 'T':
                            outputRNA.print('A');
                            break;
                        case 'A':
                            outputRNA.print('U');
                            break;
                        case 'C':
                            outputRNA.print('G');
                            break;
                        case 'G':
                            outputRNA.print('C');
                            break;
                        default:
                            // if char does not resemble a nucleotide, exception thrown
                            throw new NoSuchElementException("Not a DNA character");
                    }
                    // after char translation, first char is deleted, moving subsequent chars left in indices
                    DNASequence.deleteCharAt(0);
                }
                // after entire line is translated, a new line is outputted to RNA file and contents flushed
                outputRNA.println();
                outputRNA.flush();
            } while (inputDNA.hasNextLine());
            // catch for createInput(), if desired DNA file is not found
        } catch (FileNotFoundException excpt) {
            System.out.print("DNA File not found");
        }
    }

    /**
     * Creates new file Protein (given pathname ProteinFile) with previously created RNA's contents (specified by
     * pathname RNAFile). RNA's contents are translated line-by-line into proteins according to genetic code table
     * provided by supplementary RNA2Protein file (given pathname RNA2ProteinTable).
     *
     * @param RNAFile                   String representing pathname for RNA file
     * @param ProteinFile               String representing pathname for created Protein file
     * @param RNA2ProteinTable          String representing  pathname for RNA2Protein file (genetic code table)
     * @throws RuntimeException         Thrown by RNAIterations() in do-while loop
     */

    public void RNA2Protein(String RNAFile, String ProteinFile, String RNA2ProteinTable) throws RuntimeException {
        try {
            // RNA Scanner declared & initialized, same for RNASequence but no values for line-by-line conversion
            Scanner inputRNA = createInput(RNAFile);
            StringBuilder RNASequence = new StringBuilder();

            // Protein File and PrintWriter declared & initialized for writing into file
            File Protein = new File(ProteinFile);
            PrintWriter proteinPW = new PrintWriter(Protein);

            // RNA2ProteinTable Scanner and HashMap declared & initialized, HashMap with no values
            Scanner tableScan = createInput(RNA2ProteinTable);
            HashMap<String, String> RNA2ProteinHash = new HashMap<>();
            // while loop inputting genetic code table into HashMap RNA2ProteinHash for protein conversion
            while (tableScan.hasNext()) {
                RNA2ProteinHash.put(tableScan.next(), tableScan.next());
            }

            // do-while loop contingent on RNA File having another line of data
            do {
                // appending to empty RNASequence RNA file's subsequent line of data
                RNASequence.append(inputRNA.nextLine());
                // finding proteins present within RNASequence; if remainder nucleotides present, exception thrown
                int proteinQuantity = RNAIterations(RNASequence);

                // for loop iterating for number of proteins identified
                for (int i = 0; i < proteinQuantity; ++i) {
                    // for-each loop iterating through all RNA-protein conversions
                    for (String tripletCodon : RNA2ProteinHash.keySet()) {
                        // if 1st 3 chars resemble a protein codon, associated protein printed to Protein file
                        if (RNASequence.substring(0, 3).equals(tripletCodon)) {
                            proteinPW.print(RNA2ProteinHash.get(tripletCodon));
                            break;
                        }
                    }
                    // after translation, 1st 3 chars are deleted, moving subsequent chars left in indices
                    RNASequence.delete(0, 3);
                }
                // after line is translated, new line is printed and PrintWriter's contents are flushed
                proteinPW.println();
                proteinPW.flush();
            } while (inputRNA.hasNextLine());
            // catch statement for createInput() regarding RNA and RNA2Protein files
        } catch (FileNotFoundException excpt) {
            System.out.println("RNA and/or RNA2Protein File not found");
        }
    }

    /**
     * Returns int describing number of multiples of 3 input StringBuilder contains, signifying how many triplet codons
     * are present to be converted into proteins.
     *
     * @param RNA                       StringBuilder input into method that is evaluated
     * @return                          int representing quantity of multiples of 3
     * @throws RuntimeException         Thrown if StringBuilder's length is not fully divisible by 3
     */

    private int RNAIterations(StringBuilder RNA) throws RuntimeException {
        if (RNA.length() % 3 != 0) {
            throw new RuntimeException("Invalid RNA length");
        }
        return (RNA.length() / 3);
    }

    /**
     * Identification string
     *
     * @return                          "Program 4, Anthony Norderhaug"
     */
    public String getInfo() {
        return "Program 4, Anthony Norderhaug";
    }
}


