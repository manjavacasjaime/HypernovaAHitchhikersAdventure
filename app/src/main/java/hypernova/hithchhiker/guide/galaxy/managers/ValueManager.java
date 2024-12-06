package hypernova.hithchhiker.guide.galaxy.managers;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    ArrayList<String> myInventoryPast = new ArrayList<String>();
    ArrayList<String> dropHouseDesert11 = new ArrayList<String>();
    ArrayList<String> dropHouseDesert12 = new ArrayList<String>();
    ArrayList<String> dropHouseDesert13 = new ArrayList<String>();
    ArrayList<String> dropHouseDesert21 = new ArrayList<String>();
    ArrayList<String> dropHouseDesert22 = new ArrayList<String>();
    ArrayList<String> dropHouseDesert23 = new ArrayList<String>();
    ArrayList<String> dropHouseDesert32 = new ArrayList<String>();

    int blinkcolor = 0; //0 black, 1 grey
    int pos = -1;
    int edittouched = 0;
    int firstclicked = 0;
    int lastposselected = 0;
    boolean callbacksRemoved;
    int prevStringLength = 2;
    boolean capsLocked = false;

    int closeddoor = 0; // esta es una variable que se irá reciclando para todas las puertas a partir de obj10
    int housedesert = 12;
    int havefoundeggin = 00;
    int brokenegg = 0;

    public void initiateVariables() {
        myself.initiateVariables();
        fred.initiateVariables();
        myBasement.initiateVariables();
        ludlow.initiateVariables();
        lwLibrary.initiateVariables();
        nowhere.initiateVariables();

        currentObjective = 1;
        score = 0;

        myInventoryPast.clear();
        dropHouseDesert11.clear();
        dropHouseDesert12.clear();
        dropHouseDesert13.clear();
        dropHouseDesert21.clear();
        dropHouseDesert22.clear();
        dropHouseDesert23.clear();
        dropHouseDesert32.clear();

        closeddoor = 0; // esta es una variable que se irá reciclando para todas las puertas a partir de obj10
        housedesert = 12;
        havefoundeggin = 00;
        brokenegg = 0;
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

        Set<String> myInventoryPastSet = new HashSet<>(myInventoryPast);
        editor.putStringSet("myInventoryPastSet", myInventoryPastSet);
        Set<String> dropHouseDesert11Set = new HashSet<>(dropHouseDesert11);
        editor.putStringSet("dropHouseDesert11Set", dropHouseDesert11Set);
        Set<String> dropHouseDesert12Set = new HashSet<>(dropHouseDesert12);
        editor.putStringSet("dropHouseDesert12Set", dropHouseDesert12Set);
        Set<String> dropHouseDesert13Set = new HashSet<>(dropHouseDesert13);
        editor.putStringSet("dropHouseDesert13Set", dropHouseDesert13Set);
        Set<String> dropHouseDesert21Set = new HashSet<>(dropHouseDesert21);
        editor.putStringSet("dropHouseDesert21Set", dropHouseDesert21Set);
        Set<String> dropHouseDesert22Set = new HashSet<>(dropHouseDesert22);
        editor.putStringSet("dropHouseDesert22Set", dropHouseDesert22Set);
        Set<String> dropHouseDesert23Set = new HashSet<>(dropHouseDesert23);
        editor.putStringSet("dropHouseDesert23Set", dropHouseDesert23Set);
        Set<String> dropHouseDesert32Set = new HashSet<>(dropHouseDesert32);
        editor.putStringSet("dropHouseDesert32Set", dropHouseDesert32Set);

        editor.putInt("closeddoor", closeddoor);
        editor.putInt("housedesert", housedesert);
        editor.putInt("havefoundeggin", havefoundeggin);
        editor.putInt("brokenegg", brokenegg);

        editor.commit();
    }

    public void restore() {
        Set<String> emptyset = new HashSet<>();
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

        Set<String> myInventoryPastSet = sharedPrefs.getStringSet("myInventoryPastSet", emptyset);
        myInventoryPast = new ArrayList<>(myInventoryPastSet);
        Set<String> dropHouseDesert11Set = sharedPrefs.getStringSet("dropHouseDesert11Set", emptyset);
        dropHouseDesert11 = new ArrayList<>(dropHouseDesert11Set);
        Set<String> dropHouseDesert12Set = sharedPrefs.getStringSet("dropHouseDesert12Set", emptyset);
        dropHouseDesert12 = new ArrayList<>(dropHouseDesert12Set);
        Set<String> dropHouseDesert13Set = sharedPrefs.getStringSet("dropHouseDesert13Set", emptyset);
        dropHouseDesert13 = new ArrayList<>(dropHouseDesert13Set);
        Set<String> dropHouseDesert21Set = sharedPrefs.getStringSet("dropHouseDesert21Set", emptyset);
        dropHouseDesert21 = new ArrayList<>(dropHouseDesert21Set);
        Set<String> dropHouseDesert22Set = sharedPrefs.getStringSet("dropHouseDesert22Set", emptyset);
        dropHouseDesert22 = new ArrayList<>(dropHouseDesert22Set);
        Set<String> dropHouseDesert23Set = sharedPrefs.getStringSet("dropHouseDesert23Set", emptyset);
        dropHouseDesert23 = new ArrayList<>(dropHouseDesert23Set);
        Set<String> dropHouseDesert32Set = sharedPrefs.getStringSet("dropHouseDesert32Set", emptyset);
        dropHouseDesert32 = new ArrayList<>(dropHouseDesert32Set);

        closeddoor = sharedPrefs.getInt("closeddoor", 0);
        housedesert = sharedPrefs.getInt("housedesert", 12);
        havefoundeggin = sharedPrefs.getInt("havefoundeggin", 00);
        brokenegg = sharedPrefs.getInt("brokenegg", 0);
    }
}
