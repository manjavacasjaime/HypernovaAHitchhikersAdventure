package hypernova.hithchhiker.guide.galaxy.places;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Nowhere extends AppCompatActivity {
    SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);
    public boolean isHouseDoorOpen;
    public boolean isEggBroken;
    public int currentLocation; // 11, 12, 13, 21, 22, 23
    public ArrayList<String> objectsDropped11 = new ArrayList<>();
    public ArrayList<String> objectsDropped12 = new ArrayList<>();
    public ArrayList<String> objectsDropped13 = new ArrayList<>();
    public ArrayList<String> objectsDropped21 = new ArrayList<>();
    public ArrayList<String> objectsDropped22 = new ArrayList<>();
    public ArrayList<String> objectsDropped23 = new ArrayList<>();

    public void initiateVariables() {
        isHouseDoorOpen = true;
        isEggBroken = false;
        currentLocation = 12;
        objectsDropped11.clear();
        objectsDropped12.clear();
        objectsDropped13.clear();
        objectsDropped21.clear();
        objectsDropped22.clear();
        objectsDropped23.clear();
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putBoolean("nowhere.isHouseDoorOpen", isHouseDoorOpen);
        editor.putBoolean("nowhere.isEggBroken", isEggBroken);
        editor.putInt("nowhere.currentLocation", currentLocation);

        Set<String> objectsDropped11Set = new HashSet<>(objectsDropped11);
        editor.putStringSet("nowhere.objectsDropped11Set", objectsDropped11Set);
        Set<String> objectsDropped12Set = new HashSet<>(objectsDropped12);
        editor.putStringSet("nowhere.objectsDropped12Set", objectsDropped12Set);
        Set<String> objectsDropped13Set = new HashSet<>(objectsDropped13);
        editor.putStringSet("nowhere.objectsDropped13Set", objectsDropped13Set);
        Set<String> objectsDropped21Set = new HashSet<>(objectsDropped21);
        editor.putStringSet("nowhere.objectsDropped21Set", objectsDropped21Set);
        Set<String> objectsDropped22Set = new HashSet<>(objectsDropped22);
        editor.putStringSet("nowhere.objectsDropped22Set", objectsDropped22Set);
        Set<String> objectsDropped23Set = new HashSet<>(objectsDropped23);
        editor.putStringSet("nowhere.objectsDropped23Set", objectsDropped23Set);

        editor.commit();
    }

    public void restore() {
        Set<String> emptyset = new HashSet<>();
        isHouseDoorOpen = sharedPrefs.getBoolean("nowhere.isHouseDoorOpen", true);
        isEggBroken = sharedPrefs.getBoolean("nowhere.isEggBroken", false);
        currentLocation = sharedPrefs.getInt("nowhere.currentLocation", 12);

        Set<String> objectsDropped11Set = sharedPrefs.getStringSet("nowhere.objectsDropped11Set", emptyset);
        objectsDropped11 = new ArrayList<>(objectsDropped11Set);
        Set<String> objectsDropped12Set = sharedPrefs.getStringSet("nowhere.objectsDropped12Set", emptyset);
        objectsDropped12 = new ArrayList<>(objectsDropped12Set);
        Set<String> objectsDropped13Set = sharedPrefs.getStringSet("nowhere.objectsDropped13Set", emptyset);
        objectsDropped13 = new ArrayList<>(objectsDropped13Set);
        Set<String> objectsDropped21Set = sharedPrefs.getStringSet("nowhere.objectsDropped21Set", emptyset);
        objectsDropped21 = new ArrayList<>(objectsDropped21Set);
        Set<String> objectsDropped22Set = sharedPrefs.getStringSet("nowhere.objectsDropped22Set", emptyset);
        objectsDropped22 = new ArrayList<>(objectsDropped22Set);
        Set<String> objectsDropped23Set = sharedPrefs.getStringSet("nowhere.objectsDropped23Set", emptyset);
        objectsDropped23 = new ArrayList<>(objectsDropped23Set);
    }
}

