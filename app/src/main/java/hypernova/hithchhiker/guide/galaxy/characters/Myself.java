package hypernova.hithchhiker.guide.galaxy.characters;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Myself extends AppCompatActivity {
    SharedPreferences sharedPrefs;
    public String name;
    public String surname;
    public boolean isStanding;
    public boolean isNeckChipInstalled; // This device will be used to check which rooms is the character allowed to access
    public ArrayList<String> inventory = new ArrayList<>();
    public ArrayList<String> inventoryPast = new ArrayList<>();

    // Important choices
    public boolean hasKidIcarus; // Whether or not you have kidded Icarus
    public int kindOfPerson; // -1 not yet chosen, 0 choosing, 1 distant, 2 affable. Commented to Abigail

    public Myself(SharedPreferences sharedPreferences) {
        sharedPrefs = sharedPreferences;
    }

    public void initiateVariables() {
        name = "Alex";
        surname = "Peabody";
        isStanding = false;
        isNeckChipInstalled = false;
        inventory.clear();
        inventoryPast.clear();

        hasKidIcarus = false;
        kindOfPerson = -1;
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString("myself.name", name);
        editor.putString("myself.surname", surname);
        editor.putBoolean("myself.isStanding", isStanding);
        editor.putBoolean("myself.isNeckChipInstalled", isNeckChipInstalled);

        Set<String> inventorySet = new HashSet<>(inventory);
        editor.putStringSet("myself.inventorySet", inventorySet);
        Set<String> inventoryPastSet = new HashSet<>(inventoryPast);
        editor.putStringSet("myself.inventoryPastSet", inventoryPastSet);

        editor.putBoolean("myself.hasKidIcarus", hasKidIcarus);
        editor.putInt("myself.kindOfPerson", kindOfPerson);

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
        Set<String> inventoryPastSet = sharedPrefs.getStringSet("myself.inventoryPastSet", emptySet);
        inventoryPast = new ArrayList<>(inventoryPastSet);

        hasKidIcarus = sharedPrefs.getBoolean("myself.hasKidIcarus", false);
        kindOfPerson = sharedPrefs.getInt("myself.kindOfPerson", -1);
    }
}
