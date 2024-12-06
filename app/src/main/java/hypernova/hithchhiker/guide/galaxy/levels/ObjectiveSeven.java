package hypernova.hithchhiker.guide.galaxy.levels;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.R;
import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveSeven extends AppCompatActivity {
    ValueManager vm;

    public ObjectiveSeven(ValueManager valManager) {
        vm = valManager;
    }

    public String checkObjAnswer(String myObjSeven, Activity activity) {
        TextView myMoves = (TextView) findViewById(R.id.moves);
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(activity.getBaseContext(), R.font.lucida_console);
        TextView secondText = new TextView(activity.getBaseContext());
        secondText.setTypeface(typeface);

        // DIRECTIONS GO VERB
        if (myObjSeven.matches("up") || myObjSeven.matches("u") || myObjSeven.matches("go up") || myObjSeven.matches("upstairs") || myObjSeven.matches("go upstairs") || myObjSeven.matches("go u") || myObjSeven.matches("down") || myObjSeven.matches("d") || myObjSeven.matches("go down") || myObjSeven.matches("downstairs") || myObjSeven.matches("go downstairs") || myObjSeven.matches("go d")) {
            secondText.setText("You can't go that way.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.matches("north") || myObjSeven.matches("n") || myObjSeven.matches("go north") || myObjSeven.matches("go n") || myObjSeven.matches("go straight on")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 12:
                    if (closeddoor==0) {
                        valueManager.score++;
                        myMoves.setText("Moves: " + valueManager.score);
                        myLocation.setText("Desert House");
                        housedesert=12;
                        secondText.setText("Congratulations human.\n\nAt least, you finished the demo in " + valueManager.score + " moves.\nNot bad for a species that has pizza as a religion."); // sponge bob
                        linearLayout.addView(secondText);
                        obj=11;
                        //obj11();
                    } else {
                        s = "The door is closed.";
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 21:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, W");
                    housedesert=11;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northeast you still see the house.";
                    if (!(dropHouseDesert11.isEmpty())) {
                        int n = dropHouseDesert11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert11.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    housedesert=12;
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, E");
                    housedesert=13;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northwest you still see the house.";
                    if (!(dropHouseDesert13.isEmpty())) {
                        int n = dropHouseDesert13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert13.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 32:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("south") || myObjSeven.matches("s") || myObjSeven.matches("go south") || myObjSeven.matches("go s") || myObjSeven.matches("go backwards")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SW");
                    housedesert=21;
                    if (havefoundeggin==00 || (havefoundeggin==21 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=21;
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.";
                    }
                    if (!(dropHouseDesert21.isEmpty())) {
                        int n = dropHouseDesert21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert21.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 12:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SE");
                    housedesert=23;
                    if (havefoundeggin==00 || (havefoundeggin==23 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=23;
                        s = "Nowhere\nNow the house is northwest.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northwest.";
                    }
                    if (!(dropHouseDesert23.isEmpty())) {
                        int n = dropHouseDesert23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert23.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 21:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SS");
                    housedesert=32;
                    if (havefoundeggin==00 || (havefoundeggin==32 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=32;
                        s = "Nowhere\nNow the house is north.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is north.";
                    }
                    if (!(dropHouseDesert32.isEmpty())) {
                        int n = dropHouseDesert32.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert32.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 32:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("west") || myObjSeven.matches("w") || myObjSeven.matches("go west") || myObjSeven.matches("go w") || myObjSeven.matches("go left")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 12:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, W");
                    housedesert=11;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northeast you still see the house.";
                    if (!(dropHouseDesert11.isEmpty())) {
                        int n = dropHouseDesert11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert11.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    housedesert=12;
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 21:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SW");
                    housedesert=21;
                    if (havefoundeggin==00 || (havefoundeggin==21 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=21;
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.";
                    }
                    if (!(dropHouseDesert21.isEmpty())) {
                        int n = dropHouseDesert21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert21.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 32:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("east") || myObjSeven.matches("e") || myObjSeven.matches("go east") || myObjSeven.matches("go e") || myObjSeven.matches("go right")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    housedesert=12;
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 12:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, E");
                    housedesert=13;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northwest you still see the house.";
                    if (!(dropHouseDesert13.isEmpty())) {
                        int n = dropHouseDesert13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert13.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 21:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SE");
                    housedesert=23;
                    if (havefoundeggin==00 || (havefoundeggin==23 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=23;
                        s = "Nowhere\nNow the house is northwest.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northwest.";
                    }
                    if (!(dropHouseDesert23.isEmpty())) {
                        int n = dropHouseDesert23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert23.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 32:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("northeast") || myObjSeven.matches("ne") || myObjSeven.matches("go northeast") || myObjSeven.matches("go ne")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    if (closeddoor==0) {
                        valueManager.score++;
                        myMoves.setText("Moves: " + valueManager.score);
                        myLocation.setText("Desert House");
                        housedesert=12;
                        secondText.setText("Congratulations human.\n\nAt least, you finished the demo in " + valueManager.score + " moves.\nNot bad for a species that has pizza as a religion."); // sponge bob
                        linearLayout.addView(secondText);
                        obj=11;
                        //obj11();
                    } else {
                        s = "The door is closed.";
                        secondText.setText(s);
                    }
                    break;
                case 12:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 13:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 21:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    housedesert=12;
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, E");
                    housedesert=13;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northwest you still see the house.";
                    if (!(dropHouseDesert13.isEmpty())) {
                        int n = dropHouseDesert13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert13.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 32:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SE");
                    housedesert=23;
                    if (havefoundeggin==00 || (havefoundeggin==23 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=23;
                        s = "Nowhere\nNow the house is northwest.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northwest.";
                    }
                    if (!(dropHouseDesert23.isEmpty())) {
                        int n = dropHouseDesert23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert23.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("northwest") || myObjSeven.matches("nw") || myObjSeven.matches("go northwest") || myObjSeven.matches("go nw")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 12:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 13:
                    if (closeddoor==0) {
                        valueManager.score++;
                        myMoves.setText("Moves: " + valueManager.score);
                        myLocation.setText("Desert House");
                        housedesert=12;
                        secondText.setText("Congratulations human.\n\nAt least, you finished the demo in " + valueManager.score + " moves.\nNot bad for a species that has pizza as a religion."); // sponge bob
                        linearLayout.addView(secondText);
                        obj=11;
                        //obj11();
                    } else {
                        s = "The door is closed.";
                        secondText.setText(s);
                    }
                    break;
                case 21:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, W");
                    housedesert=11;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northeast you still see the house.";
                    if (!(dropHouseDesert11.isEmpty())) {
                        int n = dropHouseDesert11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert11.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    housedesert=12;
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 32:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SW");
                    housedesert=21;
                    if (havefoundeggin==00 || (havefoundeggin==21 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=21;
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.";
                    }
                    if (!(dropHouseDesert21.isEmpty())) {
                        int n = dropHouseDesert21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert21.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("southeast") || myObjSeven.matches("se") || myObjSeven.matches("go southeast") || myObjSeven.matches("go se")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 12:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SE");
                    housedesert=23;
                    if (havefoundeggin==00 || (havefoundeggin==23 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=23;
                        s = "Nowhere\nNow the house is northwest.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northwest.";
                    }
                    if (!(dropHouseDesert23.isEmpty())) {
                        int n = dropHouseDesert23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert23.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 21:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SS");
                    housedesert=32;
                    if (havefoundeggin==00 || (havefoundeggin==32 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=32;
                        s = "Nowhere\nNow the house is north.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is north.";
                    }
                    if (!(dropHouseDesert32.isEmpty())) {
                        int n = dropHouseDesert32.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert32.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 22:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 23:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 32:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("southwest") || myObjSeven.matches("sw") || myObjSeven.matches("go southwest") || myObjSeven.matches("go sw")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 12:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SW");
                    housedesert=21;
                    if (havefoundeggin==00 || (havefoundeggin==21 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=21;
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.";
                    }
                    if (!(dropHouseDesert21.isEmpty())) {
                        int n = dropHouseDesert21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert21.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 21:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 22:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 23:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SS");
                    housedesert=32;
                    if (havefoundeggin==00 || (havefoundeggin==32 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=32;
                        s = "Nowhere\nNow the house is north.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is north.";
                    }
                    if (!(dropHouseDesert32.isEmpty())) {
                        int n = dropHouseDesert32.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert32.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 32:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("exit") || myObjSeven.matches("leave") || ((myObjSeven.contains("go") || myObjSeven.contains("get")) && myObjSeven.contains("out")) || (myObjSeven.contains("exit") && myObjSeven.contains("room"))) {
            secondText.setText("You are already on the outside.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("speak") || myObjSeven.contains("talk") || myObjSeven.contains("ask")) {  // SPEAK VERB 10
            secondText.setText("There is no one here you can speak with.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("stop")) { // STOP VERB 10
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjSeven.contains("voices")) {
                secondText.setText("You are not hearing voices... Not yet.");
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("time")) {
                secondText.setText("You wish you could.");
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("breath")) {
                secondText.setText("If you want to die, just say DIE.");
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("sleep") || myObjSeven.contains("dream")) {
                secondText.setText("You are pretty much awake.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("There's nothing you can stop here.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("close")) {  // CLOSE VERB 10
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("close")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjSeven.contains("door")) {
                if (housedesert==12) {
                    if (closeddoor==0) {
                        closeddoor=1;
                        secondText.setText("You closed it.");
                    } else {
                        secondText.setText("It is already closed.");
                    }
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("eye")) {
                secondText.setText("This is not really useful right now.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("open")) {  // OPEN VERB 10
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("open")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjSeven.contains("door")) {
                if (housedesert==12) {
                    if (closeddoor==1) {
                        closeddoor=0;
                        secondText.setText("You opened it.");
                    } else {
                        secondText.setText("It is already open.");
                    }
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("eye")) {
                secondText.setText("Your eyes are wide open.");
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("curtain")) {
                if (housedesert==12) {
                    secondText.setText("You can hardly see anything. It is really dark inside.");
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("window")) {
                if (housedesert==12) {
                    secondText.setText("The crystal is broken. It is too sharp to touch it.");
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("egg") && (myInventoryPast.contains("egg") || (housedesert==11 && dropHouseDesert11.contains("egg")) || (housedesert==12 && dropHouseDesert12.contains("egg")) || (housedesert==13 && dropHouseDesert13.contains("egg")) || (housedesert==21 && dropHouseDesert21.contains("egg")) || (housedesert==22 && dropHouseDesert22.contains("egg")) || (housedesert==23 && dropHouseDesert23.contains("egg")) || (housedesert==32 && dropHouseDesert32.contains("egg")))) {
                brokenegg=1;
                if (myInventoryPast.contains("egg")) {myInventoryPast.remove("egg");}
                else if (dropHouseDesert11.contains("egg")) {dropHouseDesert11.remove("egg");}
                else if (dropHouseDesert12.contains("egg")) {dropHouseDesert12.remove("egg");}
                else if (dropHouseDesert13.contains("egg")) {dropHouseDesert13.remove("egg");}
                else if (dropHouseDesert21.contains("egg")) {dropHouseDesert21.remove("egg");}
                else if (dropHouseDesert22.contains("egg")) {dropHouseDesert22.remove("egg");}
                else if (dropHouseDesert23.contains("egg")) {dropHouseDesert23.remove("egg");}
                else if (dropHouseDesert32.contains("egg")) {dropHouseDesert32.remove("egg");}
                secondText.setText("When it crashes on the ground... nothing happens.\nIf there was hope to see another species in B903, now there's none.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("enter")) {  // ENTER VERB 10
            secondText.setText("This verb needs to be used with a place to get in.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("enter")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjSeven.contains("house")) {
                if (housedesert==12) {
                    if (closeddoor==0) {
                        myLocation.setText("Desert House");
                        housedesert=12;
                        secondText.setText("Congratulations human.\n\nAt least, you finished the demo in " + valueManager.score + " moves.\nNot bad for a species that has pizza as a religion."); // sponge bob
                        linearLayout.addView(secondText);
                        obj=11;
                        //obj11();
                    } else {
                        secondText.setText("The door is closed.");
                        linearLayout.addView(secondText);
                        obj10();
                    }
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                    linearLayout.addView(secondText);
                    obj10();
                }
            } else if (myObjSeven.contains("matrix")) {
                secondText.setText("Morpheus should be proud.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("You cannot get in here or the ubication's name is incorrect.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if ((myObjSeven.contains("turn") && myObjSeven.contains("off")) || (myObjSeven.contains("shut") && myObjSeven.contains("down"))) {  // TURN OFF VERB 10
            secondText.setText("There's nothing you can turn off here.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("turn on") || myObjSeven.contains("turn it on")) {  // TURN ON VERB 10
            secondText.setText("There's nothing you can turn on here.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("stand") || myObjSeven.contains("get up")) { // STAND VERB 10
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You are already standing.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("sit down") || myObjSeven.matches("sit") || myObjSeven.contains("lie")) { // LIE VERB 10
            secondText.setText("It doesn't seem that comfortable.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.matches("examine")) { // EXAMINE VERB 10
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("examine")) {
            if (housedesert==11 || housedesert==12 || housedesert==13 || housedesert==22) {
                if (myObjSeven.contains("house")) {
                    secondText.setText("The house gives off a feeling of loneliness.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myObjSeven.contains("sky")) {
                    secondText.setText("At this time of year B903's sky is always orange. It seems like it's almost night the whole time, everyday.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myObjSeven.contains("door")) {
                    if (closeddoor==0) {
                        secondText.setText("It is open. It looks older than the house.");
                    } else {
                        secondText.setText("It is closed. It looks older than the house.");
                    }
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myObjSeven.contains("window")) {
                    secondText.setText("You can hardly see anything. It is really dark inside.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myObjSeven.contains("curtain")) {
                    secondText.setText("The curtains are dark and full of loose seams.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myObjSeven.contains("stone")) {
                    secondText.setText("The black stones are cold.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myObjSeven.contains("egg")) {
                    if (myInventoryPast.contains("egg") || (housedesert==11 && dropHouseDesert11.contains("egg")) || (housedesert==12 && dropHouseDesert12.contains("egg")) || (housedesert==13 && dropHouseDesert13.contains("egg")) || (housedesert==22 && dropHouseDesert22.contains("egg"))) {
                        secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                    } else {
                        secondText.setText("This thing cannot be examined or it is not in the place.");
                    }
                    linearLayout.addView(secondText);
                    obj10();
                } else {
                    secondText.setText("This thing cannot be examined or it is not in the place.");
                    linearLayout.addView(secondText);
                    obj10();
                }
            } else {
                if (myObjSeven.contains("house")) {
                    secondText.setText("You cannot examine that from this position.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myObjSeven.contains("sky")) {
                    secondText.setText("At this time of year B903's sky is always orange. It seems like it's almost night the whole time, everyday.");
                    linearLayout.addView(secondText);
                    obj10();
                } if (housedesert==21) {
                    if (havefoundeggin==21) {
                        if (myObjSeven.contains("black") && (myObjSeven.contains("stone") || myObjSeven.contains("rock"))) {
                            secondText.setText("The black stones are cold.");
                        } else if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                secondText.setText("The black stones are cold.");
                            } else {
                                secondText.setText("It looks like the only white stone in the whole desert.");
                            }
                        } else if (myObjSeven.contains("egg") && (dropHouseDesert21.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    } else {
                        if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            secondText.setText("The black stones are cold.");
                        } else if (myObjSeven.contains("egg") && (dropHouseDesert21.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    }
                    linearLayout.addView(secondText);
                    obj10();
                } else if (housedesert==23) {
                    if (havefoundeggin==23) {
                        if (myObjSeven.contains("black") && (myObjSeven.contains("stone") || myObjSeven.contains("rock"))) {
                            secondText.setText("The black stones are cold.");
                        } else if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                secondText.setText("The black stones are cold.");
                            } else {
                                secondText.setText("It looks like the only white stone in the whole desert.");
                            }
                        } else if (myObjSeven.contains("egg") && (dropHouseDesert23.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    } else {
                        if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            secondText.setText("The black stones are cold.");
                        } else if (myObjSeven.contains("egg") && (dropHouseDesert23.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    }
                    linearLayout.addView(secondText);
                    obj10();
                } else if (housedesert==32) {
                    if (havefoundeggin==32) {
                        if (myObjSeven.contains("black") && (myObjSeven.contains("stone") || myObjSeven.contains("rock"))) {
                            secondText.setText("The black stones are cold.");
                        } else if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                secondText.setText("The black stones are cold.");
                            } else {
                                secondText.setText("It looks like the only white stone in the whole desert.");
                            }
                        } else if (myObjSeven.contains("egg") && (dropHouseDesert32.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    } else {
                        if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            secondText.setText("The black stones are cold.");
                        } else if (myObjSeven.contains("egg") && (dropHouseDesert32.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    }
                    linearLayout.addView(secondText);
                    obj10();
                }
            }
        }
        // TAKE GET VERB 10
        else if (myObjSeven.matches("take") || myObjSeven.matches("get") || myObjSeven.matches("pick") || myObjSeven.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("take") || myObjSeven.contains("get") || myObjSeven.contains("pick")|| myObjSeven.contains("grab")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (housedesert==11 || housedesert==12 || housedesert==13 || housedesert==22) {
                if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                    if (myInventoryPast.contains("stone")) {
                        secondText.setText("You already took one.");
                    } else {
                        myInventoryPast.add("stone");
                        secondText.setText("Taken.");
                    }
                } else if (myObjSeven.contains("egg")) {
                    if (housedesert==11 && dropHouseDesert11.contains("egg")) {
                        dropHouseDesert11.remove("egg");
                        myInventoryPast.add("egg");
                        secondText.setText("Taken.");
                    } else if (housedesert==12 && dropHouseDesert12.contains("egg")) {
                        dropHouseDesert12.remove("egg");
                        myInventoryPast.add("egg");
                        secondText.setText("Taken.");
                    } else if (housedesert==13 && dropHouseDesert13.contains("egg")) {
                        dropHouseDesert13.remove("egg");
                        myInventoryPast.add("egg");
                        secondText.setText("Taken.");
                    } else if (housedesert==22 && dropHouseDesert22.contains("egg")) {
                        dropHouseDesert22.remove("egg");
                        myInventoryPast.add("egg");
                        secondText.setText("Taken.");
                    } else {
                        secondText.setText("You can't see any egg here!");
                    }
                } else {
                    secondText.setText("This thing cannot be taken or it is not in the place.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else {
                if (housedesert==21) {
                    if (havefoundeggin==21) {
                        if (myObjSeven.contains("black") && (myObjSeven.contains("stone") || myObjSeven.contains("rock"))) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                if (myInventoryPast.contains("stone")) {
                                    secondText.setText("You already took one.");
                                } else {
                                    myInventoryPast.add("stone");
                                    secondText.setText("Taken.");
                                }
                            } else {
                                myInventoryPast.add("egg");
                                secondText.setText("As you hold it in your hand, you realize it is an egg. A pure white egg.\nProbably the last spark of newborn life anybody is going to see in this hell of a planet.");
                            }
                        } else if (myObjSeven.contains("egg") && dropHouseDesert21.contains("egg")) {
                            dropHouseDesert21.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    } else {
                        if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myObjSeven.contains("egg") && dropHouseDesert21.contains("egg")) {
                            dropHouseDesert21.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    }
                } else if (housedesert==23) {
                    if (havefoundeggin==23) {
                        if (myObjSeven.contains("black") && (myObjSeven.contains("stone") || myObjSeven.contains("rock"))) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                if (myInventoryPast.contains("stone")) {
                                    secondText.setText("You already took one.");
                                } else {
                                    myInventoryPast.add("stone");
                                    secondText.setText("Taken.");
                                }
                            } else {
                                myInventoryPast.add("egg");
                                secondText.setText("As you hold it in your hand, you realize it is an egg. A pure white egg.\nProbably the last spark of newborn life anybody is going to see in this hell of a planet.");
                            }
                        } else if (myObjSeven.contains("egg") && dropHouseDesert23.contains("egg")) {
                            dropHouseDesert23.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    } else {
                        if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myObjSeven.contains("egg") && dropHouseDesert23.contains("egg")) {
                            dropHouseDesert23.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    }
                } else if (housedesert==32) {
                    if (havefoundeggin==32) {
                        if (myObjSeven.contains("black") && (myObjSeven.contains("stone") || myObjSeven.contains("rock"))) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                if (myInventoryPast.contains("stone")) {
                                    secondText.setText("You already took one.");
                                } else {
                                    myInventoryPast.add("stone");
                                    secondText.setText("Taken.");
                                }
                            } else {
                                myInventoryPast.add("egg");
                                secondText.setText("As you hold it in your hand, you realize it is an egg. A pure white egg.\nProbably the last spark of newborn life anybody is going to see in this hell of a planet.");
                            }
                        } else if (myObjSeven.contains("egg") && dropHouseDesert32.contains("egg")) {
                            dropHouseDesert32.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    } else {
                        if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myObjSeven.contains("egg") && dropHouseDesert32.contains("egg")) {
                            dropHouseDesert32.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    }
                }
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.matches("drop") || myObjSeven.contains("get rid of")) { // DROP VERB 10
            secondText.setText("Just say: Drop (and the object you want to drop).");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("drop")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            int n = 4;
            String s = myObjSeven.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (myInventoryPast.contains(s)) {
                myInventoryPast.remove(s);
                switch (housedesert) {
                    case 11:
                        dropHouseDesert11.add(s);
                        break;
                    case 12:
                        dropHouseDesert12.add(s);
                        break;
                    case 13:
                        dropHouseDesert13.add(s);
                        break;
                    case 21:
                        dropHouseDesert21.add(s);
                        break;
                    case 22:
                        dropHouseDesert22.add(s);
                        break;
                    case 23:
                        dropHouseDesert23.add(s);
                        break;
                    case 32:
                        dropHouseDesert32.add(s);
                        break;
                }
                secondText.setText("Dropped.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("You're not holding the " + s + ".");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.contains("put") || myObjSeven.contains("place")) { // PUT VERB 10
            secondText.setText("There is no container here where you can put an object in.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.matches("help")) { // HELP VERB 10
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You don't need help.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("help")) {
            secondText.setText("Just say HELP.");
            linearLayout.addView(secondText);
            obj10();
        }
        // FOLLOW VERB 10
        else if (myObjSeven.contains("follow") || myObjSeven.contains("chase") || myObjSeven.contains("find") || myObjSeven.contains("search")) {
            secondText.setText("There is no one here you can follow.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("drink")) { // DRINK VERB 10
            secondText.setText("You wish you had water.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.matches("eat")) { // EAT VERB 10
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("eat")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjSeven.contains("egg") && (myInventoryPast.contains("egg") || (housedesert==11 && dropHouseDesert11.contains("egg")) || (housedesert==12 && dropHouseDesert12.contains("egg")) || (housedesert==13 && dropHouseDesert13.contains("egg")) || (housedesert==21 && dropHouseDesert21.contains("egg")) || (housedesert==22 && dropHouseDesert22.contains("egg")) || (housedesert==23 && dropHouseDesert23.contains("egg")) || (housedesert==32 && dropHouseDesert32.contains("egg")))) {
                brokenegg=1;
                if (myInventoryPast.contains("egg")) {myInventoryPast.remove("egg");}
                else if (dropHouseDesert11.contains("egg")) {dropHouseDesert11.remove("egg");}
                else if (dropHouseDesert12.contains("egg")) {dropHouseDesert12.remove("egg");}
                else if (dropHouseDesert13.contains("egg")) {dropHouseDesert13.remove("egg");}
                else if (dropHouseDesert21.contains("egg")) {dropHouseDesert21.remove("egg");}
                else if (dropHouseDesert22.contains("egg")) {dropHouseDesert22.remove("egg");}
                else if (dropHouseDesert23.contains("egg")) {dropHouseDesert23.remove("egg");}
                else if (dropHouseDesert32.contains("egg")) {dropHouseDesert32.remove("egg");}
                secondText.setText("When it crashes on the ground... nothing happens.\nIf there was hope to see another species in B903, now there's none.");
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("arm") || myObjSeven.contains("hand")) {
                secondText.setText("What would you do with just one hand?");
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("house")) {
                secondText.setText("Stop. What if there's someone inside?");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("This thing cannot be eaten or it is not in the place.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.contains("write")) {  // WRITE VERB 10
            secondText.setText("You cannot write new notes.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("read")) {  // READ VERB 10
            secondText.setText("You can't see any note here!");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.matches("break") || myObjSeven.matches("hit") || myObjSeven.matches("attack") || myObjSeven.matches("punch") || myObjSeven.matches("fight") || myObjSeven.matches("kick")) {  // BREAK VERB 10
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("break") || myObjSeven.contains("hit") || myObjSeven.contains("attack") || myObjSeven.contains("punch") || myObjSeven.contains("fight") || myObjSeven.contains("kick")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjSeven.contains("house")) {
                if (housedesert==12) {
                    secondText.setText("As you punch the house, you hear the wood cracking a little.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("door")) {
                if (housedesert==12) {
                    secondText.setText("You see the dust of the door falling off as you hit the door.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("curtain")) {
                if (housedesert==12) {
                    secondText.setText("The cloth is so fine that it breaks apart when you touch it.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("window")) {
                if (housedesert==12) {
                    secondText.setText("The windows are already half-broken.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("stone")) {
                secondText.setText("It is just a stone. Why would you hit a stone?");
                linearLayout.addView(secondText);
                obj10();
            } else if (myObjSeven.contains("egg") && (myInventoryPast.contains("egg") || (housedesert==11 && dropHouseDesert11.contains("egg")) || (housedesert==12 && dropHouseDesert12.contains("egg")) || (housedesert==13 && dropHouseDesert13.contains("egg")) || (housedesert==21 && dropHouseDesert21.contains("egg")) || (housedesert==22 && dropHouseDesert22.contains("egg")) || (housedesert==23 && dropHouseDesert23.contains("egg")) || (housedesert==32 && dropHouseDesert32.contains("egg")))) {
                brokenegg=1;
                if (myInventoryPast.contains("egg")) {myInventoryPast.remove("egg");}
                else if (dropHouseDesert11.contains("egg")) {dropHouseDesert11.remove("egg");}
                else if (dropHouseDesert12.contains("egg")) {dropHouseDesert12.remove("egg");}
                else if (dropHouseDesert13.contains("egg")) {dropHouseDesert13.remove("egg");}
                else if (dropHouseDesert21.contains("egg")) {dropHouseDesert21.remove("egg");}
                else if (dropHouseDesert22.contains("egg")) {dropHouseDesert22.remove("egg");}
                else if (dropHouseDesert23.contains("egg")) {dropHouseDesert23.remove("egg");}
                else if (dropHouseDesert32.contains("egg")) {dropHouseDesert32.remove("egg");}
                secondText.setText("When it crashes on the ground... nothing happens.\nIf there was hope to see another species in B903, now there's none.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("This thing cannot be hit or it is not in the place.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if ((((myObjSeven.contains("draw") || myObjSeven.contains("pull")) && (myObjSeven.contains("back") || myObjSeven.contains("aside"))) || myObjSeven.contains("move")) && myObjSeven.contains("curtain")) { // CURTAIN ACTIONS 10
            if (housedesert==12) {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                secondText.setText("You can hardly see anything. It is really dark inside.");
            } else {
                secondText.setText("You have wandered off the house. You better get closer to do that.");
            }
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("curtain")) {
            if (housedesert==12) {
                secondText.setText("They are just some dark curtains.");
            } else {
                secondText.setText("You are kind of far from the house.");
            }
            linearLayout.addView(secondText);
            obj10();
        } else if ((myObjSeven.contains("look") && myObjSeven.contains("around")) || myObjSeven.matches("l") || myObjSeven.matches("look")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    s = "Nowhere\nHeading northeast you still see the house.";
                    if (!(dropHouseDesert11.isEmpty())) {
                        int n = dropHouseDesert11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert11.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 12:
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nThe ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nThe ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    s = "Nowhere\nHeading northwest you still see the house.";
                    if (!(dropHouseDesert13.isEmpty())) {
                        int n = dropHouseDesert13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert13.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 21:
                    if (havefoundeggin==21 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1)) {
                        s = "Nowhere\nThe house is northeast.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nThe house is northeast.";
                    }
                    if (!(dropHouseDesert21.isEmpty())) {
                        int n = dropHouseDesert21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert21.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 22:
                    s = "Nowhere\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    if (havefoundeggin==23 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1)) {
                        s = "Nowhere\nThe house is northwest.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nThe house is northwest.";
                    }
                    if (!(dropHouseDesert23.isEmpty())) {
                        int n = dropHouseDesert23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert23.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 32:
                    if (havefoundeggin==32 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1)) {
                        s = "Nowhere\nThe house is north.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nThe house is north.";
                    }
                    if (!(dropHouseDesert32.isEmpty())) {
                        int n = dropHouseDesert32.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert32.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
            }
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.matches("i") || myObjSeven.matches("inventory")) {
            String inventory = "You have:";
            if (!(myInventoryPast.isEmpty())) {
                int n = myInventoryPast.size();
                for (int i=0; i<n; i++) {
                    inventory = inventory + "\n    " + myInventoryPast.get(i) + ".";
                }
                secondText.setText(inventory);
                linearLayout.addView(secondText);
                obj10();
            } else {
                inventory = "You have nothing.";
                secondText.setText(inventory);
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myObjSeven.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.matches("sleep")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Even the sky is still orange.\nYou just feel thirstier.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("check") && myObjSeven.contains("out")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("check") || myObjSeven.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("jump") || myObjSeven.contains("climb") || myObjSeven.contains("turn") || myObjSeven.contains("shut") || myObjSeven.contains("look") || myObjSeven.contains("see") || myObjSeven.contains("watch") || myObjSeven.contains("play") || myObjSeven.contains("run") || myObjSeven.contains("walk") || myObjSeven.contains("move") || myObjSeven.contains("give") || myObjSeven.contains("offer")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Look around you.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("smell")) {
            secondText.setText("It does not smell as bad as the part of the planet where your house is.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("listen")) {
            secondText.setText("You can barely hear someone crying inside the house.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("wait")) {
            secondText.setText("Time passes...");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.contains("what") && myObjSeven.contains("time") && myObjSeven.contains("is") || myObjSeven.matches("time") || myObjSeven.matches("the time")) {
            secondText.setText("It is sunset.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myObjSeven.matches("diagnostic") || myObjSeven.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            linearLayout.addView(secondText);
            obj10();
        }

        return (String) secondText.getText();
    }

}
