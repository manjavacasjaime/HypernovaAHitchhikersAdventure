package hypernova.hithchhiker.guide.galaxy.levels;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveSix extends AppCompatActivity {
    ValueManager vm;
    int stoppetonConversationStatus = 0;
    boolean isFirstTimeBackInHall = true;

    public ObjectiveSix(ValueManager valManager) {
        vm = valManager;
    }

    public String checkObjAnswer(String myObjSix, TextView secondText) {
        if (vm.lwLibrary.isReadingPinboard) {  // READING NOTES
            vm.lwLibrary.displayPinboardNotes(myObjSix, vm.linearLayout, secondText);
        } else if (stoppetonConversationStatus == 1) {  // STOP TIME DECISION
            switch (myObjSix) {
                case "1":
                    vm.myLocation.setText("Unknown");
                    secondText.setText("Stoppeton remained being a popular hero.\nIt didn't take long till war lords from other Bubbles wanted to hire him as a mercenary in order to infiltrate the enemy bases using his powers.\nIt is said that Stoppeton got involved in 9 out of the 12 wars that were happening in that moment at the universe.\nTired of this bountyhaunter life, he stopped the time once again to steal the treasures from the war lords and disappear.\nNobody knows where he is or what he did the rest of his life. Today we remember " + vm.myself.name + " " + vm.myself.surname + " as the human he was.\nHopefully, we will finish the adventure whenever we meet again.\nBest regards.\nFred."
                            + vm.dieOptions);
                    vm.linearLayout.addView(secondText);
                    vm.currentObjective = 0;
                    break;
                case "2":
                    stoppetonConversationStatus = 2;
                    secondText.setText("Congrats. You have chosen wisely to continue the adventure.\nWho knows what could you have become, but now Abigail and Fred need you to open the door in room D.");
                    vm.linearLayout.addView(secondText);
                    break;
                case "save":
                    vm.save();
                    secondText.setText("GAME SAVED.\nYou can keep playing if you want to.");
                    vm.linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("Choose one of the options (1 or 2).");
                    vm.linearLayout.addView(secondText);
                    break;
            }
        } else if (vm.fred.conversationStatus == 1) {  // CONVERSATIONS
            switch (myObjSix) {
                case "1":
                    secondText.setText("Are you kidding me?\nWe may have a library, a city hall and all that. Nevertheless, as far as I know, in B903 you either get killed by one of the Federation Agents who inspect the Bubble or buried alive during a sand storm.\nThere must be something better for us.");
                    vm.linearLayout.addView(secondText);
                    break;
                case "2":
                    secondText.setText("It may be written at the pinboard which is at the Library's hall.");
                    vm.linearLayout.addView(secondText);
                    break;
                case "3":
                    secondText.setText("I don't know her a lot. I think she hates the Federation as much as we want to get out of here.\nThat's something.");
                    vm.linearLayout.addView(secondText);
                    break;
                case "4":
                    vm.fred.conversationStatus = 0;
                    secondText.setText("Dialogue Fred finished.");
                    vm.linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("You just said: nwhuvweov.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'I don't know for sure if there's something better than B903 out there.'\n2. 'I have no idea who of the people in the paintings is this 'unfortunate one'.\n3. 'Do you think Abigail is trustworthy?'\n4. exit.");
                    vm.linearLayout.addView(secondText);
                    break;
            }
        } else if (vm.myself.kindOfPerson == 0) {  // YOUR KIND OF PERSON DECISION
            switch (myObjSix) {
                case "1":
                    vm.myself.kindOfPerson = 1;
                    secondText.setText("'The one that could kill you if she wants, dumbass. Agh. Nevermind.' Then she turns and stares at the paintings.");
                    vm.linearLayout.addView(secondText);
                    break;
                case "2":
                    vm.myself.kindOfPerson = 2;
                    secondText.setText("'Wait right there. I'm not asking you to tell me your life, Mr Niceguy. Duh. Just... open the door, can you?' Then she turns and stares at the paintings.");
                    vm.linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("Write the number of the option you want.\n1. (Distant) 'What kind of person asks that to a stranger?'\n2. (Affable) 'Well. I try to do my best when someone needs help. I'm not helping you for personal benefit, if that is why you asked.'");
                    vm.linearLayout.addView(secondText);
                    break;
            }
        } else if (vm.score - vm.lwLibrary.scoreWhenEnteringRoomD >= 3 && vm.lwLibrary.currentLocation.equals("room D") && vm.myself.kindOfPerson == -1) {
            vm.myself.kindOfPerson = 0;
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Abigail says 'Look boy. I don't know you, so I have to ask.'\n'What kind of person are you?'\n\n\nOptions:\n1. (Distant) 'What kind of person asks that to a stranger?'\n2. (Affable) 'Well. I try to do my best when someone needs help. I'm not helping you for personal benefit, if that is why you asked.'");
            vm.linearLayout.addView(secondText);
        }
        // DIRECTIONS GO VERB
        else if (myObjSix.matches("west") || myObjSix.matches("w") || myObjSix.matches("go west") || myObjSix.matches("go w") || myObjSix.matches("go left") || myObjSix.matches("northeast") || myObjSix.matches("ne") || myObjSix.matches("go northeast") || myObjSix.matches("go ne") || myObjSix.matches("northwest") || myObjSix.matches("nw") || myObjSix.matches("go northwest") || myObjSix.matches("go nw") || myObjSix.matches("southeast") || myObjSix.matches("se") || myObjSix.matches("go southeast") || myObjSix.matches("go se") || myObjSix.matches("southwest") || myObjSix.matches("sw") || myObjSix.matches("go southwest") || myObjSix.matches("go sw") || myObjSix.matches("up") || myObjSix.matches("u") || myObjSix.matches("go up") || myObjSix.matches("upstairs") || myObjSix.matches("go upstairs") || myObjSix.matches("go u") || myObjSix.matches("down") || myObjSix.matches("d") || myObjSix.matches("go down") || myObjSix.matches("downstairs") || myObjSix.matches("go downstairs") || myObjSix.matches("go d")) {
            secondText.setText("You can't go that way.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.matches("north") || myObjSix.matches("n") || myObjSix.matches("go north") || myObjSix.matches("go n") || myObjSix.matches("go straight on")) {
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                vm.lwLibrary.currentLocation = "room D";
                vm.myLocation.setText("Library, room D");
                secondText.setText("Room D\nEverything is still the same.\nHeading SOUTH is the Library's hall.\nAbigail and Fred are still here.");
                vm.linearLayout.addView(secondText);
            } else {
                if (vm.lwLibrary.hasChosenCorrectDoor) {
                    vm.score++;
                    vm.myMoves.setText("Moves: " + vm.score);
                    vm.myLocation.setText("Nowhere");
                    secondText.setText("\n.\n.\n.             ...you fade out\n.\n\n" + vm.myself.name + ".\nIt's October 26, 2008. You are in B903.\nIt's been 286 years, 11 months and 8 days since the last hitchhiker died.\n\nIn the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.\nThe house is black as well and all its curtains are drawn.");
                    vm.linearLayout.addView(secondText);
                    vm.currentObjective = 10;
                } else {
                    secondText.setText("You need to PUT the fuse IN one of the three containers placed under each painting.\nThere are some instructions that you can read next to the door.");
                    vm.linearLayout.addView(secondText);
                }
            }
        } else if (myObjSix.matches("east") || myObjSix.matches("e") || myObjSix.matches("go east") || myObjSix.matches("go e") || myObjSix.matches("go right")) {
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                secondText.setText("The office door is locked.");
            } else {
                secondText.setText("You can't go that way.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.matches("south") || myObjSix.matches("s") || myObjSix.matches("go south") || myObjSix.matches("go s") || myObjSix.matches("go backwards") || myObjSix.matches("exit") || myObjSix.matches("leave") || ((myObjSix.contains("go") || myObjSix.contains("get")) && myObjSix.contains("out")) || (myObjSix.contains("exit") && myObjSix.contains("room"))) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                secondText.setText("You are not leaving without Fred, are you?");
            } else {
                vm.lwLibrary.currentLocation = "hall";
                vm.myLocation.setText("Ludlow Library");
                if (isFirstTimeBackInHall) {
                    isFirstTimeBackInHall = false;
                    secondText.setText("Library\nAfter you enter the hall, you see that nobody is here...\nThe lights are still on. The books and chairs are placed like before. It is like everyone has vanished.\nThe silence makes you feel uneasy.");
                } else {
                    secondText.setText("Library\nNo one has been here. This silence still makes you feel uncomfortable.");
                }
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            vm.linearLayout.addView(secondText);
        }
        // SPEAK VERB
        else if (myObjSix.matches("speak") || myObjSix.matches("talk") || myObjSix.matches("ask")) {
            secondText.setText("This verb needs to be used with a name.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("speak") || myObjSix.contains("talk") || myObjSix.contains("ask")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                secondText.setText("There is no one in this room.");
                vm.linearLayout.addView(secondText);
            } else {
                if (myObjSix.contains("abigail")) {
                    secondText.setText("She says 'Have you already found out which one of them is?' and looks at the containers under the paintings.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("fred")) {
                    vm.fred.conversationStatus = 1;
                    secondText.setText("Dialogue Fred.\n\n1. 'I don't know for sure if there's something better than B903 out there.'\n2. 'I have no idea who of the people in the paintings is this 'unfortunate one'.\n3. 'Do you think Abigail is trustworthy?'\n4. exit.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("This person's name is incorrect or he/she is not in the place.");
                    vm.linearLayout.addView(secondText);
                }
            }
        } else if (myObjSix.contains("stop")) {  // STOP VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSix.contains("voices")) {
                secondText.setText("You are not hearing voices... Not yet.");
                vm.linearLayout.addView(secondText);
            } else if (myObjSix.contains("time")) {
                if (stoppetonConversationStatus == 0) {
                    stoppetonConversationStatus = 1;
                    secondText.setText("Oh my God. OH MY GOD! You have had powers after all this time.\nScrew the Travellers. Who needs to travel in space if you can travel in time. Now you have become... STOPPETON, THE TIME 'PAUSER'. Now choose:\n\n1. Live your life with this powers like a God.\n2. Ignore your unique skill and continue your journey.");
                } else {
                    secondText.setText("That's useless.");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("There's nothing you can stop here.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjSix.matches("close")) {  // CLOSE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("close")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSix.contains("door")) {
                if (vm.lwLibrary.hasChosenCorrectDoor && vm.lwLibrary.currentLocation.equals("room D")) {
                    secondText.setText("I don't think you are that strong.");
                } else {
                    secondText.setText("The door is already closed.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjSix.contains("eye")) {
                secondText.setText("This is not really useful right now.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjSix.matches("open")) {  // OPEN VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("open")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSix.contains("door")) {
                if (vm.lwLibrary.currentLocation.equals("hall")) {
                    secondText.setText("Locked.");
                } else {
                    if (vm.lwLibrary.hasChosenCorrectDoor) {
                        secondText.setText("You already opened it.");
                    } else {
                        secondText.setText("The door will open after you PUT the fuse IN one of the three containers placed under each painting.\nYou'll have to guess who is 'the unfortunate one'. But remember. The mistake is punished with death, so be sure about your decision.");
                    }
                }
                vm.linearLayout.addView(secondText);
            } else if ((myObjSix.contains("entry") || myObjSix.contains("library") || myObjSix.contains("entrance")) && myObjSix.contains("door")) {
                if (vm.lwLibrary.currentLocation.equals("hall")) {
                    secondText.setText("You are not leaving without Fred, are you?");
                } else {
                    secondText.setText("That's in the other room.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjSix.contains("eye")) {
                secondText.setText("Your eyes are wide open.");
                vm.linearLayout.addView(secondText);
            } else if (myObjSix.contains("window")) {
                if (vm.lwLibrary.currentLocation.equals("hall")) {
                    if (!vm.lwLibrary.hasBrokenWindow) {
                        secondText.setText("The wind feels fresh on your skin.");
                        vm.linearLayout.addView(secondText);
                    } else {
                        secondText.setText("You actually broke it, so...");
                        vm.linearLayout.addView(secondText);
                    }
                } else {
                    secondText.setText("You can't see any window here!");
                    vm.linearLayout.addView(secondText);
                }
            } else if (myObjSix.contains("book")) {
                secondText.setText("You will have to take a book first.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // TURN OFF VERB
        else if (myObjSix.matches("turn off") || myObjSix.matches("turn it off") || myObjSix.matches("shut down") || myObjSix.matches("shut it down")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjSix.contains("turn") && myObjSix.contains("off")) || (myObjSix.contains("shut") && myObjSix.contains("down"))) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSix.contains("alarm")) {
                secondText.setText("No alarm is sounding.");
                vm.linearLayout.addView(secondText);
            } else if (myObjSix.contains("music")) {
                secondText.setText("No music is being played.");
                vm.linearLayout.addView(secondText);
            } else if (myObjSix.contains("fuse")) {
                if (vm.myself.inventory.contains("fuse") || vm.lwLibrary.currentLocation.equals("room D")) {
                    secondText.setText("I'm not sure you have the electrical knowledge to do that.");
                } else {
                    secondText.setText("You are not holding the fuse or it is not here.");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be turned off or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // TURN ON VERB
        else if (myObjSix.contains("turn on") || myObjSix.contains("turn it on")) {
            secondText.setText("There's nothing you can turn on here.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("stand") || myObjSix.contains("get up")) {  // STAND VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("You are already standing.");
            vm.linearLayout.addView(secondText);
        }
        // LIE VERB
        else if (myObjSix.contains("sit down") || myObjSix.matches("sit") || myObjSix.contains("lie")) {
            secondText.setText("Abigail and Fred are waiting you to open the metal door.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.matches("examine")) {  // EXAMINE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("examine")) {
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                if (myObjSix.contains("window")) {
                    secondText.setText("Library views are no different from your house ones. As always, you see the same desert with the same pieces of metal scattered on the ground.\nAt least you can spot a part of the houses that were buried during last week's sand storm.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("book") || myObjSix.contains("shelve")) {
                    secondText.setText("Sully is the one who manages the borrowed books and now he is gone...");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("sully") || myObjSix.contains("librarian")) {
                    secondText.setText("He is not here.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("cabin")) {
                    secondText.setText("This thing is bulletproof. You couldn't get in there even if you want to.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("door")) {
                    secondText.setText("It is just a door.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("pinboard") || myObjSix.contains("note")) {
                    vm.lwLibrary.isReadingPinboard = true;
                    secondText.setText("Reading pinboard.\n\n1. Buzz Ruzzinsky's live in concert.\n2. Borrowed books.\n3. Ludlow Post 11 21 1959.\n4. The Message 06 04 1672.\n5. exit.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("This thing cannot be examined or it is not in the place.");
                    vm.linearLayout.addView(secondText);
                }
            } else {
                if (myObjSix.contains("painting")) {
                    secondText.setText("Mmh... The three of them are portraits that look pretty alike.\nUnder each one of them there is a container with a number (1, 2, 3).");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("instructions")) {
                    secondText.setText("Canterman Electronics.\nThe mechanics of this door are really simple. The door is connected with three containers (container 1, container 2 and container 3).\nYou have to PUT a fuse IN the right container in order to open the door.\nBeware of doing that with the wrong container.\n\nNote: Use PUT and IN to place an object in a container (e.g. 'put the fuse in 1' places the fuse in the container of the first painting).");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("fuse")) {
                    if (vm.myself.inventory.contains("fuse")) {
                        secondText.setText("It works.");
                    } else {
                        secondText.setText("It works. You can easily take it.");
                    }
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("door")) {
                    secondText.setText("The door looks sealed. The text 'the unfortunate one' seems to be written with fury.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("This thing cannot be examined or it is not in the place.");
                    vm.linearLayout.addView(secondText);
                }
            }
        }
        // TAKE GET VERB
        else if (myObjSix.matches("take") || myObjSix.matches("get") || myObjSix.matches("pick") || myObjSix.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("take") || myObjSix.contains("get") || myObjSix.contains("pick") || myObjSix.contains("grab")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                if ((myObjSix.contains("glass") || myObjSix.contains("crystal")) && vm.lwLibrary.hasBrokenWindow) {
                    if (vm.myself.inventory.contains("broken glass")) {
                        secondText.setText("You already took it.");
                    } else {
                        vm.myself.inventory.add("broken glass");
                        secondText.setText("Taken.");
                    }
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("book") || myObjSix.contains("shelve")) {
                    secondText.setText("Sully is the one who manages the borrowed books and now he is gone...");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("note")) {
                    secondText.setText("Notes cannot be taken, but you can read them.");
                    vm.linearLayout.addView(secondText);
                } else if (!vm.lwLibrary.objectsDroppedHall.isEmpty()) {
                    int n;
                    if (myObjSix.contains("take") || myObjSix.contains("pick") || myObjSix.contains("grab")) {
                        n = 4;
                    } else if (myObjSix.contains("get")) {
                        n = 3;
                    } else {
                        n = 0;
                    }
                    String s = myObjSix.substring(n);
                    s = s.replace(" the ", "");
                    s = s.replace(" a ", "");
                    s = s.replace(" an ", "");
                    s = s.trim();
                    if (vm.lwLibrary.objectsDroppedHall.contains(s)) {
                        vm.lwLibrary.objectsDroppedHall.remove(s);
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
            } else {
                if (myObjSix.contains("fuse")) {
                    if (vm.lwLibrary.objectsDroppedHall.contains("fuse")) {
                        secondText.setText("You can't see any fuse here!");
                    } else {
                        if (vm.myself.inventory.contains("fuse")) {
                            secondText.setText("You already took it.");
                        } else {
                            if (vm.lwLibrary.objectsDroppedRoomD.contains("fuse")) {
                                vm.lwLibrary.objectsDroppedRoomD.remove("fuse");
                            }
                            vm.myself.inventory.add("fuse");
                            secondText.setText("Taken.");
                        }
                    }
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("painting")) {
                    secondText.setText("Looks like the paintings are attached to the wall.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("instructions")) {
                    secondText.setText("Instructions cannot be taken, but you can read them.");
                    vm.linearLayout.addView(secondText);
                } else if (!vm.lwLibrary.objectsDroppedRoomD.isEmpty()) {
                    int n;
                    if (myObjSix.contains("take") || myObjSix.contains("pick") || myObjSix.contains("grab")) {
                        n = 4;
                    } else if (myObjSix.contains("get")) {
                        n = 3;
                    } else {
                        n = 0;
                    }
                    String s = myObjSix.substring(n);
                    s = s.replace(" the ", "");
                    s = s.replace(" a ", "");
                    s = s.replace(" an ", "");
                    s = s.trim();
                    if (vm.lwLibrary.objectsDroppedRoomD.contains(s)) {
                        vm.lwLibrary.objectsDroppedRoomD.remove(s);
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
            }
        }
        // DROP VERB
        else if (myObjSix.matches("drop") || myObjSix.contains("get rid of")) {
            secondText.setText("Just say: Drop (and the object you want to drop).");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("drop")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            int n = 4;
            String s = myObjSix.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (vm.myself.inventory.contains(s)) {
                vm.myself.inventory.remove(s);
                if (vm.lwLibrary.currentLocation.equals("hall")) {
                    vm.lwLibrary.objectsDroppedHall.add(s);
                } else {
                    vm.lwLibrary.objectsDroppedRoomD.add(s);
                }
                secondText.setText("Dropped.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("You're not holding the " + s + ".");
                vm.linearLayout.addView(secondText);
            }
        }
        // PUT VERB
        else if (myObjSix.matches("put") || myObjSix.matches("place")) {
            secondText.setText("This verb needs to be used with an object and a container.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("put") || myObjSix.contains("place")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSix.contains("fuse") && (myObjSix.contains("1") || myObjSix.contains("one") || myObjSix.contains("wallace") || myObjSix.contains("storm"))) {
                if (vm.lwLibrary.currentLocation.equals("room D")) {
                    if (!vm.myself.inventory.contains("fuse")) {
                        secondText.setText("The fuse is not in your inventory.");
                        vm.linearLayout.addView(secondText);
                    } else {
                        vm.myLocation.setText("Dead");
                        vm.myself.inventory.remove("fuse");
                        vm.lwLibrary.objectsDroppedRoomD.add("fuse");
                        secondText.setText("You placed the fuse in the container 1. You think Wallace Storm is 'the unfortunate one'.\n\nIt seems that the painting had a hole in his mouth where a shotgun appears and blows your head off.\n\nAs a result of the vulnerabilities that the living being's head has, there are Bubbles in the universe where your body can be modified to work without a head.\nUnfortunately, B903 is not one of those.\nYou died."
                                + vm.dieOptions);
                        vm.linearLayout.addView(secondText);
                        vm.currentObjective = 0;
                    }
                } else {
                    secondText.setText("You are not in the room where this container is.");
                    vm.linearLayout.addView(secondText);
                }
            } else if (myObjSix.contains("fuse") && (myObjSix.contains("2") || myObjSix.contains("two") || myObjSix.contains("catherine") || myObjSix.contains("barker"))) {
                if (vm.lwLibrary.currentLocation.equals("room D")) {
                    if (!vm.myself.inventory.contains("fuse")) {
                        secondText.setText("The fuse is not in your inventory.");
                        vm.linearLayout.addView(secondText);
                    } else {
                        vm.myLocation.setText("Dead");
                        vm.myself.inventory.remove("fuse");
                        vm.lwLibrary.objectsDroppedRoomD.add("fuse");
                        secondText.setText("You placed the fuse in the container 2. You think Catherine Barker is 'the unfortunate one'.\n\nIt seems that the painting had a hole in her mouth where a shotgun appears and blows your head off.\n\nAs a result of the vulnerabilities that the living being's head has, there are Bubbles in the universe where your body can be modified to work without a head.\nUnfortunately, B903 is not one of those.\nYou died."
                                + vm.dieOptions);
                        vm.linearLayout.addView(secondText);
                        vm.currentObjective = 0;
                    }
                } else {
                    secondText.setText("You are not in the room where this container is.");
                    vm.linearLayout.addView(secondText);
                }
            } else if (myObjSix.contains("fuse") && (myObjSix.contains("3") || myObjSix.contains("three") || myObjSix.contains("joseph") || myObjSix.contains("lovehart"))) {
                if (vm.lwLibrary.currentLocation.equals("room D")) {
                    if (!vm.myself.inventory.contains("fuse")) {
                        secondText.setText("The fuse is not in your inventory.");
                        vm.linearLayout.addView(secondText);
                    } else {
                        vm.lwLibrary.hasChosenCorrectDoor = true;
                        vm.myself.inventory.remove("fuse");
                        vm.lwLibrary.objectsDroppedRoomD.add("fuse");
                        secondText.setText("You placed the fuse in the container 3. You think Joseph Lovehart is 'the unfortunate one'.\n\nAfter a few seconds, the sealed door opens. Heading NORTH you can go to the next room.");
                        vm.linearLayout.addView(secondText);
                    }
                } else {
                    secondText.setText("You are not in the room where this container is.");
                    vm.linearLayout.addView(secondText);
                }
            } else {
                secondText.setText("The name of the object is wrong, the number/name of the container is wrong or this object cannot be used this way.");
                vm.linearLayout.addView(secondText);
            }
        }
        // HELP VERB
        else if (myObjSix.matches("help") && vm.score - vm.lwLibrary.scoreWhenEnteringRoomD >= 6) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("The name you are looking for is written in one of the notes which are at the hall.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.matches("help")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Try to do things by yourself first.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("help")) {
            secondText.setText("Just say HELP.");
            vm.linearLayout.addView(secondText);
        }
        // FOLLOW VERB
        else if (myObjSix.matches("follow") || myObjSix.matches("chase") || myObjSix.matches("find") || myObjSix.matches("search")) {
            secondText.setText("This verb needs to be used with a name.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("follow") || myObjSix.contains("chase") || myObjSix.contains("find") || myObjSix.contains("search")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSix.contains("abigail") || myObjSix.contains("fred")) {
                if (vm.lwLibrary.currentLocation.equals("hall")) {
                    vm.lwLibrary.currentLocation = "room D";
                    vm.myLocation.setText("Library, room D");
                    secondText.setText("Now you are in room D.");
                } else {
                    secondText.setText("You already are with them.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjSix.contains("sully") || myObjSix.contains("librarian")) {
                if (vm.lwLibrary.currentLocation.equals("hall")) {
                    secondText.setText("You don't know where he went... This is really weird.");
                } else {
                    secondText.setText("Sully was in the other room.");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This person's name is incorrect or he/she cannot be followed.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjSix.contains("drink")) {  // DRINK VERB
            secondText.setText("There is nothing you can drink here.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("write")) {  // WRITE VERB
            secondText.setText("You cannot write new notes.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.matches("read")) {  // READ VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("read")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjSix.contains("note") || myObjSix.contains("pinboard")) {
                if (vm.lwLibrary.currentLocation.equals("hall")) {
                    vm.lwLibrary.isReadingPinboard = true;
                    secondText.setText("Reading pinboard.\n\n1. Buzz Ruzzinsky's live in concert.\n2. Borrowed books.\n3. Ludlow Post 11 21 1959.\n4. The Message 06 04 1672.\n5. exit.");
                } else {
                    secondText.setText("You can't see any note here!");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjSix.contains("book")) {
                secondText.setText("You are not holding a book.");
                vm.linearLayout.addView(secondText);
            } else if (myObjSix.contains("instructions")) {
                if (vm.lwLibrary.currentLocation.equals("hall")) {
                    secondText.setText("You can't see any instructions here!");
                } else {
                    secondText.setText("Canterman Electronics.\nThe mechanics of this door are really simple. The door is connected with three containers (container 1, container 2 and container 3).\nYou have to PUT a fuse IN the right container in order to open the door.\nBeware of doing that with the wrong container.\n\nNote: Use PUT and IN to place an object in a container (e.g. 'put the fuse in 1' places the fuse in the container of the first painting).");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjSix.contains("door") && vm.lwLibrary.currentLocation.equals("room D")) {
                secondText.setText("'the unfortunate one'.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be read or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // BREAK VERB
        else if (myObjSix.matches("break") || myObjSix.matches("hit") || myObjSix.matches("attack") || myObjSix.matches("punch") || myObjSix.matches("fight") || myObjSix.matches("kick")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("break") || myObjSix.contains("hit") || myObjSix.contains("attack") || myObjSix.contains("punch") || myObjSix.contains("fight") || myObjSix.contains("kick")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                if (myObjSix.contains("people") || myObjSix.contains("person") || myObjSix.contains("sully") || myObjSix.contains("librarian")) {
                    secondText.setText("There's no one here you can attack.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("window")) {
                    if (!vm.lwLibrary.hasBrokenWindow) {
                        vm.lwLibrary.hasBrokenWindow = true;
                        secondText.setText("And now the window is broken. It's a mess.");
                        vm.linearLayout.addView(secondText);
                    } else {
                        secondText.setText("The window is already broken.");
                        vm.linearLayout.addView(secondText);
                    }
                } else if (myObjSix.contains("cabin") || myObjSix.contains("shelve") || myObjSix.contains("door")) {
                    secondText.setText("You are all alone and hitting stuff. You should be proud.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("This thing cannot be hit or it is not in the place.");
                    vm.linearLayout.addView(secondText);
                }
            } else {
                if (myObjSix.contains("abigail")) {
                    secondText.setText("She stops your hand.\n'Easy boy! Don't make me cut your hand off.'");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("fred") || myObjSix.contains("friend")) {
                    secondText.setText("After you slap Fred, Abigail looks at you with a smile.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("painting")) {
                    secondText.setText("Abigail: 'Hey Hey Hey!'\n'This paintings have been here for decades and one of them must be this 'unfortunate one'. Figure that out instead of acting like an ape.'");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("fuse")) {
                    secondText.setText("It sparks you.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjSix.contains("door")) {
                    secondText.setText("The sound of metal being hit is really cool, but useless.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("This thing cannot be hit or it is not in the place.");
                    vm.linearLayout.addView(secondText);
                }
            }
        }
        // WINDOW ACTIONS
        else if ((myObjSix.contains("fix") || myObjSix.contains("repair")) && myObjSix.contains("window")) {
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                secondText.setText("Not your business.");
            } else {
                secondText.setText("There is no window in this room.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("jump") && myObjSix.contains("window")) {
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                secondText.setText("You are not a savage.");
            } else {
                secondText.setText("There is no window in this room.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("window")) {
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                secondText.setText("Library views are no different from your house ones. As always, you see the same desert with the same pieces of metal scattered on the ground.\nAt least you can spot a part of the houses that were buried during last week's sand storm.");
            } else {
                secondText.setText("There is no window in this room.");
            }
            vm.linearLayout.addView(secondText);
        } else if ((myObjSix.contains("look") && myObjSix.contains("around")) || myObjSix.matches("l") || myObjSix.matches("look")) {
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                String intro = "Library\n";
                if (vm.lwLibrary.hasBrokenWindow) {
                    intro = intro + "There are some book shelves and a large broken window.\nSully's cabin is empty.";
                } else {
                    intro = intro + "There are some book shelves and a large window.\nSully's cabin is empty.";
                }
                intro = intro + " There is the closed office door and a pinboard with a few notes here.\nHeading NORTH is the room D.";
                if (!vm.lwLibrary.objectsDroppedHall.isEmpty()) {
                    int n = vm.lwLibrary.objectsDroppedHall.size();
                    for (int i = 0; i < n; i++) {
                        intro = intro + "\nThere's the " + vm.lwLibrary.objectsDroppedHall.get(i) + " here.";
                    }
                }
                secondText.setText(intro);
                vm.linearLayout.addView(secondText);
            } else {
                String intro = "Room D\nIt is a small place with three paintings: 1 Wallace Storm, 2 Catherine Barker and 3 Joseph Lovehart.\nHere is a metal door where it is written 'the unfortunate one' on it.\n";
                if (vm.myself.inventory.contains("fuse") || vm.lwLibrary.objectsDroppedHall.contains("fuse")
                        || vm.lwLibrary.objectsDroppedRoomD.contains("fuse")) {
                    intro = intro + "There are some instructions next to the door.\n";
                } else {
                    intro = intro + "There is a fuse with some instructions next to the door.\n";
                }
                intro = intro + "Heading SOUTH is the Library's hall.\nAbigail and Fred are here.";
                if (!vm.lwLibrary.objectsDroppedRoomD.isEmpty()) {
                    int n = vm.lwLibrary.objectsDroppedRoomD.size();
                    for (int i = 0; i < n; i++) {
                        intro = intro + "\nThere's the " + vm.lwLibrary.objectsDroppedRoomD.get(i) + " here.";
                    }
                }
                secondText.setText(intro);
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjSix.matches("i") || myObjSix.matches("inventory")) {
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
        } else if (myObjSix.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.matches("sleep")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("It will be inappropriate because Abigail and Fred are waiting you to open the metal door.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("check") && myObjSix.contains("out")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("check") || myObjSix.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("enter")) {
            secondText.setText("Try to open the doors of the places you want to get in.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("jump") || myObjSix.contains("climb") || myObjSix.contains("turn") || myObjSix.contains("shut") || myObjSix.contains("look") || myObjSix.contains("see") || myObjSix.contains("watch") || myObjSix.contains("play") || myObjSix.contains("run") || myObjSix.contains("walk") || myObjSix.contains("eat") || myObjSix.contains("move") || myObjSix.contains("give") || myObjSix.contains("offer")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Look around you.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("smell")) {
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                secondText.setText("It smells just like a library.");
            } else {
                secondText.setText("It smells just like the room has been neglected for years.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("listen")) {
            if (vm.lwLibrary.currentLocation.equals("hall")) {
                secondText.setText("It feels like the silence is killing you.");
            } else {
                secondText.setText("You can tell that there is no one at the other side of the metal door.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("wait")) {
            secondText.setText("Time passes...");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.contains("what") && myObjSix.contains("time") && myObjSix.contains("is") || myObjSix.matches("time") || myObjSix.matches("the time")) {
            secondText.setText("It is daylight.");
            vm.linearLayout.addView(secondText);
        } else if (myObjSix.matches("diagnostic") || myObjSix.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            vm.linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }
}
