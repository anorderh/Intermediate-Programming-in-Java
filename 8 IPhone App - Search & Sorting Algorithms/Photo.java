/**
 * (Program 8 - Create Your Own Program) Photo Class
 * Conrcrete class building off of Media class. Adds several supplementary methods allowing varying modification and
 * data calculation associated with Photo as opposed to Video Class
 * @author Anthony Norderhaug
 * @date 5/4/2020
 */
import java.util.HashMap;
import java.util.LinkedList;

public class Photo extends Media {
    String filter;

    /**
     * constructor deriving from Media's constructor and calculating varying memory size
     *
     * @param inputRes                      int[] derived from IPhoneDirectory
     * @param inputDesc                     String derived from IPhoneDirectory
     * @param curDirectory                  LinkedList derived from IPhoneDirectory
     * @param curDate                       int[] derived from IPhoneDirectory
     */
    public Photo(int[] inputRes, String inputDesc, LinkedList<Media> curDirectory, int[] curDate) {
        super(inputRes, inputDesc, curDirectory, curDate);
        filter = "N/A";
        calcMemorySize();
    }

    public void setFilter(String filterName) {
        filter = filterName;
    }

    /**
     * shareMedia() initializes sharedPlatform with String[] social medias. SharedPlatforms used to calc social media
     * response in junction with HashTable projection
     *
     * @param socialMedias                  String[] used for initialization sharedPlatforms
     */
    public void shareMedia(String[] socialMedias) {
        sharedPlatforms = socialMedias;
        projectedInteractions = initializeProjections();
        calcResponse();
    }

    /**
     * deriveSeason() derives what season Photo was taken in given date derived from IPhoneDirectory
     *
     * @return                              String indicating season
     */
    public String deriveSeason() {
        if (date[0] > 2 && date[0] < 6) {
            return "Spring";
        } else if (date[0] > 5 && date[0] < 9) {
            return "Summer";
        } else if (date[0] > 8 && date[0] < 12) {
            return "Fall";
        } else {
            return "Winter";
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
        projection.put("Instagram", interactionVarience.nextInt(10000));
        projection.put("Youtube", interactionVarience.nextInt(10));
        projection.put("Snapchat", interactionVarience.nextInt(100));
        projection.put("Facebook", interactionVarience.nextInt(100));

        return projection;
    }

    /**
     * calcMemorySize() calculates Photo's fileSize through resolution, bit depth, and 1/8 & 1/1000000 to convert bits
     * to bytes and bytes to megabytes
     */
    public void calcMemorySize() {
        fileSize = (resolution[0] * resolution[1] * bitDepth) / (8.0 * 1000000);
    }

    @Override
    public String toString() {
        String baseInfo = "------[PHOTO, ID# " + getID() + "]------\nDATE: " + printDate() + " | RES: " + resolution[0] + "x" +
                resolution[1] + " (" + resQuality() + ")" + " | SIZE: " + getFileSize() + "MB | # OF COMPRESSIONS: " +
                numOfCompressions;
        String photoInfo = "FILTER: " + filter + " | DESC: " + getDesc() + " | ONLINE INTERACTIONS: " + totalInteractions;

        return baseInfo + "\n" + photoInfo + "\n";
    }
}
