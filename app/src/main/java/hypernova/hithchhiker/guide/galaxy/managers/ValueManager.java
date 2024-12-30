package hypernova.hithchhiker.guide.galaxy.managers;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.R;
import hypernova.hithchhiker.guide.galaxy.characters.Abigail;
import hypernova.hithchhiker.guide.galaxy.characters.Fred;
import hypernova.hithchhiker.guide.galaxy.characters.Henry;
import hypernova.hithchhiker.guide.galaxy.characters.Icarus;
import hypernova.hithchhiker.guide.galaxy.characters.Kenny;
import hypernova.hithchhiker.guide.galaxy.characters.Myself;
import hypernova.hithchhiker.guide.galaxy.characters.Shannon;
import hypernova.hithchhiker.guide.galaxy.characters.Sully;
import hypernova.hithchhiker.guide.galaxy.places.Ludlow;
import hypernova.hithchhiker.guide.galaxy.places.LudlowLibrary;
import hypernova.hithchhiker.guide.galaxy.places.MyBasement;
import hypernova.hithchhiker.guide.galaxy.places.Nowhere;

public class ValueManager extends AppCompatActivity {
    public SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);
    public Myself myself = new Myself();
    public Fred fred = new Fred();
    public Abigail abigail = new Abigail();
    public Icarus icarus = new Icarus();
    public Shannon shannon = new Shannon();
    public Sully sully = new Sully();
    public Henry henry = new Henry();
    public Kenny kenny = new Kenny();
    public MyBasement myBasement = new MyBasement();
    public Ludlow ludlow = new Ludlow();
    public LudlowLibrary lwLibrary = new LudlowLibrary();
    public Nowhere nowhere = new Nowhere();
    public boolean isMatchSaved = sharedPrefs.getBoolean("isMatchSaved", false);
    public int appColor = sharedPrefs.getInt("appColor", 1); // 1 grey, 2 green, 3 pink
    public String dieOptions = "\n\nType RESTART, RESTORE, COMMANDS or QUIT.";
    public int currentObjective; // WHICH OBJECTIVE I AM DOING. ZERO IS GAME OVER
    public int score;

    int blinkcolor = 0; //0 black, 1 grey
    int pos = -1;
    int edittouched = 0;
    int firstclicked = 0;
    int lastposselected = 0;
    boolean callbacksRemoved;
    int prevStringLength = 2;
    boolean capsLocked = false;

    public void initiateVariables() {
        myself.initiateVariables();
        fred.initiateVariables();
        myBasement.initiateVariables();
        ludlow.initiateVariables();
        lwLibrary.initiateVariables();
        nowhere.initiateVariables();

        currentObjective = 1;
        score = 0;
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        myself.save();
        fred.save();
        myBasement.save();
        ludlow.save();
        lwLibrary.save();
        nowhere.save();

        isMatchSaved = true;
        editor.putBoolean("isMatchSaved", true);
        editor.putInt("currentObjective", currentObjective);
        editor.putInt("score", score);

        TextView myLocation = (TextView) findViewById(R.id.location);
        String locSaved = myLocation.getText().toString();
        editor.putString("locSaved", locSaved);

        editor.commit();
    }

    public void restore() {
        myself.restore();
        fred.restore();
        myBasement.restore();
        ludlow.restore();
        lwLibrary.restore();
        nowhere.restore();

        currentObjective = sharedPrefs.getInt("currentObjective", 1);
        score = sharedPrefs.getInt("score", 0);
        TextView myMoves = (TextView) findViewById(R.id.moves);
        myMoves.setText("Moves: " + score);

        String locSaved = sharedPrefs.getString("locSaved", "Just playing");
        TextView myLocation = (TextView) findViewById(R.id.location);
        myLocation.setText(locSaved);
    }
}
