package hypernova.hithchhiker.guide.galaxy.managers;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.R;

public class MechanicsManager extends AppCompatActivity {
    Activity activity;
    LinearLayout linearLayout;
    LinearLayout topBar;

    public MechanicsManager(Activity act, LinearLayout ll, LinearLayout tb) {
        activity = act;
        linearLayout = ll;
        topBar = tb;
    }

    public void changeAppColor(int appColor) {
        int newColor = 0;
        switch (appColor) {
            case 1: // new color is grey
                newColor = ContextCompat.getColor(activity, R.color.colorAccent);
                activity.getTheme().applyStyle(R.style.AppTheme, true);
                break;
            case 2: // new color is green
                newColor = ContextCompat.getColor(activity, R.color.colorGreen);
                activity.getTheme().applyStyle(R.style.AppThemeGreen, true);
                break;
            case 3: // new color is pink
                newColor = ContextCompat.getColor(activity, R.color.colorPink);
                activity.getTheme().applyStyle(R.style.AppThemePink, true);
                break;
        }

        final int childCount = linearLayout.getChildCount(); // changes old text
        for (int i = 0; i < childCount; i++) {
            View v = linearLayout.getChildAt(i);
            if (v instanceof EditText) {
                EditText vEdit = (EditText) v;
                vEdit.setTextColor(newColor);
            }
            else if (v instanceof TextView) {
                TextView vText = (TextView) v;
                vText.setTextColor(newColor);
            }
        }

        topBar.setBackgroundColor(newColor); // changes top bar
    }

    public void exitApp() {
        activity.finish();
        System.exit(0);
    }
}
