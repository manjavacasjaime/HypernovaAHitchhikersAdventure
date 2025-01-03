package hypernova.hithchhiker.guide.galaxy.levels.common;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ConsultGuide extends AppCompatActivity {
    ValueManager vm;
    public boolean isConsultingGuide = false;

    public ConsultGuide(ValueManager valManager) {
        vm = valManager;
    }

    public String checkObjAnswer(String myObjX, Activity activity) {
        TextView secondText = new TextView(activity.getBaseContext());
        secondText.setTypeface(vm.typeface);

        if (myObjX.contains("consult") && myObjX.contains("guide")) {
            secondText.setText("You can't see any Guide here!");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("consult")) {
            isConsultingGuide = true;
            secondText.setText("Consult what?");
            vm.linearLayout.addView(secondText);
        } else if (isConsultingGuide && myObjX.contains("guide")) {
            isConsultingGuide = false;
            secondText.setText("You can't see any Guide here!");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("consult")) {
            secondText.setText("There's nothing you can consult.");
            vm.linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }
}
