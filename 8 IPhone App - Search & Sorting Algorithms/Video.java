/**
 * (Program 8 - Create Your Own Program) Video Class
 * Conrcrete class building off of Media class. Adds several supplementary methods allowing varying modification and
 * data calculation associated with Video as opposed to Photo  Class
 * @author Anthony Norderhaug
 * @date 5/4/2020
 */
import java.util.LinkedList;
import java.util.HashMap;

public class Video extends Media {
    int duration = 0;
    int initialDuration = 0;
    int frameRate;
    boolean audio;

    /**
     * constructor that initializes Media's constructor and initializes duration, initialDuration, frameRate, and boolean
     * audio for calculating memory. Then proceeds to calculate memory
     *
     * @param inputRes                           int[] derived from IPhoneDirectory
     * @param inputDesc                          String derived from IPhoneDirectory
     * @param inputDuration                      int derived from IPhoneDirectory
     * @param curDirectory                       LinkedList derived from IPhoneDirectory
     * @param curDate                            int[] derived from IPhoneDirectory
     */
    public Video(int[] inputRes, String inputDesc, int inputDuration, LinkedList<Media> curDirectory, int[] curDate) {
        super(inputRes, inputDesc, curDirectory, curDate);
        duration = inputDuration;
        initialDuration = duration;
        frameRate = 24;
        audio = false;
        calcMemorySize();
    }

    /**
     * editDuration() assigns current duration to argument newDuration, unless newDuration is greater than initialDuration
     * to where false is returned.
     *
     * @param newDuration                       int representing new, desired duration
     * @return                                  boolean representing method's success
     */
    public boolean editDuration(int newDuration) {
        if (newDuration <= initialDuration) {
            duration = newDuration;
            calcMemorySize();
            return true;
        }
        return false;
    }

    public String checkAudio() {
        if (audio) {
            return "Enabled";
        } else {
            return "Disabled";
        }
    }

    public void editFrameRate(int newFrameRate) {
        frameRate = newFrameRate;
        calcMemorySize();
    }

    public boolean enableAudio() {
        if (audio) {
            return false;
        }
        audio = true;
        calcMemorySize();
        return true;
    }

    /**
     * shareMedia() initializes sharedPlatforms with input String[] representing desired socialMedias. Then used in
     * initializeProjections()
     *
     * @param socialMedias
     */
    public void shareMedia(String[] socialMedias) {
        sharedPlatforms = socialMedias;
        projectedInteractions = initializeProjections();
        calcResponse();
    }

    /**
     * calcMemorySize() calculates fileSize based on resolution, bit depth, frame rate, duration, and converts into bytes
     * and then megabytes. If audio is enabled, a MB is added to fileSize for each minute
     */
    public void calcMemorySize() {
        fileSize = ((resolution[0] * resolution[1])/10000000.0)*((bitDepth * frameRate * duration)/8.0);
        if (audio) {
            fileSize += duration/60.0;
        }
    }

    /**
     * initializeProjections() initializes HashTable projectedInteractions in Media class, generates randomly generated
     * numbers w/ interactionVarience. Bounds for interactionVarience vary for Photo and Video according to social medias
     * given
     *
     * @return                              HashTable for initializing Media's projectedInteractions
     */
    public HashMap<String, Integer> initializeProjections() {
        HashMap<String, Integer> projection = new HashMap<String, Integer>();
        projection.put("Twitter", interactionVarience.nextInt(100));
        projection.put("Instagram", interactionVarience.nextInt(10));
        projection.put("Youtube", interactionVarience.nextInt(10000));
        projection.put("Snapchat", interactionVarience.nextInt(10));
        projection.put("Facebook", interactionVarience.nextInt(1000));

        return projection;
    }

    public String printDuration() {
        int minutes = duration/60;
        int seconds = duration%60;

        return minutes + ":" + seconds;
    }

    @Override
    public String toString() {
        String baseInfo = "------[VIDEO, ID# " + getID() + "]------\nDATE: " + printDate() + " | RES: " + resolution[0] + "x" +
                resolution[1] + " (" + resQuality() + ")" + " | SIZE: " + fileSize + "MB | # OF COMPRESSIONS: " +
                numOfCompressions;
        String videoInfo = "DURATION: " + printDuration() + " | FRAME RATE: " + frameRate + " | AUDIO: " + checkAudio() +
                "\nDESC: " + getDesc() + " | ONLINE INTERACTIONS: " + totalInteractions;

        return baseInfo + "\n" + videoInfo + "\n";
    }

}
