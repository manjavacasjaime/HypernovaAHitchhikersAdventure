package hypernova.hithchhiker.guide.galaxy.levels;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveTwo extends AppCompatActivity {
    ValueManager vm;

    public ObjectiveTwo(ValueManager valManager) {
        vm = valManager;
    }

    public String checkObjAnswer(String myObjTwo, TextView secondText) {
        if ((vm.score - vm.myBasement.scoreWhenStopPlaying >= 8) && !vm.myBasement.isComputerOff) {
            vm.myLocation.setText("Dead");
            secondText.setText("While you were trying to figure out how to get out of the basement, you started to feel sick as you have never been before. This is due to the electromagnetic waves that computers emit nowadays to avoid players being AFK. Not really a great system, but it works.\n\nTo sum up, you died. Remember to turn off your computer next time you are not playing."
                    + vm.dieOptions);
            vm.linearLayout.addView(secondText);
            vm.currentObjective = 0;
        }
        // DIRECTIONS GO VERB
        else if (myObjTwo.matches("north") || myObjTwo.matches("n") || myObjTwo.matches("go north") || myObjTwo.matches("go n") || myObjTwo.matches("go straight on") || myObjTwo.matches("east") || myObjTwo.matches("e") || myObjTwo.matches("go east") || myObjTwo.matches("go e") || myObjTwo.matches("go right") || myObjTwo.matches("west") || myObjTwo.matches("w") || myObjTwo.matches("go west") || myObjTwo.matches("go w") || myObjTwo.matches("go left") || myObjTwo.matches("south") || myObjTwo.matches("s") || myObjTwo.matches("go south") || myObjTwo.matches("go s") || myObjTwo.matches("go backwards") || myObjTwo.matches("northeast") || myObjTwo.matches("ne") || myObjTwo.matches("go northeast") || myObjTwo.matches("go ne") || myObjTwo.matches("northwest") || myObjTwo.matches("nw") || myObjTwo.matches("go northwest") || myObjTwo.matches("go nw") || myObjTwo.matches("southeast") || myObjTwo.matches("se") || myObjTwo.matches("go southeast") || myObjTwo.matches("go se") || myObjTwo.matches("southwest") || myObjTwo.matches("sw") || myObjTwo.matches("go southwest") || myObjTwo.matches("go sw") || myObjTwo.matches("down") || myObjTwo.matches("d") || myObjTwo.matches("go down") || myObjTwo.matches("downstairs") || myObjTwo.matches("go downstairs") || myObjTwo.matches("go d")) {
            secondText.setText("You can't go that way.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.matches("up") || myObjTwo.matches("u") || myObjTwo.matches("go up") || myObjTwo.matches("upstairs") || myObjTwo.matches("go upstairs") || myObjTwo.matches("go u") || myObjTwo.matches("exit") || myObjTwo.matches("leave") || ((myObjTwo.contains("go") || myObjTwo.contains("get")) && myObjTwo.contains("out")) || (myObjTwo.contains("exit") && myObjTwo.contains("room"))) {
            if (!vm.myself.isStanding) {
                secondText.setText("You'll have to stand up first to go there.");
            } else if (!vm.myBasement.isComputerOff) {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                secondText.setText("You almost get out of the basement, but the dizziness has increased.\nOne of the things you have in the room is making you feel sick.");
            } else {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                vm.myLocation.setText("Hall");
                secondText.setText("Good job. Keep it like that.\n\nYour house hall\nThe basement door has closed behind you.\nHere you see a window and a glass of water.\nSomeone is knocking on your house door.");
                vm.ludlow.currentLocation = "house";
                vm.currentObjective = 3;
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("speak") || myObjTwo.contains("talk") || myObjTwo.contains("ask")) {  // SPEAK VERB
            secondText.setText("There is no one here you can speak with.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.matches("stop")) {  // STOP VERB
            secondText.setText("This verb needs to be used with the action you wanna stop doing.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("stop")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjTwo.contains("voices")) {
                secondText.setText("You are not hearing voices... Not yet.");
            } else if (myObjTwo.contains("music") || myObjTwo.contains("song") || myObjTwo.contains("disc") || myObjTwo.contains("vinyl")) {
                secondText.setText("Take care of other stuff.");
            } else if (myObjTwo.contains("play") || myObjTwo.contains("game")) {
                secondText.setText("You already stopped playing.");
            } else {
                if (vm.myBasement.isComputerOff) {
                    secondText.setText("This action cannot be stopped or you're not doing it.");
                } else {
                    secondText.setText("One of your things is making you feel sick. Take care of that.");
                }
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("play") && myObjTwo.contains("game")) {  // PLAY VERB, added just in case
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("... You just stopped doing that.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("window")) {  // WINDOW ACTIONS, added just in case
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("You can't see any window here!");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.matches("close")) {  // CLOSE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("close")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjTwo.contains("eye")) {
                if (vm.myBasement.isComputerOff) {
                    secondText.setText("This is not really useful right now.");
                } else {
                    secondText.setText("This is not really useful right now. It feels like the room is spinning.");
                }
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.matches("open")) {  // OPEN VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("open")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjTwo.contains("eye")) {
                if (vm.myBasement.isComputerOff) {
                    secondText.setText("You already see.");
                } else {
                    secondText.setText("You already see and it feels like the room is spinning.");
                }
            } else if (myObjTwo.contains("door")) {
                if (!vm.myself.isStanding) {
                    secondText.setText("You'll have to stand up first.");
                } else if (!vm.myBasement.isComputerOff) {
                    secondText.setText("You can't because the headache is killing you and you feel too weak.\nOne of the things you have in the room is making you feel sick.");
                } else {
                    secondText.setText("You must just say the direction you want to go. (NORTH, UP, etc)");
                }
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
            }
            vm.linearLayout.addView(secondText);
        }
        // TURN OFF VERB
        else if (myObjTwo.matches("turn off") || myObjTwo.matches("turn it off") || myObjTwo.matches("shut down") || myObjTwo.matches("shut it down")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjTwo.contains("turn") && myObjTwo.contains("off")) || (myObjTwo.contains("shut") && myObjTwo.contains("down"))) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjTwo.contains("computer") || myObjTwo.contains("console") || myObjTwo.contains("game")) {
                if (!vm.myBasement.isComputerOff) {
                    vm.myBasement.isComputerOff = true;
                    secondText.setText("Once it is off, you don't feel dizzy anymore. You don't know it, but you could have just died.");
                } else {
                    secondText.setText("You already turned it off.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjTwo.contains("tv") || myObjTwo.contains("television")) {
                if (vm.myBasement.isComputerOff) {
                    secondText.setText("The TV is off now.");
                } else {
                    secondText.setText("The game console is still on.");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be turned off or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjTwo.matches("turn on") || myObjTwo.matches("turn it on")) {  // TURN ON VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("turn") && myObjTwo.contains("on")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjTwo.contains("computer") || myObjTwo.contains("console") || myObjTwo.contains("game")) {
                if (!vm.myBasement.isComputerOff) {
                    secondText.setText("It is on and it's giving you a headache.");
                } else {
                    secondText.setText("You have just turned that off.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjTwo.contains("vinyl") || myObjTwo.contains("player") || myObjTwo.contains("cd") || myObjTwo.contains("disc") || myObjTwo.contains("music")) {
                if (!vm.myself.isStanding) {
                    secondText.setText("You'll have to stand up first.");
                } else {
                    secondText.setTypeface(secondText.getTypeface(), Typeface.BOLD_ITALIC);
                    secondText.setText("I smoke on the mic like Smokin' Joe Frazier. The hell-raiser...");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be turned on or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // MUSIC VERBS, added just in case
        else if ((myObjTwo.contains("play") || myObjTwo.contains("put") || myObjTwo.contains("listen")) && (myObjTwo.contains("vinyl") || myObjTwo.contains("player") || myObjTwo.contains("cd") || myObjTwo.contains("disc") || myObjTwo.contains("music"))) {
            if (!vm.myself.isStanding) {
                secondText.setText("You'll have to stand up first.");
                vm.linearLayout.addView(secondText);
            } else {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                secondText.setTypeface(secondText.getTypeface(), Typeface.BOLD_ITALIC);
                secondText.setText("I smoke on the mic like Smokin' Joe Frazier. The hell-raiser...");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjTwo.contains("stand") || myObjTwo.contains("get up")) {  // STAND VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.myBasement.isComputerOff || vm.myself.isStanding) {
                vm.myself.isStanding = true;
                secondText.setText("You are standing now.");
                vm.linearLayout.addView(secondText);
            } else {
                vm.myself.isStanding = true;
                secondText.setText("It feels like a kick in the stomach, but you do it.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjTwo.contains("sit down") || myObjTwo.matches("sit") || myObjTwo.contains("lie")) {  // LIE VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.myself.isStanding) {
                vm.myself.isStanding = false;
                if (vm.myBasement.isComputerOff) {
                    secondText.setText("Now you're on the floor again.");
                } else {
                    secondText.setText("You're on the floor again. The ceiling is spinning.");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("You are already on the floor.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjTwo.matches("examine")) {  // EXAMINE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("examine")) {
            if (myObjTwo.contains("vinyl") || myObjTwo.contains("player") || myObjTwo.contains("cd") || myObjTwo.contains("disc")) {
                if (!vm.myself.isStanding) {
                    secondText.setText("You'll have to stand up first.");
                } else if (vm.myBasement.isComputerOff) {
                    secondText.setText("It says 'ENTER THE WU-TANG'.");
                } else {
                    secondText.setText("It says 'ENTER THE WU-TANG'.\nThe dizziness is still increasing.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjTwo.contains("blanket")) {
                if (!vm.myself.isStanding) {
                    secondText.setText("You'll have to stand up first.");
                } else {
                    secondText.setText("It is just a blanket.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjTwo.contains("pencil")) {
                if (!vm.myself.isStanding) {
                    secondText.setText("You'll have to stand up first.");
                } else {
                    secondText.setText("It is just a pencil.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjTwo.contains("furniture")) {
                if (!vm.myself.isStanding) {
                    secondText.setText("You'll have to stand up first.");
                } else {
                    secondText.setText("Nothing interesting in it.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjTwo.contains("console") || myObjTwo.contains("computer")) {
                if (vm.myBasement.isComputerOff) {
                    secondText.setText("You can recognize that it is your classic game console.");
                } else {
                    secondText.setText("You can recognize that it is your classic game console and... Ouch! the dizziness gets worse when you look at it.");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be examined or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // TAKE GET VERB
        else if (myObjTwo.matches("take") || myObjTwo.matches("get") || myObjTwo.matches("pick") || myObjTwo.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("take") || myObjTwo.contains("get") || myObjTwo.contains("pick") || myObjTwo.contains("grab")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if ((myObjTwo.contains("cd") || myObjTwo.contains("disc")) && !vm.myBasement.objectsDropped.contains("cd")) {
                if (!vm.myself.isStanding) {
                    secondText.setText("You'll have to stand up first.");
                } else if (vm.myBasement.isComputerOff) {
                    if (vm.myself.inventory.contains("cd")) {
                        secondText.setText("You already took it.");
                    } else {
                        vm.myself.inventory.add("cd");
                        secondText.setText("Taken.");
                    }
                } else {
                    if (vm.myself.inventory.contains("cd")) {
                        secondText.setText("You already took it.");
                    } else {
                        vm.myself.inventory.add("cd");
                        secondText.setText("It is even difficult to move... But you manage to get it.");
                    }
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjTwo.contains("blanket") && !vm.myBasement.objectsDropped.contains("blanket")) {
                if (!vm.myself.isStanding) {
                    secondText.setText("You'll have to stand up first.");
                } else if (vm.myBasement.isComputerOff) {
                    if (vm.myself.inventory.contains("blanket")) {
                        secondText.setText("You already took it.");
                    } else {
                        vm.myself.inventory.add("blanket");
                        secondText.setText("Taken.");
                    }
                } else {
                    if (vm.myself.inventory.contains("blanket")) {
                        secondText.setText("You already took it.");
                    } else {
                        vm.myself.inventory.add("blanket");
                        secondText.setText("The dizziness doesn't make it easy... But you manage to get it.");
                    }
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjTwo.contains("pencil") && !vm.myBasement.objectsDropped.contains("pencil")) {
                if (!vm.myself.isStanding) {
                    secondText.setText("You'll have to stand up first.");
                } else if (vm.myBasement.isComputerOff) {
                    if (vm.myself.inventory.contains("pencil")) {
                        secondText.setText("You already took it.");
                    } else {
                        vm.myself.inventory.add("pencil");
                        secondText.setText("Taken.");
                    }
                } else {
                    if (vm.myself.inventory.contains("pencil")) {
                        secondText.setText("You already took it.");
                    } else {
                        vm.myself.inventory.add("pencil");
                        secondText.setText("Your headache increases... But you manage to get it.");
                    }
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjTwo.contains("console") || myObjTwo.contains("computer")) {
                secondText.setText("You cannot take that.");
                vm.linearLayout.addView(secondText);
            } else if (!vm.myBasement.objectsDropped.isEmpty()) {
                int n;
                if (myObjTwo.contains("take") || myObjTwo.contains("pick") || myObjTwo.contains("grab")) {
                    n = 4;
                } else if (myObjTwo.contains("get")) {
                    n = 3;
                } else {
                    n = 0;
                }
                String s = myObjTwo.substring(n);
                s = s.replace(" the ","");
                s = s.replace(" a ","");
                s = s.replace(" an ","");
                s = s.trim();
                if (vm.myBasement.objectsDropped.contains(s)) {
                    vm.myBasement.objectsDropped.remove(s);
                    vm.myself.inventory.add(s);
                    if (vm.myBasement.isComputerOff) {
                        secondText.setText("Taken.");
                    } else {
                        secondText.setText("Taken... Ufff... But the headache hasn't stopped.");
                    }
                } else {
                    if (vm.myBasement.isComputerOff) {
                        secondText.setText("You can't see any " + s + " here!");
                    } else {
                        secondText.setText("You can't see any " + s + " here... and the headache is still increasing.");
                    }
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be taken or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjTwo.matches("drop") || myObjTwo.contains("get rid of")) {  // DROP VERB
            secondText.setText("Just say: Drop (and the object you want to drop).");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("drop")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            int n = 4;
            String s = myObjTwo.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (vm.myself.inventory.contains(s)) {
                vm.myself.inventory.remove(s);
                vm.myBasement.objectsDropped.add(s);
                secondText.setText("Dropped.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("You're not holding the " + s + ".");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjTwo.matches("help")) {  // HELP VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.myBasement.isComputerOff) {
                secondText.setText("You may wanna GO UPSTAIRS now that you can.");
            } else {
                secondText.setText("Don't ask for help.\n\nType COMMANDS to get a list of some actions you can do.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("help")) {
            secondText.setText("Just say HELP.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("drink")) {  // DRINK VERB
            secondText.setText("There is nothing you can drink here.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjTwo.contains("look") && myObjTwo.contains("around")) || myObjTwo.matches("l") || myObjTwo.matches("look")) {
            String intro = "Basement";
            if (!vm.myself.isStanding) {
                intro = intro + ". You are sitting on the floor.";
            }
            intro = intro + "\nThere is a piece of furniture with a vinyl player and some discs on it. Your game console is next to you. There's a door leading UP.";
            if (!vm.myself.inventory.contains("blanket") && !vm.myBasement.objectsDropped.contains("blanket")) {
                intro = intro + "\nThere is a blanket here.";
            }
            if (!vm.myself.inventory.contains("pencil") && !vm.myBasement.objectsDropped.contains("pencil")) {
                intro = intro + "\nThere is a pencil here.";
            }
            if (!vm.myBasement.objectsDropped.isEmpty()) {
                int n = vm.myBasement.objectsDropped.size();
                for (int i=0; i<n; i++) {
                    intro = intro + "\nThere's the " + vm.myBasement.objectsDropped.get(i) + " here.";
                }
            }
            secondText.setText(intro);
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.matches("i") || myObjTwo.matches("inventory")) {
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
        } else if (myObjTwo.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.matches("sleep")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Amazing strategy.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjTwo.contains("check") && myObjTwo.contains("out")) || myObjTwo.contains("find") || myObjTwo.contains("search")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("check") || myObjTwo.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("jump") || myObjTwo.contains("climb") || myObjTwo.contains("turn") || myObjTwo.contains("shut") || myObjTwo.contains("look") || myObjTwo.contains("see") || myObjTwo.contains("watch") || myObjTwo.contains("play") || myObjTwo.contains("run") || myObjTwo.contains("walk") || myObjTwo.contains("eat") || myObjTwo.contains("move") || myObjTwo.contains("put") || myObjTwo.contains("give") || myObjTwo.contains("offer") || myObjTwo.contains("read") || myObjTwo.contains("write") || myObjTwo.contains("enter") || myObjTwo.contains("follow") || myObjTwo.contains("fix") || myObjTwo.contains("repair") || myObjTwo.contains("attack") || myObjTwo.contains("hit") || myObjTwo.contains("break") || myObjTwo.contains("fight") || myObjTwo.contains("kick")) {
            if (!vm.myself.isStanding) {
                secondText.setText("You're still sitting, remember?");
                vm.linearLayout.addView(secondText);
            } else {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                secondText.setText("Look around you.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjTwo.contains("smell")) {
            secondText.setText("It smells just like a basement.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("listen")) {
            if (!vm.myBasement.isComputerOff) {
                secondText.setText("The silence has never felt better.");
            } else {
                secondText.setText("The game's music results to be annoying now.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("wait")) {
            secondText.setText("Time passes...");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.contains("what") && myObjTwo.contains("time") && myObjTwo.contains("is") || myObjTwo.matches("time") || myObjTwo.matches("the time")) {
            secondText.setText("You cannot tell that from the basement.");
            vm.linearLayout.addView(secondText);
        } else if (myObjTwo.matches("diagnostic") || myObjTwo.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            vm.linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }
}
