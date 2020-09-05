/**
 * (Program 8 - Create Your Own Program) IPhoneDriver Class
 * Driver class which offers UI for user to interact with IPhoneDirectory
 * (did not offer extensive javaDoc since driver was optional)
 * @author Anthony Norderhaug
 * @date 5/4/2020
 */
import java.util.Scanner;

public class IPhoneDriver {
    static Scanner localScan = new Scanner(System.in);
    static IPhoneDirectory myIPhone;

    public static void main(String[] args) {
        myIPhone = new IPhoneDirectory();

        boolean exit = false;
        String userInput;

        while (!exit) {
            try {
                printMenu();
                userInput = localScan.nextLine();
                switch (Integer.parseInt(userInput)) {

                    // Take a picture
                    case 1:
                        int[] inputRes = resolutionPrompt();
                        String inputDesc = descPrompt();

                        if ((myIPhone.takePicture(inputRes, inputDesc, myIPhone.directory, myIPhone.date))) {
                            System.out.println("\nNice shot! Here's some info.\n" +
                                    myIPhone.directory.get(myIPhone.directory.size() - 1));
                        }
                        break;

                    // Record a video
                    case 2:
                        inputRes = resolutionPrompt();
                        inputDesc = descPrompt();
                        int inputDuration = durationPrompt();

                        if ((myIPhone.recordVideo(inputRes, inputDesc, inputDuration, myIPhone.directory, myIPhone.date))) {
                            System.out.println("\nA cinematic masterpiece! Here's some info.\n" +
                                    myIPhone.directory.get(myIPhone.directory.size() - 1));
                        }
                        break;

                    // Delete media
                    case 3:
                        if (!(directoryCheck(myIPhone))) {
                            break;
                        }

                        System.out.println("Enter media ID.");
                        myIPhone.removeMedia(Integer.parseInt(localScan.nextLine()));
                        break;

                    // Display media info
                    case 4:
                        if (!(directoryCheck(myIPhone))) {
                            break;
                        }

                        System.out.println("Enter media ID.");
                        int mediaIndex = myIPhone.search(Integer.parseInt(localScan.nextLine()));
                        if (mediaIndex < 0) {
                            System.out.println("Error. Media not found.");
                        } else {
                            myIPhone.displayInfo(myIPhone.sortedDirectory.get(mediaIndex));
                        }
                        break;

                    // Modify media
                    case 5:
                        if (!(directoryCheck(myIPhone))) {
                            break;
                        }

                        System.out.println("Enter media ID.");
                        mediaIndex = myIPhone.search(Integer.parseInt(localScan.nextLine()));
                        if (mediaIndex < 0) {
                            System.out.println("Error. Media not found.\n");
                        } else {
                            myIPhone.modify(myIPhone.sortedDirectory.get(mediaIndex));
                        }
                        break;

                    // Share media
                    case 6:
                        if (!(directoryCheck(myIPhone))) {
                            break;
                        }

                        System.out.println("Enter media ID.");
                        mediaIndex = myIPhone.search(Integer.parseInt(localScan.nextLine()));
                        if (mediaIndex < 0) {
                            System.out.println("Error. Media not found.\n");
                        } else {
                            System.out.println("Enter desired social medias (separated by spaces).");
                            myIPhone.share(myIPhone.sortedDirectory.get(mediaIndex), localScan.nextLine().split(" "));
                        }
                        break;

                    // Format media directory
                    case 7:
                        if (myIPhone.formatDirectory()) {
                            System.out.println("\nActive directory has now been formatted.");
                        }
                        break;

                    // Print media directory
                    case 8:
                        if (myIPhone.printDirectory()) {
                            System.out.println("\nDirectory successfully printed.");
                        }
                        break;

                    // Advance date
                    case 9:
                        System.out.println("Enter int and time unit (year, month, day) separated by space.");
                        String[] stringDate = localScan.nextLine().split(" ");
                        if (myIPhone.advanceDate(Integer.parseInt(stringDate[0]), stringDate[1])) {
                            System.out.println("Successfully advanced the date, it is " + myIPhone.printDate() + ".\n");
                        } else {
                            System.out.println("Error. Time unit not recognized. ");
                        }
                        break;
                    // Exit
                    case 0:
                        exit = true;
                        System.out.println("\nExiting IPhone application.");
                        break;

                    default:
                        System.out.println("Invalid input. Re-accessing menu.\n");
                }
            } catch (NumberFormatException except) {
                System.out.println("Invalid input. Re-accessing menu");
            }
        }
    }

    public static void printMenu() {
        System.out.println("\nDisplaying IPhone media directory options.\n");
        System.out.println("1. Take a picture.");
        System.out.println("2. Record a video.");
        System.out.println("3. Delete media.");
        System.out.println("4. Display media info.");
        System.out.println("5. Modify media.");
        System.out.println("6. Share media.");
        System.out.println("7. Format media directory according to IDs.");
        System.out.println("8. Print media directory.");
        System.out.println("9. Advance date.");
        System.out.println("0. Exit");
        System.out.println();
    }

    public static int[] resolutionPrompt() {
        boolean validResolution;
        String[] stringRes;
        int[] inputRes = new int[2];

        do {
            validResolution = true;
            System.out.println("Enter media resolution (separated by 'x' & both less than 2500)");
            stringRes = localScan.nextLine().replace(" ", "").split("x");

            for (int i = 0; i < 2; i++) {
                try {
                    inputRes[i] = Integer.parseInt(stringRes[i]);
                } catch (NumberFormatException except) {
                    System.out.println("Invalid resolution input. Avoid unnecessary characters.\n");
                    validResolution = false;
                    break;
                }

                if (inputRes[i] > 2500 || inputRes[i] < 0 || stringRes.length < 2) {
                    System.out.println("Invalid resolution input. Enter new pixel dimensions.\n");
                    validResolution = false;
                    break;
                }
            }
        } while (!validResolution);

        return inputRes;
    }

    public static String descPrompt() {
        System.out.println("Enter one-line description for media");
        return localScan.nextLine();
    }

    public static int durationPrompt() {
        System.out.println("Enter video duration, in seconds");
        return Integer.parseInt(localScan.nextLine());
    }

    public static boolean directoryCheck(IPhoneDirectory inputIPhone) {
        if (inputIPhone.directory.size() <= 0) {
            System.out.println("Error. Directory is empty");
            return false;
        }
        return true;
    }
}
