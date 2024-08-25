package hypernova.hithchhiker.guide.galaxy.characters;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class Myself extends AppCompatActivity {
    public String name;
    public String surname;

    public void initiateVariables() {
        name = "Alex";
        surname = "Peabody";
    }

    public void save() {
        SharedPreferences.Editor editor = getSharedPreferences("hypernova.save", MODE_PRIVATE).edit();

        editor.putString("name", name);
        editor.putString("surname", surname);

        editor.commit();
    }

    public void restore() {
        SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);

        name = sharedPrefs.getString("name", "Alex");
        surname = sharedPrefs.getString("surname", "Peabody");
    }
}
