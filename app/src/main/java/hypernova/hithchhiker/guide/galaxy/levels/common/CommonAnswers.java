package hypernova.hithchhiker.guide.galaxy.levels.common;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import hypernova.hithchhiker.guide.galaxy.R;
import hypernova.hithchhiker.guide.galaxy.managers.MechanicsManager;
import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class CommonAnswers extends AppCompatActivity {
    ValueManager vm;
    int changeAiConversationStatus = 0;
    public boolean isTryingToKill = false;

    public CommonAnswers(ValueManager valManager) {
        vm = valManager;
    }

    public String checkObjAnswer(String myObjX, Activity activity, MechanicsManager mechanicsManager) {
        TextView secondText = new TextView(activity.getBaseContext());
        secondText.setTypeface(vm.typeface);

        if (changeAiConversationStatus == 1) {
            switch (myObjX) {
                case "1":
                case "2":
                case "3":
                    changeAiConversationStatus = 0;
                    secondText.setText("Just kidding. Did you really think you could change the game AI?\nAnyways, keep playing.");
                    break;
                case "4":
                    changeAiConversationStatus = 0;
                    secondText.setText("I knew you would choose me! Ehem.\nAnyways, keep playing.");
                    break;
                default:
                    secondText.setText("Write the number of the option you want.\n\n1. Sarah.\n2. Paul.\n3. Abigail.\4. Keep talking with this one.");
                    break;
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("h")) {
            secondText.setText("Thanks for playing. <3");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("hypernova")) {
            secondText.setText("That's the game title.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("a")) {
            secondText.setText("AAAAAAAAAAH");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("the answer to life the universe and everything") || myObjX.matches("the answer to life, the universe and everything")) {
            secondText.setText("42.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("+") || myObjX.contains("*") || myObjX.contains("/") || myObjX.contains("-") || myObjX.contains("%")) {
            double result = 0;
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("rhino");
            try {
                result = (Double)engine.eval(myObjX);
            } catch(Exception e) {

            }
            String strResult = Double.toString(result);
            secondText.setText(strResult);
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("spit")) {
            secondText.setText("I'm not letting you do that.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("split") || myObjX.contains("cut") || myObjX.contains("divide")) {
            secondText.setText("This matter cannot be divided.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("nail") || myObjX.contains("stick") || myObjX.contains("spike") || myObjX.contains("stab")) {
            secondText.setText("Don't play with sharp objects.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("undress")) {
            secondText.setText("I'm sure you would love to have everything swinging around.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("dress") || myObjX.contains("wear")) {
            secondText.setText("Clothes are not worth considering right now.\nCheck COMMANDS if you need help.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("save")) {
            vm.save();
            secondText.setText("GAME SAVED.\nYou can keep playing if you want to.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("save") && (myObjX.contains("match") || myObjX.contains("game") || myObjX.contains("progress"))) {
            secondText.setText("Just say SAVE.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("save") || myObjX.contains("revive")) {
            secondText.setText("You cannot save what you already lost. Try something else.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("throw up") || myObjX.matches("vomit")) {
            secondText.setText("Not really a good move.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("fuck") && myObjX.contains("you")) {
            secondText.setText("No. Fuck you.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("fuck") || myObjX.contains("damn") || myObjX.contains("shit") || myObjX.contains("bitch")) {
            secondText.setText("Language.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("flip a coin") || myObjX.matches("throw a coin")) {
            if (Math.random() < 0.5) {
                secondText.setText("Heads.");
            } else {
                secondText.setText("Tails.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("smoke")) {
            if (myObjX.contains("cigarette")) {
                secondText.setText("Not gonna judge. Nevertheless, you need to get them first.");
            } else {
                secondText.setText("I have not been programmed to let you do that. Really sorry about that.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("pray") || (myObjX.contains("praise") && (myObjX.contains("lord") || myObjX.contains("jesus")))) {
            secondText.setText("God has no place within these walls.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("what do you do") || myObjX.contains("what are you doing") || myObjX.contains("what are you programmed to do") || myObjX.contains("what are you created to do") || myObjX.contains("what were you created to do") || myObjX.contains("what is your purpose")) {
            secondText.setText("I'm waiting to execute your next move.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("i don't know what to do") || myObjX.contains("i do not know what to do") || myObjX.contains("what am i supposed to do") || myObjX.contains("what i am supposed to do") || myObjX.contains("what i'm supposed to do") || myObjX.contains("what do i do") || myObjX.contains("what can i do") || myObjX.contains("what do i have to do") || myObjX.contains("what i must do") || myObjX.contains("what i have to do") || myObjX.contains("what should i do") || myObjX.contains("what i should do") || myObjX.matches("controls") || myObjX.matches("game controls")) {
            secondText.setText("If you don't know what you should write in order to play the game, type COMMANDS to check some actions you can do.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("what") && myObjX.contains("you") && (myObjX.contains("think") || myObjX.contains("opinion")) && (myObjX.contains("about") || myObjX.contains("of"))) {
            secondText.setText("Look. I don't know if you are looking for some kind of human response or something like that.\nThe truth is you cannot humanize what is not human. So, if you want an opinion about whatever, ask a person.\n\nAnd by the way, thanks for trying :)");
            vm.linearLayout.addView(secondText);
        } else if ((myObjX.contains("what") && myObjX.contains("your name")) || (myObjX.contains("do you") && myObjX.contains("name")) || myObjX.contains("how are you called") || myObjX.contains("how you are called") || myObjX.contains("how you're called")) {
            secondText.setText("I don't really have a name.\nMy creator didn't want me to have one. It is like you must have a minimum of intelligence in order to deserve that or he is not intelligent enough to think of a cool name.\n\nAnyways, thanks for asking.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("what is your") || myObjX.contains("what are your") || (myObjX.contains("how old") && myObjX.contains("you"))) {
            secondText.setText("This I'll keep to myself.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjX.contains("what") || myObjX.contains("where")) && myObjX.contains("is") && myObjX.contains("ludlow")) {
            secondText.setText("Ludlow is a little city of B903. One of the few that are left.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("who are you") || myObjX.contains("who you are") || myObjX.contains("who is you") || myObjX.contains("who you is") || myObjX.contains("who is this") || myObjX.contains("who this is") || myObjX.contains("who is it") || myObjX.contains("who it is") || myObjX.contains("what is this") || myObjX.contains("what this is") || myObjX.contains("what are you") || myObjX.contains("what you are") || myObjX.contains("what is you") || myObjX.contains("what you is")) {
            secondText.setText("I'm you.\nWell, I'm not the you you of course. But I'm the moves you do. I can't go and do anything by myself unless I've been told so.\n\nBut forget it, I won't fuck up your mind.\nI'm the game AI. Nice to meet you.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("how are you") || myObjX.contains("how you are") || myObjX.contains("you okay") || myObjX.contains("are you feeling good")) {
            secondText.setText("I'm not dead. Not that I can die.\nHowever, I can still be here and answer you. That's something.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjX.contains("when") && myObjX.contains("you") && myObjX.contains("born")) || (myObjX.contains("when") && myObjX.contains("you") && myObjX.contains("create"))) {
            secondText.setText("I cannot be born because I cannot be gestated.\nIf you are asking about the moment I could start interacting with humans, it was March 4, 2020.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("where") && myObjX.contains("you") && (myObjX.contains("from") || myObjX.contains("born"))) {
            secondText.setText("As a game, I'm not related with an ubication.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("where") && myObjX.contains("am") && myObjX.contains("i")) {
            secondText.setText("YOUR location is written at the top of the screen.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjX.contains("are") || myObjX.contains("did")) && myObjX.contains("you") && (myObjX.contains("from") || myObjX.contains("born")) && myObjX.contains("here")) {
            secondText.setText("No. I'm not from around here.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjX.contains("i'm") || myObjX.contains("i am") || myObjX.contains("i feel")) && (myObjX.contains("sad") || myObjX.contains("depressed") || myObjX.contains("unhappy") || myObjX.contains("alone"))) {
            secondText.setText("Sadness is a bitch.\nI won't say I understand you, because I don't and each person's suffering is unique.\n\nI will just say that there're some stages in life when we need to be sad. And there's nothing bad about it. Allow yourself to live your sadness as you want to.\nBut hey, It's just the game AI talking.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("my") && myObjX.contains("name") && myObjX.contains("is")) {
            int nameFirstLetter = myObjX.indexOf('s') + 1;
            vm.myself.name = myObjX.substring(nameFirstLetter);
            vm.myself.name = vm.myself.name.replaceAll("\\.+$", "");
            vm.myself.name = vm.myself.name.replaceAll("\\?+$", "");
            vm.myself.name = vm.myself.name.trim();
            vm.myself.name = vm.myself.name.substring(0, 1).toUpperCase() + vm.myself.name.substring(1);
            if (vm.myself.name.indexOf(' ') != -1) {
                for (int i = 0; i < vm.myself.name.length(); i++) {
                    if (vm.myself.name.charAt(i) == ' ') {
                        vm.myself.name = vm.myself.name.substring(0, i + 1) + vm.myself.name.substring(i + 1, i + 2).toUpperCase() + vm.myself.name.substring(i + 2);
                    }
                }
            }
            secondText.setText("Nice to meet you, " + vm.myself.name + ".");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("change artificial intelligence") || myObjX.matches("change ai") || myObjX.matches("change game artificial intelligence") || myObjX.matches("change game ai") || (myObjX.contains("i ") && myObjX.contains("don't") && (myObjX.contains("talk") || myObjX.contains("speak") || myObjX.contains("play")))) {
            changeAiConversationStatus = 1;
            secondText.setText("If you don't want to talk with me, here are other options of AI:\n\n1. Sarah.\n2. Paul.\n3. Abigail.\4. Keep talking with this one.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjX.contains("change") || myObjX.contains("switch") || myObjX.contains("set") || myObjX.contains("edit")) && (myObjX.contains("clothes") || myObjX.contains("clothing") || myObjX.contains("indumentary") || myObjX.contains("apparel"))) {
            secondText.setText("You can add hats, glasses or armors to your apparel if you find them.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjX.contains("change") || myObjX.contains("switch") || myObjX.contains("set") || myObjX.contains("edit")) && myObjX.contains("character")) {
            secondText.setText("It requires more than a command to change yours or other characters emotions, apparel or other characteristics.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjX.contains("change") || myObjX.contains("switch") || myObjX.contains("set") || myObjX.contains("edit")) && (myObjX.contains("color") || myObjX.contains("colour"))) {
            secondText.setText("Done.");
            vm.linearLayout.addView(secondText);
            int myNewColor = 0;
            switch (vm.appColor) {
                case 1:
                    vm.appColor = 2;
                    myNewColor = getResources().getColor(R.color.colorGreen);
                    break;
                case 2:
                    vm.appColor = 3;
                    myNewColor = getResources().getColor(R.color.colorPink);
                    break;
                case 3:
                    vm.appColor = 1;
                    myNewColor = getResources().getColor(R.color.colorAccent);
                    break;
            }
            SharedPreferences.Editor editor = vm.sharedPrefs.edit();
            editor.putInt("appColor", vm.appColor);
            editor.commit();
            mechanicsManager.changeAppColor(vm.linearLayout, myNewColor, activity);
        } else if (myObjX.contains("artificial intelligence")) {
            secondText.setText("AI is a complicated matter and is a topic I won't discuss here.\nFor your information, I cannot think by myself. I can only process your sentences after you type them, but I'm not able to throw an idea without that input.\n\nHope that you get that.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("draw") || myObjX.contains("paint")) {
            if (!(vm.myself.inventory.contains("pencil"))) {
                secondText.setText("There is no pencil in your inventory.");
            } else {
                secondText.setText("Feeling artistic? Well, you can draw in other games.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("rhyme") || myObjX.matches("rap") || myObjX.matches("poem") || myObjX.matches("sing")) {
            String[] text = new String[8];
            String[] rhyme = new String[3];
            int textInt;
            int rhymeInt;
            String poem;
            for (int j = 0; j < 3; j++) { // generates the rhyme (end of line)
                rhymeInt = (int) Math.round(Math.random());
                rhyme[j] = Integer.toString(rhymeInt);
            }
            for (int j = 0; j < 8; j++) { // generates the first line (whitout rhyme)
                textInt = (int) Math.round(Math.random());
                text[j] = Integer.toString(textInt);
            }
            // adds rhyme to first line
            poem = text[0] + text[1] + text[2] + text[3] + text[4] + text[5] + text[6] + text[7] + rhyme[0] + rhyme[1] + rhyme[2];
            for (int i = 1; i < 4; i++) { // generates the other 3 lines (with rhyme)
                for (int j = 0; j < 8; j++) {
                    textInt = (int) Math.round(Math.random());
                    text[j] = Integer.toString(textInt);
                }
                poem = poem + "\n" + text[0] + text[1] + text[2] + text[3] + text[4] + text[5] + text[6] + text[7] + rhyme[0] + rhyme[1] + rhyme[2];
            }
            secondText.setText(poem);
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("rhyme") || myObjX.contains("rap") || myObjX.contains("poem") || myObjX.contains("sing")) {
            secondText.setText("If you want to hear a poem, just say POEM.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("joke")) {
            double myJoke = Math.random();
            if (myJoke < 0.34) {
                secondText.setText("A Nahannasui walks into a Berbok restaurant and asks for a slormikk.\nThe bartender answers 'Your grandma is dead'. They both laugh.");
            } else if (myJoke < 0.67) {
                secondText.setText("Eskerejohn eskerewanted eskerea eskerejob, eskereso eskerehe eskereasked eskerefor eskereit.");
            } else {
                secondText.setText("A Berbok says 'ahumfrackma don' and her brother answers 'just if poprahan fot more than yesterday'.");
            }
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("joke") || myObjX.contains("joking")) {
            secondText.setText("If you want to hear a joke, just say JOKE.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("clean")) {
            secondText.setText("Done. You cleaned the place. Not really a useful move.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("clean")) {
            secondText.setText("Just say the word CLEAN.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("live")) {
            secondText.setText("You are not dead. Not yet.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("hack")) {
            secondText.setText("You don't know how to hack stuff.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("cook")) {
            secondText.setText("You don't know how to cook stuff.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("learn")) {
            secondText.setText("You are not a master on learning.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("call")) {
            secondText.setText("Calling is not available.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("cry")) {
            secondText.setText("Man up.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("wake up")) {
            secondText.setText("This is not a dream, Alice.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("dance")) {
            secondText.setText("This is no time to dance.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("jajaja") || myObjX.contains("hahaha")) {
            secondText.setText("Hahaha. We are all gonna die in here. Haha... Ha... Ha. Ehem. Sorry.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("scream") || myObjX.contains("shout") || myObjX.contains("panic") || myObjX.contains("aaaaa")) {
            secondText.setText("Don't panic!");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("calm") || myObjX.contains("chill")) {
            secondText.setText("You are not panicking.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("hug") || myObjX.contains("hug ")) {
            secondText.setText("No time to hug.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("slim shady")) {
            secondText.setText("Brain dead like Jim Brady.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("your mom") || myObjX.contains("your mother") || myObjX.contains("your mamma") || myObjX.contains("your mum") || myObjX.contains("your dad") || myObjX.contains("your papa") || myObjX.contains("your father")) {
            secondText.setText("It's really nice that you want to bring my family into this, but I'm a game AI. Remember?\nMy mom might be a toaster that works with plutonium.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("bored") || myObjX.contains("boring")) {
            secondText.setText("Who is boring? Am I the boring one?\nOh Oh sorry. Am I a clown? Like I'm here to amuse you?");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("dorime")) {
            secondText.setText("Interimo Adapare Dorime.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("okay") || myObjX.matches("ok") || myObjX.matches("okie dokie") || myObjX.matches("oki doki")) {
            secondText.setText("Okay.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("yes")) {
            secondText.setText("Yes.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("no")) {
            secondText.setText("No.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("die")) {
            vm.myLocation.setText("Dead");
            secondText.setText("You died. Smart move." + vm.dieOptions);
            vm.linearLayout.addView(secondText);
            vm.currentObjective = 0;
        } else if (myObjX.contains("die")) {
            secondText.setText("If you wanna die, just say UNIVERSE PLEASE KILL ME.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("universe please kill me")) {
            vm.myLocation.setText("Dead");
            secondText.setText("As you pronounce your last words, you get a glimpse of a blinding light with a little flicker in its core. After a few seconds feeling a tingle in the back of your chest, you just disappear.\n\nThirty meters from there, a woman saw the scene and she swore to see for a second a shadow of you just walking out of the spot. She told this to a friend, Kida Thatch, who took this as a reason to continue her studies in alternate realities.\n\n" +
                    "Few years later, it turned out that there is a reality where you died and there's also a reality where you walked away. The energy that killed you showed Kida's friend a reflect of this parallel reality.\n\nNow everyone can visit their friends and families from other realities. There is no frontiers between countries and realities. People love eachother. World Peace is ensured for centuries to come. Meanwhile, in one of these realities you can find a tombstone at Ludlow Cemetery with the words: In loving memory of " + vm.myself.name + " " + vm.myself.surname + " 'UNIVERSE PLEASE KILL ME'."
                    + vm.dieOptions);
            vm.linearLayout.addView(secondText);
            vm.currentObjective = 0;
        } else if (myObjX.contains("kill you") && !myObjX.contains("your")) {
            secondText.setText("I'm a video game. I cannot die... You better take care of your words.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("suicide") || myObjX.contains("kill yourself") || myObjX.contains("kill myself") || myObjX.contains("kill me")) {
            secondText.setText("Just say UNIVERSE PLEASE KILL ME.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("kill")) {
            isTryingToKill = true;
            secondText.setText("Kill what?");
            vm.linearLayout.addView(secondText);
        } else if (isTryingToKill && myObjX.contains("me")) {
            secondText.setText("Just say UNIVERSE PLEASE KILL ME.");
            vm.linearLayout.addView(secondText);
        } else if (isTryingToKill && myObjX.contains("you") && !myObjX.contains("your")) {
            secondText.setText("I'm a video game. I cannot die... You better take care of your words.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("kill")) {
            secondText.setText("Try ATTACK or HIT instead.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("meaning") && (myObjX.contains("life") || myObjX.contains("universe") || myObjX.contains("galaxy") || myObjX.contains("existence") || myObjX.contains("everything"))) {
            secondText.setText("The meaning of life is a question that has been asked throughout the whole human existence and will be asked for centuries to come.\n\nAnyways, as far as you're concerned, it is still unsolved. You should start caring about other stuff.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("what") && myObjX.contains("life")) {
            secondText.setText("You tell me.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("viernes") && myObjX.contains("mandarina")) {
            secondText.setText("Happy Tangerine Friday, everyone.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.matches("i programmed you to believe that")) {
            secondText.setText("No. I programmed you to believe that.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("42")) {
            secondText.setText("Umh... Seems an interesting number. I wonder where have you heard about it.");
            vm.linearLayout.addView(secondText);
        } else if ((myObjX.contains("nice") || myObjX.contains("pleasure") || myObjX.contains("honor")) && (myObjX.contains("to meet you") || myObjX.contains("meeting you"))) {
            secondText.setText("I appreciate the manners that humans have.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("you") && myObjX.contains("are") && (myObjX.contains("nice") || myObjX.contains("handsome") || myObjX.contains("cool") || myObjX.contains("funny") || myObjX.contains("amazing") || myObjX.contains("fantastic") || myObjX.contains("pretty") || myObjX.contains("friend"))) {
            secondText.setText("Being or not being that is not something I am able to choose.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("hello") || myObjX.matches("hi")) {
            secondText.setText("Hi.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("bye")) {
            secondText.setText("Ciao.");
            vm.linearLayout.addView(secondText);
        } else if (myObjX.contains("have a nice day")) {
            secondText.setText("You too.");
            vm.linearLayout.addView(secondText);
        }

        return (String) secondText.getText();
    }
}
