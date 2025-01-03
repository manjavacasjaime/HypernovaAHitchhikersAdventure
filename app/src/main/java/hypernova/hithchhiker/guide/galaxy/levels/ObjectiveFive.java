package hypernova.hithchhiker.guide.galaxy.levels;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class ObjectiveFive extends AppCompatActivity {
    ValueManager vm;
    int icarusChatPhase = 1;
    int abigailChatPhase = 1;

    public ObjectiveFive(ValueManager valManager) {
        vm = valManager;
    }

    public String checkObjAnswer(String myObjFive, Activity activity) {
        TextView secondText = new TextView(activity.getBaseContext());
        secondText.setTypeface(vm.typeface);

        if (vm.lwLibrary.isReadingPinboard) {  // READING NOTES
            vm.lwLibrary.displayPinboardNotes(myObjFive, vm.linearLayout, secondText);
        } else if (vm.shannon.conversationStatus == 1) {  // CONVERSATIONS
            vm.lwLibrary.displayConversationShannon(myObjFive, vm.shannon, vm.linearLayout, secondText);
        } else if (vm.sully.conversationStatus == 1) {
            vm.lwLibrary.displayConversationSully(myObjFive, vm.sully, vm.linearLayout, secondText);
        } else if (vm.henry.conversationStatus == 1) {
            vm.lwLibrary.displayConversationHenry(myObjFive, vm.henry, vm.linearLayout, secondText);
        } else if (vm.kenny.conversationStatus == 1) {
            vm.lwLibrary.displayConversationKenny(myObjFive, vm.kenny, vm.linearLayout, secondText);
        } else if (vm.icarus.conversationStatus == 1) {
            if (icarusChatPhase == 1) {
                switch (myObjFive) {
                    case "1":
                        icarusChatPhase = 2;
                        secondText.setText("Yeah... Okay. You could say that. However, Canterman's theories result to be obsolete in the present day. Sigh. But I don't really care.");
                        vm.linearLayout.addView(secondText);
                        break;
                    case "2":
                        icarusChatPhase = 2;
                        secondText.setText("What do you know about shutting your mouth?\nWhatever you want to tell me, I don't care.");
                        vm.linearLayout.addView(secondText);
                        break;
                    case "3":
                        vm.icarus.conversationStatus = 0;
                        secondText.setText("Dialogue Icarus finished.");
                        vm.linearLayout.addView(secondText);
                        break;
                    default:
                        secondText.setText("You just said: mknfwrhwe.\nWrite the number of the option you want.\n\n\n" +
                                "Dialogue Icarus.\n\n1. 'Hi. That's a great book right there.'\n2. 'What do you know about the Travellers?'\n3. exit.");
                        vm.linearLayout.addView(secondText);
                        break;
                }
            } else {
                switch (myObjFive) {
                    case "1":
                        secondText.setText("Yeah... Okay. You could say that. However, Canterman's theories result to be obsolete in the present day. Sigh. But I don't really care.");
                        vm.linearLayout.addView(secondText);
                        break;
                    case "2":
                        secondText.setText("What do you know about shutting your mouth?\nWhatever you want to tell me, I don't care.");
                        vm.linearLayout.addView(secondText);
                        break;
                    case "3":
                        vm.myself.hasKidIcarus = true;
                        vm.icarus.conversationStatus = 0;
                        secondText.setText("Hey. Hey. Hey. Don't mess with me, 'explorer'.\nYou don't know me and you don't know the things I do here.\n\nDialogue Icarus finished.\n\nHe didn't like your comment.");
                        vm.linearLayout.addView(secondText);
                        break;
                    case "4":
                        vm.icarus.conversationStatus = 0;
                        secondText.setText("Dialogue Icarus finished.");
                        vm.linearLayout.addView(secondText);
                        break;
                    default:
                        secondText.setText("You just said: mknfwrhwe.\nWrite the number of the option you want.\n\n\n" +
                                "Dialogue Icarus.\n\n1. 'Hi. That's a great book right there.'\n2. 'What do you know about the Travellers?'\n3. 'I'm about to drop an ion bomb at B903, but I guess you don't really care.'\n4. exit.");
                        vm.linearLayout.addView(secondText);
                        break;
                }
            }
        } else if (vm.abigail.conversationStatus == 1) {
            if (abigailChatPhase == 1) {
                if (vm.lwLibrary.hasKnownArmoredPeople) {
                    switch (myObjFive) {
                        case "1":
                            secondText.setText("It's useless. During the whole morning I've been trying to find something in these pages.\nI'm afraid the book is obsolete.\nMaybe It was useful for the people at B526, but not for us.\n\nNote: You won't need this object during your adventure.");
                            vm.linearLayout.addView(secondText);
                            break;
                        case "2":
                            abigailChatPhase = 2;
                            secondText.setText("What do you think? Don't play the innocent role with me.\nDo you think you're the only one that has noticed the incoherence in the spacial routes? I'm one step ahead of you.\n\nMmh. Listen. Maybe you're smarter than you seem.\nI managed to discover there's a place hidden in the library which is related with the hitchhikers that existed many years ago.\nI don't know what it is. I have just found the door, but it needs to be unlocked following some weird instructions.\n\n\n" +
                                    "Dialogue Abigail.\n\n1. 'Why haven't you unlocked it yourself?'\n2. 'What kind of door?'\n3. 'Maybe I can help you if you let us go with you.'\n4. exit.");
                            vm.linearLayout.addView(secondText);
                            break;
                        case "3":
                            secondText.setText("Did Sully tell you that? Don't let him scare you.\nHe is just tired of seeing the Federation Agents sniffing here.");
                            vm.linearLayout.addView(secondText);
                            break;
                        case "4":
                            vm.abigail.conversationStatus = 0;
                            secondText.setText("Dialogue Abigail finished.");
                            vm.linearLayout.addView(secondText);
                            break;
                        default:
                            secondText.setText("You just said: yuwegvfbja.\nWrite the number of the option you want.\n\n\n" +
                                    "Dialogue Abigail.\n\n1. 'I need that book.'\n2. 'What are you trying to find in that book?'\n3. 'Who comes to a Library with an armor?'\n4. exit.");
                            vm.linearLayout.addView(secondText);
                            break;
                    }
                } else {
                    switch (myObjFive) {
                        case "1":
                            secondText.setText("It's useless. During the whole morning I've been trying to find something in these pages.\nI'm afraid the book is obsolete.\nMaybe It was useful for the people at B526, but not for us.\n\nNote: You won't need this object during your adventure.");
                            vm.linearLayout.addView(secondText);
                            break;
                        case "2":
                            abigailChatPhase = 2;
                            secondText.setText("What do you think? Don't play the innocent role with me.\nDo you think you're the only one that has noticed the incoherence in the spacial routes? I'm one step ahead of you.\n\nMmh. Listen. Maybe you're smarter than you seem.\nI managed to discover there's a place hidden in the library which is related with the hitchhikers that existed many years ago.\nI don't know what it is. I have just found the door, but it needs to be unlocked following some weird instructions.\n\n\n" +
                                    "Dialogue Abigail.\n\n1. 'Why haven't you unlocked it yourself?'\n2. 'What kind of door?'\n3. 'Maybe I can help you if you let us go with you.'\n4. exit.");
                            vm.linearLayout.addView(secondText);
                            break;
                        case "3":
                            vm.abigail.conversationStatus = 0;
                            secondText.setText("Dialogue Abigail finished.");
                            vm.linearLayout.addView(secondText);
                            break;
                        default:
                            secondText.setText("You just said: yuwegvfbja.\nWrite the number of the option you want.\n\n\n" +
                                    "Dialogue Abigail.\n\n1. 'I need that book.'\n2. 'What are you trying to find in that book?'\n3. exit.");
                            vm.linearLayout.addView(secondText);
                            break;
                    }
                }
            } else {
                switch (myObjFive) {
                    case "1":
                        secondText.setText("Because you need to find a name. It mentions something like... 'the unfortunate one'.\nI've been reading every book here and I do not know who could it be.");
                        vm.linearLayout.addView(secondText);
                        break;
                    case "2":
                        secondText.setText("The one that gets you killed if you fail the instructions.");
                        vm.linearLayout.addView(secondText);
                        break;
                    case "3":
                        vm.abigail.conversationStatus = 0;
                        secondText.setText("'Sounds like a deal to me.\nHowever, after we open that door, I'll go and do my thing and you'll go and do yours. Because I know that you're looking for the Traveller too, obviously. But I don't know why you are looking for him...'\n\nDialogue Abigail finished.\n\nSuddenly, Fred joins Abigail and you.\nYou tell Fred everything you've talked with Abigail and he's pleased to know that you've found something interesting.\n\nAbigail indicates you and Fred to follow her. The three of you go NORTH ending in a private room which Abigail seems to know.");
                        vm.linearLayout.addView(secondText);
                        vm.myLocation.setText("Library, room D");
                        vm.lwLibrary.scoreWhenEnteringRoomD = vm.score;
                        vm.lwLibrary.currentLocation = "room D";
                        vm.currentObjective = 6;
                        break;
                    case "4":
                        vm.abigail.conversationStatus = 0;
                        secondText.setText("Dialogue Abigail finished.");
                        vm.linearLayout.addView(secondText);
                        break;
                    default:
                        secondText.setText("You just said: efbrqhkbfb.\nWrite the number of the option you want.\n\n\n" +
                                "Dialogue Abigail.\n\n1. 'Why haven't you unlocked it yourself?'\n2. 'What kind of door?'\n3. 'Maybe I can help you if you let us go with you.'\n4. exit.");
                        vm.linearLayout.addView(secondText);
                        break;
                }
            }
        } else if (vm.score - vm.lwLibrary.scoreWhenPeopleLeave == 4) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Restart the game or you will be stuck here playing forever.");
            vm.linearLayout.addView(secondText);
        }
        // DIRECTIONS GO VERB
        else if (myObjFive.matches("north") || myObjFive.matches("n") || myObjFive.matches("go north") || myObjFive.matches("go n") || myObjFive.matches("go straight on") || myObjFive.matches("west") || myObjFive.matches("w") || myObjFive.matches("go west") || myObjFive.matches("go w") || myObjFive.matches("go left") || myObjFive.matches("northeast") || myObjFive.matches("ne") || myObjFive.matches("go northeast") || myObjFive.matches("go ne") || myObjFive.matches("northwest") || myObjFive.matches("nw") || myObjFive.matches("go northwest") || myObjFive.matches("go nw") || myObjFive.matches("southeast") || myObjFive.matches("se") || myObjFive.matches("go southeast") || myObjFive.matches("go se") || myObjFive.matches("southwest") || myObjFive.matches("sw") || myObjFive.matches("go southwest") || myObjFive.matches("go sw") || myObjFive.matches("up") || myObjFive.matches("u") || myObjFive.matches("go up") || myObjFive.matches("upstairs") || myObjFive.matches("go upstairs") || myObjFive.matches("go u") || myObjFive.matches("down") || myObjFive.matches("d") || myObjFive.matches("go down") || myObjFive.matches("downstairs") || myObjFive.matches("go downstairs") || myObjFive.matches("go d")) {
            secondText.setText("You can't go that way.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.matches("east") || myObjFive.matches("e") || myObjFive.matches("go east") || myObjFive.matches("go e") || myObjFive.matches("go right")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("The office door is locked.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.matches("south") || myObjFive.matches("s") || myObjFive.matches("go south") || myObjFive.matches("go s") || myObjFive.matches("go backwards") || myObjFive.matches("exit") || myObjFive.matches("leave") || ((myObjFive.contains("go") || myObjFive.contains("get")) && myObjFive.contains("out")) || (myObjFive.contains("exit") && myObjFive.contains("room"))) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("You are not leaving without Fred, are you?");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            vm.linearLayout.addView(secondText);
        }
        // SPEAK VERB
        else if (myObjFive.matches("speak") || myObjFive.matches("talk") || myObjFive.matches("ask")) {
            secondText.setText("This verb needs to be used with a name.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("speak") || myObjFive.contains("talk") || myObjFive.contains("ask")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.lwLibrary.hasPeopleLeft) {
                secondText.setText("You can't speak with anyone because everybody has left.");
                vm.linearLayout.addView(secondText);
            } else {
                if (myObjFive.contains("sully")) {
                    vm.sully.conversationStatus = 1;
                    secondText.setText("Dialogue Sully, the librarian.\n\n1. 'Excuse me. I want to ask for a book.'\n2. 'Why is this cabin bulletproof?'\n3. exit.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjFive.contains("people") || myObjFive.contains("person")) {
                    secondText.setText("When you go talk with the people reading, the librarian stops you.\n\nNote: Do not disturb the readers if you don't have something to tell them.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjFive.contains("abigail")) {
                    vm.abigail.conversationStatus = 1;
                    if (abigailChatPhase == 1) {
                        if (vm.lwLibrary.hasKnownArmoredPeople) {
                            secondText.setText("Dialogue Abigail.\n\n1. 'I need that book.'\n2. 'What are you trying to find in that book?'\n3. 'Who comes to a Library with an armor?'\n4. exit.");
                        } else {
                            secondText.setText("Dialogue Abigail.\n\n1. 'I need that book.'\n2. 'What are you trying to find in that book?'\n3. exit.");
                        }
                    } else {
                        secondText.setText("Dialogue Abigail.\n\n1. 'Why haven't you unlocked it yourself?'\n2. 'What kind of door?'\n3. 'Maybe I can help you if you let us go with you.'\n4. exit.");
                    }
                    vm.linearLayout.addView(secondText);
                } else if (myObjFive.contains("kenny")) {
                    vm.kenny.conversationStatus = 1;
                    secondText.setText("Dialogue Kenny.\n\n1. 'What do you know about The Hitchhiker's Guide to the Galaxy?'\n2. 'If there was... a fugitive Traveller alive, do you think the Space Federation will hunt him down?'\n3. 'Hey man... Do you need company?'\n4. exit.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjFive.contains("henry")) {
                    vm.henry.conversationStatus = 1;
                    secondText.setText("Dialogue Henry.\n\n1. 'What is on the other side of the office door?'\n2. 'You come here a lot, don't you?'\n3. exit.");
                    vm.linearLayout.addView(secondText);
                } else if (myObjFive.contains("icarus")) {
                    if (!vm.myself.hasKidIcarus) {
                        vm.icarus.conversationStatus = 1;
                        if (icarusChatPhase == 1) {
                            secondText.setText("Dialogue Icarus.\n\n1. 'Hi. That's a great book right there.'\n2. 'What do you know about the Travellers?'\n3. exit.");
                        } else {
                            secondText.setText("Dialogue Icarus.\n\n1. 'Hi. That's a great book right there.'\n2. 'What do you know about the Travellers?'\n3. 'I'm about to drop an ion bomb at B903, but I guess you don't really care.'\n4. exit.");
                        }
                    } else {
                        secondText.setText("Icarus doesn't want to talk with you.");
                    }
                    vm.linearLayout.addView(secondText);
                } else if (myObjFive.contains("shannon")) {
                    vm.shannon.conversationStatus = 1;
                    secondText.setText("Dialogue Shannon.\n\n1. 'Do you enjoy living at B903?'\n2. 'What is your job here?'\n3. 'By the way, I absolutely love the book you're reading.'\n4. exit.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("This person's name is incorrect or he/she is not in the place.");
                    vm.linearLayout.addView(secondText);
                }
            }
        } else if (myObjFive.contains("stop")) {  // STOP VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFive.contains("voices")) {
                secondText.setText("You are not hearing voices... Not yet.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("time")) {
                secondText.setText("That's useless.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("There's nothing you can stop here.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjFive.matches("close")) {  // CLOSE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("close")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFive.contains("door")) {
                secondText.setText("The door is already closed.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("eye")) {
                secondText.setText("This is not really useful right now.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjFive.matches("open")) {  // OPEN VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("open")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFive.contains("door")) {
                secondText.setText("Locked.");
                vm.linearLayout.addView(secondText);
            } else if ((myObjFive.contains("entry") || myObjFive.contains("library") || myObjFive.contains("entrance")) && myObjFive.contains("door")) {
                secondText.setText("You are not leaving without Fred, are you?");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("eye")) {
                secondText.setText("Your eyes are wide open.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("window")) {
                if (!vm.lwLibrary.hasBrokenWindow) {
                    secondText.setText("The wind feels fresh on your skin.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("You actually broke it, so...");
                    vm.linearLayout.addView(secondText);
                }
            } else if (myObjFive.contains("book")) {
                secondText.setText("You will have to take a book first.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // TURN OFF VERB
        else if (myObjFive.matches("turn off") || myObjFive.matches("turn it off") || myObjFive.matches("shut down") || myObjFive.matches("shut it down")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjFive.contains("turn") && myObjFive.contains("off")) || (myObjFive.contains("shut") && myObjFive.contains("down"))) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFive.contains("alarm")) {
                secondText.setText("No alarm is sounding.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("music")) {
                secondText.setText("No music is being played.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be turned off or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        } else if (myObjFive.contains("turn on") || myObjFive.contains("turn it on")) {  // TURN ON VERB
            secondText.setText("There's nothing you can turn on here.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("stand") || myObjFive.contains("get up")) {  // STAND VERB
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("You are already standing.");
            vm.linearLayout.addView(secondText);
        }
        // LIE VERB
        else if (myObjFive.contains("sit down") || myObjFive.matches("sit") || myObjFive.contains("lie")) {
            secondText.setText("There's no chairs left.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.matches("examine")) {  // EXAMINE VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("examine")) {
            if (myObjFive.contains("window")) {
                secondText.setText("Library views are no different from your house ones. As always, you see the same desert with the same pieces of metal scattered on the ground.\nAt least you can spot a part of the houses that were buried during last week's sand storm.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("people")) {
                if (vm.lwLibrary.hasPeopleLeft) {
                    secondText.setText("They are all gone.");
                } else {
                    secondText.setText("They are reading peacefully.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("book") || myObjFive.contains("shelve")) {
                secondText.setText("Go and SPEAK with the librarian to ask for a book.\nRemember: say SPEAK and the name of the person you wanna speak with.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("sully") || myObjFive.contains("librarian")) {
                if (vm.lwLibrary.hasPeopleLeft) {
                    secondText.setText("Sully is not here to help you.");
                } else {
                    secondText.setText("Sully seems an almost depressed man with white hair and mustache.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("cabin")) {
                secondText.setText("This thing is bulletproof. You couldn't get in there even if you want to.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("door")) {
                secondText.setText("It is just a door.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("pinboard") || myObjFive.contains("note")) {
                vm.lwLibrary.isReadingPinboard = true;
                secondText.setText("Reading pinboard.\n\n1. Buzz Ruzzinsky's live in concert.\n2. Borrowed books.\n3. Ludlow Post 11 21 1959.\n4. The Message 06 04 1672.\n5. exit.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be examined or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // TAKE GET VERB
        else if (myObjFive.matches("take") || myObjFive.matches("get") || myObjFive.matches("pick") || myObjFive.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("take") || myObjFive.contains("get") || myObjFive.contains("pick") || myObjFive.contains("grab")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if ((myObjFive.contains("glass") || myObjFive.contains("crystal")) && vm.lwLibrary.hasBrokenWindow) {
                if (vm.myself.inventory.contains("broken glass")) {
                    secondText.setText("You already took it.");
                } else {
                    vm.myself.inventory.add("broken glass");
                    secondText.setText("Taken.");
                }
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("book") || myObjFive.contains("shelve")) {
                secondText.setText("Go and SPEAK with the librarian to ask for a book.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("note")) {
                secondText.setText("Notes cannot be taken, but you can read them.");
                vm.linearLayout.addView(secondText);
            } else if (!vm.lwLibrary.objectsDroppedHall.isEmpty()) {
                int n;
                if (myObjFive.contains("take") || myObjFive.contains("pick") || myObjFive.contains("grab")) {
                    n = 4;
                } else if (myObjFive.contains("get")) {
                    n = 3;
                } else {
                    n = 0;
                }
                String s = myObjFive.substring(n);
                s = s.replace(" the ","");
                s = s.replace(" a ","");
                s = s.replace(" an ","");
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
        } else if (myObjFive.matches("drop") || myObjFive.contains("get rid of")) {  // DROP VERB
            secondText.setText("Just say: Drop (and the object you want to drop).");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("drop")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            int n = 4;
            String s = myObjFive.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (vm.myself.inventory.contains(s)) {
                vm.myself.inventory.remove(s);
                vm.lwLibrary.objectsDroppedHall.add(s);
                secondText.setText("Dropped.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("You're not holding the " + s + ".");
                vm.linearLayout.addView(secondText);
            }
        }
        // HELP VERB
        else if (myObjFive.matches("help") && vm.score - vm.lwLibrary.scoreWhenEnteringLibrary >= 6) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Find that hitchhiker's book.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.matches("help")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Try to do things by yourself first.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("help")) {
            secondText.setText("Just say HELP.");
            vm.linearLayout.addView(secondText);
        }
        // FOLLOW VERB
        else if (myObjFive.matches("follow") || myObjFive.matches("chase") || myObjFive.matches("find") || myObjFive.matches("search")) {
            secondText.setText("This verb needs to be used with a name.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("follow") || myObjFive.contains("chase") || myObjFive.contains("find") || myObjFive.contains("search")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (vm.lwLibrary.hasPeopleLeft) {
                secondText.setText("You can't follow anyone. You don't know where they went.");
                vm.linearLayout.addView(secondText);
            } else {
                if (myObjFive.contains("fred")) {
                    secondText.setText("Fred is probably talking with his friend. They surely want to be alone.");
                    vm.linearLayout.addView(secondText);
                } else {
                    secondText.setText("This person's name is incorrect or he/she cannot be followed.");
                    vm.linearLayout.addView(secondText);
                }
            }
        } else if (myObjFive.contains("drink")) {  // DRINK VERB
            secondText.setText("There is nothing you can drink here.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("write")) {  // WRITE VERB
            secondText.setText("You cannot write new notes.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.matches("read")) {  // READ VERB
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("read")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFive.contains("note") || myObjFive.contains("pinboard")) {
                vm.lwLibrary.isReadingPinboard = true;
                secondText.setText("Reading pinboard.\n\n1. Buzz Ruzzinsky's live in concert.\n2. Borrowed books.\n3. Ludlow Post 11 21 1959.\n4. The Message 06 04 1672.\n5. exit.");
                vm.linearLayout.addView(secondText);
            } else if (myObjFive.contains("book")) {
                secondText.setText("You are not holding a book.");
                vm.linearLayout.addView(secondText);
            } else {
                secondText.setText("This thing cannot be read or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // BREAK VERB
        else if (myObjFive.matches("break") || myObjFive.matches("hit") || myObjFive.matches("attack") || myObjFive.matches("punch") || myObjFive.matches("fight") || myObjFive.matches("kick")) {
            secondText.setText("This verb needs to be used with a noun.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("break") || myObjFive.contains("hit") || myObjFive.contains("attack") || myObjFive.contains("punch") || myObjFive.contains("fight") || myObjFive.contains("kick")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            if (myObjFive.contains("people") || myObjFive.contains("person") || myObjFive.contains("sully") || myObjFive.contains("librarian")) {
                if (vm.lwLibrary.hasPeopleLeft) {
                    secondText.setText("There's no one here you can attack.");
                    vm.linearLayout.addView(secondText);
                } else {
                    vm.lwLibrary.hasPeopleLeft = true;
                    vm.lwLibrary.scoreWhenPeopleLeave = vm.score;
                    secondText.setText("After showing your true violent self, everybody leaves the place running.\nYou are left here alone.");
                    vm.linearLayout.addView(secondText);
                }
            } else if (myObjFive.contains("window")) {
                if (!vm.lwLibrary.hasBrokenWindow && vm.lwLibrary.hasPeopleLeft) {
                    vm.lwLibrary.hasBrokenWindow = true;
                    secondText.setText("And now the window is broken. It's a mess.");
                    vm.linearLayout.addView(secondText);
                } if (vm.lwLibrary.hasBrokenWindow) {
                    secondText.setText("The window is already broken.");
                    vm.linearLayout.addView(secondText);
                } else {
                    vm.lwLibrary.hasBrokenWindow = true;
                    vm.lwLibrary.hasPeopleLeft = true;
                    vm.lwLibrary.scoreWhenPeopleLeave = vm.score;
                    secondText.setText("You broke the window. After showing your true violent self, everybody leaves the place running.\nYou are left here alone.");
                    vm.linearLayout.addView(secondText);
                }
            } else if (myObjFive.contains("cabin") || myObjFive.contains("shelve") || myObjFive.contains("door")) {
                if (vm.lwLibrary.hasPeopleLeft) {
                    secondText.setText("You are all alone and hitting stuff. You should be proud.");
                    vm.linearLayout.addView(secondText);
                } else {
                    vm.lwLibrary.hasPeopleLeft = true;
                    vm.lwLibrary.scoreWhenPeopleLeave = vm.score;
                    secondText.setText("After showing your true violent self, everybody leaves the place running.\nYou are left here alone.");
                    vm.linearLayout.addView(secondText);
                }
            } else {
                secondText.setText("This thing cannot be hit or it is not in the place.");
                vm.linearLayout.addView(secondText);
            }
        }
        // WINDOW ACTIONS
        else if ((myObjFive.contains("fix") || myObjFive.contains("repair")) && myObjFive.contains("window")) {
            secondText.setText("Not your business.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("jump") && myObjFive.contains("window")) {
            secondText.setText("You are not a savage.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("window")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Library views are no different from your house ones. As always, you see the same desert with the same pieces of metal scattered on the ground.\nAt least you can spot a part of the houses that were buried during last week's sand storm.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjFive.contains("look") && myObjFive.contains("around")) || myObjFive.matches("l") || myObjFive.matches("look")) {
            String intro = "Library\n";
            if (vm.lwLibrary.hasBrokenWindow && vm.lwLibrary.hasPeopleLeft) {
                intro = intro + "There are some book shelves and a large broken window.\nSully's cabin is empty.";
            } else if (vm.lwLibrary.hasPeopleLeft) {
                intro = intro + "There are some book shelves and a large window.\nSully's cabin is empty.";
            } else {
                if (!vm.lwLibrary.hasReadBorrowedBooks) {
                    intro = intro + "There are five people reading, some book shelves and a large window.\nSully, the librarian, is inside his crystal cabin.";
                } else {
                    intro = intro + "There are some book shelves and a large window.\nSully, the librarian, is inside his crystal cabin.\nShannon, Icarus, Henry, Abigail and Kenny are here.";
                }
            }
            intro = intro + " There is the closed office door and a pinboard with a few notes here.";
            if (!vm.lwLibrary.objectsDroppedHall.isEmpty()) {
                int n = vm.lwLibrary.objectsDroppedHall.size();
                for (int i=0; i<n; i++) {
                    intro = intro + "\nThere's the " + vm.lwLibrary.objectsDroppedHall.get(i) + " here.";
                }
            }
            secondText.setText(intro);
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.matches("i") || myObjFive.matches("inventory")) {
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
        } else if (myObjFive.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.matches("sleep")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Amazing strategy.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("check") && myObjFive.contains("out")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("check") || myObjFive.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("enter")) {
            secondText.setText("Try to open the doors of the places you want to get in.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("jump") || myObjFive.contains("climb") || myObjFive.contains("turn") || myObjFive.contains("shut") || myObjFive.contains("look") || myObjFive.contains("see") || myObjFive.contains("watch") || myObjFive.contains("play") || myObjFive.contains("run") || myObjFive.contains("walk") || myObjFive.contains("eat") || myObjFive.contains("move") || myObjFive.contains("put") || myObjFive.contains("give") || myObjFive.contains("offer")) {
            vm.score++;
            vm.myMoves.setText("Moves: " + vm.score);
            secondText.setText("Look around you.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("smell")) {
            secondText.setText("It smells just like a library.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("listen")) {
            secondText.setText("You just hear the pleasant sound of the pages.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("wait")) {
            secondText.setText("Time passes...");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.contains("what") && myObjFive.contains("time") && myObjFive.contains("is") || myObjFive.matches("time") || myObjFive.matches("the time")) {
            secondText.setText("It is daylight.");
            vm.linearLayout.addView(secondText);
        } else if (myObjFive.matches("diagnostic") || myObjFive.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            vm.linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }
}
