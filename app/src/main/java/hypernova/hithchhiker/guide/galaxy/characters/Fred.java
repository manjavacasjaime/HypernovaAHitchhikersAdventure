package hypernova.hithchhiker.guide.galaxy.characters;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Fred extends AppCompatActivity {
    SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);
    public Handler handlerFred;
    public Runnable runnableFred;
    public int examineFredDuringObj7Count;
    public boolean isDead;
    public int scoreDuringFredKilling;
    public boolean isPresent;
    public int scoreDuringFredWaiting;
    public int conversationStatus = 0;

    public void initiateVariables() {
        examineFredDuringObj7Count = 0;
        isDead = false;
        scoreDuringFredKilling = 0;
        isPresent = false;
        scoreDuringFredWaiting = 0;
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putInt("fred.examineFredDuringObj7Count", examineFredDuringObj7Count);
        editor.putBoolean("fred.isDead", isDead);
        editor.putInt("fred.scoreDuringFredKilling", scoreDuringFredKilling);
        editor.putBoolean("fred.isPresent", isPresent);
        editor.putInt("fred.scoreDuringFredWaiting", scoreDuringFredWaiting);

        editor.commit();
    }

    public void restore() {
        examineFredDuringObj7Count = sharedPrefs.getInt("fred.examineFredDuringObj7Count", 0);
        isDead = sharedPrefs.getBoolean("fred.isDead", false);
        scoreDuringFredKilling = sharedPrefs.getInt("fred.scoreDuringFredKilling", 0);
        isPresent = sharedPrefs.getBoolean("fred.isPresent", false);
        scoreDuringFredWaiting = sharedPrefs.getInt("fred.scoreDuringFredWaiting", 0);
    }
}
