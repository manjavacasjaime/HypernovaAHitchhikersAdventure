package hypernova.hithchhiker.guide.galaxy.places;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Nowhere extends AppCompatActivity {
    SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);

    public void initiateVariables() {}

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.commit();
    }

    public void restore() {
        Set<String> emptyset = new HashSet<>();
    }
}

