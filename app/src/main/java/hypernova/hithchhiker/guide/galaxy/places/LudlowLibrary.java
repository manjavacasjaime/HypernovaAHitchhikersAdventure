package hypernova.hithchhiker.guide.galaxy.places;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import hypernova.hithchhiker.guide.galaxy.characters.Henry;
import hypernova.hithchhiker.guide.galaxy.characters.Kenny;
import hypernova.hithchhiker.guide.galaxy.characters.Shannon;
import hypernova.hithchhiker.guide.galaxy.characters.Sully;

public class LudlowLibrary extends AppCompatActivity {
    SharedPreferences sharedPrefs;
    public int scoreWhenEnteringLibrary;
    public int scoreWhenEnteringRoomD;
    public int scoreWhenPeopleLeave;
    public boolean isReadingPinboard = false;
    public boolean hasReadBorrowedBooks;
    public boolean hasKnownArmoredPeople;
    public boolean hasPeopleLeft;
    public boolean hasBrokenWindow;
    public boolean hasChosenCorrectDoor;
    public String currentLocation; // hall, room D
    public ArrayList<String> objectsDroppedHall = new ArrayList<>();
    public ArrayList<String> objectsDroppedRoomD = new ArrayList<>();

    public LudlowLibrary(SharedPreferences sharedPreferences) {
        sharedPrefs = sharedPreferences;
    }

    public void initiateVariables() {
        scoreWhenEnteringLibrary = 0;
        scoreWhenEnteringRoomD = 0;
        scoreWhenPeopleLeave = 0;
        hasReadBorrowedBooks = false;
        hasKnownArmoredPeople = false;
        hasPeopleLeft = false;
        hasBrokenWindow = false;
        hasChosenCorrectDoor = false;
        currentLocation = "hall";
        objectsDroppedHall.clear();
        objectsDroppedRoomD.clear();
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putInt("ludlowLibrary.scoreWhenEnteringLibrary", scoreWhenEnteringLibrary);
        editor.putInt("ludlowLibrary.scoreWhenEnteringRoomD", scoreWhenEnteringRoomD);
        editor.putInt("ludlowLibrary.scoreWhenPeopleLeave", scoreWhenPeopleLeave);
        editor.putBoolean("ludlowLibrary.hasReadBorrowedBooks", hasReadBorrowedBooks);
        editor.putBoolean("ludlowLibrary.hasKnownArmoredPeople", hasKnownArmoredPeople);
        editor.putBoolean("ludlowLibrary.hasPeopleLeft", hasPeopleLeft);
        editor.putBoolean("ludlowLibrary.hasBrokenWindow", hasBrokenWindow);
        editor.putBoolean("ludlowLibrary.hasChosenCorrectDoor", hasChosenCorrectDoor);
        editor.putString("ludlowLibrary.currentLocation", currentLocation);

        Set<String> objectsDroppedHallSet = new HashSet<>(objectsDroppedHall);
        editor.putStringSet("ludlowLibrary.objectsDroppedHallSet", objectsDroppedHallSet);
        Set<String> objectsDroppedRoomDSet = new HashSet<>(objectsDroppedRoomD);
        editor.putStringSet("ludlowLibrary.objectsDroppedRoomDSet", objectsDroppedRoomDSet);

        editor.commit();
    }

    public void restore() {
        Set<String> emptyset = new HashSet<>();
        scoreWhenEnteringLibrary = sharedPrefs.getInt("ludlowLibrary.scoreWhenEnteringLibrary", 0);
        scoreWhenEnteringRoomD = sharedPrefs.getInt("ludlowLibrary.scoreWhenEnteringRoomD", 0);
        scoreWhenPeopleLeave = sharedPrefs.getInt("ludlowLibrary.scoreWhenPeopleLeave", 0);
        hasReadBorrowedBooks = sharedPrefs.getBoolean("ludlowLibrary.hasReadBorrowedBooks", false);
        hasKnownArmoredPeople = sharedPrefs.getBoolean("ludlowLibrary.hasKnownArmoredPeople", false);
        hasPeopleLeft = sharedPrefs.getBoolean("ludlowLibrary.hasPeopleLeft", false);
        hasBrokenWindow = sharedPrefs.getBoolean("ludlowLibrary.hasBrokenWindow", false);
        hasChosenCorrectDoor = sharedPrefs.getBoolean("ludlowLibrary.hasChosenCorrectDoor", false);
        currentLocation = sharedPrefs.getString("ludlowLibrary.currentLocation", "hall");

        Set<String> objectsDroppedHallSet = sharedPrefs.getStringSet("ludlowLibrary.objectsDroppedHallSet", emptyset);
        objectsDroppedHall = new ArrayList<>(objectsDroppedHallSet);
        Set<String> objectsDroppedRoomDSet = sharedPrefs.getStringSet("ludlowLibrary.objectsDroppedRoomDSet", emptyset);
        objectsDroppedRoomD = new ArrayList<>(objectsDroppedRoomDSet);
    }

    public void displayPinboardNotes(String note, LinearLayout linearLayout, TextView secondText) {
        switch (note) {
            case "1":
                secondText.setText("LUDLOW B903\nOPEN AIR\n1994      Buzz Ruzzinsky\n          Live in concert\n10.6.     Stadium\n8:00 P.M.");
                linearLayout.addView(secondText);
                break;
            case "2":
                hasReadBorrowedBooks = true;
                secondText.setText("Shannon --> Lucía Clemente - Jane Lester.\nIcarus --> Mr. Canterman - Singularity Paradox.\nHenry --> Mr. Canterman - What do we know it's real?.\nAbigail --> Douglas Adams - The Hitchhiker's Guide to the Galaxy.\nKenny --> Gabe Lee - Ludlow's History.");
                linearLayout.addView(secondText);
                break;
            case "3":
                secondText.setText("Dear readers,\nJoseph Lovehart, the B903 opposition leader, is leaving office due to a personal tragedy.\nIt was a car accident that took the lives of his wife and one of his daughters.\nLovehart, the driver, is currently waiting for the results of his other daughter at the hospital.");
                linearLayout.addView(secondText);
                break;
            case "4":
                secondText.setText("The citizens voice has been heard. The public library is being built starting next Monday. Hopefully, it will provide the knowledge that people demand.");
                linearLayout.addView(secondText);
                break;
            case "5":
                isReadingPinboard = false;
                secondText.setText("Reading pinboard finished.");
                linearLayout.addView(secondText);
                break;
            default:
                secondText.setText("Write the number of the option you want.\n\n\n" +
                        "Reading pinboard.\n\n1. Buzz Ruzzinsky's live in concert.\n2. Borrowed books.\n3. Ludlow Post 11 21 1959.\n4. The Message 06 04 1672.\n5. exit.");
                linearLayout.addView(secondText);
                break;
        }
    }

    public void displayConversationShannon(String option, Shannon shannon, LinearLayout linearLayout, TextView secondText) {
        switch (option) {
            case "1":
                secondText.setText("I guess you don't think about that when you know you can't live the planet.");
                linearLayout.addView(secondText);
                break;
            case "2":
                secondText.setText("Oh. I don't work here.\nI work in a mechanincs workshop where I customize the cars in order to resist the sand storms.\nThey are mostly cars owned by the upper-class people.");
                linearLayout.addView(secondText);
                break;
            case "3":
                secondText.setText("Thanks a lot! When the world starts becoming crazy, books help me keep my feet on the ground.");
                linearLayout.addView(secondText);
                break;
            case "4":
                shannon.conversationStatus = 0;
                secondText.setText("Dialogue Shannon finished.");
                linearLayout.addView(secondText);
                break;
            default:
                secondText.setText("You just said: jdfjwurnrvjwq.\nWrite the number of the option you want.\n\n\n" +
                        "Dialogue Shannon.\n\n1. 'Do you enjoy living at B903?'\n2. 'What is your job here?'\n3. 'By the way, I absolutely love the book you're reading.'\n4. exit.");
                linearLayout.addView(secondText);
                break;
        }
    }

    public void displayConversationSully(String option, Sully sully, LinearLayout linearLayout, TextView secondText) {
        switch (option) {
            case "1":
                secondText.setText("And I want a Thai massage and a pizza. Look kid, I have a lot of work to do. You can check the borrowed books at the pinboard on the wall.");
                linearLayout.addView(secondText);
                break;
            case "2":
                hasKnownArmoredPeople = true;
                secondText.setText("I'm not a coward or anything like that. It is just that sometimes these people coming with armor make me feel uneasy.");
                linearLayout.addView(secondText);
                break;
            case "3":
                sully.conversationStatus = 0;
                secondText.setText("Dialogue Sully, the librarian finished.");
                linearLayout.addView(secondText);
                break;
            default:
                secondText.setText("You just said: ppqwpfejwñen.\nWrite the number of the option you want.\n\n\n" +
                        "Dialogue Sully, the librarian.\n\n1. 'Excuse me. I want to ask for a book.'\n2. 'Why is this cabin bulletproof?'\n3. exit.");
                linearLayout.addView(secondText);
                break;
        }
    }

    public void displayConversationHenry(String option, Henry henry, LinearLayout linearLayout, TextView secondText) {
        switch (option) {
            case "1":
                secondText.setText("Your lower-class friends shall be chatting and avoiding work as they use to do. And this is how the Bubble will prevail...");
                linearLayout.addView(secondText);
                break;
            case "2":
                secondText.setText("Indeed, my curious partner. A gentleman like me has to enrich his knowledge daily.");
                linearLayout.addView(secondText);
                break;
            case "3":
                henry.conversationStatus = 0;
                secondText.setText("Dialogue Henry finished.");
                linearLayout.addView(secondText);
                break;
            default:
                secondText.setText("You just said: mknewbfowc.\nWrite the number of the option you want.\n\n\n" +
                        "Dialogue Henry.\n\n1. 'What is on the other side of the office door?'\n2. 'You come here a lot, don't you?'\n3. exit.");
                linearLayout.addView(secondText);
                break;
        }
    }

    public void displayConversationKenny(String option, Kenny kenny, LinearLayout linearLayout, TextView secondText) {
        switch (option) {
            case "1":
                secondText.setText("It was a book written back at the hitchhiker years in B526.\nAfter the destruction of that Bubble, the book got forgotten.");
                linearLayout.addView(secondText);
                break;
            case "2":
                secondText.setText("They will send the Federation Agents to burn him alive and to make sure that no one will ever know about his existence.\n\nThat's what they do. That's what people do. As a matter of fact, people hate people. But you can be different, can't you?");
                linearLayout.addView(secondText);
                break;
            case "3":
                secondText.setText("Do I look like I need that? I would like many things in the universe, but company is not one of them.\nSometimes people don't even deserve the Bubble they live in.");
                linearLayout.addView(secondText);
                break;
            case "4":
                kenny.conversationStatus = 0;
                secondText.setText("Dialogue Kenny finished.");
                linearLayout.addView(secondText);
                break;
            default:
                secondText.setText("You just said: sadflhjsdf.\nWrite the number of the option you want.\n\n\n" +
                        "Dialogue Kenny.\n\n1. 'What do you know about The Hitchhiker's Guide to the Galaxy?'\n2. 'If there was... a fugitive Traveller alive, do you think the Space Federation will hunt him down?'\n3. 'Hey man... Do you need company?'\n4. exit.");
                linearLayout.addView(secondText);
                break;
        }
    }
}
