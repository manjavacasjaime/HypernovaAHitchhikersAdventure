package hypernova.hithchhiker.guide.galaxy.places;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyBasement extends AppCompatActivity {
    SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);
    public int scoreWhenStopPlaying;
    public boolean isComputerOff;
    public ArrayList<String> objectsDropped = new ArrayList<>();

    public void initiateVariables() {
        scoreWhenStopPlaying = 0;
        isComputerOff = false;
        objectsDropped.clear();
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putInt("myBasement.scoreWhenStopPlaying", scoreWhenStopPlaying);
        editor.putBoolean("myBasement.isComputerOff", isComputerOff);

        Set<String> objectsDroppedSet = new HashSet<>(objectsDropped);
        editor.putStringSet("myBasement.objectsDroppedSet", objectsDroppedSet);

        editor.commit();
    }

    public void restore() {
        Set<String> emptyset = new HashSet<>();
        scoreWhenStopPlaying = sharedPrefs.getInt("myBasement.scoreWhenStopPlaying", 0);
        isComputerOff = sharedPrefs.getBoolean("myBasement.isComputerOff", false);

        Set<String> objectsDroppedSet = sharedPrefs.getStringSet("myBasement.objectsDroppedSet", emptyset);
        objectsDropped = new ArrayList<>(objectsDroppedSet);
    }
}
