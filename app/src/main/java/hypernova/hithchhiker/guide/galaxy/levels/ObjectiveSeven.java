package hypernova.hithchhiker.guide.galaxy.levels;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveSeven extends AppCompatActivity {
    ValueManager vm;

    public ObjectiveSeven(ValueManager valManager) {
        vm = valManager;
    }

    public String checkObjAnswer(String myObjSeven, TextView secondText) {
        // DIRECTIONS GO VERB
        if (myObjSeven.matches("up") || myObjSeven.matches("u") || myObjSeven.matches("go up") || myObjSeven.matches("upstairs") || myObjSeven.matches("go upstairs") || myObjSeven.matches("go u") || myObjSeven.matches("down") || myObjSeven.matches("d") || myObjSeven.matches("go down") || myObjSeven.matches("downstairs") || myObjSeven.matches("go downstairs") || myObjSeven.matches("go d")) {
            secondText.setText("You can't go that way.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("north") || myObjSeven.matches("n") || myObjSeven.matches("go north") || myObjSeven.matches("go n") || myObjSeven.matches("go straight on")) {
            String s = "";
            switch (vm.nowhere.currentLocation) {
                case 11:
                case 13:
                    s = "You can't go that way.";
                    break;
                case 12:
                    if (vm.nowhere.isHouseDoorOpen) {
                        vm.score++;
                        vm.myMoves.setText("Moves: " + vm.score);
                        vm.myLocation.setText("Desert House");
                        s = "Congratulations human.\n\nAt least, you finished the demo in " + vm.score + " moves.\nNot bad for a species that has pizza as a religion.";
                        vm.currentObjective = 8;
                    } else {
                        s = "The door is closed.";
                    }
                    break;
                case 21:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, W");
                    vm.nowhere.currentLocation = 11;
                    s = "Nowhere\nHeading northeast you still see the house.";
                    if (!vm.nowhere.objectsDropped11.isEmpty()) {
                        int n = vm.nowhere.objectsDropped11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped11.get(i) + " here.";
                        }
                    }
                    break;
                case 22:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere");
                    vm.nowhere.currentLocation = 12;
                    s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.";
                    if (vm.nowhere.isHouseDoorOpen) {
                        s = s + "\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = s + "\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!vm.nowhere.objectsDropped12.isEmpty()) {
                        int n = vm.nowhere.objectsDropped12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped12.get(i) + " here.";
                        }
                    }
                    break;
                case 23:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, E");
                    vm.nowhere.currentLocation = 13;
                    s = "Nowhere\nHeading northwest you still see the house.";
                    if (!vm.nowhere.objectsDropped13.isEmpty()) {
                        int n = vm.nowhere.objectsDropped13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped13.get(i) + " here.";
                        }
                    }
                    break;
            }
            secondText.setText(s);
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("south") || myObjSeven.matches("s") || myObjSeven.matches("go south") || myObjSeven.matches("go s") || myObjSeven.matches("go backwards")) {
            String s = "";
            switch (vm.nowhere.currentLocation) {
                case 11:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, SW");
                    vm.nowhere.currentLocation = 21;
                    if (vm.myself.inventoryPast.contains("egg") || vm.nowhere.objectsDropped11.contains("egg") ||
                        vm.nowhere.objectsDropped12.contains("egg") || vm.nowhere.objectsDropped13.contains("egg") ||
                        vm.nowhere.objectsDropped21.contains("egg") || vm.nowhere.objectsDropped22.contains("egg") ||
                        vm.nowhere.objectsDropped23.contains("egg") || vm.nowhere.isEggBroken) {
                        s = "Nowhere\nNow the house is northeast.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    }
                    if (!vm.nowhere.objectsDropped21.isEmpty()) {
                        int n = vm.nowhere.objectsDropped21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped21.get(i) + " here.";
                        }
                    }
                    break;
                case 12:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, S");
                    vm.nowhere.currentLocation = 22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!vm.nowhere.objectsDropped22.isEmpty()) {
                        int n = vm.nowhere.objectsDropped22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped22.get(i) + " here.";
                        }
                    }
                    break;
                case 13:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, SE");
                    vm.nowhere.currentLocation = 23;
                    s = "Nowhere\nNow the house is northwest.";
                    if (!vm.nowhere.objectsDropped23.isEmpty()) {
                        int n = vm.nowhere.objectsDropped23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped23.get(i) + " here.";
                        }
                    }
                    break;
                case 21:
                case 22:
                case 23:
                    s = "You can't go that way.";
                    break;
            }
            secondText.setText(s);
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("west") || myObjSeven.matches("w") || myObjSeven.matches("go west") || myObjSeven.matches("go w") || myObjSeven.matches("go left")) {
            String s = "";
            switch (vm.nowhere.currentLocation) {
                case 11:
                case 21:
                    s = "You can't go that way.";
                    break;
                case 12:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, W");
                    vm.nowhere.currentLocation = 11;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northeast you still see the house.";
                    if (!vm.nowhere.objectsDropped11.isEmpty()) {
                        int n = vm.nowhere.objectsDropped11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped11.get(i) + " here.";
                        }
                    }
                    break;
                case 13:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere");
                    vm.nowhere.currentLocation = 12;
                    s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.";
                    if (vm.nowhere.isHouseDoorOpen) {
                        s = s + "\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = s + "\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!vm.nowhere.objectsDropped12.isEmpty()) {
                        int n = vm.nowhere.objectsDropped12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped12.get(i) + " here.";
                        }
                    }
                    break;
                case 22:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, SW");
                    vm.nowhere.currentLocation = 21;
                    if (vm.myself.inventoryPast.contains("egg") || vm.nowhere.objectsDropped11.contains("egg") ||
                        vm.nowhere.objectsDropped12.contains("egg") || vm.nowhere.objectsDropped13.contains("egg") ||
                        vm.nowhere.objectsDropped21.contains("egg") || vm.nowhere.objectsDropped22.contains("egg") ||
                        vm.nowhere.objectsDropped23.contains("egg") || vm.nowhere.isEggBroken) {
                        s = "Nowhere\nNow the house is northeast.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    }
                    if (!vm.nowhere.objectsDropped21.isEmpty()) {
                        int n = vm.nowhere.objectsDropped21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped21.get(i) + " here.";
                        }
                    }
                    break;
                case 23:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, S");
                    vm.nowhere.currentLocation = 22;
                    s = "Nowhere\nHeading north you still see the house.";
                    if (!vm.nowhere.objectsDropped22.isEmpty()) {
                        int n = vm.nowhere.objectsDropped22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped22.get(i) + " here.";
                        }
                    }
                    break;
            }
            secondText.setText(s);
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("east") || myObjSeven.matches("e") || myObjSeven.matches("go east") || myObjSeven.matches("go e") || myObjSeven.matches("go right")) {
            String s = "";
            switch (vm.nowhere.currentLocation) {
                case 11:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere");
                    vm.nowhere.currentLocation = 12;
                    s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.";
                    if (vm.nowhere.isHouseDoorOpen) {
                        s = s + "\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = s + "\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!vm.nowhere.objectsDropped12.isEmpty()) {
                        int n = vm.nowhere.objectsDropped12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped12.get(i) + " here.";
                        }
                    }
                    break;
                case 12:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, E");
                    vm.nowhere.currentLocation = 13;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northwest you still see the house.";
                    if (!vm.nowhere.objectsDropped13.isEmpty()) {
                        int n = vm.nowhere.objectsDropped13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped13.get(i) + " here.";
                        }
                    }
                    break;
                case 13:
                case 23:
                    s = "You can't go that way.";
                    break;
                case 21:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, S");
                    vm.nowhere.currentLocation = 22;
                    s = "Nowhere\nHeading north you still see the house.";
                    if (!vm.nowhere.objectsDropped22.isEmpty()) {
                        int n = vm.nowhere.objectsDropped22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped22.get(i) + " here.";
                        }
                    }
                    break;
                case 22:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, SE");
                    vm.nowhere.currentLocation = 23;
                    s = "Nowhere\nNow the house is northwest.";
                    if (!vm.nowhere.objectsDropped23.isEmpty()) {
                        int n = vm.nowhere.objectsDropped23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped23.get(i) + " here.";
                        }
                    }
                    break;
            }
            secondText.setText(s);
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("northeast") || myObjSeven.matches("ne") || myObjSeven.matches("go northeast") || myObjSeven.matches("go ne")) {
            String s = "";
            switch (vm.nowhere.currentLocation) {
                case 11:
                    if (vm.nowhere.isHouseDoorOpen) {
                        vm.score++;
                        vm.myMoves.setText("Moves: " + vm.score);
                        vm.myLocation.setText("Desert House");
                        s = "Congratulations human.\n\nAt least, you finished the demo in " + vm.score + " moves.\nNot bad for a species that has pizza as a religion.";
                        vm.currentObjective = 8;
                    } else {
                        s = "The door is closed.";
                    }
                    break;
                case 12:
                case 13:
                case 23:
                    s = "You can't go that way.";
                    break;
                case 21:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere");
                    vm.nowhere.currentLocation = 12;
                    s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.";
                    if (vm.nowhere.isHouseDoorOpen) {
                        s = s + "\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = s + "\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!vm.nowhere.objectsDropped12.isEmpty()) {
                        int n = vm.nowhere.objectsDropped12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped12.get(i) + " here.";
                        }
                    }
                    break;
                case 22:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, E");
                    vm.nowhere.currentLocation = 13;
                    s = "Nowhere\nHeading northwest you still see the house.";
                    if (!vm.nowhere.objectsDropped13.isEmpty()) {
                        int n = vm.nowhere.objectsDropped13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped13.get(i) + " here.";
                        }
                    }
                    break;
            }
            secondText.setText(s);
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("northwest") || myObjSeven.matches("nw") || myObjSeven.matches("go northwest") || myObjSeven.matches("go nw")) {
            String s = "";
            switch (vm.nowhere.currentLocation) {
                case 11:
                case 12:
                case 21:
                    s = "You can't go that way.";
                    break;
                case 13:
                    if (vm.nowhere.isHouseDoorOpen) {
                        vm.score++;
                        vm.myMoves.setText("Moves: " + vm.score);
                        vm.myLocation.setText("Desert House");
                        s = "Congratulations human.\n\nAt least, you finished the demo in " + vm.score + " moves.\nNot bad for a species that has pizza as a religion.";
                        vm.currentObjective = 8;
                    } else {
                        s = "The door is closed.";
                    }
                    break;
                case 22:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, W");
                    vm.nowhere.currentLocation = 11;
                    s = "Nowhere\nHeading northeast you still see the house.";
                    if (!vm.nowhere.objectsDropped11.isEmpty()) {
                        int n = vm.nowhere.objectsDropped11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped11.get(i) + " here.";
                        }
                    }
                    break;
                case 23:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere");
                    vm.nowhere.currentLocation = 12;
                    s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.";
                    if (vm.nowhere.isHouseDoorOpen) {
                        s = s + "\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = s + "\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!vm.nowhere.objectsDropped12.isEmpty()) {
                        int n = vm.nowhere.objectsDropped12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped12.get(i) + " here.";
                        }
                    }
                    break;
            }
            secondText.setText(s);
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("southeast") || myObjSeven.matches("se") || myObjSeven.matches("go southeast") || myObjSeven.matches("go se")) {
            String s = "";
            switch (vm.nowhere.currentLocation) {
                case 11:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, S");
                    vm.nowhere.currentLocation = 22;
                    s = "Nowhere\nHeading north you still see the house.";
                    if (!vm.nowhere.objectsDropped22.isEmpty()) {
                        int n = vm.nowhere.objectsDropped22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped22.get(i) + " here.";
                        }
                    }
                    break;
                case 12:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, SE");
                    vm.nowhere.currentLocation = 23;
                    s = "Nowhere\nNow the house is northwest.";
                    if (!vm.nowhere.objectsDropped23.isEmpty()) {
                        int n = vm.nowhere.objectsDropped23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped23.get(i) + " here.";
                        }
                    }
                    break;
                case 13:
                case 21:
                case 22:
                case 23:
                    s = "You can't go that way.";
                    break;
            }
            secondText.setText(s);
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("southwest") || myObjSeven.matches("sw") || myObjSeven.matches("go southwest") || myObjSeven.matches("go sw")) {
            String s = "";
            switch (vm.nowhere.currentLocation) {
                case 11:
                case 21:
                case 22:
                case 23:
                    s = "You can't go that way.";
                    break;
                case 12:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, SW");
                    vm.nowhere.currentLocation = 21;
                    if (vm.myself.inventoryPast.contains("egg") || vm.nowhere.objectsDropped11.contains("egg") ||
                        vm.nowhere.objectsDropped12.contains("egg") || vm.nowhere.objectsDropped13.contains("egg") ||
                        vm.nowhere.objectsDropped21.contains("egg") || vm.nowhere.objectsDropped22.contains("egg") ||
                        vm.nowhere.objectsDropped23.contains("egg") || vm.nowhere.isEggBroken) {
                        s = "Nowhere\nNow the house is northeast.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    }
                    if (!vm.nowhere.objectsDropped21.isEmpty()) {
                        int n = vm.nowhere.objectsDropped21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped21.get(i) + " here.";
                        }
                    }
                    break;
                case 13:
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere, S");
                    vm.nowhere.currentLocation = 22;
                    s = "Nowhere\nHeading north you still see the house.";
                    if (!vm.nowhere.objectsDropped22.isEmpty()) {
                        int n = vm.nowhere.objectsDropped22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped22.get(i) + " here.";
                        }
                    }
                    break;
            }
            secondText.setText(s);
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("exit") || myObjSeven.matches("leave") || ((myObjSeven.contains("go") || myObjSeven.contains("get")) && myObjSeven.contains("out")) || (myObjSeven.contains("exit") && myObjSeven.contains("room"))) {
            secondText.setText("You are already on the outside.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            vm.linearLayout.addView(secondText);
        }
        // SPEAK VERB
        else if (myObjSeven.contains("speak") || myObjSeven.contains("talk") || myObjSeven.contains("ask")) {
            secondText.setText("There is no one here you can speak with.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("stop")) {  // STOP VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSeven.contains("voices")) {
                secondText.setText("You are not hearing voices... Not yet.");
            } else if (myObjSeven.contains("time")) {
                secondText.setText("You wish you could.");
            } else if (myObjSeven.contains("breath")) {
                secondText.setText("If you want to die, just say DIE.");
            } else if (myObjSeven.contains("sleep") || myObjSeven.contains("dream")) {
                secondText.setText("You are pretty much awake.");
            } else {
                secondText.setText("There's nothing you can stop here.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("close")) {  // CLOSE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("close")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSeven.contains("door")) {
                if (vm.nowhere.currentLocation == 12) {
                    if (vm.nowhere.isHouseDoorOpen) {
                        vm.nowhere.isHouseDoorOpen = false;
                        secondText.setText("You closed it.");
                    } else {
                        secondText.setText("It is already closed.");
                    }
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
            } else if (myObjSeven.contains("eye")) {
                secondText.setText("This is not really useful right now.");
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("open")) {  // OPEN VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("open")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSeven.contains("door")) {
                if (vm.nowhere.currentLocation == 12) {
                    if (!vm.nowhere.isHouseDoorOpen) {
                        vm.nowhere.isHouseDoorOpen = true;
                        secondText.setText("You opened it.");
                    } else {
                        secondText.setText("It is already open.");
                    }
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
            } else if (myObjSeven.contains("eye")) {
                secondText.setText("Your eyes are wide open.");
            } else if (myObjSeven.contains("curtain")) {
                if (vm.nowhere.currentLocation == 12) {
                    secondText.setText("You can hardly see anything. It is really dark inside.");
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
            } else if (myObjSeven.contains("window")) {
                if (vm.nowhere.currentLocation == 12) {
                    secondText.setText("The crystal is broken. It is too sharp to touch it.");
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
            } else if (myObjSeven.contains("egg") && (vm.myself.inventoryPast.contains("egg") ||
                (vm.nowhere.currentLocation == 11 && vm.nowhere.objectsDropped11.contains("egg")) ||
                (vm.nowhere.currentLocation == 12 && vm.nowhere.objectsDropped12.contains("egg")) ||
                (vm.nowhere.currentLocation == 13 && vm.nowhere.objectsDropped13.contains("egg")) ||
                (vm.nowhere.currentLocation == 21 && vm.nowhere.objectsDropped21.contains("egg")) ||
                (vm.nowhere.currentLocation == 22 && vm.nowhere.objectsDropped22.contains("egg")) ||
                (vm.nowhere.currentLocation == 23 && vm.nowhere.objectsDropped23.contains("egg")))) {
                vm.nowhere.isEggBroken = true;
                if (vm.myself.inventoryPast.contains("egg")) {vm.myself.inventoryPast.remove("egg");}
                else if (vm.nowhere.objectsDropped11.contains("egg")) {vm.nowhere.objectsDropped11.remove("egg");}
                else if (vm.nowhere.objectsDropped12.contains("egg")) {vm.nowhere.objectsDropped12.remove("egg");}
                else if (vm.nowhere.objectsDropped13.contains("egg")) {vm.nowhere.objectsDropped13.remove("egg");}
                else if (vm.nowhere.objectsDropped21.contains("egg")) {vm.nowhere.objectsDropped21.remove("egg");}
                else if (vm.nowhere.objectsDropped22.contains("egg")) {vm.nowhere.objectsDropped22.remove("egg");}
                else vm.nowhere.objectsDropped23.remove("egg");
                secondText.setText("When it crashes on the ground... nothing happens.\nIf there was hope to see another species in B903, now there's none.");
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("enter")) {  // ENTER VERB
            secondText.setText("This verb needs to be used with a place to get in.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("enter")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSeven.contains("house")) {
                if (vm.nowhere.currentLocation == 12) {
                    if (vm.nowhere.isHouseDoorOpen) {
                        vm.myLocation.setText("Desert House");
                        secondText.setText("Congratulations human.\n\nAt least, you finished the demo in " + vm.score + " moves.\nNot bad for a species that has pizza as a religion.");
                        vm.currentObjective = 8;
                    } else {
                        secondText.setText("The door is closed.");
                    }
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
            } else if (myObjSeven.contains("matrix")) {
                secondText.setText("Morpheus should be proud.");
            } else {
                secondText.setText("You cannot get in here or the ubication's name is incorrect.");
            }
            vm.linearLayout.addView(secondText);
        }
        // TURN OFF VERB
        else if ((myObjSeven.contains("turn") && myObjSeven.contains("off")) || (myObjSeven.contains("shut") && myObjSeven.contains("down"))) {
            secondText.setText("There's nothing you can turn off here.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("turn on") || myObjSeven.contains("turn it on")) {  // TURN ON VERB
            secondText.setText("There's nothing you can turn on here.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("stand") || myObjSeven.contains("get up")) {  // STAND VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("You are already standing.");
            vm.linearLayout.addView(secondText);
        }
        // LIE VERB
        else if (myObjSeven.contains("sit down") || myObjSeven.matches("sit") || myObjSeven.contains("lie")) {
            secondText.setText("The ground doesn't seem that comfortable.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("examine")) {  // EXAMINE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("examine")) {
            if (myObjSeven.contains("house")) {
                if (vm.nowhere.currentLocation == 11 || vm.nowhere.currentLocation == 12 ||
                    vm.nowhere.currentLocation == 13 || vm.nowhere.currentLocation == 22) {
                    secondText.setText("The house gives off a feeling of loneliness.");
                } else {
                    secondText.setText("You cannot examine that from this position.");
                }
            } else if (myObjSeven.contains("door") && (vm.nowhere.currentLocation == 11 ||
                vm.nowhere.currentLocation == 12 || vm.nowhere.currentLocation == 13 ||
                vm.nowhere.currentLocation == 22)) {
                if (vm.nowhere.isHouseDoorOpen) {
                    secondText.setText("It is open. It looks older than the house.");
                } else {
                    secondText.setText("It is closed. It looks older than the house.");
                }
            } else if (myObjSeven.contains("window") && (vm.nowhere.currentLocation == 11 ||
                vm.nowhere.currentLocation == 12 || vm.nowhere.currentLocation == 13 ||
                vm.nowhere.currentLocation == 22)) {
                secondText.setText("You can hardly see anything. It is really dark inside.");
            } else if (myObjSeven.contains("curtain") && (vm.nowhere.currentLocation == 11 ||
                vm.nowhere.currentLocation == 12 || vm.nowhere.currentLocation == 13 ||
                vm.nowhere.currentLocation == 22)) {
                secondText.setText("The curtains are dark and full of loose seams.");
            } else if (myObjSeven.contains("sky")) {
                secondText.setText("At this time of year B903's sky is always orange. It seems like it's almost night the whole time, everyday.");
            } else if (myObjSeven.contains("black") && (myObjSeven.contains("stone") || myObjSeven.contains("rock"))) {
                secondText.setText("The black stones are cold.");
            } else if ((myObjSeven.contains("stone") || myObjSeven.contains("rock"))) {
                if (vm.nowhere.currentLocation == 21 && !(vm.myself.inventoryPast.contains("egg") || vm.nowhere.isEggBroken ||
                    vm.nowhere.objectsDropped11.contains("egg") || vm.nowhere.objectsDropped12.contains("egg") ||
                    vm.nowhere.objectsDropped13.contains("egg") || vm.nowhere.objectsDropped21.contains("egg") ||
                    vm.nowhere.objectsDropped22.contains("egg") || vm.nowhere.objectsDropped23.contains("egg"))) {
                    secondText.setText("It looks like the only white stone in the whole desert.");
                } else {
                    secondText.setText("The black stones are cold.");
                }
            } else if (myObjSeven.contains("egg") && (vm.myself.inventoryPast.contains("egg") ||
                (vm.nowhere.currentLocation == 11 && vm.nowhere.objectsDropped11.contains("egg")) ||
                (vm.nowhere.currentLocation == 12 && vm.nowhere.objectsDropped12.contains("egg")) ||
                (vm.nowhere.currentLocation == 13 && vm.nowhere.objectsDropped13.contains("egg")) ||
                (vm.nowhere.currentLocation == 21 && vm.nowhere.objectsDropped21.contains("egg")) ||
                (vm.nowhere.currentLocation == 22 && vm.nowhere.objectsDropped22.contains("egg")) ||
                (vm.nowhere.currentLocation == 23 && vm.nowhere.objectsDropped23.contains("egg")))) {
                secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
            } else {
                secondText.setText("This thing cannot be examined or it is not in the place.");
            }
            vm.linearLayout.addView(secondText);
        }
        // TAKE GET VERB
        else if (myObjSeven.matches("take") || myObjSeven.matches("get") || myObjSeven.matches("pick") || myObjSeven.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("take") || myObjSeven.contains("get") || myObjSeven.contains("pick") || myObjSeven.contains("grab")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSeven.contains("stone") || myObjSeven.contains("rock")) {
                if (vm.nowhere.currentLocation == 21 && !(vm.myself.inventoryPast.contains("egg") || vm.nowhere.isEggBroken ||
                    vm.nowhere.objectsDropped11.contains("egg") || vm.nowhere.objectsDropped12.contains("egg") ||
                    vm.nowhere.objectsDropped13.contains("egg") || vm.nowhere.objectsDropped21.contains("egg") ||
                    vm.nowhere.objectsDropped22.contains("egg") || vm.nowhere.objectsDropped23.contains("egg"))) {
                    vm.myself.inventoryPast.add("egg");
                    secondText.setText("As you hold it in your hand, you realize it is an egg. A pure white egg.\nProbably the last spark of newborn life anybody is going to see in this hell of a planet.");
                } else {
                    if (vm.myself.inventoryPast.contains("stone")) {
                        secondText.setText("You already took one.");
                    } else {
                        vm.myself.inventoryPast.add("stone");
                        secondText.setText("Taken.");
                    }
                }
            } else if (myObjSeven.contains("egg") && vm.nowhere.currentLocation == 21) {
                vm.myself.inventoryPast.add("egg");
                secondText.setText("Taken.");
            } else if (!vm.nowhere.objectsDropped11.isEmpty() || !vm.nowhere.objectsDropped12.isEmpty() ||
                    !vm.nowhere.objectsDropped13.isEmpty() || !vm.nowhere.objectsDropped21.isEmpty() ||
                    !vm.nowhere.objectsDropped22.isEmpty() || !vm.nowhere.objectsDropped23.isEmpty()) {
                int n;
                if (myObjSeven.contains("take") || myObjSeven.contains("pick") || myObjSeven.contains("grab")) {
                    n = 4;
                } else if (myObjSeven.contains("get")) {
                    n = 3;
                } else {
                    n = 0;
                }
                String s = myObjSeven.substring(n);
                s = s.replace(" the ","");
                s = s.replace(" a ","");
                s = s.replace(" an ","");
                s = s.trim();
                switch (vm.nowhere.currentLocation) {
                    case 11:
                        if (vm.nowhere.objectsDropped11.contains(s)) {
                            vm.nowhere.objectsDropped11.remove(s);
                            vm.myself.inventoryPast.add(s);
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("You can't see any " + s + " here!");
                        }
                        break;
                    case 12:
                        if (vm.nowhere.objectsDropped12.contains(s)) {
                            vm.nowhere.objectsDropped12.remove(s);
                            vm.myself.inventoryPast.add(s);
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("You can't see any " + s + " here!");
                        }
                        break;
                    case 13:
                        if (vm.nowhere.objectsDropped13.contains(s)) {
                            vm.nowhere.objectsDropped13.remove(s);
                            vm.myself.inventoryPast.add(s);
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("You can't see any " + s + " here!");
                        }
                        break;
                    case 21:
                        if (vm.nowhere.objectsDropped21.contains(s)) {
                            vm.nowhere.objectsDropped21.remove(s);
                            vm.myself.inventoryPast.add(s);
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("You can't see any " + s + " here!");
                        }
                        break;
                    case 22:
                        if (vm.nowhere.objectsDropped22.contains(s)) {
                            vm.nowhere.objectsDropped22.remove(s);
                            vm.myself.inventoryPast.add(s);
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("You can't see any " + s + " here!");
                        }
                        break;
                    case 23:
                        if (vm.nowhere.objectsDropped23.contains(s)) {
                            vm.nowhere.objectsDropped23.remove(s);
                            vm.myself.inventoryPast.add(s);
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("You can't see any " + s + " here!");
                        }
                        break;
                }
            } else {
                secondText.setText("This thing cannot be taken or it is not in the place.");
            }
            vm.linearLayout.addView(secondText);
        }
        // DROP VERB
        else if (myObjSeven.matches("drop") || myObjSeven.contains("get rid of")) {
            secondText.setText("Just say: Drop (and the object you want to drop).");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("drop")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            int n = 4;
            String s = myObjSeven.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (vm.myself.inventoryPast.contains(s)) {
                vm.myself.inventoryPast.remove(s);
                switch (vm.nowhere.currentLocation) {
                    case 11:
                        vm.nowhere.objectsDropped11.add(s);
                        break;
                    case 12:
                        vm.nowhere.objectsDropped12.add(s);
                        break;
                    case 13:
                        vm.nowhere.objectsDropped13.add(s);
                        break;
                    case 21:
                        vm.nowhere.objectsDropped21.add(s);
                        break;
                    case 22:
                        vm.nowhere.objectsDropped22.add(s);
                        break;
                    case 23:
                        vm.nowhere.objectsDropped23.add(s);
                        break;
                }
                secondText.setText("Dropped.");
            } else {
                secondText.setText("You're not holding the " + s + ".");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("put") || myObjSeven.contains("place")) {  // PUT VERB
            secondText.setText("There is no container here where you can put an object in.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("help")) {  // HELP VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("You don't need help.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("help")) {
            secondText.setText("Just say HELP.");
            vm.linearLayout.addView(secondText);
        }
        // FOLLOW VERB
        else if (myObjSeven.contains("follow") || myObjSeven.contains("chase") || myObjSeven.contains("find") || myObjSeven.contains("search")) {
            secondText.setText("There is no one here you can follow.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("drink")) {  // DRINK VERB
            secondText.setText("You wish you had water.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("eat")) {  // EAT VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("eat")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSeven.contains("egg") && (vm.myself.inventoryPast.contains("egg") ||
                (vm.nowhere.currentLocation == 11 && vm.nowhere.objectsDropped11.contains("egg")) ||
                (vm.nowhere.currentLocation == 12 && vm.nowhere.objectsDropped12.contains("egg")) ||
                (vm.nowhere.currentLocation == 13 && vm.nowhere.objectsDropped13.contains("egg")) ||
                (vm.nowhere.currentLocation == 21 && vm.nowhere.objectsDropped21.contains("egg")) ||
                (vm.nowhere.currentLocation == 22 && vm.nowhere.objectsDropped22.contains("egg")) ||
                (vm.nowhere.currentLocation == 23 && vm.nowhere.objectsDropped23.contains("egg")))) {
                vm.nowhere.isEggBroken = true;
                if (vm.myself.inventoryPast.contains("egg")) {vm.myself.inventoryPast.remove("egg");}
                else if (vm.nowhere.objectsDropped11.contains("egg")) {vm.nowhere.objectsDropped11.remove("egg");}
                else if (vm.nowhere.objectsDropped12.contains("egg")) {vm.nowhere.objectsDropped12.remove("egg");}
                else if (vm.nowhere.objectsDropped13.contains("egg")) {vm.nowhere.objectsDropped13.remove("egg");}
                else if (vm.nowhere.objectsDropped21.contains("egg")) {vm.nowhere.objectsDropped21.remove("egg");}
                else if (vm.nowhere.objectsDropped22.contains("egg")) {vm.nowhere.objectsDropped22.remove("egg");}
                else vm.nowhere.objectsDropped23.remove("egg");
                secondText.setText("Before you eat the egg, it slips through your fingers and breaks on the ground.\nIf there was hope to see another species in B903, now there's none.");
            } else if (myObjSeven.contains("arm") || myObjSeven.contains("hand")) {
                secondText.setText("What would you do with just one hand?");
            } else if (myObjSeven.contains("house")) {
                secondText.setText("Stop. What if there's someone inside?");
            } else {
                secondText.setText("This thing cannot be eaten or it is not in the place.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("write")) {  // WRITE VERB
            secondText.setText("You cannot write new notes.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("read")) {  // READ VERB
            secondText.setText("You can't see any note here!");
            vm.linearLayout.addView(secondText);
        }
        // BREAK VERB
        else if (myObjSeven.matches("break") || myObjSeven.matches("hit") || myObjSeven.matches("attack") || myObjSeven.matches("punch") || myObjSeven.matches("fight") || myObjSeven.matches("kick")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("break") || myObjSeven.contains("hit") || myObjSeven.contains("attack") || myObjSeven.contains("punch") || myObjSeven.contains("fight") || myObjSeven.contains("kick")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSeven.contains("house")) {
                if (vm.nowhere.currentLocation == 12) {
                    secondText.setText("As you punch the house, you hear the wood cracking a little.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
            } else if (myObjSeven.contains("door")) {
                if (vm.nowhere.currentLocation == 12) {
                    secondText.setText("You see the dust of the door falling off as you hit the door.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
            } else if (myObjSeven.contains("curtain")) {
                if (vm.nowhere.currentLocation == 12) {
                    secondText.setText("The cloth is so fine that it breaks apart when you touch it.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
            } else if (myObjSeven.contains("window")) {
                if (vm.nowhere.currentLocation == 12) {
                    secondText.setText("The windows are already half-broken.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
            } else if (myObjSeven.contains("stone")) {
                secondText.setText("It is just a stone. Why would you hit a stone?");
            } else if (myObjSeven.contains("egg") && (vm.myself.inventoryPast.contains("egg") ||
                (vm.nowhere.currentLocation == 11 && vm.nowhere.objectsDropped11.contains("egg")) ||
                (vm.nowhere.currentLocation == 12 && vm.nowhere.objectsDropped12.contains("egg")) ||
                (vm.nowhere.currentLocation == 13 && vm.nowhere.objectsDropped13.contains("egg")) ||
                (vm.nowhere.currentLocation == 21 && vm.nowhere.objectsDropped21.contains("egg")) ||
                (vm.nowhere.currentLocation == 22 && vm.nowhere.objectsDropped22.contains("egg")) ||
                (vm.nowhere.currentLocation == 23 && vm.nowhere.objectsDropped23.contains("egg")))) {
                vm.nowhere.isEggBroken = true;
                if (vm.myself.inventoryPast.contains("egg")) {vm.myself.inventoryPast.remove("egg");}
                else if (vm.nowhere.objectsDropped11.contains("egg")) {vm.nowhere.objectsDropped11.remove("egg");}
                else if (vm.nowhere.objectsDropped12.contains("egg")) {vm.nowhere.objectsDropped12.remove("egg");}
                else if (vm.nowhere.objectsDropped13.contains("egg")) {vm.nowhere.objectsDropped13.remove("egg");}
                else if (vm.nowhere.objectsDropped21.contains("egg")) {vm.nowhere.objectsDropped21.remove("egg");}
                else if (vm.nowhere.objectsDropped22.contains("egg")) {vm.nowhere.objectsDropped22.remove("egg");}
                else vm.nowhere.objectsDropped23.remove("egg");
                secondText.setText("When it crashes on the ground... nothing happens.\nIf there was hope to see another species in B903, now there's none.");
            } else {
                secondText.setText("This thing cannot be hit or it is not in the place.");
            }
            vm.linearLayout.addView(secondText);
        }
        // CURTAIN ACTIONS
        else if ((((myObjSeven.contains("draw") || myObjSeven.contains("pull")) && (myObjSeven.contains("back") || myObjSeven.contains("aside"))) || myObjSeven.contains("move")) && myObjSeven.contains("curtain")) {
            if (vm.nowhere.currentLocation == 12) {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                secondText.setText("You can hardly see anything. It is really dark inside.");
            } else {
                secondText.setText("You have wandered off the house. You better get closer to do that.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("curtain")) {
            if (vm.nowhere.currentLocation == 12) {
                secondText.setText("They are just some dark curtains.");
            } else {
                secondText.setText("You are kind of far from the house.");
            }
            vm.linearLayout.addView(secondText);
        } else if ((myObjSeven.contains("look") && myObjSeven.contains("around")) || myObjSeven.matches("l") || myObjSeven.matches("look")) {
            String s = "";
            switch (vm.nowhere.currentLocation) {
                case 11:
                    s = "Nowhere\nHeading northeast you still see the house.";
                    if (!vm.nowhere.objectsDropped11.isEmpty()) {
                        int n = vm.nowhere.objectsDropped11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped11.get(i) + " here.";
                        }
                    }
                    break;
                case 12:
                    s = "In the middle of nowhere\nThe ground is covered with black stones. The sky is still orange.";
                    if (vm.nowhere.isHouseDoorOpen) {
                        s = s + "\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = s + "\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!vm.nowhere.objectsDropped12.isEmpty()) {
                        int n = vm.nowhere.objectsDropped12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped12.get(i) + " here.";
                        }
                    }
                    break;
                case 13:
                    s = "Nowhere\nHeading northwest you still see the house.";
                    if (!vm.nowhere.objectsDropped13.isEmpty()) {
                        int n = vm.nowhere.objectsDropped13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped13.get(i) + " here.";
                        }
                    }
                    break;
                case 21:
                    if (vm.myself.inventoryPast.contains("egg") || vm.nowhere.objectsDropped11.contains("egg") ||
                        vm.nowhere.objectsDropped12.contains("egg") || vm.nowhere.objectsDropped13.contains("egg") ||
                        vm.nowhere.objectsDropped21.contains("egg") || vm.nowhere.objectsDropped22.contains("egg") ||
                        vm.nowhere.objectsDropped23.contains("egg") || vm.nowhere.isEggBroken) {
                        s = "Nowhere\nThe house is northeast.";
                    } else {
                        s = "Nowhere\nThe house is northeast.\nHere you see a white stone that calls your attention.";
                    }
                    if (!vm.nowhere.objectsDropped21.isEmpty()) {
                        int n = vm.nowhere.objectsDropped21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped21.get(i) + " here.";
                        }
                    }
                    break;
                case 22:
                    s = "Nowhere\nHeading north you still see the house.";
                    if (!vm.nowhere.objectsDropped22.isEmpty()) {
                        int n = vm.nowhere.objectsDropped22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped22.get(i) + " here.";
                        }
                    }
                    break;
                case 23:
                    s = "Nowhere\nThe house is northwest.";
                    if (!vm.nowhere.objectsDropped23.isEmpty()) {
                        int n = vm.nowhere.objectsDropped23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + vm.nowhere.objectsDropped23.get(i) + " here.";
                        }
                    }
                    break;
            }
            secondText.setText(s);
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("i") || myObjSeven.matches("inventory")) {
            String inventory = "You have:";
            if (!vm.myself.inventoryPast.isEmpty()) {
                int n = vm.myself.inventoryPast.size();
                for (int i=0; i<n; i++) {
                    inventory = inventory + "\n    " + vm.myself.inventoryPast.get(i) + ".";
                }
            } else {
                inventory = "You have nothing.";
            }
            secondText.setText(inventory);
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("sleep")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Even the sky is still orange.\nYou just feel thirstier.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("check") && myObjSeven.contains("out")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("check") || myObjSeven.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("jump") || myObjSeven.contains("climb") || myObjSeven.contains("turn") || myObjSeven.contains("shut") || myObjSeven.contains("look") || myObjSeven.contains("see") || myObjSeven.contains("watch") || myObjSeven.contains("play") || myObjSeven.contains("run") || myObjSeven.contains("walk") || myObjSeven.contains("move") || myObjSeven.contains("give") || myObjSeven.contains("offer")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Look around you.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("smell")) {
            secondText.setText("It does not smell as bad as the part of the planet where your house is.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("listen")) {
            secondText.setText("You can barely hear someone crying inside the house.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("wait")) {
            secondText.setText("Time passes...");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.contains("what") && myObjSeven.contains("time") && myObjSeven.contains("is") || myObjSeven.matches("time") || myObjSeven.matches("the time")) {
            secondText.setText("It is sunset.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSeven.matches("diagnostic") || myObjSeven.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            vm.linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }

}
