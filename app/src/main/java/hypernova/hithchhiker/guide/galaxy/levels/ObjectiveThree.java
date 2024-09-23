package hypernova.hithchhiker.guide.galaxy.levels;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.R;
import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveThree extends AppCompatActivity {
    ValueManager valueManager;

    public ObjectiveThree(ValueManager valManager) {
        valueManager = valManager;
    }

    public String checkObjAnswer(String myObjThree, Activity activity) {
        TextView myMoves = (TextView) findViewById(R.id.moves);
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(activity.getBaseContext(), R.font.lucida_console);
        TextView secondText = new TextView(activity.getBaseContext());
        secondText.setTypeface(typeface);

        if (valueManager.fred.conversationStatus == 31) {  // CONVERSATION FRED
            switch (myObjThree) {
                case "1":
                    valueManager.fred.conversationStatus = 32;
                    secondText.setText("I'm F.R.E.D.\nFormed Rico Emotionally Disturbed, but you always call me Fred.\n\n\nDialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    linearLayout.addView(secondText);
                    break;
                case "2":
                    valueManager.fred.conversationStatus = 32;
                    secondText.setText("You bet I know, " + valueManager.myself.name + ".\nHey it's me, F.R.E.D.\nFormed Rico Emotionally Disturbed, but you always call me Fred.\n\n\nDialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    linearLayout.addView(secondText);
                    break;
                case "3":
                    valueManager.fred.conversationStatus = 32;
                    secondText.setText("Of course you know me. I'm F.R.E.D.\nFormed Rico Emotionally Disturbed, but you always call me Fred.\n\n\nDialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("You just said: oasidjfdd.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'Who are you?'\n2. 'You know who are you talking with!?'\n3. 'I'm " + valueManager.myself.name + ". Do I know you?'");
                    linearLayout.addView(secondText);
                    break;
            }
        } else if (valueManager.fred.conversationStatus == 32) {
            switch (myObjThree) {
                case "1":
                    valueManager.fred.conversationStatus = 33;
                    secondText.setText("I'll get to that in a minute. First of all, the weekly report:\nNow, 89% of the Bubbles have electricity. The 300 years universal peace anniversary is closer. And our brothers from B905 have made it into the Space Federation.\n\n\nDialogue Fred.\n\n1. 'Pleased to hear that.'\n2. 'I hate these B905 guys.'\n3. 'Anything else?'");
                    linearLayout.addView(secondText);
                    break;
                case "2":
                    valueManager.fred.conversationStatus = 33;
                    secondText.setText("Don't make me talk about those bastards.\nAnyways, let's take a look at the weekly report:\nNow, 89% of the Bubbles have electricity. The 300 years universal peace anniversary is closer. And our brothers from B905 have made it into the Space Federation.\n\n\nDialogue Fred.\n\n1. 'Pleased to hear that.'\n2. 'I hate these B905 guys.'\n3. 'Anything else?'");
                    linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("You just said: nyhfwaa.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    linearLayout.addView(secondText);
                    break;
            }
        } else if (valueManager.fred.conversationStatus == 33) {
            switch (myObjThree) {
                case "1":
                    valueManager.fred.conversationStatus = 34;
                    secondText.setText("You always so nice.\n\nOne more thing.\nI've been doing some research about the Space Travel Ban.\nI've checked every route made by the Space Federation and the routes allowed according to the law.\nThe numbers don't fit... There might be one Traveller alive.\n\n\nDialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    linearLayout.addView(secondText);
                    break;
                case "2":
                    valueManager.fred.conversationStatus = 34;
                    secondText.setText("Although they kill their parents when they're twelve, we don't have to treat them differently.\n\nOne more thing.\nI've been doing some research about the Space Travel Ban.\nI've checked every route made by the Space Federation and the routes allowed according to the law.\nThe numbers don't fit... There might be one Traveller alive.\n\n\nDialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    linearLayout.addView(secondText);
                    break;
                case "3":
                    valueManager.fred.conversationStatus = 34;
                    secondText.setText("Yes.\nI've been doing some research about the Space Travel Ban.\nI've checked every route made by the Space Federation and the routes allowed according to the law.\nThe numbers don't fit... There might be one Traveller alive.\n\n\nDialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("You just said: ywegvwn.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'Pleased to hear that.'\n2. 'I hate these B905 guys.'\n3. 'Anything else?'");
                    linearLayout.addView(secondText);
                    break;
            }
        } else if (valueManager.fred.conversationStatus == 34) {
            switch (myObjThree) {
                case "1":
                    valueManager.fred.conversationStatus = 35;
                    secondText.setText("I've said there MIGHT be one alive. Maybe. Just maybe...\nNow there's hope to get out of B903.\n\n\nDialogue Fred.\n\n1. 'I'm happy in the Bubble.'\n2. 'Assuming this Traveller exists, what now?'");
                    linearLayout.addView(secondText);
                    break;
                case "2":
                    valueManager.fred.conversationStatus = 35;
                    secondText.setText("Fred doesn't want that. Nobody in the universe would want that...\nNow there's hope to get out of B903.\n\n\nDialogue Fred.\n\n1. 'I'm happy in the Bubble.'\n2. 'Assuming this Traveller exists, what now?'");
                    linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("You just said: ajdhasj.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    linearLayout.addView(secondText);
                    break;
            }
        } else if (valueManager.fred.conversationStatus == 35) {
            switch (myObjThree) {
                case "1":
                    valueManager.fred.conversationStatus = 0;
                    valueManager.ludlow.scoreWhenFredWaiting = valueManager.score;
                    valueManager.myself.inventory.add("library neck chip");
                    secondText.setText("'You won't be when the Space Federation comes to look for the Traveller.\nNow we can be part of something more important than this Bubble. Won't you ever wanted to see with your own eyes what is out of this place?\n\nMy research will lead us to that. Finding the Traveller will lead us to that.\n\nI just need one more ingredient, a book called The Hitchhiker's Guide to the Galaxy. They have one of those at the Library and we need to get it.'\n\nDialogue Fred finished.\n\nFred gives you a chip similar to the one he has on his neck which says LUDLOW PUBLIC LIBRARY.\nLIBRARY NECK CHIP has been added to your inventory.");
                    linearLayout.addView(secondText);
                    valueManager.currentObjective = 7;
                    break;
                case "2":
                    valueManager.fred.conversationStatus = 0;
                    valueManager.ludlow.scoreWhenFredWaiting = valueManager.score;
                    valueManager.myself.inventory.add("library neck chip");
                    secondText.setText("'Now we can be part of something more important than this Bubble. Won't you ever wanted to see with your own eyes what is out of this place?\n\nMy research will lead us to that. Finding the Traveller will lead us to that.\n\nI just need one more ingredient, a book called The Hitchhiker's Guide to the Galaxy. They have one of those at the Library and we need to get it.'\n\nDialogue Fred finished.\n\nFred gives you a chip similar to the one he has on his neck which says LUDLOW PUBLIC LIBRARY.\nLIBRARY NECK CHIP has been added to your inventory.");
                    linearLayout.addView(secondText);
                    valueManager.currentObjective = 7;
                    break;
                default:
                    secondText.setText("You just said: nuwnuds.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'I'm happy in the Bubble.'\n2. 'Assuming this Traveller exists, what now?'");
                    linearLayout.addView(secondText);
                    break;
            }
        }
        // DIRECTIONS GO VERB
        else if (myObjThree.matches("south") || myObjThree.matches("s") || myObjThree.matches("go south") || myObjThree.matches("go s") || myObjThree.matches("go backwards") || myObjThree.matches("east") || myObjThree.matches("e") || myObjThree.matches("go east") || myObjThree.matches("go e") || myObjThree.matches("go right") || myObjThree.matches("west") || myObjThree.matches("w") || myObjThree.matches("go west") || myObjThree.matches("go w") || myObjThree.matches("go left") || myObjThree.matches("northeast") || myObjThree.matches("ne") || myObjThree.matches("go northeast") || myObjThree.matches("go ne") || myObjThree.matches("northwest") || myObjThree.matches("nw") || myObjThree.matches("go northwest") || myObjThree.matches("go nw") || myObjThree.matches("southeast") || myObjThree.matches("se") || myObjThree.matches("go southeast") || myObjThree.matches("go se") || myObjThree.matches("southwest") || myObjThree.matches("sw") || myObjThree.matches("go southwest") || myObjThree.matches("go sw") || myObjThree.matches("down") || myObjThree.matches("d") || myObjThree.matches("go down") || myObjThree.matches("downstairs") || myObjThree.matches("go downstairs") || myObjThree.matches("go d") || myObjThree.matches("up") || myObjThree.matches("u") || myObjThree.matches("go up") || myObjThree.matches("upstairs") || myObjThree.matches("go upstairs") || myObjThree.matches("go u")) {
            secondText.setText("You can't go that way.");
            linearLayout.addView(secondText);
        } else if (myObjThree.matches("exit") || myObjThree.matches("leave") || myObjThree.matches("north") || myObjThree.matches("n") || myObjThree.matches("go north") || myObjThree.matches("go n") || myObjThree.matches("go straight on") || ((myObjThree.contains("go") || myObjThree.contains("get")) && myObjThree.contains("out")) || (myObjThree.contains("exit") && myObjThree.contains("room"))) {
            secondText.setText("You'll have to open the door first.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("speak") || myObjThree.contains("talk") || myObjThree.contains("ask")) {  // SPEAK VERB
            secondText.setText("There is no one here you can speak with.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("stop")) {  // STOP VERB
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjThree.contains("play") || myObjThree.contains("game")) {
                secondText.setText("You already stopped playing.");
                linearLayout.addView(secondText);
            } else if (myObjThree.contains("noise") || myObjThree.contains("sound") || myObjThree.contains("knock")) {
                secondText.setText("Opening the door is the way to stop someone knocking on your house door. Duh.");
                linearLayout.addView(secondText);
            } else {
                secondText.setText("There's nothing you can stop here.");
                linearLayout.addView(secondText);
            }
        } else if (myObjThree.matches("close")) {  // CLOSE VERB
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("close")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjThree.contains("door")) {
                secondText.setText("The door is already closed.");
                linearLayout.addView(secondText);
            } else if (myObjThree.contains("eye")) {
                secondText.setText("This is not really useful right now.\nThe knocks keep going.");
                linearLayout.addView(secondText);
            } else if (myObjThree.contains("window")) {
                secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                linearLayout.addView(secondText);
            }
        } else if (myObjThree.matches("open")) {  // OPEN VERB
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("open")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjThree.contains("eye")) {
                secondText.setText("You already see.");
                linearLayout.addView(secondText);
            } else if (myObjThree.contains("basement door")) {
                secondText.setText("The basement door is locked.");
                linearLayout.addView(secondText);
            } else if (myObjThree.contains("door")) {
                valueManager.ludlow.isHouseDoorOpen = true;
                valueManager.fred.conversationStatus = 31;
                myLocation.setText("Hall, open door");
                secondText.setText("'10 o'clock at your door. 10 o'clock at your door. Is it that difficult?'\n\n\nDialogue Fred.\n\n1. 'Who are you?'\n2. 'You know who are you talking with!?'\n3. 'I'm " + valueManager.myself.name + ". Do I know you?'");
                linearLayout.addView(secondText);
            } else if (myObjThree.contains("window")) {
                secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                linearLayout.addView(secondText);
            }
        // TURN OFF VERB
        } else if ((myObjThree.contains("turn") && myObjThree.contains("off")) || (myObjThree.contains("shut") && myObjThree.contains("down"))) {
            secondText.setText("There is nothing you can turn off here.\nSomeone is still knocking on your house door.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("turn on") || myObjThree.contains("turn it on")) {  // TURN ON VERB
            secondText.setText("There's nothing you can turn on here.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("stand") || myObjThree.contains("get up")) {  // STAND VERB
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You are already standing.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("sit down") || myObjThree.matches("sit") || myObjThree.contains("lie")) {  // LIE VERB
            secondText.setText("Someone is knocking on your house door. Take care of that.");
            linearLayout.addView(secondText);
        } else if (myObjThree.matches("examine")) {  // EXAMINE VERB
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("examine")) {
            if (myObjThree.contains("glass") || myObjThree.contains("water")) {
                if (valueManager.ludlow.isHouseWaterDrunk) {
                    secondText.setText("You drank that.");
                    linearLayout.addView(secondText);
                } else {
                    secondText.setText("It is just a glass of water.");
                    linearLayout.addView(secondText);
                }
            } else if (myObjThree.contains("window")) {
                secondText.setText("Just look at it.");
                linearLayout.addView(secondText);
            } else if (myObjThree.contains("basement door")) {
                secondText.setText("It is closed.");
                linearLayout.addView(secondText);
            } else if (myObjThree.contains("door")) {
                secondText.setText("Someone is knocking at it.");
                linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be examined or it is not in the place.");
                linearLayout.addView(secondText);
            }
        }
        // TAKE GET VERB
        else if (myObjThree.matches("take") || myObjThree.matches("get") || myObjThree.matches("pick") || myObjThree.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("take") || myObjThree.contains("get") || myObjThree.contains("pick") || myObjThree.contains("grab")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if ((myObjThree.contains("glass") || myObjThree.contains("water")) && valueManager.ludlow.isHouseWaterDrunk) {
                secondText.setText("You already drank that.");
                linearLayout.addView(secondText);
            } else if ((myObjThree.contains("glass") || myObjThree.contains("water")) && !valueManager.ludlow.isHouseWaterDrunk && !valueManager.ludlow.objectsDroppedHouse.contains("water")) {
                if (valueManager.myself.inventory.contains("water")) {
                    secondText.setText("You already took it.");
                } else {
                    valueManager.myself.inventory.add("water");
                    secondText.setText("Taken.\nYou still hear the knocks on the door.");
                }
                linearLayout.addView(secondText);
            } else if (!valueManager.ludlow.objectsDroppedHouse.isEmpty()) {
                int n;
                if (myObjThree.contains("take") || myObjThree.contains("pick") || myObjThree.contains("grab")) {
                    n = 4;
                } else if (myObjThree.contains("get")) {
                    n = 3;
                } else {
                    n = 0;
                }
                String s = myObjThree.substring(n);
                s = s.replace(" the ","");
                s = s.replace(" a ","");
                s = s.replace(" an ","");
                s = s.trim();
                if (valueManager.ludlow.objectsDroppedHouse.contains(s)) {
                    valueManager.ludlow.objectsDroppedHouse.remove(s);
                    valueManager.myself.inventory.add(s);
                    secondText.setText("Taken.");
                    linearLayout.addView(secondText);
                } else {
                    secondText.setText("You can't see any " + s + " here!");
                    linearLayout.addView(secondText);
                }
            } else {
                secondText.setText("This thing cannot be taken or it is not in the place.");
                linearLayout.addView(secondText);
            }
        } else if (myObjThree.matches("drop") || myObjThree.contains("get rid of")) {  // DROP VERB
            secondText.setText("Just say: Drop (and the object you want to drop).");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("drop")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            int n = 4;
            String s = myObjThree.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (valueManager.myself.inventory.contains(s)) {
                valueManager.myself.inventory.remove(s);
                valueManager.ludlow.objectsDroppedHouse.add(s);
                secondText.setText("Dropped.");
                linearLayout.addView(secondText);
            } else {
                secondText.setText("You're not holding the " + s + ".");
                linearLayout.addView(secondText);
            }
        } else if (myObjThree.matches("help")) {  // HELP VERB
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("I'm sure you're smart enough to know the procedure when someone is knocking at your door.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("help")) {
            secondText.setText("Just say HELP.");
            linearLayout.addView(secondText);
        } else if (myObjThree.matches("drink")) {  // DRINK VERB
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("drink")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjThree.contains("glass") || myObjThree.contains("water")) {
                if (valueManager.ludlow.isHouseWaterDrunk) {
                    secondText.setText("You already drank that.");
                    linearLayout.addView(secondText);
                } else if (valueManager.myself.inventory.contains("water")) {
                    valueManager.ludlow.isHouseWaterDrunk = true;
                    secondText.setText("It feels refreshing.");
                    linearLayout.addView(secondText);
                } else {
                    secondText.setText("You will have to take it first.");
                    linearLayout.addView(secondText);
                }
            } else {
                secondText.setText("This thing cannot be drunk or it is not in the place.");
                linearLayout.addView(secondText);
            }
        } else if (myObjThree.contains("window")) {  // WINDOW ACTIONS, added just in case
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
            linearLayout.addView(secondText);
        } else if ((myObjThree.contains("look") && myObjThree.contains("around")) || myObjThree.matches("l") || myObjThree.matches("look")) {
            String intro = "";
            if (valueManager.ludlow.isHouseWaterDrunk || valueManager.myself.inventory.contains("water") || valueManager.ludlow.objectsDroppedHouse.contains("water")) {
                intro = "Your house hall\nHere you see a window.\nSomeone is knocking on your house door.";
            } else {
                intro = "Your house hall\nHere you see a window and a glass of water.\nSomeone is knocking on your house door.";
            }
            if (!valueManager.ludlow.objectsDroppedHouse.isEmpty()) {
                int n = valueManager.ludlow.objectsDroppedHouse.size();
                for (int i=0; i<n; i++) {
                    intro = intro + "\nThere's the " + valueManager.ludlow.objectsDroppedHouse.get(i) + " here.";
                }
            }
            secondText.setText(intro);
            linearLayout.addView(secondText);
        } else if (myObjThree.matches("i") || myObjThree.matches("inventory")) {
            String inventory = "You have:";
            if (!valueManager.myself.inventory.isEmpty()) {
                int n = valueManager.myself.inventory.size();
                for (int i=0; i<n; i++) {
                    inventory = inventory + "\n    " + valueManager.myself.inventory.get(i) + ".";
                }
            } else {
                inventory = "You have nothing.";
            }
            secondText.setText(inventory);
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            linearLayout.addView(secondText);
        } else if (myObjThree.matches("sleep")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Amazing strategy.");
            linearLayout.addView(secondText);
        } else if ((myObjThree.contains("check") && myObjThree.contains("out")) || myObjThree.contains("find") || myObjThree.contains("search")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("check") || myObjThree.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("smell")) {
            secondText.setText("It smells like carrot salad from the other side of the door.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("listen")) {
            secondText.setText("Whoever is knocking on your door is doing it harder.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("wait")) {
            secondText.setText("Time passes...");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("what") && myObjThree.contains("time") && myObjThree.contains("is") || myObjThree.matches("time") || myObjThree.matches("the time")) {
            secondText.setText("It is sunrise.");
            linearLayout.addView(secondText);
        } else if (myObjThree.matches("diagnostic") || myObjThree.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            linearLayout.addView(secondText);
        } else if (myObjThree.contains("jump") || myObjThree.contains("climb") || myObjThree.contains("turn") || myObjThree.contains("shut") || myObjThree.contains("look") || myObjThree.contains("see") || myObjThree.contains("watch") || myObjThree.contains("play") || myObjThree.contains("run") || myObjThree.contains("walk") || myObjThree.contains("eat") || myObjThree.contains("move") || myObjThree.contains("put") || myObjThree.contains("give") || myObjThree.contains("offer") || myObjThree.contains("read") || myObjThree.contains("write") || myObjThree.contains("enter")|| myObjThree.contains("follow") || myObjThree.contains("fix") || myObjThree.contains("repair") || myObjThree.contains("attack") || myObjThree.contains("hit") || myObjThree.contains("break") || myObjThree.contains("fight") || myObjThree.contains("kick")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Look around you.");
            linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }
}
