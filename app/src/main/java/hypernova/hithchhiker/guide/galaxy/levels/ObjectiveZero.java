package hypernova.hithchhiker.guide.galaxy.levels;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.managers.MechanicsManager;
import hypernova.hithchhiker.guide.galaxy.R;
import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveZero extends AppCompatActivity {
    Handler handler;
    Runnable myRunnable;
    ValueManager valueManager;

    public ObjectiveZero(ValueManager valManager) {
        valueManager = valManager;
    }

    public String checkObjAnswer (String myObjZero, Activity activity, MechanicsManager mechanicsManager) {
        TextView myMoves = (TextView) findViewById(R.id.moves);
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(activity.getBaseContext(), R.font.lucida_console);
        TextView secondText = new TextView(activity.getBaseContext());
        secondText.setTypeface(typeface);

        if (myObjZero.matches("restart")) {
            valueManager.initiateVariables();
            myMoves.setText("Moves: " + valueManager.score);
            myLocation.setText("Just playing");
            linearLayout.removeAllViews();
            secondText.setText(valueManager.myself.name + ".\nIt's April 16, 2019. You are in B903, one of the Bubbles regulated by the Space Federation.\nIt's been 298 years, 5 months and 13 days since the last hitchhiker, now referred to as Traveller, died.\n\nBasement\nYou find yourself sat down playing video games. Your eyes aren't able to stop looking at the screen.");
            linearLayout.addView(secondText);
        } else if (myObjZero.matches("restore")) {
            if (valueManager.isMatchSaved) {
                valueManager.restore();
                secondText.setText("Restored.");
            } else {
                secondText.setText("I couldn't find a saved game.\nYou need to SAVE first.");
            }
            linearLayout.addView(secondText);
        } else if (myObjZero.matches("commands") || myObjZero.matches("verbose")) {
            String myCommands = "GO (direction)\nSPEAK WITH (name)\nSTOP (action)\nCLOSE (object)\nOPEN (object)\nENTER (location)\nTURN OFF/ON (object)\nSTAND/LIE\nEXAMINE (object)\nTAKE/DROP (object)\nPUT (item) IN (container)\nHELP\nFOLLOW (name)\nDRINK (item)\nREAD (object)\nATTACK (object/name)\nLOOK\nI/INVENTORY\nSMELL/LISTEN\nSAVE/RESTORE\nCHANGE COLOR";
            secondText.setText(myCommands);
            linearLayout.addView(secondText);
        } else if (myObjZero.matches("quit")) {
            secondText.setText("Thanks for playing. <3");
            linearLayout.addView(secondText);
            handler = new Handler();
            myRunnable = new Runnable() {
                @Override
                public void run() {
                    mechanicsManager.exitApp(activity);
                }
            };
            handler.postDelayed(myRunnable, 1500);
        }

        return (String) secondText.getText();
    }
}
