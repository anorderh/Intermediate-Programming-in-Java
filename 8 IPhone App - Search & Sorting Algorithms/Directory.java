import java.util.ArrayList;
import java.util.LinkedList;

public interface Directory {
    boolean advanceDate(int advance, String timeUnit);
    boolean takePicture(int[] inputRes, String inputDesc, LinkedList<Media> curDirectory, int[] curDate);
    boolean recordVideo(int[] inputRes, String inputDesc, int inputDuration, LinkedList<Media> curDirectory, int[] curDate);
    void removeMedia(int mediaID);
    void sort(int start, int end);
    int search(int ID);
    void share(Media selectedMedia, String[] inputSocialMedias);
    void displayInfo(Media selectedMedia);
    void modify(Media selectedMedia);
    boolean printDirectory();
}
