package hypernova.hithchhiker.guide.galaxy.characters;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Fred extends AppCompatActivity {
    SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);
    // 0 no conversation, XY being X the objective and Y the conversation stage
    public int conversationStatus = 0;
    public Handler handlerFred;
    public Runnable runnableFred;
    public int examineFredDuringObj7Count;
    public boolean isDead;
    public int scoreDuringFredKilling;
    public boolean isPresent;

    public void initiateVariables() {
        examineFredDuringObj7Count = 0;
        isDead = false;
        scoreDuringFredKilling = 0;
        isPresent = false;
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putInt("fred.examineFredDuringObj7Count", examineFredDuringObj7Count);
        editor.putBoolean("fred.isDead", isDead);
        editor.putInt("fred.scoreDuringFredKilling", scoreDuringFredKilling);
        editor.putBoolean("fred.isPresent", isPresent);

        editor.commit();
    }

    public void restore() {
        examineFredDuringObj7Count = sharedPrefs.getInt("fred.examineFredDuringObj7Count", 0);
        isDead = sharedPrefs.getBoolean("fred.isDead", false);
        scoreDuringFredKilling = sharedPrefs.getInt("fred.scoreDuringFredKilling", 0);
        isPresent = sharedPrefs.getBoolean("fred.isPresent", false);
    }
}
