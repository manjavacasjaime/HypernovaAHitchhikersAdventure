package hypernova.hithchhiker.guide.galaxy.characters;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class Jorge extends AppCompatActivity {
    SharedPreferences sharedPrefs;
    public int conversationStatus = 0;
    public boolean hasTakenLighter;

    public Jorge(SharedPreferences sharedPreferences) {
        sharedPrefs = sharedPreferences;
    }

    public void initiateVariables() {
        hasTakenLighter = false;
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putBoolean("jorge.hasTakenLighter", hasTakenLighter);

        editor.commit();
    }

    public void restore() {
        hasTakenLighter = sharedPrefs.getBoolean("jorge.hasTakenLighter", false);
    }
}
