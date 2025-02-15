package hypernova.hithchhiker.guide.galaxy.levels;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveFour extends AppCompatActivity {
    ValueManager vm;
    int scoreWhenFredKilling = 0;
    boolean isFirstTimeInStreet = true;
    int speakCount = 0;
    int examineFredCount = 0;

    public ObjectiveFour(ValueManager valManager) {
        vm = valManager;
    }

    public String checkObjAnswer(String myObjFour, TextView secondText) {
        if (vm.fred.isPresent && vm.ludlow.currentLocation.equals("house") && (vm.score - vm.ludlow.scoreWhenFredWaiting >= 5)) {
            vm.fred.isPresent = false;
            if (!vm.ludlow.isHouseWaterDrunk && !vm.myself.inventory.contains("water") && !vm.ludlow.objectsDroppedHouse.contains("water")) {
                vm.ludlow.isHouseWaterDrunk = true;
                secondText.setText("After waiting a while, Fred drinks the glass of water and goes North in order to arrive to the library.");
            } else {
                secondText.setText("After waiting a while, Fred goes North in order to arrive to the library.");
            }
            vm.myLocation.setText("Hall, open door");
            vm.linearLayout.addView(secondText);
        } else if (vm.fred.isDead && (vm.score - scoreWhenFredKilling >= 3)) {
            vm.myLocation.setText("Dead");
            String dieOptions = "\n\nType RESTART, RESTORE, COMMANDS or QUIT.";
            secondText.setText("Killing Fred makes you fall in a terrible depression. You live the last days of your life isolated at home. You died." + dieOptions);
            vm.linearLayout.addView(secondText);
            vm.currentObjective = 0;
        }
        // CHECK IS STANDING, added just in case
        else if (!vm.myself.isStanding && !(myObjFour.contains("stand") || myObjFour.contains("get up") || myObjFour.contains("sit down") || myObjFour.matches("sit") || myObjFour.contains("lie") || myObjFour.contains("stop") || myObjFour.contains("examine") || (myObjFour.contains("look") && myObjFour.contains("around")) || myObjFour.matches("l") || myObjFour.matches("look") || myObjFour.matches("i") || myObjFour.matches("inventory") || myObjFour.matches("sleep"))) {
            secondText.setText("Better stand up first.");
            vm.linearLayout.addView(secondText);
        }
        // DIRECTIONS GO VERB
        else if (myObjFour.matches("northeast") || myObjFour.matches("ne") || myObjFour.matches("go northeast") || myObjFour.matches("go ne") || myObjFour.matches("northwest") || myObjFour.matches("nw") || myObjFour.matches("go northwest") || myObjFour.matches("go nw") || myObjFour.matches("southeast") || myObjFour.matches("se") || myObjFour.matches("go southeast") || myObjFour.matches("go se") || myObjFour.matches("southwest") || myObjFour.matches("sw") || myObjFour.matches("go southwest") || myObjFour.matches("go sw") || myObjFour.matches("down") || myObjFour.matches("d") || myObjFour.matches("go down") || myObjFour.matches("downstairs") || myObjFour.matches("go downstairs") || myObjFour.matches("go d") || myObjFour.matches("up") || myObjFour.matches("u") || myObjFour.matches("go up") || myObjFour.matches("upstairs") || myObjFour.matches("go upstairs") || myObjFour.matches("go u")) {
            secondText.setText("You can't go that way.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.matches("north") || myObjFour.matches("n") || myObjFour.matches("go north") || myObjFour.matches("go n") || myObjFour.matches("go straight on")) {
            if (!vm.ludlow.currentLocation.equals("house")) {
                secondText.setText("You can't go that way.");
            } else if (!vm.ludlow.isHouseDoorOpen) {
                secondText.setText("Open the door to get out.");
            } else {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                vm.ludlow.currentLocation = "street";
                vm.fred.isPresent = true;
                vm.myLocation.setText("Street");
                if (isFirstTimeInStreet) {
                    isFirstTimeInStreet = false;
                    String intro = "";
                    if (!vm.fred.isDead) {
                        intro = intro + "'Another day in the rathole!' Fred says.\n\nNaturally, B903 is not the best Bubble to live in.\n";
                    }
                    intro = intro + "Wherever you look, you can see the tons of scrap piled up like it was a landfill. Also, no one reminds seeing something non-human alive in here.\nIt is like a desert made planet.\n\nStreet\nYou can see that Ludlow Library is West and your house is South.";
                    secondText.setText(intro);
                } else {
                    secondText.setText("Street\nYou can see that Ludlow Library is West and your house is South.");
                }
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.matches("south") || myObjFour.matches("s") || myObjFour.matches("go south") || myObjFour.matches("go s") || myObjFour.matches("go backwards")) {
            if (!vm.ludlow.currentLocation.equals("street")) {
                secondText.setText("You can't go that way.");
            } else {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                vm.ludlow.currentLocation = "house";
                vm.fred.isPresent = false;
                if (!vm.ludlow.isHouseDoorOpen) {
                    vm.myLocation.setText("Hall");
                } else {
                    vm.myLocation.setText("Hall, open door");
                }
                String intro = "";
                if (vm.ludlow.isHouseWindowBroken) {
                    intro = "Your house hall\nHere you see a broken window, your house door and your basement door.";
                } else {
                    intro = "Your house hall\nHere you see a window, your house door and your basement door.";
                }
                intro = intro + "\nHeading North you see the Street.";
                secondText.setText(intro);
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.matches("west") || myObjFour.matches("w") || myObjFour.matches("go west") || myObjFour.matches("go w") || myObjFour.matches("go left")) {
            if (!vm.ludlow.currentLocation.equals("street")) {
                secondText.setText("You can't go that way.");
            } else {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                vm.ludlow.currentLocation = "library";
                vm.fred.isPresent = true;
                vm.myLocation.setText("Library, door");
                String intro = "Ludlow Public Library door\n\nIt is a two-storey wood building. One of these days it is going to fall.\n";
                if (!vm.fred.isDead) {
                    intro = intro + "Fred stopped walking and now he is looking at you.";
                    secondText.setText(intro);
                } else {
                    if (!vm.myself.isNeckChipInstalled) {
                        intro = intro + "Something needs to be done before you enter.";
                    } else {
                        intro = intro + "You are allowed to enter.";
                    }
                    secondText.setText(intro);
                }
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.matches("east") || myObjFour.matches("e") || myObjFour.matches("go east") || myObjFour.matches("go e") || myObjFour.matches("go right")) {
            if (!vm.ludlow.currentLocation.equals("library")) {
                secondText.setText("You can't go that way.");
            } else {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                vm.ludlow.currentLocation = "street";
                vm.fred.isPresent = false;
                vm.myLocation.setText("Street");
                secondText.setText("Street\nYou can see that Ludlow Library is West and your house is South.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.matches("exit") || myObjFour.matches("leave") || ((myObjFour.contains("go") || myObjFour.contains("get")) && myObjFour.contains("out")) || (myObjFour.contains("exit") && myObjFour.contains("room"))) {
            if (!vm.ludlow.currentLocation.equals("house")) {
                secondText.setText("You are already on the outside.");
            } else if (!vm.ludlow.isHouseDoorOpen) {
                secondText.setText("Open the door to get out.");
            } else {
                vm.score++;
                vm.myMoves.setText("Moves: " + vm.score);
                vm.ludlow.currentLocation = "street";
                vm.fred.isPresent = true;
                vm.myLocation.setText("Street");
                if (isFirstTimeInStreet) {
                    isFirstTimeInStreet = false;
                    String intro = "";
                    if (!vm.fred.isDead) {
                        intro = intro + "'Another day in the rathole!' Fred says.\n\nNaturally, B903 is not the best Bubble to live in.\n";
                    }
                    intro = intro + "Wherever you look, you can see the tons of scrap piled up like it was a landfill. Also, no one reminds seeing something non-human alive in here.\nIt is like a desert made planet.\n\nStreet\nYou can see that Ludlow Library is West and your house is South.";
                    secondText.setText(intro);
                } else {
                    secondText.setText("Street\nYou can see that Ludlow Library is West and your house is South.");
                }
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            vm.linearLayout.addView(secondText);
        }
        // SPEAK VERB
        else if (myObjFour.matches("speak") || myObjFour.matches("talk") || myObjFour.matches("ask")) {
            secondText.setText("This verb needs to be used with a name.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("speak") || myObjFour.contains("talk") || myObjFour.contains("ask")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFour.contains("fred")) {
                if (vm.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!vm.fred.isPresent) {
                    secondText.setText("Fred is not here.");
                } else {
                    if (speakCount == 0) {
                        secondText.setText(vm.myself.name + ", we should be inside the library by now.");
                    } else if ((speakCount >= 1 && speakCount < 3) || speakCount > 3) {
                        secondText.setText("There's nothing much I can tell you.");
                    } else {
                        secondText.setText("Look.\nI'm a video game AI, so don't expect me to have deep conversations.");
                    }
                    speakCount++;
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This person's name is incorrect or he/she is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjFour.contains("stop")) {  // STOP VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFour.contains("play") || myObjFour.contains("game")) {
                secondText.setText("You moved on from that.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("noise") || myObjFour.contains("sound")) {
                secondText.setText("There's no noise.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("There's nothing you can stop here.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjFour.matches("close")) {  // CLOSE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("close")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFour.contains("eye")) {
                secondText.setText("Not really useful.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("door")) {
                if (vm.ludlow.currentLocation.equals("house")) {
                    if (!vm.ludlow.isHouseDoorOpen) {
                        secondText.setText("It is already closed.");
                    } else {
                        vm.ludlow.isHouseDoorOpen = false;
                        vm.myLocation.setText("Hall");
                        secondText.setText("Closed.");
                    }
                } else if (vm.ludlow.currentLocation.equals("street")) {
                    secondText.setText("You can't see any door here!");
                } else {
                    secondText.setText("It is already closed.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("window")) {
                if (vm.ludlow.currentLocation.equals("house")) {
                    secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                } else {
                    secondText.setText("You can't see any window here!");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjFour.matches("open")) {  // OPEN VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("open")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFour.contains("eye")) {
                secondText.setText("You already see.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("basement door")) {
                if (vm.ludlow.currentLocation.equals("house")) {
                    secondText.setText("The basement door is locked.");
                } else {
                    secondText.setText("You have to be in your house.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("door")) {
                if (vm.ludlow.currentLocation.equals("house")) {
                    vm.ludlow.isHouseDoorOpen = true;
                    vm.myLocation.setText("Hall, open door");
                    secondText.setText("The house door is open.");
                    vm.linearLayout.addView(secondText);
                } else if (vm.ludlow.currentLocation.equals("street")) {
                    secondText.setText("You can't see any door here!");
                    vm.linearLayout.addView(secondText);
                } else {
                    if (!vm.myself.isNeckChipInstalled) {
                        secondText.setText("The door is locked for you. It seems you need to do something before...");
                        vm.linearLayout.addView(secondText);
                    } else {
                        vm.myLocation.setText("Ludlow Library");
                        String intro = "";
                        if (vm.fred.isDead) {
                            vm.fred.isDead = false;
                            intro = intro + "Due to game incoherences Fred has to return from the dead in this level.\n";
                        }
                        intro = intro + "You both enter the library.\n\n'I need to talk with someone here. I'll be back in a minute. Find the book, " + vm.myself.name + ".'\nFred enters the office door of the library and closes it behind him. Look around.\n\nNote: Whenever you want to speak with someone say SPEAK (and the name of the person).";
                        secondText.setText(intro);
                        vm.linearLayout.addView(secondText);
                        vm.lwLibrary.scoreWhenEnteringLibrary = vm.score;
                        vm.currentObjective = 5;
                    }
                }
            } else if (myObjFour.contains("window")) {
                if (vm.ludlow.currentLocation.equals("house")) {
                    secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                } else {
                    secondText.setText("You can't see any window here!");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        else if (myObjFour.matches("enter")) {  // ENTER VERB
            secondText.setText("This verb needs to be used with a place to get in.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("enter")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFour.contains("house")) {
                if (vm.ludlow.currentLocation.equals("house")) {
                    secondText.setText("You are already inside.");
                } else {
                    vm.ludlow.currentLocation = "house";
                    vm.fred.isPresent = false;
                    vm.ludlow.isHouseDoorOpen = true;
                    vm.myLocation.setText("Hall, open door");
                    secondText.setText("After walking a while, you get to the house... again.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("library")) {
                if (vm.ludlow.currentLocation.equals("library")) {
                    if (!vm.myself.isNeckChipInstalled) {
                        secondText.setText("The door is locked for you. It seems you need to do something before...");
                        vm.linearLayout.addView(secondText);
                    } else {
                        vm.myLocation.setText("Ludlow Library");
                        String intro = "";
                        if (vm.fred.isDead) {
                            vm.fred.isDead = false;
                            intro = intro + "Due to game incoherences Fred has to return from the dead in this level.\n";
                        }
                        intro = intro + "You both enter the library.\n\n'I need to talk with someone here. I'll be back in a minute. Find the book, " + vm.myself.name + ".'\nFred enters the office door of the library and closes it behind him. Look around.\n\nNote: Whenever you want to speak with someone say SPEAK (and the name of the person).";
                        secondText.setText(intro);
                        vm.linearLayout.addView(secondText);
                        vm.lwLibrary.scoreWhenEnteringLibrary = vm.score;
                        vm.currentObjective = 5;
                    }
                } else {
                    secondText.setText("You have to go to the library first.");
                    vm.linearLayout.addView(secondText);
                }
            } else if (myObjFour.contains("matrix")) {
                secondText.setText("Morpheus should be proud.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("You cannot get in here or the ubication's name is incorrect.");
                vm.linearLayout.addView(secondText);
            }
        }
        // TURN OFF VERB
        else if ((myObjFour.contains("turn") && myObjFour.contains("off")) || (myObjFour.contains("shut") && myObjFour.contains("down"))) {
            secondText.setText("There is nothing you can turn off here.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("turn on") || myObjFour.contains("turn it on")) {  // TURN ON VERB
            secondText.setText("There's nothing you can turn on here.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("stand") || myObjFour.contains("get up")) {  // STAND VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            vm.myself.isStanding = true;
            secondText.setText("You are standing.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("sit down") || myObjFour.matches("sit") || myObjFour.contains("lie")) {  // LIE VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            vm.myself.isStanding = false;
            secondText.setText("You are on the floor.");
            vm.linearLayout.addView(secondText);
        }
        // EXAMINE VERB
        else if (myObjFour.matches("examine")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("examine")) {
            if (myObjFour.contains("glass") || myObjFour.contains("water")) {
                if ((vm.ludlow.currentLocation.equals("house") && (!vm.ludlow.objectsDroppedStreet.contains("water") || !vm.ludlow.objectsDroppedLibrary.contains("water")))
                        || (vm.ludlow.currentLocation.equals("street") && vm.ludlow.objectsDroppedStreet.contains("water"))
                        || (vm.ludlow.currentLocation.equals("library") && vm.ludlow.objectsDroppedLibrary.contains("water"))
                        || vm.myself.inventory.contains("water")) {
                    if (vm.ludlow.isHouseWaterDrunk) {
                        secondText.setText("Someone drank that.");
                    } else {
                        secondText.setText("It is just a glass of water.");
                    }
                } else {
                    secondText.setText("You can't see any water here!");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("window")) {
                if (vm.ludlow.currentLocation.equals("house")) {
                    secondText.setText("It is just a window.");
                } else {
                    secondText.setText("You can't see any window here!");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("door")) {
                if (vm.ludlow.currentLocation.equals("street")) {
                    secondText.setText("You can't see any door here!");
                } else {
                    secondText.setText("It is just a door.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("fred")) {
                if (vm.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!vm.fred.isPresent) {
                    secondText.setText("Fred is not here.");
                } else {
                    if (examineFredCount == 0) {
                        secondText.setText("Don't look at me like that.");
                        examineFredCount++;
                    } else {
                        secondText.setText("Stop it.");
                    }
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be examined or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // TAKE GET VERB
        else if (myObjFour.matches("take") || myObjFour.matches("get") || myObjFour.matches("pick") || myObjFour.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("take") || myObjFour.contains("get") || myObjFour.contains("pick") || myObjFour.contains("grab")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.ludlow.currentLocation.equals("house")
                && (myObjFour.contains("broken glass") || myObjFour.contains("crystal"))
                && vm.ludlow.isHouseWindowBroken) {
                if (vm.myself.inventory.contains("broken glass")) {
                    secondText.setText("You already took it.");
                } else {
                    vm.myself.inventory.add("broken glass");
                    secondText.setText("Taken.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("glass") || myObjFour.contains("water")) {
                if (vm.ludlow.isHouseWaterDrunk) {
                    secondText.setText("Someone already drank that.");
                } else if (vm.myself.inventory.contains("water")) {
                    secondText.setText("You already took it.");
                } else if (vm.ludlow.currentLocation.equals("house")
                        && !vm.ludlow.objectsDroppedStreet.contains("water")
                        && !vm.ludlow.objectsDroppedLibrary.contains("water")) {
                    if (vm.ludlow.objectsDroppedHouse.contains("water")) {
                        vm.ludlow.objectsDroppedHouse.remove("water");
                    }
                    vm.myself.inventory.add("water");
                    secondText.setText("Taken.");
                } else if (vm.ludlow.currentLocation.equals("street") && vm.ludlow.objectsDroppedStreet.contains("water")) {
                    vm.ludlow.objectsDroppedStreet.remove("water");
                    vm.myself.inventory.add("water");
                    secondText.setText("Taken.");
                } else if (vm.ludlow.currentLocation.equals("library") && vm.ludlow.objectsDroppedLibrary.contains("water")) {
                    vm.ludlow.objectsDroppedLibrary.remove("water");
                    vm.myself.inventory.add("water");
                    secondText.setText("Taken.");
                } else {
                    secondText.setText("You can't see any water here!");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("fred")) {
                if (vm.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!vm.fred.isPresent) {
                    secondText.setText("Fred is not here.");
                } else {
                    secondText.setText("You cannot take Fred.");
                }
                vm.linearLayout.addView(secondText);
            } else if (!vm.ludlow.objectsDroppedHouse.isEmpty()
                    || !vm.ludlow.objectsDroppedStreet.isEmpty()
                    || !vm.ludlow.objectsDroppedLibrary.isEmpty()) {
                int n;
                if (myObjFour.contains("take") || myObjFour.contains("pick") || myObjFour.contains("grab")) {
                    n = 4;
                } else if (myObjFour.contains("get")) {
                    n = 3;
                } else {
                    n = 0;
                }
                String s = myObjFour.substring(n);
                s = s.replace(" the ","");
                s = s.replace(" a ","");
                s = s.replace(" an ","");
                s = s.trim();
                if (vm.ludlow.currentLocation.equals("house")) {
                    if (vm.ludlow.objectsDroppedHouse.contains(s)) {
                        vm.ludlow.objectsDroppedHouse.remove(s);
                        vm.myself.inventory.add(s);
                        secondText.setText("Taken.");
                    } else {
                        secondText.setText("You can't see any " + s + " here!");
                    }
                } else if (vm.ludlow.currentLocation.equals("street")) {
                    if (vm.ludlow.objectsDroppedStreet.contains(s)) {
                        vm.ludlow.objectsDroppedStreet.remove(s);
                        vm.myself.inventory.add(s);
                        secondText.setText("Taken.");
                    } else {
                        secondText.setText("You can't see any " + s + " here!");
                    }
                } else {
                    if (vm.ludlow.objectsDroppedLibrary.contains(s)) {
                        vm.ludlow.objectsDroppedLibrary.remove(s);
                        vm.myself.inventory.add(s);
                        secondText.setText("Taken.");
                    } else {
                        secondText.setText("You can't see any " + s + " here!");
                    }
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be taken or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // DROP VERB
        else if (myObjFour.matches("drop") || myObjFour.contains("get rid of")) {
            secondText.setText("Just say: Drop (and the object you want to drop).");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("drop")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            int n = 4;
            String s = myObjFour.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (vm.myself.inventory.contains(s)) {
                vm.myself.inventory.remove(s);
                if (vm.ludlow.currentLocation.equals("house")) {
                    vm.ludlow.objectsDroppedHouse.add(s);
                } else if (vm.ludlow.currentLocation.equals("street")) {
                    vm.ludlow.objectsDroppedStreet.add(s);
                } else {
                    vm.ludlow.objectsDroppedLibrary.add(s);
                }
                secondText.setText("Dropped.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("You're not holding the " + s + ".");
                vm.linearLayout.addView(secondText);
            }
        }
        // PUT VERB
        else if (myObjFour.matches("put") || myObjFour.matches("place") || myObjFour.matches("stick") || myObjFour.matches("set")) {
            secondText.setText("This verb needs to be used with an object and a the site where you wanna place it.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("put") || myObjFour.contains("place") || myObjFour.contains("stick") || myObjFour.contains("set")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFour.contains("chip") && myObjFour.contains("neck")) {
                if (!vm.myself.inventory.contains("library neck chip")) {
                    secondText.setText("You're not holding the Library neck chip.");
                    vm.linearLayout.addView(secondText);
                } else {
                    vm.myself.inventory.remove("library neck chip");
                    vm.myself.isNeckChipInstalled = true;
                    secondText.setText("Done.");
                    vm.linearLayout.addView(secondText);
                }
            } else {
                secondText.setText("The name of the object is wrong, the name of the place to put it is wrong or this object cannot be used this way.");
                vm.linearLayout.addView(secondText);
            }
        }
        // HELP VERB
        else if (myObjFour.matches("help")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFour.contains("fred")) {
                if (vm.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!vm.fred.isPresent) {
                    secondText.setText("Fred is not here.");
                } else {
                    secondText.setText("I'm OK, " + vm.myself.name + ".");
                }
            } else {
                secondText.setText("Don't ask for help. That just worked on the start level.\n\nType COMMANDS to get a list of some actions you can do.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("help")) {
            secondText.setText("Just say HELP.");
            vm.linearLayout.addView(secondText);
        }
        // FOLLOW VERB
        else if (myObjFour.matches("follow") || myObjFour.matches("chase") || myObjFour.matches("find") || myObjFour.matches("search")) {
            secondText.setText("This verb needs to be used with a name.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("follow") || myObjFour.contains("chase") || myObjFour.contains("find") || myObjFour.contains("search")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFour.contains("fred")) {
                if (vm.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!vm.fred.isPresent && vm.ludlow.currentLocation.equals("house")
                        && !vm.ludlow.isHouseDoorOpen) {
                    secondText.setText("Open the door to get out.");
                } else if (!vm.fred.isPresent && vm.ludlow.currentLocation.equals("house")) {
                    vm.ludlow.currentLocation = "street";
                    vm.fred.isPresent = true;
                    vm.myLocation.setText("Street");
                    if (isFirstTimeInStreet) {
                        isFirstTimeInStreet = false;
                        secondText.setText("'Another day in the rathole!' Fred says.\n\nNaturally, B903 is not the best Bubble to live in.\nWherever you look, you can see the tons of scrap piled up like it was a landfill. Also, no one reminds seeing something non-human alive in here.\nIt is like a desert made planet.\n\nStreet\nYou can see that Ludlow Library is West and your house is South.");
                    } else {
                        secondText.setText("Street\nYou can see that Ludlow Library is West and your house is South.");
                    }
                } else if (!vm.fred.isPresent && vm.ludlow.currentLocation.equals("street")) {
                    vm.ludlow.currentLocation = "library";
                    vm.fred.isPresent = true;
                    vm.myLocation.setText("Library, door");
                    String intro = "Ludlow Public Library door\n\n";
                    if (!vm.myself.isNeckChipInstalled) {
                        intro = intro + "Fred is pointing at the neckchip that you have in your inventory.";
                        secondText.setText(intro);
                    } else {
                        intro = intro + "You are allowed to enter.";
                        secondText.setText(intro);
                    }

                } else {
                    secondText.setText("Fred is right here.");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This person's name is incorrect or he/she cannot be followed.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjFour.matches("drink")) {  // DRINK VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("drink")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFour.contains("glass") || myObjFour.contains("water")) {
                if (vm.ludlow.isHouseWaterDrunk) {
                    secondText.setText("Someone already drank that.");
                } else if (vm.myself.inventory.contains("water")) {
                    vm.myself.inventory.remove("water");
                    vm.ludlow.isHouseWaterDrunk = true;
                    secondText.setText("It feels refreshing.");
                } else if (vm.ludlow.currentLocation.equals("house") && !vm.ludlow.objectsDroppedStreet.contains("water")
                        && !vm.ludlow.objectsDroppedLibrary.contains("water")) {
                    secondText.setText("You will have to take it first.");
                } else if (vm.ludlow.currentLocation.equals("street") && vm.ludlow.objectsDroppedStreet.contains("water")) {
                    secondText.setText("You will have to take it first.");
                } else if (vm.ludlow.currentLocation.equals("library") && vm.ludlow.objectsDroppedLibrary.contains("water")) {
                    secondText.setText("You will have to take it first.");
                } else {
                    secondText.setText("You can't see any water here!");
                }
            } else {
                secondText.setText("This thing cannot be drunk or it is not in the place.");
            }
            vm.linearLayout.addView(secondText);
        }
        // BREAK VERB
        else if (myObjFour.matches("break") || myObjFour.matches("hit") || myObjFour.matches("attack") || myObjFour.matches("punch") || myObjFour.matches("fight") || myObjFour.matches("kick")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("break") || myObjFour.contains("hit") || myObjFour.contains("attack") || myObjFour.contains("punch") || myObjFour.contains("fight") || myObjFour.contains("kick")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFour.contains("person") || myObjFour.contains("fred")) {
                if (vm.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!vm.fred.isPresent) {
                    secondText.setText("Fred is not here.");
                } else {
                    vm.fred.isDead = true;
                    scoreWhenFredKilling = vm.score;
                    secondText.setText("You killed Fred.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFour.contains("window")) {
                if (vm.ludlow.currentLocation.equals("house")) {
                    if (vm.fred.isDead || !vm.fred.isPresent) {
                        if (!vm.ludlow.isHouseWindowBroken) {
                            vm.ludlow.isHouseWindowBroken = true;
                            secondText.setText("And now the window is broken. It's a mess.");
                        } else {
                            secondText.setText("The window is already broken.");
                        }
                    } else {
                        secondText.setText("Before you do it, Fred points his watch and then he makes a signal indicating to the North.");
                    }
                } else {
                    secondText.setText("You can't see any window here!");
                }
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be hit or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjFour.contains("window")) {  // WINDOW ACTIONS, added just in case
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.ludlow.currentLocation.equals("house")) {
                secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
            } else {
                secondText.setText("You can't see any window here!");
            }
            vm.linearLayout.addView(secondText);
        } else if ((myObjFour.contains("look") && myObjFour.contains("around")) || myObjFour.matches("l") || myObjFour.matches("look")) {
            if (vm.ludlow.currentLocation.equals("house")) {
                String intro = "";
                if (vm.ludlow.isHouseWindowBroken) {
                    intro = "Your house hall\nHere you see a broken window";
                } else {
                    intro = "Your house hall\nHere you see a window";
                }
                if (vm.ludlow.isHouseWaterDrunk || vm.myself.inventory.contains("water")
                        || vm.ludlow.objectsDroppedHouse.contains("water") || vm.ludlow.objectsDroppedStreet.contains("water")
                        || vm.ludlow.objectsDroppedLibrary.contains("water")) {
                    intro = intro + ", your house door and your basement door.";
                } else {
                    intro = intro + ", your house door, your basement door and a glass of water.";
                }
                intro = intro + "\nHeading North you see the Street.";
                if (vm.fred.isPresent) {
                    intro = intro + "\nFred is here.";
                }
                if (!vm.ludlow.objectsDroppedHouse.isEmpty()) {
                    int n = vm.ludlow.objectsDroppedHouse.size();
                    for (int i = 0; i < n; i++) {
                        intro = intro + "\nThere's the " + vm.ludlow.objectsDroppedHouse.get(i) + " here.";
                    }
                }
                secondText.setText(intro);
                vm.linearLayout.addView(secondText);
            } else if (vm.ludlow.currentLocation.equals("street")) {
                String intro = "Street\nYou can see that Ludlow Library is West and your house is South.";
                if (vm.fred.isPresent) {
                    intro = intro + "\nFred is here.";
                }
                if (!vm.ludlow.objectsDroppedStreet.isEmpty()) {
                    int n = vm.ludlow.objectsDroppedStreet.size();
                    for (int i = 0; i < n; i++) {
                        intro = intro + "\nThere's the " + vm.ludlow.objectsDroppedStreet.get(i) + " here.";
                    }
                }
                secondText.setText(intro);
                vm.linearLayout.addView(secondText);
            } else {
                String intro = "You find yourself in front of the library door.\nFred is here.";
                if (!vm.ludlow.objectsDroppedLibrary.isEmpty()) {
                    int n = vm.ludlow.objectsDroppedLibrary.size();
                    for (int i = 0; i < n; i++) {
                        intro = intro + "\nThere's the " + vm.ludlow.objectsDroppedLibrary.get(i) + " here.";
                    }
                }
                secondText.setText(intro);
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjFour.matches("i") || myObjFour.matches("inventory")) {
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
        } else if (myObjFour.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.matches("sleep")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Amazing strategy.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("check") && myObjFour.contains("out")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("check") || myObjFour.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("smell")) {
            secondText.setText("It smells like the whole planet has been dead for years.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("listen")) {
            secondText.setText("There's nothing to listen at.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("wait")) {
            secondText.setText("Time passes...");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("what") && myObjFour.contains("time") && myObjFour.contains("is") || myObjFour.matches("time") || myObjFour.matches("the time")) {
            secondText.setText("It is sunrise.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.matches("diagnostic") || myObjFour.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFour.contains("jump") || myObjFour.contains("climb") || myObjFour.contains("turn") || myObjFour.contains("shut") || myObjFour.contains("look") || myObjFour.contains("see") || myObjFour.contains("watch") || myObjFour.contains("play") || myObjFour.contains("run") || myObjFour.contains("walk") || myObjFour.contains("eat") || myObjFour.contains("move") || myObjFour.contains("give") || myObjFour.contains("offer") || myObjFour.contains("read") || myObjFour.contains("write") || myObjFour.contains("fix") || myObjFour.contains("repair")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Look around you.");
            vm.linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }
}
