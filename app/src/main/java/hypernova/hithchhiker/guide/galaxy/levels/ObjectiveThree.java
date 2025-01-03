package hypernova.hithchhiker.guide.galaxy.levels;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveThree extends AppCompatActivity {
    ValueManager vm;

    public ObjectiveThree(ValueManager valManager) {
        vm = valManager;
    }

    public String checkObjAnswer(String myObjThree, TextView secondText) {
        if (vm.fred.conversationStatus == 1) {  // CONVERSATION FRED
            switch (myObjThree) {
                case "1":
                    vm.fred.conversationStatus = 2;
                    secondText.setText("I'm F.R.E.D.\nFormed Rico Emotionally Disturbed, but you always call me Fred.\n\n\nDialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    vm.linearLayout.addView(secondText);
                    break;
                case "2":
                    vm.fred.conversationStatus = 2;
                    secondText.setText("You bet I know, " + vm.myself.name + ".\nHey it's me, F.R.E.D.\nFormed Rico Emotionally Disturbed, but you always call me Fred.\n\n\nDialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    vm.linearLayout.addView(secondText);
                    break;
                case "3":
                    vm.fred.conversationStatus = 2;
                    secondText.setText("Of course you know me. I'm F.R.E.D.\nFormed Rico Emotionally Disturbed, but you always call me Fred.\n\n\nDialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    vm.linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("You just said: oasidjfdd.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'Who are you?'\n2. 'You know who are you talking with!?'\n3. 'I'm " + vm.myself.name + ". Do I know you?'");
                    vm.linearLayout.addView(secondText);
                    break;
            }
        } else if (vm.fred.conversationStatus == 2) {
            switch (myObjThree) {
                case "1":
                    vm.fred.conversationStatus = 3;
                    secondText.setText("I'll get to that in a minute. First of all, the weekly report:\nNow, 89% of the Bubbles have electricity. The 300 years universal peace anniversary is closer. And our brothers from B905 have made it into the Space Federation.\n\n\nDialogue Fred.\n\n1. 'Pleased to hear that.'\n2. 'I hate these B905 guys.'\n3. 'Anything else?'");
                    vm.linearLayout.addView(secondText);
                    break;
                case "2":
                    vm.fred.conversationStatus = 3;
                    secondText.setText("Don't make me talk about those bastards.\nAnyways, let's take a look at the weekly report:\nNow, 89% of the Bubbles have electricity. The 300 years universal peace anniversary is closer. And our brothers from B905 have made it into the Space Federation.\n\n\nDialogue Fred.\n\n1. 'Pleased to hear that.'\n2. 'I hate these B905 guys.'\n3. 'Anything else?'");
                    vm.linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("You just said: nyhfwaa.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    vm.linearLayout.addView(secondText);
                    break;
            }
        } else if (vm.fred.conversationStatus == 3) {
            switch (myObjThree) {
                case "1":
                    vm.fred.conversationStatus = 4;
                    secondText.setText("You always so nice.\n\nOne more thing.\nI've been doing some research about the Space Travel Ban.\nI've checked every route made by the Space Federation and the routes allowed according to the law.\nThe numbers don't fit... There might be one Traveller alive.\n\n\nDialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    vm.linearLayout.addView(secondText);
                    break;
                case "2":
                    vm.fred.conversationStatus = 4;
                    secondText.setText("Although they kill their parents when they're twelve, we don't have to treat them differently.\n\nOne more thing.\nI've been doing some research about the Space Travel Ban.\nI've checked every route made by the Space Federation and the routes allowed according to the law.\nThe numbers don't fit... There might be one Traveller alive.\n\n\nDialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    vm.linearLayout.addView(secondText);
                    break;
                case "3":
                    vm.fred.conversationStatus = 4;
                    secondText.setText("Yes.\nI've been doing some research about the Space Travel Ban.\nI've checked every route made by the Space Federation and the routes allowed according to the law.\nThe numbers don't fit... There might be one Traveller alive.\n\n\nDialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    vm.linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("You just said: ywegvwn.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'Pleased to hear that.'\n2. 'I hate these B905 guys.'\n3. 'Anything else?'");
                    vm.linearLayout.addView(secondText);
                    break;
            }
        } else if (vm.fred.conversationStatus == 4) {
            switch (myObjThree) {
                case "1":
                    vm.fred.conversationStatus = 5;
                    secondText.setText("I've said there MIGHT be one alive. Maybe. Just maybe...\nNow there's hope to get out of B903.\n\n\nDialogue Fred.\n\n1. 'I'm happy in the Bubble.'\n2. 'Assuming this Traveller exists, what now?'");
                    vm.linearLayout.addView(secondText);
                    break;
                case "2":
                    vm.fred.conversationStatus = 5;
                    secondText.setText("Fred doesn't want that. Nobody in the universe would want that...\nNow there's hope to get out of B903.\n\n\nDialogue Fred.\n\n1. 'I'm happy in the Bubble.'\n2. 'Assuming this Traveller exists, what now?'");
                    vm.linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("You just said: ajdhasj.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    vm.linearLayout.addView(secondText);
                    break;
            }
        } else if (vm.fred.conversationStatus == 5) {
            switch (myObjThree) {
                case "1":
                    vm.fred.conversationStatus = 0;
                    vm.ludlow.scoreWhenFredWaiting = vm.score;
                    vm.myself.inventory.add("library neck chip");
                    secondText.setText("'You won't be when the Space Federation comes to look for the Traveller.\nNow we can be part of something more important than this Bubble. Won't you ever wanted to see with your own eyes what is out of this place?\n\nMy research will lead us to that. Finding the Traveller will lead us to that.\n\nI just need one more ingredient, a book called The Hitchhiker's Guide to the Galaxy. They have one of those at the Library and we need to get it.'\n\nDialogue Fred finished.\n\nFred gives you a chip similar to the one he has on his neck which says LUDLOW PUBLIC LIBRARY.\nLIBRARY NECK CHIP has been added to your inventory.");
                    vm.linearLayout.addView(secondText);
                    vm.currentObjective = 4;
                    break;
                case "2":
                    vm.fred.conversationStatus = 0;
                    vm.ludlow.scoreWhenFredWaiting = vm.score;
                    vm.myself.inventory.add("library neck chip");
                    secondText.setText("'Now we can be part of something more important than this Bubble. Won't you ever wanted to see with your own eyes what is out of this place?\n\nMy research will lead us to that. Finding the Traveller will lead us to that.\n\nI just need one more ingredient, a book called The Hitchhiker's Guide to the Galaxy. They have one of those at the Library and we need to get it.'\n\nDialogue Fred finished.\n\nFred gives you a chip similar to the one he has on his neck which says LUDLOW PUBLIC LIBRARY.\nLIBRARY NECK CHIP has been added to your inventory.");
                    vm.linearLayout.addView(secondText);
                    vm.currentObjective = 4;
                    break;
                default:
                    secondText.setText("You just said: nuwnuds.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'I'm happy in the Bubble.'\n2. 'Assuming this Traveller exists, what now?'");
                    vm.linearLayout.addView(secondText);
                    break;
            }
        }
        // DIRECTIONS GO VERB
        else if (myObjThree.matches("south") || myObjThree.matches("s") || myObjThree.matches("go south") || myObjThree.matches("go s") || myObjThree.matches("go backwards") || myObjThree.matches("east") || myObjThree.matches("e") || myObjThree.matches("go east") || myObjThree.matches("go e") || myObjThree.matches("go right") || myObjThree.matches("west") || myObjThree.matches("w") || myObjThree.matches("go west") || myObjThree.matches("go w") || myObjThree.matches("go left") || myObjThree.matches("northeast") || myObjThree.matches("ne") || myObjThree.matches("go northeast") || myObjThree.matches("go ne") || myObjThree.matches("northwest") || myObjThree.matches("nw") || myObjThree.matches("go northwest") || myObjThree.matches("go nw") || myObjThree.matches("southeast") || myObjThree.matches("se") || myObjThree.matches("go southeast") || myObjThree.matches("go se") || myObjThree.matches("southwest") || myObjThree.matches("sw") || myObjThree.matches("go southwest") || myObjThree.matches("go sw") || myObjThree.matches("down") || myObjThree.matches("d") || myObjThree.matches("go down") || myObjThree.matches("downstairs") || myObjThree.matches("go downstairs") || myObjThree.matches("go d") || myObjThree.matches("up") || myObjThree.matches("u") || myObjThree.matches("go up") || myObjThree.matches("upstairs") || myObjThree.matches("go upstairs") || myObjThree.matches("go u")) {
            secondText.setText("You can't go that way.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.matches("exit") || myObjThree.matches("leave") || myObjThree.matches("north") || myObjThree.matches("n") || myObjThree.matches("go north") || myObjThree.matches("go n") || myObjThree.matches("go straight on") || ((myObjThree.contains("go") || myObjThree.contains("get")) && myObjThree.contains("out")) || (myObjThree.contains("exit") && myObjThree.contains("room"))) {
            secondText.setText("You'll have to open the door first.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("speak") || myObjThree.contains("talk") || myObjThree.contains("ask")) {  // SPEAK VERB
            secondText.setText("There is no one here you can speak with.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("stop")) {  // STOP VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjThree.contains("play") || myObjThree.contains("game")) {
                secondText.setText("You already stopped playing.");
                vm.linearLayout.addView(secondText);
            } else if (myObjThree.contains("noise") || myObjThree.contains("sound") || myObjThree.contains("knock")) {
                secondText.setText("Opening the door is the way to stop someone knocking on your house door. Duh.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("There's nothing you can stop here.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjThree.matches("close")) {  // CLOSE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("close")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjThree.contains("door")) {
                secondText.setText("The door is already closed.");
                vm.linearLayout.addView(secondText);
            } else if (myObjThree.contains("eye")) {
                secondText.setText("This is not really useful right now.\nThe knocks keep going.");
                vm.linearLayout.addView(secondText);
            } else if (myObjThree.contains("window")) {
                secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjThree.matches("open")) {  // OPEN VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("open")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjThree.contains("eye")) {
                secondText.setText("You already see.");
                vm.linearLayout.addView(secondText);
            } else if (myObjThree.contains("basement door")) {
                secondText.setText("The basement door is locked.");
                vm.linearLayout.addView(secondText);
            } else if (myObjThree.contains("door")) {
                vm.ludlow.isHouseDoorOpen = true;
                vm.fred.isPresent = true;
                vm.fred.conversationStatus = 1;
                vm.myLocation.setText("Hall, open door");
                secondText.setText("'10 o'clock at your door. 10 o'clock at your door. Is it that difficult?'\n\n\nDialogue Fred.\n\n1. 'Who are you?'\n2. 'You know who are you talking with!?'\n3. 'I'm " + vm.myself.name + ". Do I know you?'");
                vm.linearLayout.addView(secondText);
            } else if (myObjThree.contains("window")) {
                secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // TURN OFF VERB
        else if ((myObjThree.contains("turn") && myObjThree.contains("off")) || (myObjThree.contains("shut") && myObjThree.contains("down"))) {
            secondText.setText("There is nothing you can turn off here.\nSomeone is still knocking on your house door.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("turn on") || myObjThree.contains("turn it on")) {  // TURN ON VERB
            secondText.setText("There's nothing you can turn on here.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("stand") || myObjThree.contains("get up")) {  // STAND VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("You are already standing.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("sit down") || myObjThree.matches("sit") || myObjThree.contains("lie")) {  // LIE VERB
            secondText.setText("Someone is knocking on your house door. Take care of that.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.matches("examine")) {  // EXAMINE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("examine")) {
            if (myObjThree.contains("glass") || myObjThree.contains("water")) {
                if (vm.ludlow.isHouseWaterDrunk) {
                    secondText.setText("You drank that.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("It is just a glass of water.");
                    vm.linearLayout.addView(secondText);
                }
            } else if (myObjThree.contains("window")) {
                secondText.setText("Just look at it.");
                vm.linearLayout.addView(secondText);
            } else if (myObjThree.contains("basement door")) {
                secondText.setText("It is closed.");
                vm.linearLayout.addView(secondText);
            } else if (myObjThree.contains("door")) {
                secondText.setText("Someone is knocking at it.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be examined or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // TAKE GET VERB
        else if (myObjThree.matches("take") || myObjThree.matches("get") || myObjThree.matches("pick") || myObjThree.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("take") || myObjThree.contains("get") || myObjThree.contains("pick") || myObjThree.contains("grab")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if ((myObjThree.contains("glass") || myObjThree.contains("water")) && vm.ludlow.isHouseWaterDrunk) {
                secondText.setText("You already drank that.");
                vm.linearLayout.addView(secondText);
            } else if ((myObjThree.contains("glass") || myObjThree.contains("water")) && !vm.ludlow.isHouseWaterDrunk && !vm.ludlow.objectsDroppedHouse.contains("water")) {
                if (vm.myself.inventory.contains("water")) {
                    secondText.setText("You already took it.");
                } else {
                    vm.myself.inventory.add("water");
                    secondText.setText("Taken.\nYou still hear the knocks on the door.");
                }
                vm.linearLayout.addView(secondText);
            } else if (!vm.ludlow.objectsDroppedHouse.isEmpty()) {
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
                if (vm.ludlow.objectsDroppedHouse.contains(s)) {
                    vm.ludlow.objectsDroppedHouse.remove(s);
                    vm.myself.inventory.add(s);
                    secondText.setText("Taken.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("You can't see any " + s + " here!");
                    vm.linearLayout.addView(secondText);
                }
            } else {
                secondText.setText("This thing cannot be taken or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjThree.matches("drop") || myObjThree.contains("get rid of")) {  // DROP VERB
            secondText.setText("Just say: Drop (and the object you want to drop).");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("drop")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            int n = 4;
            String s = myObjThree.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (vm.myself.inventory.contains(s)) {
                vm.myself.inventory.remove(s);
                vm.ludlow.objectsDroppedHouse.add(s);
                secondText.setText("Dropped.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("You're not holding the " + s + ".");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjThree.matches("help")) {  // HELP VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("I'm sure you're smart enough to know the procedure when someone is knocking at your door.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("help")) {
            secondText.setText("Just say HELP.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.matches("drink")) {  // DRINK VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("drink")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjThree.contains("glass") || myObjThree.contains("water")) {
                if (vm.ludlow.isHouseWaterDrunk) {
                    secondText.setText("You already drank that.");
                    vm.linearLayout.addView(secondText);
                } else if (vm.myself.inventory.contains("water")) {
                    vm.ludlow.isHouseWaterDrunk = true;
                    secondText.setText("It feels refreshing.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("You will have to take it first.");
                    vm.linearLayout.addView(secondText);
                }
            } else {
                secondText.setText("This thing cannot be drunk or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjThree.contains("window")) {  // WINDOW ACTIONS, added just in case
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjThree.contains("look") && myObjThree.contains("around")) || myObjThree.matches("l") || myObjThree.matches("look")) {
            String intro = "";
            if (vm.ludlow.isHouseWaterDrunk || vm.myself.inventory.contains("water") || vm.ludlow.objectsDroppedHouse.contains("water")) {
                intro = "Your house hall\nHere you see a window.\nSomeone is knocking on your house door.";
            } else {
                intro = "Your house hall\nHere you see a window and a glass of water.\nSomeone is knocking on your house door.";
            }
            if (!vm.ludlow.objectsDroppedHouse.isEmpty()) {
                int n = vm.ludlow.objectsDroppedHouse.size();
                for (int i=0; i<n; i++) {
                    intro = intro + "\nThere's the " + vm.ludlow.objectsDroppedHouse.get(i) + " here.";
                }
            }
            secondText.setText(intro);
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.matches("i") || myObjThree.matches("inventory")) {
            String inventory = "You have:";
            if (!vm.myself.inventory.isEmpty()) {
                int n = vm.myself.inventory.size();
                for (int i=0; i<n; i++) {
                    inventory = inventory + "\n    " + vm.myself.inventory.get(i) + ".";
                }
            } else {
                inventory = "You have nothing.";
            }
            secondText.setText(inventory);
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.matches("sleep")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Amazing strategy.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjThree.contains("check") && myObjThree.contains("out")) || myObjThree.contains("find") || myObjThree.contains("search")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("check") || myObjThree.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("smell")) {
            secondText.setText("It smells like carrot salad from the other side of the door.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("listen")) {
            secondText.setText("Whoever is knocking on your door is doing it harder.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("wait")) {
            secondText.setText("Time passes...");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("what") && myObjThree.contains("time") && myObjThree.contains("is") || myObjThree.matches("time") || myObjThree.matches("the time")) {
            secondText.setText("It is sunrise.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.matches("diagnostic") || myObjThree.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            vm.linearLayout.addView(secondText);
        } else if (myObjThree.contains("jump") || myObjThree.contains("climb") || myObjThree.contains("turn") || myObjThree.contains("shut") || myObjThree.contains("look") || myObjThree.contains("see") || myObjThree.contains("watch") || myObjThree.contains("play") || myObjThree.contains("run") || myObjThree.contains("walk") || myObjThree.contains("eat") || myObjThree.contains("move") || myObjThree.contains("put") || myObjThree.contains("give") || myObjThree.contains("offer") || myObjThree.contains("read") || myObjThree.contains("write") || myObjThree.contains("enter") || myObjThree.contains("follow") || myObjThree.contains("fix") || myObjThree.contains("repair") || myObjThree.contains("attack") || myObjThree.contains("hit") || myObjThree.contains("break") || myObjThree.contains("fight") || myObjThree.contains("kick")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Look around you.");
            vm.linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }
}
