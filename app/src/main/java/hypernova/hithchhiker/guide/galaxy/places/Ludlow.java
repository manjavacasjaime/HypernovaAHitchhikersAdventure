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
    public ArrayList<String> objectsDroppedHouse = new ArrayList<>();

    public void initiateVariables() {
        scoreWhenFredWaiting = 0;
        isHouseDoorOpen = false;
        isHouseWaterDrunk = false;
        objectsDroppedHouse.clear();
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putInt("ludlow.scoreWhenFredWaiting", scoreWhenFredWaiting);
        editor.putBoolean("ludlow.isHouseDoorOpen", isHouseDoorOpen);
        editor.putBoolean("ludlow.isHouseWaterDrunk", isHouseWaterDrunk);

        Set<String> objectsDroppedHouseSet = new HashSet<>(objectsDroppedHouse);
        editor.putStringSet("ludlow.objectsDroppedHouseSet", objectsDroppedHouseSet);

        editor.commit();
    }

    public void restore() {
        Set<String> emptyset = new HashSet<>();
        scoreWhenFredWaiting = sharedPrefs.getInt("ludlow.scoreWhenFredWaiting", 0);
        isHouseDoorOpen = sharedPrefs.getBoolean("ludlow.isHouseDoorOpen", false);
        isHouseWaterDrunk = sharedPrefs.getBoolean("ludlow.isHouseWaterDrunk", false);

        Set<String> objectsDroppedHouseSet = sharedPrefs.getStringSet("ludlow.objectsDroppedHouseSet", emptyset);
        objectsDroppedHouse = new ArrayList<>(objectsDroppedHouseSet);
    }
}
