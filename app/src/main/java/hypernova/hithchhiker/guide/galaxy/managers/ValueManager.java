package hypernova.hithchhiker.guide.galaxy.managers;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import hypernova.hithchhiker.guide.galaxy.R;
import hypernova.hithchhiker.guide.galaxy.characters.Fred;
import hypernova.hithchhiker.guide.galaxy.characters.Myself;
import hypernova.hithchhiker.guide.galaxy.places.Ludlow;
import hypernova.hithchhiker.guide.galaxy.places.MyBasement;

public class ValueManager extends AppCompatActivity {
    public SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);
    public Myself myself = new Myself();
    public Fred fred = new Fred();
    public MyBasement myBasement = new MyBasement();
    public Ludlow ludlow = new Ludlow();
    public boolean isMatchSaved = sharedPrefs.getBoolean("isMatchSaved", false);
    public int appColor = sharedPrefs.getInt("appColor", 1); // 1 grey, 2 green, 3 pink
    public String dieOptions = "\n\nType RESTART, RESTORE, COMMANDS or QUIT.";
    public int currentObjective; // WHICH OBJECTIVE I AM DOING. ZERO IS GAME OVER
    public int score;

    ArrayList<String> myInventoryPast = new ArrayList<String>();
    ArrayList<String> dropLibraryL = new ArrayList<String>();
    ArrayList<String> dropRoomD = new ArrayList<String>();
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

    int inreading = 0;
    int inconversationsully = 0;
    int knowsarmoredpeople = 0; //pregunta y le habla de gente con armadura
    int inconversationabigail = 0;
    int abigailchatphase = 1; // DIALOGO DINAMICO ABIGAIL
    int inconversationkenny = 0;
    int inconversationhenry = 0;
    int inconversationicarus = 0;
    int icaruschatphase = 1; // DIALOGO DINAMICO ICARUS
    int inconversationshannon = 0;
    int brokenwindow8 = 0;
    int peopleleave8 = 0;
    int haskidicarus = 0; // VACILAR ICARUS
    int score8 = 0;
    int scorepeopleleave8 = 0;
    int hasreadborrowedbooks = 0;

    int stoppeton = 0;
    int firsttimebacklibrary = 1;
    int pastabigail = 12; // decision tú pasado a abigail: 1 persona distante, 2 persona afable
    int correctdoor9 = 0;
    int score9 = 0;

    int closeddoor = 0; // esta es una variable que se irá reciclando para todas las puertas a partir de obj10
    int housedesert = 12;
    int havefoundeggin = 00;
    int brokenegg = 0;

    public void initiateVariables() {
        myself.initiateVariables();
        fred.initiateVariables();
        myBasement.initiateVariables();
        ludlow.initiateVariables();

        currentObjective = 1;
        score = 0;

        myInventoryPast.clear();
        dropLibraryL.clear();
        dropRoomD.clear();
        dropHouseDesert11.clear();
        dropHouseDesert12.clear();
        dropHouseDesert13.clear();
        dropHouseDesert21.clear();
        dropHouseDesert22.clear();
        dropHouseDesert23.clear();
        dropHouseDesert32.clear();

        knowsarmoredpeople = 0; //pregunta y le habla de gente con armadura
        abigailchatphase = 1; // DIALOGO DINAMICO ABIGAIL
        icaruschatphase = 1; // DIALOGO DINAMICO ICARUS
        brokenwindow8 = 0;
        peopleleave8 = 0;
        haskidicarus = 0; // VACILAR ICARUS
        score8 = 0;
        scorepeopleleave8 = 0;
        hasreadborrowedbooks = 0;

        stoppeton = 0;
        firsttimebacklibrary = 1;
        pastabigail = 12; // decision tú pasado a abigail: 1 persona distante, 2 persona afable
        correctdoor9 = 0;
        score9 = 0;

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

        isMatchSaved = true;
        editor.putBoolean("isMatchSaved", true);
        editor.putInt("currentObjective", currentObjective);

        editor.putInt("score", score);

        TextView myLocation = (TextView) findViewById(R.id.location);
        String locSaved = myLocation.getText().toString();
        editor.putString("locSaved", locSaved);

        Set<String> myInventoryPastSet = new HashSet<>(myInventoryPast);
        editor.putStringSet("myInventoryPastSet", myInventoryPastSet);
        Set<String> dropLibraryLSet = new HashSet<>(dropLibraryL);
        editor.putStringSet("dropLibraryLSet", dropLibraryLSet);
        Set<String> dropRoomDSet = new HashSet<>(dropRoomD);
        editor.putStringSet("dropRoomDSet", dropRoomDSet);
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

        editor.putInt("knowsarmoredpeople", knowsarmoredpeople);
        editor.putInt("abigailchatphase", abigailchatphase);
        editor.putInt("icaruschatphase", icaruschatphase);
        editor.putInt("brokenwindow8", brokenwindow8);
        editor.putInt("peopleleave8", peopleleave8);
        editor.putInt("haskidicarus", haskidicarus);
        editor.putInt("score8", score8);
        editor.putInt("scorepeopleleave8", scorepeopleleave8);
        editor.putInt("hasreadborrowedbooks", hasreadborrowedbooks);

        editor.putInt("stoppeton", stoppeton);
        editor.putInt("firsttimebacklibrary", firsttimebacklibrary);
        editor.putInt("pastabigail", pastabigail);
        editor.putInt("correctdoor9", correctdoor9);
        editor.putInt("score9", score9);

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

        currentObjective = sharedPrefs.getInt("currentObjective", 1);

        score = sharedPrefs.getInt("score", 0);
        TextView myMoves = (TextView) findViewById(R.id.moves);
        myMoves.setText("Moves: " + score);

        String locSaved = sharedPrefs.getString("locSaved", "Just playing");
        TextView myLocation = (TextView) findViewById(R.id.location);
        myLocation.setText(locSaved);

        Set<String> myInventoryPastSet = sharedPrefs.getStringSet("myInventoryPastSet", emptyset);
        myInventoryPast = new ArrayList<>(myInventoryPastSet);
        Set<String> dropLibraryLSet = sharedPrefs.getStringSet("dropLibraryLSet", emptyset);
        dropLibraryL = new ArrayList<>(dropLibraryLSet);
        Set<String> dropRoomDSet = sharedPrefs.getStringSet("dropRoomDSet", emptyset);
        dropRoomD = new ArrayList<>(dropRoomDSet);
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

        knowsarmoredpeople = sharedPrefs.getInt("knowsarmoredpeople", 0);
        abigailchatphase = sharedPrefs.getInt("abigailchatphase", 1);
        icaruschatphase = sharedPrefs.getInt("icaruschatphase", 1);
        brokenwindow8 = sharedPrefs.getInt("brokenwindow8", 0);
        peopleleave8 = sharedPrefs.getInt("peopleleave8", 0);
        haskidicarus = sharedPrefs.getInt("haskidicarus", 0);
        score8 = sharedPrefs.getInt("score8", 0);
        scorepeopleleave8 = sharedPrefs.getInt("scorepeopleleave8", 0);
        hasreadborrowedbooks = sharedPrefs.getInt("hasreadborrowedbooks", 0);

        stoppeton = sharedPrefs.getInt("stoppeton", 0);
        firsttimebacklibrary = sharedPrefs.getInt("firsttimebacklibrary", 1);
        pastabigail = sharedPrefs.getInt("pastabigail", 12);
        correctdoor9 = sharedPrefs.getInt("correctdoor9", 0);
        score9 = sharedPrefs.getInt("score9", 0);

        closeddoor = sharedPrefs.getInt("closeddoor", 0);
        housedesert = sharedPrefs.getInt("housedesert", 12);
        havefoundeggin = sharedPrefs.getInt("havefoundeggin", 00);
        brokenegg = sharedPrefs.getInt("brokenegg", 0);
    }
}
