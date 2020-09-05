import java.io.FileNotFoundException;

public class DNAConvertDebug {

    public static void main(String[] args) throws FileNotFoundException {

       //  DNAConvertStrings practice = new DNAConvertStrings();
       DNAConverter practice2 = new DNAConverter();

        practice2.DNA2RNA("DNA.txt", "RNA.txt");
        practice2.RNA2Protein("RNA.txt", "Protein.txt", "RNA2Protein.txt");
        System.out.println(practice2.getInfo());









    }
}
