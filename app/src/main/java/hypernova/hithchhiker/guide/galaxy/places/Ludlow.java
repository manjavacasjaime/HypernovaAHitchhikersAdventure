package hypernova.hithchhiker.guide.galaxy.places;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Ludlow extends AppCompatActivity {
    SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);
    public int scoreWhenFredWaiting;
    public boolean isHouseDoorOpen;
    public boolean isHouseWaterDrunk;
    public String currentLocation; // house, street, library
    public ArrayList<String> objectsDroppedHouse = new ArrayList<>();
    public ArrayList<String> objectsDroppedStreet = new ArrayList<>();
    public ArrayList<String> objectsDroppedLibrary = new ArrayList<>();

    public void initiateVariables() {
        scoreWhenFredWaiting = 0;
        isHouseDoorOpen = false;
        isHouseWaterDrunk = false;
        currentLocation = "house";
        objectsDroppedHouse.clear();
        objectsDroppedStreet.clear();
        objectsDroppedLibrary.clear();
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putInt("ludlow.scoreWhenFredWaiting", scoreWhenFredWaiting);
        editor.putBoolean("ludlow.isHouseDoorOpen", isHouseDoorOpen);
        editor.putBoolean("ludlow.isHouseWaterDrunk", isHouseWaterDrunk);
        editor.putString("ludlow.currentLocation", currentLocation);

        Set<String> objectsDroppedHouseSet = new HashSet<>(objectsDroppedHouse);
        editor.putStringSet("ludlow.objectsDroppedHouseSet", objectsDroppedHouseSet);
        Set<String> objectsDroppedStreetSet = new HashSet<>(objectsDroppedStreet);
        editor.putStringSet("ludlow.objectsDroppedStreetSet", objectsDroppedStreetSet);
        Set<String> objectsDroppedLibrarySet = new HashSet<>(objectsDroppedLibrary);
        editor.putStringSet("ludlow.objectsDroppedLibrarySet", objectsDroppedLibrarySet);

        editor.commit();
    }

    public void restore() {
        Set<String> emptyset = new HashSet<>();
        scoreWhenFredWaiting = sharedPrefs.getInt("ludlow.scoreWhenFredWaiting", 0);
        isHouseDoorOpen = sharedPrefs.getBoolean("ludlow.isHouseDoorOpen", false);
        isHouseWaterDrunk = sharedPrefs.getBoolean("ludlow.isHouseWaterDrunk", false);
        currentLocation = sharedPrefs.getString("ludlow.currentLocation", "house");

        Set<String> objectsDroppedHouseSet = sharedPrefs.getStringSet("ludlow.objectsDroppedHouseSet", emptyset);
        objectsDroppedHouse = new ArrayList<>(objectsDroppedHouseSet);
        Set<String> objectsDroppedStreetSet = sharedPrefs.getStringSet("ludlow.objectsDroppedStreetSet", emptyset);
        objectsDroppedStreet = new ArrayList<>(objectsDroppedStreetSet);
        Set<String> objectsDroppedLibrarySet = sharedPrefs.getStringSet("ludlow.objectsDroppedLibrarySet", emptyset);
        objectsDroppedLibrary = new ArrayList<>(objectsDroppedLibrarySet);
    }
}
