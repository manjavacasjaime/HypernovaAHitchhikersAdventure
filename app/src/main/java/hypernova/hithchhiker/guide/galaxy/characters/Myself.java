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
    public boolean isStanding;
    public boolean isNeckChipInstalled; // This device will be used to check which rooms is the character allowed to access
    public ArrayList<String> inventory = new ArrayList<>();

    public void initiateVariables() {
        name = "Alex";
        surname = "Peabody";
        isStanding = false;
        isNeckChipInstalled = false;
        inventory.clear();
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString("myself.name", name);
        editor.putString("myself.surname", surname);
        editor.putBoolean("myself.isStanding", isStanding);
        editor.putBoolean("myself.isNeckChipInstalled", isNeckChipInstalled);

        Set<String> inventorySet = new HashSet<>(inventory);
        editor.putStringSet("myself.inventorySet", inventorySet);

        editor.commit();
    }

    public void restore() {
        Set<String> emptySet = new HashSet<>();
        name = sharedPrefs.getString("myself.name", "Alex");
        surname = sharedPrefs.getString("myself.surname", "Peabody");
        isStanding = sharedPrefs.getBoolean("myself.isStanding", false);
        isNeckChipInstalled = sharedPrefs.getBoolean("myself.isNeckChipInstalled", false);

        Set<String> inventorySet = sharedPrefs.getStringSet("myself.inventorySet", emptySet);
        inventory = new ArrayList<>(inventorySet);
    }
}
