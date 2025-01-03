package hypernova.hithchhiker.guide.galaxy.managers;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.R;

public class MechanicsManager extends AppCompatActivity {
    LinearLayout topBar;
    public MechanicsManager(LinearLayout tb) {
        topBar = tb;
    }

    public void changeAppColor(LinearLayout linearLayout, int newColor, Activity activity) {
        switch (newColor) {
            case -5592406: // new color is grey
                activity.getTheme().applyStyle(R.style.AppTheme, true);
                break;
            case -10044566: // new color is green
                activity.getTheme().applyStyle(R.style.AppThemeGreen, true);
                break;
            case -476208: // new color is pink
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

    public void exitApp(Activity activity) {
        activity.finish();
        System.exit(0);
    }
}
