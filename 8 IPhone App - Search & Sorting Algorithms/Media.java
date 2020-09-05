/**
 * (Program 8 - Create Your Own Program) Media Class
 * Abstract class serving to help initialize objects created by IPhoneDirectory.Java. Contains data regarding memory,
 * social media platforms, description, resolution, and ID in relation to IPhoneDirectory's static Random
 * @author Anthony Norderhaug
 * @date 5/4/2020
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public abstract class Media {
    int ID;
    int[] date;
    int[] resolution;
    String desc;
    double fileSize;
    int bitDepth;
    int numOfCompressions = 0;
    HashMap<String, Integer> projectedInteractions;
    String[] sharedPlatforms;
    int totalInteractions = 0;
    Random interactionVarience = new Random();

    /**
     * Media constructor used to initialize ID, resolution, description, bit depth, sharedPlatforms, and file Size.
     * Checks that generated ID does not already exist, if so, ID is randomly generated again.
     *
     * @param inputRes                  int[] pulled from IPhoneDirectory in initialization
     * @param inputDesc                 String pulled from IPhoneDirectory in initialization
     * @param curDirectory              LinkedList<Media> to determine pre-existing IDs
     * @param curDate                   int[] to access IPhoneDirectory date
     */

    public Media(int[] inputRes, String inputDesc, LinkedList<Media> curDirectory, int[] curDate) {
        setDate(curDate);
        ID = IPhoneDirectory.randGen.nextInt(500);
        for (int i = 0; i < curDirectory.size(); i++)  {
            if (this.ID == curDirectory.get(i).ID) {
                ID = IPhoneDirectory.randGen.nextInt(500);
                i = 0;
            }
        }
        resolution = inputRes;
        setDesc(inputDesc);
        calcBitDepth();
        sharedPlatforms = null;
        fileSize = -1;
    }

    public void setDate(int[] inputDate) {
        date = inputDate;
    }

    public int[] getDate() {
        return date;
    }

    public int getID() {
        return ID;
    }

    public void setResolution(int[] inputRes) {
        resolution = inputRes;
        calcMemorySize();
    }

    public void setDesc(String inputDesc) {
        desc = inputDesc;
    }

    public String printDate() {
        return date[0] + "/" + date[1] + "/" + date[2];
    }

    public double getFileSize() {
        return fileSize;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * calcBitDepth() pulls String from resQuality() to determine what media's bit depth should be, used for calculating
     * memory size. (BY NO MEANS IS THIS ACCURATE FILE SIZE CALCULATION)
     */

    public void calcBitDepth() {
        String quality = resQuality();

        if (quality.equals("High quality")) {
            bitDepth = 8;
        } else if (quality.equals("Medium quality")) {
            bitDepth = 4;
        }
        else {
            bitDepth = 2;
        }
    }

    /**
     * resQuality() compares product of resolutions to hard-coded resolutions in order to determine String describing
     * media quality
     *
     * @return                          String representing media's quality
     */

    public String resQuality() {
        if (resolution[0] * resolution[1] >= Math.pow(1080,2)) {
            return "High quality";
        } else if (resolution[0] * resolution[1] >= Math.pow(720,2)) {
            return "Medium quality";
        }
        return "Poor quality";
    }

    /**
     * calcResponse() calculates social media response given String[] sharedPlatforms and HashTable
     */
    public void calcResponse() {
        if (sharedPlatforms != null) {
            for (String platform : sharedPlatforms) {
                if (projectedInteractions.containsKey(platform)) {
                    totalInteractions += projectedInteractions.get(platform);
                } else {
                    totalInteractions++;
                }
            }
        }
    }

    /**
     * compressMedia() interprets bitDepth and divides both resolution dimensions by half. Quality, bit depth, and memory
     * size is calculated to interpret new file size. int numOfCompressions is also incremented.
     *
     * @return                          boolean
     */
    public boolean compressMedia() {
        if (bitDepth > 2) {
            for (int i = 0; i < resolution.length; i++) {
                resolution[i] = resolution[i]/2;
            }
            resQuality();
            calcBitDepth();
            calcMemorySize();
            numOfCompressions++;

            return true;
        }
        return false;
    }

    /**
     * abstract method calcMemorySize() which differs between inheriting object classes Photo and Video
     */
    public abstract void calcMemorySize();
}
