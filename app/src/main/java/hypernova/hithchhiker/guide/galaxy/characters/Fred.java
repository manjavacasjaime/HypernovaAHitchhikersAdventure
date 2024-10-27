package hypernova.hithchhiker.guide.galaxy.characters;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class Fred extends AppCompatActivity {
    SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);
    public int conversationStatus = 0;
    public boolean isDead;
    public boolean isPresent;

    public void initiateVariables() {
        isDead = false;
        isPresent = false;
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putBoolean("fred.isDead", isDead);
        editor.putBoolean("fred.isPresent", isPresent);

        editor.commit();
    }

    public void restore() {
        isDead = sharedPrefs.getBoolean("fred.isDead", false);
        isPresent = sharedPrefs.getBoolean("fred.isPresent", false);
    }
}
