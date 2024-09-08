package hypernova.hithchhiker.guide.galaxy.levels.common;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.R;

public class ConsultGuide extends AppCompatActivity {
    public boolean isConsultingGuide = false;

    public ConsultGuide() {}

    public String checkObjAnswer(String myObjX, Activity activity) {
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(activity.getBaseContext(), R.font.lucida_console);
        TextView secondText = new TextView(activity.getBaseContext());
        secondText.setTypeface(typeface);

        if (myObjX.contains("consult") && myObjX.contains("guide")) {
            secondText.setText("You can't see any Guide here!");
            linearLayout.addView(secondText);
        } else if (myObjX.matches("consult")) {
            isConsultingGuide = true;
            secondText.setText("Consult what?");
            linearLayout.addView(secondText);
        } else if (isConsultingGuide && myObjX.contains("guide")) {
            isConsultingGuide = false;
            secondText.setText("You can't see any Guide here!");
            linearLayout.addView(secondText);
        } else if (myObjX.contains("consult")) {
            secondText.setText("There's nothing you can consult.");
            linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }
}
