/**
 * (Program 8 - Create Your Own Program) IPhoneDirectory Class
 * Main class that catalogs a theoretical IPhone's file directory, allowing modification and tracking of various
 * photos and videos
 * @author Anthony Norderhaug
 * @date 5/4/2020
 */
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;

public class IPhoneDirectory implements Directory {
    static Random randGen = new Random(235);

    int[] date = {1, 1, 2020};
    LinkedList<Media> directory = new LinkedList<>();
    LinkedList<Media> sortedDirectory = new LinkedList<>();
    Scanner scan = new Scanner(System.in);

    /**
     * advanceDate() deciphers which timeUnit is desired according to input String and changes date accordingly given
     * int input. If input String is not recognized, false is returned.
     *
     * @param advance                           int specifying how many units of time to be advanced
     * @param timeUnit                          String specifying unit of time
     * @return                                  boolean indicating method's success
     */
    public boolean advanceDate(int advance, String timeUnit) {
        if (timeUnit.contains("month")) {
            date[2] += advance / 12;
            date[0] += advance % 12;
            return true;
        } else if (timeUnit.contains("day")) { // assumption that all months are 30 days
            date[2] += advance/365;
            date[0] += (advance%365)/30;
            date[1] += (advance%365)%30;
            return true;
        } else if (timeUnit.contains("year")) {
            date[2] += advance;
            return true;
        }
        return false;
    }

    /**
     * takePicture() verifies current directory size is below 50, and then declares & initializes new Photo. This Photo
     * is then added to Media directory for future cataloguing and modification.
     *
     * @param inputRes                          int[] representing desired pixel dimensions
     * @param inputDesc                         String representing desired description associated
     * @param curDirectory                      Reference to Main Class directory for accessing variables
     * @param curDate                           Reference to Main Class date
     * @return                                  boolean indicating method's success
     */
    public boolean takePicture(int[] inputRes, String inputDesc, LinkedList<Media> curDirectory, int[] curDate) {
        if (directory.size() < 50) {
            Photo newPhoto = new Photo(inputRes, inputDesc, directory, date);
            directory.add(newPhoto);

            return true;
        }
        System.out.println("Error. Current directory is full.");
        return false;
    }

    /**
     * recordVideo() ensures inputDuration <= 3600 secs(<= 60 minutes), verifies current directory size is below 50, and
     * then declares & initializes new Video. This Photo is then added to Media directory for future cataloguing and
     * modification.
     *
     * @param inputRes                          int[] representing desired pixel dimensions
     * @param inputDesc                         String representing desired description associated
     * @param inputDuration                     int representing desired duration, for memory calculation
     * @param curDirectory                      Reference to Main Class directory for accessing variables
     * @param curDate                           Reference to Main Class date
     * @return                                  boolean indicating method's success
     */
    public boolean recordVideo(int[] inputRes, String inputDesc, int inputDuration, LinkedList<Media> curDirectory, int[] curDate) {
        if (inputDuration > 3600) {
            System.out.println("Error. Duration cannot exceed 1 hour (3600 seconds)");
            return false;
        }
        if (directory.size() < 50) {
            Video newVideo = new Video(inputRes, inputDesc, inputDuration, directory, date);
            directory.add(newVideo);

            return true;
        }
        System.out.println("Error. Current directory is full.");
        return false;
    }

    /**
     * removeMedia() takes input int representing media's ID and calls search(). If ID identified > -1 (exists), specific
     * media is pulled from sortedDirectory and media is removed from both LinkedLists sorteddirectory & directory.
     * Respective statements are outputted.
     *
     * @param mediaID                           int representing ID of media to be removed
     */
    public void removeMedia(int mediaID) {
        int mediaIndex = search(mediaID);
        if (mediaIndex < 0) {
            System.out.println("Error. Media not found.\n");
            return;
        }
        Media selectedMedia = sortedDirectory.get(mediaIndex);

        sortedDirectory.remove(selectedMedia);
        directory.remove(selectedMedia);
        System.out.println("Media successfully deleted.\n");
    }

    /**
     * sort() (helper method of implemented MergeSort algorithm) that recursively deciphers indexes required to allow
     * for LinkedList's partitioning. After each valid partition is created, mergePartition() is accordingly invokved in
     * each frame to gradually sort LinkedList sortedDirectory.
     *
     * @param start                         int representing specified starting index to sort (always 0)
     * @param end                           int representing last valid index       (input.LinkedList.size() - 1)
     */
    public void sort(int start, int end) {
        int midpoint = 0;

        if (start < end) {
            midpoint = (start + end) / 2;

            sort(start, midpoint);
            sort(midpoint + 1, end);

            mergePartition(start, midpoint, end);
        }
    }

    /**
     * mergePartition is included in sort()'s recursion to sort each identified partition. New Media[] sortedResult is assigned
     * with each partition of sortedDirectory's values (which isn't itself sorted yet) in sorted order to be used in
     * gradually altering sortedDirectory. In deciphering sorted order, partitions are compared from indices 0 to
     * midpoint and midpoint+1 to end. If one partition is completely processed, the other is added to LinkedList as it is
     * already in order. NOTE! Sorted according to Media's ID!
     *
     * @param start                         int representing 1st partition's start index
     * @param midpoint                      int representing 2nd partition's start index+1 & 1st partition's end
     * @param end                           int representing 2nd partition's end index
     */
    public void mergePartition(int start, int midpoint, int end) {
        int mergedSize = end - start + 1;
        int currentMergePos = 0;
        int indexA = start;
        int indexB = midpoint + 1;
        Media[] sortedResult = new Media[mergedSize];

        while (indexA <= midpoint && indexB <= end) {
            if (sortedDirectory.get(indexA).getID() <= sortedDirectory.get(indexB).getID()) {
                sortedResult[currentMergePos] = sortedDirectory.get(indexA);
                ++indexA;
            } else {
                sortedResult[currentMergePos] = sortedDirectory.get(indexB);
                ++indexB;
            }
            currentMergePos++;
        }

        while (indexA <= midpoint) {
            sortedResult[currentMergePos] = sortedDirectory.get(indexA);
            ++indexA;
            ++currentMergePos;
        }

        while (indexB <= end) {
            sortedResult[currentMergePos] = sortedDirectory.get(indexB);
            ++indexB;
            ++currentMergePos;
        }

        for (currentMergePos = 0; currentMergePos < mergedSize; currentMergePos++) {
            sortedDirectory.set(start + currentMergePos, sortedResult[currentMergePos]);
        }
    }

    /**
     * search() includes implementation of a binarySearch in identifying desired media. Prior to searching, sortedDirectory
     * is ensured to contain all elements of directory. If not true, sortedDirectory is initialized w/ directory's values
     * and sorted to make binary search possible. search() calls specific searchBinary() method w/ given ID and int indices
     * describing start smallest value, middle value, and size
     *
     * @param ID                            int representing desired Media's ID
     * @return                              int representing desired Media's index in sortedDirectory
     */
    public int search(int ID) {
        if (!(sortedDirectory.containsAll(directory) && directory.containsAll(sortedDirectory))) {
            sortedDirectory.clear();
            sortedDirectory.addAll(directory);
            sort(0, directory.size()-1);
        }

        int highValue = sortedDirectory.size();
        return searchBinary(ID, 0, highValue/2, highValue);
    }

    /**
     * searchBinary() implements a binary search on LinkedList sortedDirectory through comparing each Media's ID. If
     * greater, method is recursively called w/ currentIndex + 1 replacing lowValue and currentIndex updated. If less,
     * method is recursively called w/ currentIndex replacing highValue and currentIndex updated.
     *
     * @param ID                            int ID representing desired Media's iD
     * @param lowValue                      int representing lowest index of range being searched
     * @param currentIndex                  int representing current index being searched within range
     * @param highValue                     int representing top border value of range
     * @return                              int representing desired Media's index (-1 if DNE)
     */
    public int searchBinary(int ID, int lowValue, int currentIndex, int highValue) {
        while (sortedDirectory.get(currentIndex).getID() != ID) {
            if ((highValue - lowValue) <= 1) { // failing case
                return -1;
            }
            if (sortedDirectory.get(currentIndex).getID() < ID) {
                lowValue = currentIndex+1;
                currentIndex = searchBinary(ID, lowValue, (highValue+lowValue)/2, highValue);
            } else {
                highValue = currentIndex;
                currentIndex = searchBinary(ID, lowValue, (highValue+lowValue)/2, highValue);
            }
            if (currentIndex == highValue) { // failing case
                return -1;
            }
            if (currentIndex == -1) { // so when -1 is identified, it will not interact w/ while statement's ".get" function
                break;
            }
        }
        return currentIndex;
    }

    /**
     * displayInfo() accepts input Media and prints in print statement alongwith ID
     *
     * @param selectedMedia                 identified Media
     */
    public void displayInfo(Media selectedMedia) {
        System.out.println("\nDisplaying media ID# " + selectedMedia.getID() + "\n" + selectedMedia);
    }

    /**
     * share() accepts Media and String[]. According to Media's instance, respective shareMedia() methods of Video and
     * Photo are called to derive amount of interactions Media receiving according to type and input social media
     *
     * @param selectedMedia                 desired Media to be shared
     * @param inputSocialMedias             input String[] representing desired social medias to be shared to
     */
    public void share(Media selectedMedia, String[] inputSocialMedias) {
        if (selectedMedia instanceof Video) {
            ((Video) selectedMedia).shareMedia(inputSocialMedias);
        } else {
            ((Photo) selectedMedia).shareMedia(inputSocialMedias);
        }
        System.out.println(selectedMedia.totalInteractions + " person(s) interacted with this!\n");
    }

    /**
     * modify() accepts Media and based on instance, outputs a prompt explaining possible ways to modify/manipulate the
     * file. In Photo instance, can also be used to extrapolate information about season.
     *
     * @param selectedMedia                 desired Media to be modified
     */
    public void modify(Media selectedMedia) {
        if (selectedMedia instanceof Video) {
            System.out.println("Video ID#" + selectedMedia.ID + " identified, select an option. ");
            modifyOptions(selectedMedia);
            switch(Integer.parseInt(scan.nextLine())) {
                case 1:
                    if (selectedMedia.compressMedia()) {
                        System.out.println("Compression successful. New file size is " + selectedMedia.getFileSize() + " MB\n");
                    } else {
                        System.out.println("Error. Already at bit depth 4\n");
                    }
                    break;
                case 2:
                    System.out.println("Enter new frame rate. 0 is applicable.\n");
                    ((Video) selectedMedia).editFrameRate(Integer.parseInt(scan.nextLine()));
                    System.out.println("Frame rate set. New file size is " + selectedMedia.getFileSize() + " MB\n");
                    break;
                case 3:
                    System.out.println("Enter new duration. Cannot be greater than media's initial duration\n");
                    if (((Video) selectedMedia).editDuration(Integer.parseInt(scan.nextLine()))) {
                        System.out.println("New duration successfully added. New file size is " + selectedMedia.getFileSize()
                        + " MB\n");
                    } else {
                        System.out.println("Error. Input duration greater than initial duration\n");
                    }
                    break;
                case 4:
                    if (((Video) selectedMedia).enableAudio()) {
                        System.out.println("Audio successfully enabled. New file size is " + selectedMedia.getFileSize()
                                + " MB\n");
                    } else {
                        System.out.println("Error. Audio already enabled\n");
                    }
                    break;
                default:
                    System.out.println("Improper input. Returning to main menu.");
                    break;
            }
        } else {
            System.out.println("Photo ID#" + selectedMedia.ID + " identified, select an option.\n");
            modifyOptions(selectedMedia);
            switch(Integer.parseInt(scan.nextLine())) {
                case 1:
                    if (selectedMedia.compressMedia()) {
                        System.out.println("Compression successful. New file size is " + selectedMedia.getFileSize() + "MB");
                    } else {
                        System.out.println("Unsuccessful. Already at bit depth 4");
                    }
                    break;
                case 2:
                    System.out.println("Enter String representing photo filter to be added");
                    ((Photo) selectedMedia).setFilter(scan.nextLine());
                    System.out.println("Filtering successful: New filter added is " + ((Photo) selectedMedia).filter);
                    break;
                case 3:
                    System.out.println("According to the date, this was taken in " + ((Photo)selectedMedia).deriveSeason());
                    break;
                default:
                    System.out.println("Improper input. Returning to main menu.");
                    break;
            }
        }
    }

    /**
     * printDirectory() ensures directory is populated, and prints out all Media within. Also outputs statement describing
     * current storage.
     *
     * @return                              boolean indicating method's success
     */
    public boolean printDirectory() {
        if (directory.size() == 0) {
            System.out.println("Error. Directory is empty");
            return false;
        }
        for (Media file: directory) {
            System.out.println(file);
        }
        System.out.println("\nMedia Directory Current Storage: " + directory.size() + "/50");
        return true;
    }

    /**
     * formatDirectory() ensures directory is populated and that sortedDirectory & directory have the same values. If
     * values are not the same, sortedDirectory is cleared, assigned all of directory's values, and then sorted. Then,
     * sortedDirectory is copied to directory.
     *
     * @return                              boolean indicating method's success
     */
    public boolean formatDirectory() {
        if (directory.size() == 0) {
            System.out.println("Error. Directory is empty");
            return false;
        }

        if (directory.size() == 1 && sortedDirectory.size() == 0) {
            sortedDirectory.add(directory.get(0));
        } else if (!(sortedDirectory.containsAll(directory) && directory.containsAll(sortedDirectory))) {
            sortedDirectory.clear();
            sortedDirectory.addAll(directory);
            sort(0, directory.size()-1);
        }

        Collections.copy(directory, sortedDirectory);
        return true;
    }

    /**
     * modifyOptions() prints prompt regarding modification options according to Media instace
     *
     * @param selectedMedia                 desired Media identified in modify()
     */
    public void modifyOptions(Media selectedMedia) {
        if (selectedMedia instanceof Video) {
            System.out.println("1. Compress media\n2. Edit frame rate\n3. Edit duration\n4. Enable audio");
        } else {
            System.out.println("1. Compress media\n2. Add filter\n3. Derive season shot was taken");
        }
    }

    public String printDate() {
        return date[0] + "/" + date[1] + "/" + date[2];
    }

    public int[] getDate() {
        return date;
    }
}
