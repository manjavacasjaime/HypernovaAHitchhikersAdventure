package hypernova.hithchhiker.guide.galaxy.levels;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.managers.MechanicsManager;
import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveZero extends AppCompatActivity {
    ValueManager vm;
    Handler handler;
    Runnable myRunnable;

    public ObjectiveZero(ValueManager valManager) {
        vm = valManager;
    }

    public String checkObjAnswer(String myObjZero, TextView secondText, MechanicsManager mechanicsManager) {
        if (myObjZero.matches("restart")) {
            vm.initiateVariables();
            vm.myMoves.setText("Moves: " + vm.score);
            vm.myLocation.setText("Just playing");
            vm.linearLayout.removeAllViews();
            secondText.setText(vm.myself.name + ".\nIt's April 16, 2019. You are in B903, one of the Bubbles regulated by the Space Federation.\nIt's been 298 years, 5 months and 13 days since the last hitchhiker, now referred to as Traveller, died.\n\nBasement\nYou find yourself sat down playing video games. Your eyes aren't able to stop looking at the screen.");
            vm.linearLayout.addView(secondText);
        } else if (myObjZero.matches("restore")) {
            if (vm.isMatchSaved) {
                vm.restore();
                secondText.setText("Restored.");
            } else {
                secondText.setText("I couldn't find a saved game.\nYou need to SAVE first.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjZero.matches("clear") || myObjZero.matches("cls")) {
            vm.linearLayout.removeAllViews();
            return "clear"; // Dummy string to avoid displaying "I don't recognize this sentence."
        } else if (myObjZero.matches("commands") || myObjZero.matches("verbose")) {
            String myCommands = "GO (direction)\nSPEAK WITH (name)\nSTOP (action)\nCLOSE (object)\nOPEN (object)\nENTER (location)\nTURN OFF/ON (object)\nSTAND/LIE\nEXAMINE (object)\nTAKE/DROP (object)\nPUT (item) IN (container)\nHELP\nFOLLOW (name)\nDRINK (item)\nREAD (object)\nATTACK (object/name)\nLOOK\nI/INVENTORY\nSMELL/LISTEN\nSAVE/RESTORE\nCHANGE COLOR";
            secondText.setText(myCommands);
            vm.linearLayout.addView(secondText);
        } else if (myObjZero.matches("quit")) {
            secondText.setText("Thanks for playing. <3");
            vm.currentObjective = 100;
            vm.linearLayout.addView(secondText);
            handler = new Handler();
            myRunnable = new Runnable() {
                @Override
                public void run() {
                    mechanicsManager.exitApp();
                }
            };
            handler.postDelayed(myRunnable, 1500);
        }

        return (String) secondText.getText();
    }
}
