package hypernova.hithchhiker.guide.galaxy.characters;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class Myself extends AppCompatActivity {
    public String name;
    public String surname;
    public boolean isStanding;
    public boolean isNeckChipInstalled; // This device will be used to check which rooms is the character allowed to access

    public void initiateVariables() {
        name = "Alex";
        surname = "Peabody";
        isStanding = false;
        isNeckChipInstalled = false;
    }

    public void save() {
        SharedPreferences.Editor editor = getSharedPreferences("hypernova.save", MODE_PRIVATE).edit();

        editor.putString("name", name);
        editor.putString("surname", surname);
        editor.putBoolean("isStanding", isStanding);
        editor.putBoolean("isNeckChipInstalled", isNeckChipInstalled);

        editor.commit();
    }

    public void restore() {
        SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);

        name = sharedPrefs.getString("name", "Alex");
        surname = sharedPrefs.getString("surname", "Peabody");
        isStanding = sharedPrefs.getBoolean("isStanding", false);
        isNeckChipInstalled = sharedPrefs.getBoolean("isNeckChipInstalled", false);
    }
}
