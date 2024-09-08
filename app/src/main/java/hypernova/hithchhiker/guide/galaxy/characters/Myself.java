package hypernova.hithchhiker.guide.galaxy.characters;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Myself extends AppCompatActivity {
    SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);
    public String name;
    public String surname;
    public ArrayList<String> inventory = new ArrayList<>();
    public boolean isStanding;
    public boolean isNeckChipInstalled; // This device will be used to check which rooms is the character allowed to access

    public void initiateVariables() {
        name = "Alex";
        surname = "Peabody";
        inventory.clear();
        isStanding = false;
        isNeckChipInstalled = false;
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString("name", name);
        editor.putString("surname", surname);
        Set<String> inventorySet = new HashSet<>(inventory);
        editor.putStringSet("inventorySet", inventorySet);
        editor.putBoolean("isStanding", isStanding);
        editor.putBoolean("isNeckChipInstalled", isNeckChipInstalled);

        editor.commit();
    }

    public void restore() {
        name = sharedPrefs.getString("name", "Alex");
        surname = sharedPrefs.getString("surname", "Peabody");
        Set<String> emptySet = new HashSet<>();
        Set<String> inventorySet = sharedPrefs.getStringSet("inventorySet", emptySet);
        inventory = new ArrayList<>(inventorySet);
        isStanding = sharedPrefs.getBoolean("isStanding", false);
        isNeckChipInstalled = sharedPrefs.getBoolean("isNeckChipInstalled", false);
    }
}
