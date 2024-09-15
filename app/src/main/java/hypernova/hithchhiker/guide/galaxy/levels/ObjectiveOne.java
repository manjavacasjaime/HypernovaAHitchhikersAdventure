package hypernova.hithchhiker.guide.galaxy.levels;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.R;
import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveOne extends AppCompatActivity {
    ValueManager valueManager;
    boolean hasAskedForHelp = false;

    public ObjectiveOne(ValueManager valManager) {
        valueManager = valManager;
    }

    public String checkObjAnswer(String myObjOne, Activity activity) {
        TextView myMoves = (TextView) findViewById(R.id.moves);
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(activity.getBaseContext(), R.font.lucida_console);
        TextView secondText = new TextView(activity.getBaseContext());
        secondText.setTypeface(typeface);

        if (myObjOne.matches("test606 obj7")) {
            valueManager.score = 12;
            myMoves.setText("Moves: " + valueManager.score);
            myLocation.setText("Library, door");
            valueManager.fred.isPresent = true;
            valueManager.myself.isStanding = true;
            libraryobj7 = 1; // CHECK LATER WHEN NEW NAVIGATION SYSTEM IS DEVELOPED IN OBJ7
            valueManager.myself.isNeckChipInstalled = true;
            valueManager.currentObjective = 7;
        }
        // DIRECTIONS GO VERB
        else if (myObjOne.matches("north") || myObjOne.matches("n") || myObjOne.matches("go north") || myObjOne.matches("go n") || myObjOne.matches("go straight on") || myObjOne.matches("south") || myObjOne.matches("s") || myObjOne.matches("go south") || myObjOne.matches("go s") || myObjOne.matches("go backwards") || myObjOne.matches("east") || myObjOne.matches("e") || myObjOne.matches("go east") || myObjOne.matches("go e") || myObjOne.matches("go right") || myObjOne.matches("west") || myObjOne.matches("w") || myObjOne.matches("go west") || myObjOne.matches("go w") || myObjOne.matches("go left") || myObjOne.matches("northeast") || myObjOne.matches("ne") || myObjOne.matches("go northeast") || myObjOne.matches("go ne") || myObjOne.matches("northwest") || myObjOne.matches("nw") || myObjOne.matches("go northwest") || myObjOne.matches("go nw") || myObjOne.matches("southeast") || myObjOne.matches("se") || myObjOne.matches("go southeast") || myObjOne.matches("go se") || myObjOne.matches("southwest") || myObjOne.matches("sw") || myObjOne.matches("go southwest") || myObjOne.matches("go sw") || myObjOne.matches("down") || myObjOne.matches("d") || myObjOne.matches("go down") || myObjOne.matches("downstairs") || myObjOne.matches("go downstairs") || myObjOne.matches("go d")) {
            secondText.setText("You can't go that way.");
            linearLayout.addView(secondText);
        } else if (myObjOne.matches("up") || myObjOne.matches("u") || myObjOne.matches("go up") || myObjOne.matches("upstairs") || myObjOne.matches("go upstairs") || myObjOne.matches("go u") || myObjOne.matches("exit") || myObjOne.matches("leave") || ((myObjOne.contains("go") || myObjOne.contains("get")) && myObjOne.contains("out")) || (myObjOne.contains("exit") && myObjOne.contains("room"))) {
            secondText.setText("You can't leave right now.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("speak") || myObjOne.contains("talk") || myObjOne.contains("ask")) {  // SPEAK VERB
            secondText.setText("There is no one here you can speak with.");
            linearLayout.addView(secondText);
        } else if (myObjOne.matches("stop")) {  // STOP VERB
            secondText.setText("This verb needs to be used with the action you wanna stop doing.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("stop")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjOne.contains("voices")) {
                secondText.setText("You are not hearing voices... Not yet.");
                linearLayout.addView(secondText);
            } else if (myObjOne.contains("play")) {
                myLocation.setText("Basement");
                secondText.setText("A soft light bulb allows you to see your house basement.\nYou are sitting on the floor. There is a piece of furniture with a vinyl player and some discs on it. Your game console is next to you. There's a door leading UP.\nThere is a blanket here.\nThere is a pencil here.");
                linearLayout.addView(secondText);
                valueManager.scoreDuringObj1 = valueManager.score;
                valueManager.currentObjective = 2;
            } else {
                secondText.setText("This action cannot be stopped or you're not doing it.");
                linearLayout.addView(secondText);
            }
        } else if (myObjOne.contains("keep play")) {  // KEEP VERB, added just in case
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You keep playing.");
            linearLayout.addView(secondText);
        } else if (myObjOne.matches("close")) {  // CLOSE VERB
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("close")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjOne.contains("eye")) {
                secondText.setText("You try it hard... But it results to be impossible.");
                linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be closed or you don't see it because you're focused on the game.");
                linearLayout.addView(secondText);
            }
        } else if (myObjOne.matches("open")) {  // OPEN VERB
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("open")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myObjOne.contains("eye")) {
                secondText.setText("Your eyes are already wide open.");
                linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be opened or you don't see it because you're focused on the game.");
                linearLayout.addView(secondText);
            }
        } else if (myObjOne.matches("help")) {  // HELP VERB
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (!hasAskedForHelp) {
                hasAskedForHelp = true;
                secondText.setText("No one is coming to help you. Ha Ha Ha!\n\nJust joking. Say STOP PLAYING.");
            } else {
                secondText.setText("Stop asking for help.");
            }
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("help")) {
            secondText.setText("Just say HELP.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("drink")) {  // DRINK VERB
            secondText.setText("There is nothing you can drink here.");
            linearLayout.addView(secondText);
        } else if (myObjOne.matches("i") || myObjOne.matches("inventory")) {
            secondText.setText("You have nothing.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("repeat")) {
            secondText.setText("There is nothing you can repeat. You're playing video games in your basement.");
            linearLayout.addView(secondText);
        } else if ((myObjOne.contains("check") && myObjOne.contains("out")) || myObjOne.contains("find") || myObjOne.contains("search")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("check") || myObjOne.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("play")) {  // PLAY VERB, added just in case
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You are already playing. Nice move...");
            linearLayout.addView(secondText);
        } else if (myObjOne.matches("sleep") || myObjOne.contains("jump") || myObjOne.contains("climb") || myObjOne.contains("stand") || myObjOne.contains("turn") || myObjOne.contains("shut") || myObjOne.contains("get") || myObjOne.contains("take") || myObjOne.contains("pick") || myObjOne.contains("grab") || myObjOne.contains("lie") || myObjOne.contains("sit") || myObjOne.contains("look") || myObjOne.contains("see") || myObjOne.contains("watch") || myObjOne.contains("play") || myObjOne.contains("run") || myObjOne.contains("walk") || myObjOne.contains("move") || myObjOne.contains("examine") || myObjOne.contains("eat") || myObjOne.contains("read") || myObjOne.contains("drop") || myObjOne.contains("put") || myObjOne.contains("give") || myObjOne.contains("offer") || myObjOne.contains("enter") || myObjOne.contains("attack") || myObjOne.contains("hit") || myObjOne.contains("break") || myObjOne.contains("fight") || myObjOne.contains("kick")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You cannot do that while you're still playing.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("smell")) {
            secondText.setText("It smells just like a basement.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("listen")) {
            secondText.setText("All you hear is retro video games music, which is nice.");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("wait")) {
            secondText.setText("Time passes...");
            linearLayout.addView(secondText);
        } else if (myObjOne.contains("what") && myObjOne.contains("time") && myObjOne.contains("is") || myObjOne.matches("time") || myObjOne.matches("the time")) {
            secondText.setText("You cannot tell that from the basement.");
            linearLayout.addView(secondText);
        } else if (myObjOne.matches("diagnostic") || myObjOne.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }
}
