package hypernova.hithchhiker.guide.galaxy;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import hypernova.hithchhiker.guide.galaxy.managers.LevelManager;
import hypernova.hithchhiker.guide.galaxy.managers.MechanicsManager;
import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gameearth);
    final TextView myMoves = (TextView) findViewById(R.id.moves);
    final TextView myLocation = (TextView) findViewById(R.id.location);
    final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
    public static int deletingLowbar = 0;
    ValueManager valueManager = new ValueManager();
    MechanicsManager mechanicsManager = new MechanicsManager();
    LevelManager levelManager = new LevelManager(valueManager);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //splash();
        earth(); //this will be removed
    }

    /*public void splash() {
        ImageView stT = (ImageView) findViewById(R.id.startTitle);
        AlphaAnimation ani = new AlphaAnimation(1.0f, 0.0f);
        ani.setDuration(1000);
        ani.setFillAfter(true);
        stT.startAnimation(ani);

        if (true) {
            earth(); //this will be used to check the reload
        }
    }*/

    public void earth() {
        valueManager.initiateVariables();

        switch (valueManager.appColor) {
            case 1:
                mechanicsManager.changeAppColor(linearLayout, getResources().getColor(R.color.colorAccent), this);
                break;
            case 2:
                mechanicsManager.changeAppColor(linearLayout, getResources().getColor(R.color.colorGreen), this);
                break;
            case 3:
                mechanicsManager.changeAppColor(linearLayout, getResources().getColor(R.color.colorPink), this);
                break;
        }

        TextView firstText = new TextView(this);
        firstText.setTypeface(typeface);

        myMoves.setText("Moves: " + valueManager.score);
        myLocation.setText("Just playing");
        firstText.setText(valueManager.myself.name + ".\nIt's April 16, 2019. You are in B903, one of the Bubbles regulated by the Space Federation.\nIt's been 298 years, 5 months and 13 days since the last hitchhiker, now referred to as Traveller, died.\n\nBasement\nYou find yourself sat down playing video games. Your eyes aren't able to stop looking at the screen.\n\n-Say COMMANDS if you need help.\n(Click below the text to write)");
        linearLayout.addView(firstText);
        obj();
    }

    public void keepLowbarWhileWriting(final EditText objx) {
        if (deletingLowbar == 0) {
            int auxStringLength = objx.getText().toString().length();
            if (auxStringLength > prevStringLength) {
                prevStringLength = auxStringLength;

                handler.removeCallbacks(myRunnable);
                callbacksRemoved = true;
                if (blinkcolor == 0) {
                    callbacksRemoved = false;
                    blinkLowbar(objx);
                }
                if (callbacksRemoved) {
                    handler.postDelayed(myRunnable, 750);
                    callbacksRemoved = false;
                }

            } else if (auxStringLength < prevStringLength) {
                prevStringLength = auxStringLength;
            }
        }
    }

    public void blinkLowbar(final EditText objx) {
        final String text = "_";
        Spannable modifiedText = new SpannableString(text);
        if (blinkcolor == 1) {
            modifiedText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            blinkcolor = 0;
        } else {
            switch (valueManager.appColor) {
                case 1:
                    modifiedText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                case 2:
                    modifiedText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorGreen)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                case 3:
                    modifiedText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPink)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
            }
            blinkcolor = 1;
        }
        Editable s = objx.getText();
        int lowbar = s.toString().indexOf('_');
        if (lowbar != -1) {
            deletingLowbar = 1;
            s.delete(lowbar, lowbar + 1);
            s.insert(lowbar, modifiedText);
            deletingLowbar = 0;
            objx.setSelection(lowbar);
        }

        handler = new Handler();
        myRunnable = new Runnable() {
            @Override
            public void run() {
                blinkLowbar(objx);
            }
        };
        handler.postDelayed(myRunnable, 750);
    }

    public void setInputListeners(final EditText objx, final LinearLayout linearLayout, final Typeface typeface, final TextView secondText) {
        objx.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().contains("_") && deletingLowbar == 0) {
                    int pos = objx.getSelectionStart();
                    s.insert(pos, "_");
                }

                if (!s.toString().startsWith(">")) {
                    pos = -1;
                    edittouched = 0;
                    firstclicked = 0;
                    lastposselected = 0;
                    objx.setText(">_");
                    deletingLowbar = 0;
                    Selection.setSelection(objx.getText(), objx.getText().length());
                }

                if (!s.toString().endsWith("_") && (edittouched == 0 || lastposselected == 1)) {
                    int lowbar = s.toString().indexOf('_');
                    if (lowbar != -1) {
                        deletingLowbar = 1;
                        s.delete(lowbar, lowbar + 1);
                        s.insert(lowbar + 1, "_");
                        deletingLowbar = 0;
                    }
                }
                keepLowbarWhileWriting(objx);
            }
        });

        objx.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int aux = objx.getOffsetForPosition(event.getX(), event.getY());
                if (aux != pos || deletingLowbar == 1) {
                    pos = aux;
                    if (objx.getText().toString().matches(">")) {
                        objx.setText(">_");
                        deletingLowbar = 0;
                        if (handler != null) handler.removeCallbacks(myRunnable);
                        blinkLowbar(objx);

                    } else if (pos != 0) {
                        if (firstclicked == 1) {
                            edittouched = 1;
                            String s = objx.getText().toString();
                            if (pos != s.length()) {
                                lastposselected = 0;
                            }
                            int lowbar = s.indexOf('_');
                            if (lowbar != -1) {
                                deletingLowbar = 1;
                                s = s.substring(0, lowbar) + s.substring(lowbar + 1); // quito lowbar
                            }

                            if (pos == s.length() + 1) pos--;
                            s = s.substring(0, pos) + "_" + s.substring(pos); // añado lowbar en pos
                            deletingLowbar = 0;
                            objx.setText(s);
                            if (pos == s.length() - 1) {
                                lastposselected = 1;
                            }
                        } else {
                            firstclicked++;
                        }
                    }
                }

                return false;
            }
        });

        objx.setOnKeyListener(new View.OnKeyListener() {
                  public boolean onKey(View v, int keyCode, KeyEvent event) {
                      //KeyListener pilla muy pocas teclas en android mobile,
                      //no coge carácteres por ejemplo
                      if (keyCode==KeyEvent.KEYCODE_ENTER) {
                          handler.removeCallbacks(myRunnable);
                          capsLocked = false;
                          pos = -1;
                          edittouched = 0;
                          firstclicked = 0;
                          lastposselected = 0;

                          deletingLowbar = 1;
                          Editable s = objx.getText();
                          int lowbar = s.toString().indexOf('_');
                          if (lowbar != -1) {
                              s.delete(lowbar, lowbar + 1);
                          }

                          String myobjx = objx.getText().toString();
                          myobjx = myobjx.substring(1);
                          String myobjxlow = myobjx.toLowerCase();
                          myobjxlow = myobjxlow.replaceAll("\\.+$", "");
                          myobjxlow = myobjxlow.replaceAll("\\?+$", "");
                          myobjxlow = myobjxlow.trim();
                          objx.setEnabled(false);

                          switch (valueManager.currentObjective) {
                              case 0:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("")) {
                                      secondText.setText("Type RESTART, RESTORE, COMMANDS or QUIT.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 1:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.objectiveOne.checkObjAnswer(myobjxlow, MainActivity.this).matches("") && levelManager.commonAnswers.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.consultGuide.checkObjAnswer(myobjxlow, MainActivity.this).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 2:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.objectiveTwo.checkObjAnswer(myobjxlow, MainActivity.this).matches("") && levelManager.commonAnswers.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.consultGuide.checkObjAnswer(myobjxlow, MainActivity.this).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 3:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.objectiveThree.checkObjAnswer(myobjxlow, MainActivity.this).matches("") && levelManager.commonAnswers.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.consultGuide.checkObjAnswer(myobjxlow, MainActivity.this).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 4:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.objectiveFour.checkObjAnswer(myobjxlow, MainActivity.this).matches("") && levelManager.commonAnswers.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.consultGuide.checkObjAnswer(myobjxlow, MainActivity.this).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 8:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && checkObj8Answer(myobjxlow).matches("") && levelManager.commonAnswers.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.consultGuide.checkObjAnswer(myobjxlow, MainActivity.this).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 9:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && checkObj9Answer(myobjxlow).matches("") && levelManager.commonAnswers.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.consultGuide.checkObjAnswer(myobjxlow, MainActivity.this).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 10:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && checkObj10Answer(myobjxlow).matches("") && levelManager.commonAnswers.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.consultGuide.checkObjAnswer(myobjxlow, MainActivity.this).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                          }

                          if (!(myobjxlow.matches("kill"))) {
                              levelManager.commonAnswers.isTryingToKill = false;
                          }
                          if (!(myobjxlow.matches("consult"))) {
                              levelManager.consultGuide.isConsultingGuide = false;
                          }
                      }

                      if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_DEL) {
                          handler.removeCallbacks(myRunnable);
                          callbacksRemoved = true;
                          if (blinkcolor == 0) {
                              callbacksRemoved = false;
                              blinkLowbar(objx);
                          }
                          if (objx.getText().toString().endsWith("_")) {
                              Editable s = objx.getText();
                              deletingLowbar = 1;
                              s.delete(s.length() - 2, s.length() - 1);
                              s.insert(s.length() - 1, "_");
                              deletingLowbar = 0;
                          }
                          if (callbacksRemoved) {
                              handler.postDelayed(myRunnable, 750);
                              callbacksRemoved = false;
                          }
                      }

                      if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_SHIFT_LEFT) {
                          Log.d(TAG, "ARNOLD1");
                          if (!capsLocked) {
                              Log.d(TAG, "ARNOLD2");
                              objx.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                              objx.setSingleLine(false);
                              objx.setTypeface(typeface);
                              capsLocked = true;
                          } else {
                              Log.d(TAG, "ARNOLD3");
                              objx.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                              objx.setSingleLine(false);
                              objx.setTypeface(typeface);
                              capsLocked = false;
                          }

                          Editable s = objx.getText();
                          int lowbar = s.toString().indexOf('_');
                          if (lowbar != -1) {
                              objx.setSelection(lowbar);
                          }

                      }

                      return false;
                  }
              }
        );
    }

    public void obj() {
        final CustomEditText obj = new CustomEditText(this);
        final TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);
        obj.setCursorVisible(false);
        obj.setTypeface(typeface);
        obj.setText(">");
        linearLayout.addView(obj);

        setInputListeners(obj, linearLayout, typeface, secondText);
    }






    public void obj9() {
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        final CustomEditText obj9 = new CustomEditText(this);
        final TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);
        obj9.setCursorVisible(false);
        obj9.setTypeface(typeface);
        obj9.setText(">");
        linearLayout.addView(obj9);

        setInputListeners(obj9, linearLayout, typeface, secondText);

    }

    public String checkObj9Answer(String myobj9) {
        TextView myMoves = (TextView) findViewById(R.id.moves);
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);


        if (stoppeton==1) {  // STOP TIME DECISION
            switch (myobj9) {
                case "1":
                    myLocation.setText("Unknown");
                    String dieOptions = "\n\nType RESTART, RESTORE, COMMANDS or QUIT.";
                    secondText.setText("Stoppeton remained being a popular hero.\nIt didn't take long till war lords from other Bubbles wanted to hire him as a mercenary in order to infiltrate the enemy bases using his powers.\nIt is said that Stoppeton got involved in 9 out of the 12 wars that were happening in that moment at the universe.\nTired of this bountyhaunter life, he stopped the time once again to steal the treasures from the war lords and disappear.\nNobody knows where he is or what he did the rest of his life. Today we remember " + valueManager.myself.name + " " + valueManager.myself.surname + " as the human he was.\nHopefully, we will finish the adventure whenever we meet again.\nBest regards.\nFred."
                                        + dieOptions);
                    linearLayout.addView(secondText);
                    obj=0;
                    obj();
                    break;
                case "2":
                    stoppeton=2;
                    secondText.setText("Congrats. You have chosen wisely to continue the adventure.\nWho knows what could you have become, but now Abigail and Fred need you to open the door in room D.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "save":
                    stoppeton=0;
                    save();
                    secondText.setText("GAME SAVED.\nYou can keep playing if you want to.");
                    linearLayout.addView(secondText);
                    stoppeton=1;
                    obj();
                    break;
                default:
                    secondText.setText("Choose one of the options (1 or 2).");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (inreading==1) {  // READING NOTES
            switch (myobj9) {
                case "1":
                    secondText.setText("LUDLOW B903\nOPEN AIR\n1994      Buzz Ruzzinsky\n          Live in concert\n10.6.   Stadium\n8:00 P.M.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    secondText.setText("Shannon --> Lucía Clemente - Jane Lester.\nIcarus --> Mr. Canterman - Singularity Paradox.\nHenry --> Mr. Canterman - What do we know it's real?.\nAbigail --> Douglas Adams - The Hitchhiker's Guide to the Galaxy.\nKenny --> Gabe Lee - Ludlow's History.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "3":
                    secondText.setText("Dear readers,\nJoseph Lovehart, the B903 opposition leader, is leaving office due to a personal tragedy.\nIt was a car accident that took the lives of his wife and one of his daughters.\nLovehart, the driver, is currently waiting for the results of his other daughter at the hospital.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "4":
                    secondText.setText("The citizens voice has been heard. The public library is being built starting next Monday. Hopefully, it will provide the knowledge that people demand.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "5":
                    inreading=0;
                    secondText.setText("Reading pinboard finished.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("Write the number of the option you want.\n\n\n" +
                            "Reading pinboard.\n\n1. Buzz Ruzzinsky's live in concert.\n2. Borrowed books.\n3. Ludlow Post 11 21 1959.\n4. The Message 06 04 1672.\n5. exit.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (valueManager.fred.conversationStatus == 1) {  // CONVERSATIONS
            switch (myobj9) {
                case "1":
                    secondText.setText("Are you kidding me?\nWe may have a library, a city hall and all that. Nevertheless, as far as I know, in B903 you either get killed by one of the Federation Agents who inspect the Bubble or buried alive during a sand storm.\nThere must be something better for us.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    secondText.setText("It may be written at the pinboard which is at the Library's hall.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "3":
                    secondText.setText("I don't know her a lot. I think she hates the Federation as much as we want to get out of here.\nThat's something.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "4":
                    valueManager.fred.conversationStatus = 0;
                    secondText.setText("Dialogue Fred finished.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("You just said: nwhuvweov.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'I don't know for sure if there's something better than B903 out there.'\n2. 'I have no idea who of the people in the paintings is this 'unfortunate one'.\n3. 'Do you think Abigail is trustworthy?'\n4. exit.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (pastabigail==0) {   // YOUR PAST DECISION
            switch (myobj9) {
                case "1":
                    pastabigail=1;
                    secondText.setText("'The one that could kill you if she wants, dumbass. Agh. Nevermind.' Then she turns and stares at the paintings.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    pastabigail=2;
                    secondText.setText("'Wait right there. I'm not asking you to tell me your life, Mr Niceguy. Duh. Just... open the door, can you?' Then she turns and stares at the paintings.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("Write the number of the option you want.\n1. (Distant) 'What kind of person asks that to a stranger?'\n2. (Affable) 'Well. I try to do my best when someone needs help. I'm not helping you for personal benefit, if that is why you asked.'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if ((valueManager.score - score9 >= 3) && (((String) myLocation.getText()).matches("Library, room D")) && pastabigail==12) {
            pastabigail=0;
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Abigail says 'Look boy. I don't know you, so I have to ask.'\n'What kind of person are you?'\n\n\nOptions:\n1. (Distant) 'What kind of person asks that to a stranger?'\n2. (Affable) 'Well. I try to do my best when someone needs help. I'm not helping you for personal benefit, if that is why you asked.'");
            linearLayout.addView(secondText);
            obj();
        }
        // DIRECTIONS GO VERB 9
        else if (myobj9.matches("west") || myobj9.matches("w") || myobj9.matches("go west") || myobj9.matches("go w") || myobj9.matches("go left") || myobj9.matches("northeast") || myobj9.matches("ne") || myobj9.matches("go northeast") || myobj9.matches("go ne") || myobj9.matches("northwest") || myobj9.matches("nw") || myobj9.matches("go northwest") || myobj9.matches("go nw") || myobj9.matches("southeast") || myobj9.matches("se") || myobj9.matches("go southeast") || myobj9.matches("go se") || myobj9.matches("southwest") || myobj9.matches("sw") || myobj9.matches("go southwest") || myobj9.matches("go sw") || myobj9.matches("up") || myobj9.matches("u") || myobj9.matches("go up") || myobj9.matches("upstairs") || myobj9.matches("go upstairs") || myobj9.matches("go u") || myobj9.matches("down") || myobj9.matches("d") || myobj9.matches("go down") || myobj9.matches("downstairs") || myobj9.matches("go downstairs") || myobj9.matches("go d")) {
            secondText.setText("You can't go that way.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.matches("north") || myobj9.matches("n") || myobj9.matches("go north") || myobj9.matches("go n") || myobj9.matches("go straight on")) {
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                myLocation.setText("Library, room D");
                secondText.setText("Room D\nEverything stills the same.\nHeading SOUTH is the library's hall.\nAbigail and Fred are still here.");
                linearLayout.addView(secondText);
                obj();
            } else {
                if (correctdoor9==1) {
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    secondText.setText("\n.\n.\n.             ...you fade out\n.\n\n" + valueManager.myself.name + ".\nIt's October 26, 2008. You are in B903.\nIt's been 286 years, 11 months and 8 days since the last hitchhiker died.\n\nIn the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.\nThe house is black as well and all its curtains are drawn.");
                    linearLayout.addView(secondText);
                    obj=10;
                    obj10();
                } else {
                    secondText.setText("You need to PUT the fuse IN one of the three containers placed under each painting.\nThere are some instructions that you can read next to the door.");
                    linearLayout.addView(secondText);
                    obj();
                }
            }
        } else if (myobj9.matches("east") || myobj9.matches("e") || myobj9.matches("go east") || myobj9.matches("go e") || myobj9.matches("go right")) {
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                secondText.setText("The office door is locked.");
            } else {
                secondText.setText("You can't go that way.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.matches("south") || myobj9.matches("s") || myobj9.matches("go south") || myobj9.matches("go s") || myobj9.matches("go backwards") || myobj9.matches("exit") || myobj9.matches("leave") || ((myobj9.contains("go") || myobj9.contains("get")) && myobj9.contains("out")) || (myobj9.contains("exit") && myobj9.contains("room"))) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                secondText.setText("You are not leaving without Fred, are you?");
            } else {
                myLocation.setText("Ludlow Library");
                if (firsttimebacklibrary==1) {
                    firsttimebacklibrary=0;
                    secondText.setText("Library\nAfter you enter the hall, you see that nobody is here...\nThe lights are still on. The books and chairs are placed like before. It is like everyone has vanished.\nThe silence makes you feel uneasy.");
                } else {
                    secondText.setText("Library\nNo one has been here. This silence still makes you feel uncomfortable.");
                }
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.matches("speak") || myobj9.matches("talk") || myobj9.matches("ask")) {  // SPEAK VERB 9
            secondText.setText("This verb needs to be used with a name.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("speak") || myobj9.contains("talk") || myobj9.contains("ask")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                secondText.setText("There is no one in this room.");
                linearLayout.addView(secondText);
                obj();
            } else {
                if (myobj9.contains("abigail")) {
                    secondText.setText("She says 'Have you already found out which one of them is?' and looks at the containers under the paintings.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("fred")) {
                    valueManager.fred.conversationStatus = 1;
                    secondText.setText("Dialogue Fred.\n\n1. 'I don't know for sure if there's something better than B903 out there.'\n2. 'I have no idea who of the people in the paintings is this 'unfortunate one'.\n3. 'Do you think Abigail is trustworthy?'\n4. exit.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText("This person's name is incorrect or he/she is not in the place.");
                    linearLayout.addView(secondText);
                    obj();
                }
            }
        } else if (myobj9.contains("stop")) { // STOP VERB 9
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj9.contains("voices")) {
                secondText.setText("You are not hearing voices... Not yet.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj9.contains("time")) {
                if (stoppeton==0) {
                    stoppeton=1;
                    secondText.setText("Oh my God. OH MY GOD! You have had powers after all this time.\nScrew the Travellers. Who needs to travel in space if you can travel in time. Now you have become... STOPPETON, THE TIME 'PAUSER'. Now choose:\n\n1. Live your life with this powers like a God.\n2. Ignore your unique skill and continue your journey.");
                } else {
                    secondText.setText("That's useless.");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("There's nothing you can stop here.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj9.matches("close")) {  // CLOSE VERB 9
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("close")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj9.contains("door")) {
                if (correctdoor9==1 && (((String) myLocation.getText()).matches("Library, room D"))) {
                    secondText.setText("I don't think you are that strong.");
                } else {
                    secondText.setText("The door is already closed.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj9.contains("eye")) {
                secondText.setText("This is not really useful right now.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj9.matches("open")) {  // OPEN VERB 9
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("open")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj9.contains("door")) {
                if (((String) myLocation.getText()).matches("Ludlow Library")) {
                    secondText.setText("Locked.");
                } else {
                    if (correctdoor9==1) {
                        secondText.setText("You already opened it.");
                    } else {
                        secondText.setText("The door will open after you PUT the fuse IN one of the three containers placed under each painting.\nYou'll have to guess who is 'the unfortunate one'. But remember. The mistake is punished with death, so be sure about your decision.");
                    }
                }
                linearLayout.addView(secondText);
                obj();
            } else if ((myobj9.contains("entry") || myobj9.contains("library") || myobj9.contains("entrance")) && myobj9.contains("door")) {
                if (((String) myLocation.getText()).matches("Ludlow Library")) {
                    secondText.setText("You are not leaving without Fred, are you?");
                } else {
                    secondText.setText("That's in the other room.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj9.contains("eye")) {
                secondText.setText("Your eyes are wide open.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj9.contains("window")) {
                if (((String) myLocation.getText()).matches("Ludlow Library")) {
                    if (brokenwindow8 == 0) {
                        secondText.setText("The wind feels fresh on your skin.");
                        linearLayout.addView(secondText);
                        obj();
                    } else {
                        secondText.setText("You actually broke it, so...");
                        linearLayout.addView(secondText);
                        obj();
                    }
                } else {
                    secondText.setText("You can't see any window here!");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else if (myobj9.contains("book")) {
                secondText.setText("You will have to take a book first.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj9.matches("turn off") || myobj9.matches("turn it off") || myobj9.matches("shut down") || myobj9.matches("shut it down")) {  // TURN OFF VERB 9
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if ((myobj9.contains("turn") && myobj9.contains("off")) || (myobj9.contains("shut") && myobj9.contains("down"))) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj9.contains("alarm")) {
                secondText.setText("No alarm is sounding.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj9.contains("music")) {
                secondText.setText("No music is being played.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj9.contains("fuse")) {
                if (myInventory.contains("fuse") || (((String) myLocation.getText()).matches("Library, room D"))) {
                    secondText.setText("I'm not sure you have the electrical knowledge to do that.");
                } else {
                    secondText.setText("You are not holding the fuse or it is not here.");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be turned off or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj9.contains("turn on") || myobj9.contains("turn it on")) {  // TURN ON VERB 9
            secondText.setText("There's nothing you can turn on here.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("stand") || myobj9.contains("get up")) { // STAND VERB 9
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You are already standing.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("sit down") || myobj9.matches("sit") || myobj9.contains("lie")) { // LIE VERB 9
            secondText.setText("Abigail and Fred are waiting you to open the metal door.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.matches("examine")) { // EXAMINE VERB 9
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("examine")) {
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                if (myobj9.contains("window")) {
                    secondText.setText("Library views are no different from your house ones. As always, you see the same desert with the same pieces of metal scattered on the ground.\nAt least you can spot a part of the houses that were buried during last week's sand storm.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("book") || myobj9.contains("shelve")) {
                    secondText.setText("Go and SPEAK with the librarian to ask for a book.\nRemember: say SPEAK and the name of the person you wanna speak with.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("sully") || myobj9.contains("librarian")) {
                    secondText.setText("He is not here.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("cabin")) {
                    secondText.setText("This thing is bulletproof. You couldn't get in there even if you want to.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("door")) {
                    secondText.setText("It is just a door.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("pinboard") || myobj9.contains("note")) {
                    inreading=1;
                    secondText.setText("Reading pinboard.\n\n1. Buzz Ruzzinsky's live in concert.\n2. Borrowed books.\n3. Ludlow Post 11 21 1959.\n4. The Message 06 04 1672.\n5. exit.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText("This thing cannot be examined or it is not in the place.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else {
                if (myobj9.contains("painting")) {
                    secondText.setText("Mmh... The three of them are portraits that look pretty alike.\nUnder each one of them there is a container with a number (1, 2, 3).");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("instructions")) {
                    secondText.setText("Canterman Electronics.\nThe mechanics of this door are really simple. The door is connected with three containers (container 1, container 2 and container 3).\nYou have to PUT a fuse IN the right container in order to open the door.\nBeware of doing that with the wrong container.\n\nNote: Use PUT and IN to place an object in a container (e.g. 'put the fuse in 1' places the fuse in the container of the first painting).");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("fuse")) {
                    if (myInventory.contains("fuse")) {
                        secondText.setText("It works.");
                    } else {
                        secondText.setText("It works. You can easily take it.");
                    }
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("door")) {
                    secondText.setText("The door looks sealed. The text 'the unfortunate one' seems to be written with fury.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText("This thing cannot be examined or it is not in the place.");
                    linearLayout.addView(secondText);
                    obj();
                }
            }
        }
        // TAKE GET VERB 9
        else if (myobj9.matches("take") || myobj9.matches("get") || myobj9.matches("pick") || myobj9.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("take") || myobj9.contains("get") || myobj9.contains("pick") || myobj9.contains("grab")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                if ((myobj9.contains("glass") || myobj9.contains("crystal")) && brokenwindow8 == 1) {
                    String brokenglass = "broken glass";
                    if (myInventory.contains("broken glass")) {
                        secondText.setText("You already took it.");
                    } else {
                        myInventory.add(brokenglass);
                        secondText.setText("Taken.");
                    }
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("book") || myobj9.contains("shelve")) {
                    secondText.setText("Sully is the one who manages the borrowed books and now he is gone...");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("note")) {
                    secondText.setText("Notes cannot be taken, but you can read them.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (!(dropLibraryL.isEmpty())) {
                    int n;
                    if (myobj9.contains("take") || myobj9.contains("pick") || myobj9.contains("grab")) {
                        n = 4;
                    } else if (myobj9.contains("get")) {
                        n = 3;
                    } else {
                        n = 0;
                    }
                    String s = myobj9.substring(n);
                    s = s.replace(" the ", "");
                    s = s.replace(" a ", "");
                    s = s.replace(" an ", "");
                    s = s.trim();
                    if (dropLibraryL.contains(s)) {
                        dropLibraryL.remove(s);
                        myInventory.add(s);
                        secondText.setText("Taken.");
                        linearLayout.addView(secondText);
                        obj();
                    } else {
                        secondText.setText("You can't see any " + s + " here!");
                        linearLayout.addView(secondText);
                        obj();
                    }
                } else {
                    secondText.setText("This thing cannot be taken or it is not in the place.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else {
                if (myobj9.contains("fuse")) {
                    if (dropLibraryL.contains("fuse")) {
                        secondText.setText("You can't see any fuse here!");
                    } else {
                        if (myInventory.contains("fuse")) {
                            secondText.setText("You already took it.");
                        } else {
                            if (dropRoomD.contains("fuse")) {
                                dropRoomD.remove("fuse");
                            }
                            myInventory.add("fuse");
                            secondText.setText("Taken.");
                        }
                    }
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("painting")) {
                    secondText.setText("Looks like the paintings are attached to the wall.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("instructions")) {
                    secondText.setText("Instructions cannot be taken, but you can read them.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (!(dropRoomD.isEmpty())) {
                    int n;
                    if (myobj9.contains("take") || myobj9.contains("pick") || myobj9.contains("grab")) {
                        n = 4;
                    } else if (myobj9.contains("get")) {
                        n = 3;
                    } else {
                        n = 0;
                    }
                    String s = myobj9.substring(n);
                    s = s.replace(" the ", "");
                    s = s.replace(" a ", "");
                    s = s.replace(" an ", "");
                    s = s.trim();
                    if (dropRoomD.contains(s)) {
                        dropRoomD.remove(s);
                        myInventory.add(s);
                        secondText.setText("Taken.");
                        linearLayout.addView(secondText);
                        obj();
                    } else {
                        secondText.setText("You can't see any " + s + " here!");
                        linearLayout.addView(secondText);
                        obj();
                    }
                } else {
                    secondText.setText("This thing cannot be taken or it is not in the place.");
                    linearLayout.addView(secondText);
                    obj();
                }
            }
        } else if (myobj9.matches("drop") || myobj9.contains("get rid of")) { // DROP VERB 9
            secondText.setText("Just say: Drop (and the object you want to drop).");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("drop")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            int n = 4;
            String s = myobj9.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (myInventory.contains(s)) {
                myInventory.remove(s);
                if (((String) myLocation.getText()).matches("Ludlow Library")) {
                    dropLibraryL.add(s);
                } else {
                    dropRoomD.add(s);
                }
                secondText.setText("Dropped.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("You're not holding the " + s + ".");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj9.matches("put") || myobj9.matches("place")) { // PUT VERB 9
            secondText.setText("This verb needs to be used with an object and a container.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("put") || myobj9.contains("place")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj9.contains("fuse") && (myobj9.contains("1") || myobj9.contains("one") || myobj9.contains("wallace") || myobj9.contains("storm"))) {
                if (((String) myLocation.getText()).matches("Library, room D")) {
                    if (!(myInventory.contains("fuse"))) {
                        secondText.setText("The fuse is not in your inventory.");
                        linearLayout.addView(secondText);
                        obj();
                    } else {
                        myLocation.setText("Dead");
                        String dieOptions = "\n\nType RESTART, RESTORE, COMMANDS or QUIT.";
                        myInventory.remove("fuse");
                        dropRoomD.add("fuse");
                        secondText.setText("You placed the fuse in the container 1. You think Wallace Storm is 'the unfortunate one'.\n\nIt seems that the painting had a hole in his mouth where a shotgun appears and blows your head off.\n\nAs a result of the vulnerabilities that the living being's head has, there are Bubbles in the universe where your body can be modified to work without a head.\nUnfortunately, B903 is not one of those.\nYou died."
                                            + dieOptions);
                        linearLayout.addView(secondText);
                        obj=0;
                        obj();
                    }
                } else {
                    secondText.setText("You are not in the room where this container is.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else if (myobj9.contains("fuse") && (myobj9.contains("2") || myobj9.contains("two") || myobj9.contains("catherine") || myobj9.contains("barker"))) {
                if (((String) myLocation.getText()).matches("Library, room D")) {
                    if (!(myInventory.contains("fuse"))) {
                        secondText.setText("The fuse is not in your inventory.");
                        linearLayout.addView(secondText);
                        obj();
                    } else {
                        myLocation.setText("Dead");
                        String dieOptions = "\n\nType RESTART, RESTORE, COMMANDS or QUIT.";
                        myInventory.remove("fuse");
                        dropRoomD.add("fuse");
                        secondText.setText("You placed the fuse in the container 2. You think Catherine Barker is 'the unfortunate one'.\n\nIt seems that the painting had a hole in her mouth where a shotgun appears and blows your head off.\n\nAs a result of the vulnerabilities that the living being's head has, there are Bubbles in the universe where your body can be modified to work without a head.\nUnfortunately, B903 is not one of those.\nYou died."
                                            + dieOptions);
                        linearLayout.addView(secondText);
                        obj=0;
                        obj();
                    }
                } else {
                    secondText.setText("You are not in the room where this container is.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else if (myobj9.contains("fuse") && (myobj9.contains("3") || myobj9.contains("three") || myobj9.contains("joseph") || myobj9.contains("lovehart"))) {
                if (((String) myLocation.getText()).matches("Library, room D")) {
                    if (!(myInventory.contains("fuse"))) {
                        secondText.setText("The fuse is not in your inventory.");
                        linearLayout.addView(secondText);
                        obj();
                    } else {
                        correctdoor9 = 1;
                        myInventory.remove("fuse");
                        dropRoomD.add("fuse");
                        secondText.setText("You placed the fuse in the container 3. You think Joseph Lovehart is 'the unfortunate one'.\n\nAfter a few seconds, the sealed door opens. Heading NORTH you can go to the next room.");
                        linearLayout.addView(secondText);
                        obj();
                    }
                } else {
                    secondText.setText("You are not in the room where this container is.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else {
                secondText.setText("The name of the object is wrong, the number/name of the container is wrong or this object cannot be used this way.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj9.matches("help") && valueManager.score - score9 >= 6) { // HELP VERB 9
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("The name you are looking for is written in one of the notes which are at the hall.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.matches("help")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Try to do things by yourself first.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("help")) {
            secondText.setText("Just say HELP.");
            linearLayout.addView(secondText);
            obj();
        }
        // FOLLOW VERB 9
        else if (myobj9.matches("follow") || myobj9.matches("chase") || myobj9.matches("find") || myobj9.matches("search")) {
            secondText.setText("This verb needs to be used with a name.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("follow") || myobj9.contains("chase") || myobj9.contains("find") || myobj9.contains("search")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj9.contains("abigail") || myobj9.contains("fred")) {
                if (((String) myLocation.getText()).matches("Ludlow Library")) {
                    myLocation.setText("Library, room D");
                    secondText.setText("Now you are in room D.");
                } else {
                    secondText.setText("You already are with them.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj9.contains("sully") || myobj9.contains("librarian")) {
                if (((String) myLocation.getText()).matches("Ludlow Library")) {
                    secondText.setText("You don't know where he went... This is really weird.");
                } else {
                    secondText.setText("Sully was in the other room.");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This person's name is incorrect or he/she cannot be followed.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj9.contains("drink")) { // DRINK VERB 9
            secondText.setText("There is nothing you can drink here.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("write")) {  // WRITE VERB 9
            secondText.setText("You cannot write new notes.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.matches("read")) {  // READ VERB 9
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("read")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj9.contains("note") || myobj9.contains("pinboard")) {
                if (((String) myLocation.getText()).matches("Ludlow Library")) {
                    inreading=1;
                    secondText.setText("Reading pinboard.\n\n1. Buzz Ruzzinsky's live in concert.\n2. Borrowed books.\n3. Ludlow Post 11 21 1959.\n4. The Message 06 04 1672.\n5. exit.");
                } else {
                    secondText.setText("You can't see any note here!");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj9.contains("book")) {
                secondText.setText("You are not holding a book.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj9.contains("instructions")) {
                if (((String) myLocation.getText()).matches("Ludlow Library")) {
                    secondText.setText("You can't see any instructions here!");
                } else {
                    secondText.setText("Canterman Electronics.\nThe mechanics of this door are really simple. The door is connected with three containers (container 1, container 2 and container 3).\nYou have to PUT a fuse IN the right container in order to open the door.\nBeware of doing that with the wrong container.\n\nNote: Use PUT and IN to place an object in a container (e.g. 'put the fuse in 1' places the fuse in the container of the first painting).");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj9.contains("door") && (((String) myLocation.getText()).matches("Library, room D"))) {
                secondText.setText("'the unfortunate one'.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be read or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj9.matches("break") || myobj9.matches("hit") || myobj9.matches("attack") || myobj9.matches("punch") || myobj9.matches("fight") || myobj9.matches("kick")) {  // BREAK VERB 9
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("break") || myobj9.contains("hit") || myobj9.contains("attack") || myobj9.contains("punch") || myobj9.contains("fight") || myobj9.contains("kick")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                if (myobj9.contains("people") || myobj9.contains("person") || myobj9.contains("sully") || myobj9.contains("librarian")) {
                    secondText.setText("There's no one here you can attack.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("window")) {
                    if (brokenwindow8 == 0) {
                        brokenwindow8 = 1;
                        secondText.setText("And now the window is broken. It's a mess.");
                        linearLayout.addView(secondText);
                        obj();
                    } else {
                        secondText.setText("The window is already broken.");
                        linearLayout.addView(secondText);
                        obj();
                    }
                } else if (myobj9.contains("cabin") || myobj9.contains("shelve") || myobj9.contains("door")) {
                    secondText.setText("You are all alone and hitting stuff. You should be proud.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText("This thing cannot be hitted or it is not in the place.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else {
                if (myobj9.contains("abigail")) {
                    secondText.setText("She stops your hand.\n'Easy boy! Don't make me cut your hand off.'");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("fred") || myobj9.contains("friend")) {
                    secondText.setText("After you slap Fred, Abigail looks at you with a smile.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("painting")) {
                    secondText.setText("Abigail: 'Hey Hey Hey!'\n'This paintings have been here for decades and one of them must be this 'unfortunate one'. Figure that out instead of acting like an ape.'");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("fuse")) {
                    secondText.setText("It sparks you.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj9.contains("door")) {
                    secondText.setText("The sound of metal being hitted is really cool, but useless.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText("This thing cannot be hitted or it is not in the place.");
                    linearLayout.addView(secondText);
                    obj();
                }
            }
        } else if ((myobj9.contains("fix") || myobj9.contains("repair")) && myobj9.contains("window")) { // WINDOW ACTIONS 9
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                secondText.setText("Not your business.");
            } else {
                secondText.setText("There is no window in this room.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("jump") && myobj9.contains("window")) {
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                secondText.setText("You are not a savage.");
            } else {
                secondText.setText("There is no window in this room.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("window")) {
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                secondText.setText("Library views are no different from your house ones. As always, you see the same desert with the same pieces of metal scattered on the ground.\nAt least you can spot a part of the houses that were buried during last week's sand storm.");
            } else {
                secondText.setText("There is no window in this room.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if ((myobj9.contains("look") && myobj9.contains("around")) || myobj9.matches("l") || myobj9.matches("look")) {
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                String intro = "Library\n";
                if (brokenwindow8 == 1) {
                    intro = intro + "There are some book shelves and a large broken window.\nSully's cabin is empty.";
                } else {
                    intro = intro + "There are some book shelves and a large window.\nSully's cabin is empty.";
                }
                intro = intro + " There is the closed office door and a pinboard with a few notes here.\nHeading NORTH is the room D.";
                if (!(dropLibraryL.isEmpty())) {
                    int n = dropLibraryL.size();
                    for (int i = 0; i < n; i++) {
                        intro = intro + "\nThere's the " + dropLibraryL.get(i) + " here.";
                    }
                    secondText.setText(intro);
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText(intro);
                    linearLayout.addView(secondText);
                    obj();
                }
            } else {
                String intro = "Room D\nIt is a small place with three paintings: 1 Wallace Storm, 2 Catherine Barker and 3 Joseph Lovehart.\nHere is a metal door where it is written 'the unfortunate one' on it.\n";
                if (myInventory.contains("fuse") || dropLibraryL.contains("fuse") || dropRoomD.contains("fuse")) {
                    intro = intro + "There are some instructions next to the door.\n";
                } else {
                    intro = intro + "There is a fuse with some instructions next to the door.\n";
                }
                intro = intro + "Heading SOUTH is the library's hall.\nAbigail and Fred are here.";
                if (!(dropRoomD.isEmpty())) {
                    int n = dropRoomD.size();
                    for (int i = 0; i < n; i++) {
                        intro = intro + "\nThere's the " + dropRoomD.get(i) + " here.";
                    }
                    secondText.setText(intro);
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText(intro);
                    linearLayout.addView(secondText);
                    obj();
                }
            }
        } else if (myobj9.matches("i") || myobj9.matches("inventory")) {
            String inventory = "You have:";
            if (!(myInventory.isEmpty())) {
                int n = myInventory.size();
                for (int i=0; i<n; i++) {
                    inventory = inventory + "\n    " + myInventory.get(i) + ".";
                }
                secondText.setText(inventory);
                linearLayout.addView(secondText);
                obj();
            } else {
                inventory = "You have nothing.";
                secondText.setText(inventory);
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj9.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.matches("sleep")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("It will be inappropriate because Abigail and Fred are waiting you to open the metal door.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("check") && myobj9.contains("out")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("check") || myobj9.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("enter")) {
            secondText.setText("Try to open the doors of the places you want to get in.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("jump") || myobj9.contains("climb") || myobj9.contains("turn") || myobj9.contains("shut") || myobj9.contains("look") || myobj9.contains("see") || myobj9.contains("watch") || myobj9.contains("play") || myobj9.contains("run") || myobj9.contains("walk") || myobj9.contains("eat") || myobj9.contains("move") || myobj9.contains("give") || myobj9.contains("offer")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Look around you.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("smell")) {
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                secondText.setText("It smells just like a library.");
            } else {
                secondText.setText("It smells just like the room has been neglected for years.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("listen")) {
            if (((String) myLocation.getText()).matches("Ludlow Library")) {
                secondText.setText("It feels like the silence is killing you.");
            } else {
                secondText.setText("You can tell that there is no one at the other side of the metal door.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("wait")) {
            secondText.setText("Time passes...");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.contains("what") && myobj9.contains("time") && myobj9.contains("is") || myobj9.matches("time") || myobj9.matches("the time")) {
            secondText.setText("It is daylight.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj9.matches("diagnostic") || myobj9.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            linearLayout.addView(secondText);
            obj();
        }

        return (String) secondText.getText();
    }














    public void obj10() {
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        final CustomEditText obj10 = new CustomEditText(this);
        final TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);
        obj10.setCursorVisible(false);
        obj10.setTypeface(typeface);
        obj10.setText(">");
        linearLayout.addView(obj10);

        setInputListeners(obj10, linearLayout, typeface, secondText);

    }

    public String checkObj10Answer(String myobj10) {
        TextView myMoves = (TextView) findViewById(R.id.moves);
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);


        // DIRECTIONS GO VERB 10
        if (myobj10.matches("up") || myobj10.matches("u") || myobj10.matches("go up") || myobj10.matches("upstairs") || myobj10.matches("go upstairs") || myobj10.matches("go u") || myobj10.matches("down") || myobj10.matches("d") || myobj10.matches("go down") || myobj10.matches("downstairs") || myobj10.matches("go downstairs") || myobj10.matches("go d")) {
            secondText.setText("You can't go that way.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.matches("north") || myobj10.matches("n") || myobj10.matches("go north") || myobj10.matches("go n") || myobj10.matches("go straight on")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 12:
                    if (closeddoor==0) {
                        valueManager.score++;
                        myMoves.setText("Moves: " + valueManager.score);
                        myLocation.setText("Desert House");
                        housedesert=12;
                        secondText.setText("Congratulations human.\n\nAt least, you finished the demo in " + valueManager.score + " moves.\nNot bad for a species that has pizza as a religion."); // sponge bob
                        linearLayout.addView(secondText);
                        obj=11;
                        //obj11();
                    } else {
                        s = "The door is closed.";
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 21:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, W");
                    housedesert=11;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northeast you still see the house.";
                    if (!(dropHouseDesert11.isEmpty())) {
                        int n = dropHouseDesert11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert11.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    housedesert=12;
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, E");
                    housedesert=13;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northwest you still see the house.";
                    if (!(dropHouseDesert13.isEmpty())) {
                        int n = dropHouseDesert13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert13.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 32:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("south") || myobj10.matches("s") || myobj10.matches("go south") || myobj10.matches("go s") || myobj10.matches("go backwards")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SW");
                    housedesert=21;
                    if (havefoundeggin==00 || (havefoundeggin==21 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=21;
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.";
                    }
                    if (!(dropHouseDesert21.isEmpty())) {
                        int n = dropHouseDesert21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert21.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 12:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SE");
                    housedesert=23;
                    if (havefoundeggin==00 || (havefoundeggin==23 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=23;
                        s = "Nowhere\nNow the house is northwest.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northwest.";
                    }
                    if (!(dropHouseDesert23.isEmpty())) {
                        int n = dropHouseDesert23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert23.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 21:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SS");
                    housedesert=32;
                    if (havefoundeggin==00 || (havefoundeggin==32 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=32;
                        s = "Nowhere\nNow the house is north.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is north.";
                    }
                    if (!(dropHouseDesert32.isEmpty())) {
                        int n = dropHouseDesert32.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert32.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 32:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("west") || myobj10.matches("w") || myobj10.matches("go west") || myobj10.matches("go w") || myobj10.matches("go left")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 12:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, W");
                    housedesert=11;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northeast you still see the house.";
                    if (!(dropHouseDesert11.isEmpty())) {
                        int n = dropHouseDesert11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert11.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    housedesert=12;
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 21:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SW");
                    housedesert=21;
                    if (havefoundeggin==00 || (havefoundeggin==21 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=21;
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.";
                    }
                    if (!(dropHouseDesert21.isEmpty())) {
                        int n = dropHouseDesert21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert21.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 32:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("east") || myobj10.matches("e") || myobj10.matches("go east") || myobj10.matches("go e") || myobj10.matches("go right")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    housedesert=12;
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 12:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, E");
                    housedesert=13;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northwest you still see the house.";
                    if (!(dropHouseDesert13.isEmpty())) {
                        int n = dropHouseDesert13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert13.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 21:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SE");
                    housedesert=23;
                    if (havefoundeggin==00 || (havefoundeggin==23 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=23;
                        s = "Nowhere\nNow the house is northwest.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northwest.";
                    }
                    if (!(dropHouseDesert23.isEmpty())) {
                        int n = dropHouseDesert23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert23.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 32:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("northeast") || myobj10.matches("ne") || myobj10.matches("go northeast") || myobj10.matches("go ne")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    if (closeddoor==0) {
                        valueManager.score++;
                        myMoves.setText("Moves: " + valueManager.score);
                        myLocation.setText("Desert House");
                        housedesert=12;
                        secondText.setText("Congratulations human.\n\nAt least, you finished the demo in " + valueManager.score + " moves.\nNot bad for a species that has pizza as a religion."); // sponge bob
                        linearLayout.addView(secondText);
                        obj=11;
                        //obj11();
                    } else {
                        s = "The door is closed.";
                        secondText.setText(s);
                    }
                    break;
                case 12:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 13:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 21:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    housedesert=12;
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, E");
                    housedesert=13;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northwest you still see the house.";
                    if (!(dropHouseDesert13.isEmpty())) {
                        int n = dropHouseDesert13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert13.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 32:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SE");
                    housedesert=23;
                    if (havefoundeggin==00 || (havefoundeggin==23 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=23;
                        s = "Nowhere\nNow the house is northwest.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northwest.";
                    }
                    if (!(dropHouseDesert23.isEmpty())) {
                        int n = dropHouseDesert23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert23.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("northwest") || myobj10.matches("nw") || myobj10.matches("go northwest") || myobj10.matches("go nw")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 12:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 13:
                    if (closeddoor==0) {
                        valueManager.score++;
                        myMoves.setText("Moves: " + valueManager.score);
                        myLocation.setText("Desert House");
                        housedesert=12;
                        secondText.setText("Congratulations human.\n\nAt least, you finished the demo in " + valueManager.score + " moves.\nNot bad for a species that has pizza as a religion."); // sponge bob
                        linearLayout.addView(secondText);
                        obj=11;
                        //obj11();
                    } else {
                        s = "The door is closed.";
                        secondText.setText(s);
                    }
                    break;
                case 21:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 22:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, W");
                    housedesert=11;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading northeast you still see the house.";
                    if (!(dropHouseDesert11.isEmpty())) {
                        int n = dropHouseDesert11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert11.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere");
                    housedesert=12;
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nYou are standing on a place where the whole ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 32:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SW");
                    housedesert=21;
                    if (havefoundeggin==00 || (havefoundeggin==21 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=21;
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.";
                    }
                    if (!(dropHouseDesert21.isEmpty())) {
                        int n = dropHouseDesert21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert21.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("southeast") || myobj10.matches("se") || myobj10.matches("go southeast") || myobj10.matches("go se")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 12:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SE");
                    housedesert=23;
                    if (havefoundeggin==00 || (havefoundeggin==23 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=23;
                        s = "Nowhere\nNow the house is northwest.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northwest.";
                    }
                    if (!(dropHouseDesert23.isEmpty())) {
                        int n = dropHouseDesert23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert23.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 21:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SS");
                    housedesert=32;
                    if (havefoundeggin==00 || (havefoundeggin==32 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=32;
                        s = "Nowhere\nNow the house is north.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is north.";
                    }
                    if (!(dropHouseDesert32.isEmpty())) {
                        int n = dropHouseDesert32.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert32.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 22:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 23:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 32:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("southwest") || myobj10.matches("sw") || myobj10.matches("go southwest") || myobj10.matches("go sw")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 12:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SW");
                    housedesert=21;
                    if (havefoundeggin==00 || (havefoundeggin==21 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=21;
                        s = "Nowhere\nNow the house is northeast.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is northeast.";
                    }
                    if (!(dropHouseDesert21.isEmpty())) {
                        int n = dropHouseDesert21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert21.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, S");
                    housedesert=22;
                    s = "Nowhere\nFrom this place, the black desert looks immense.\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 21:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 22:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
                case 23:
                    valueManager.score++;
                    myMoves.setText("Moves: " + valueManager.score);
                    myLocation.setText("Nowhere, SS");
                    housedesert=32;
                    if (havefoundeggin==00 || (havefoundeggin==32 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1))) {
                        havefoundeggin=32;
                        s = "Nowhere\nNow the house is north.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nNow the house is north.";
                    }
                    if (!(dropHouseDesert32.isEmpty())) {
                        int n = dropHouseDesert32.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert32.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 32:
                    s = "You can't go that way.";
                    secondText.setText(s);
                    break;
            }
            if (obj==10) {
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("exit") || myobj10.matches("leave") || ((myobj10.contains("go") || myobj10.contains("get")) && myobj10.contains("out")) || (myobj10.contains("exit") && myobj10.contains("room"))) {
            secondText.setText("You are already on the outside.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("speak") || myobj10.contains("talk") || myobj10.contains("ask")) {  // SPEAK VERB 10
            secondText.setText("There is no one here you can speak with.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("stop")) { // STOP VERB 10
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj10.contains("voices")) {
                secondText.setText("You are not hearing voices... Not yet.");
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("time")) {
                secondText.setText("You wish you could.");
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("breath")) {
                secondText.setText("If you want to die, just say DIE.");
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("sleep") || myobj10.contains("dream")) {
                secondText.setText("You are pretty much awake.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("There's nothing you can stop here.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("close")) {  // CLOSE VERB 10
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("close")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj10.contains("door")) {
                if (housedesert==12) {
                    if (closeddoor==0) {
                        closeddoor=1;
                        secondText.setText("You closed it.");
                    } else {
                        secondText.setText("It is already closed.");
                    }
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("eye")) {
                secondText.setText("This is not really useful right now.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("open")) {  // OPEN VERB 10
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("open")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj10.contains("door")) {
                if (housedesert==12) {
                    if (closeddoor==1) {
                        closeddoor=0;
                        secondText.setText("You opened it.");
                    } else {
                        secondText.setText("It is already open.");
                    }
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("eye")) {
                secondText.setText("Your eyes are wide open.");
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("curtain")) {
                if (housedesert==12) {
                    secondText.setText("You can hardly see anything. It is really dark inside.");
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("window")) {
                if (housedesert==12) {
                    secondText.setText("The crystal is broken. It is too sharp to touch it.");
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("egg") && (myInventoryPast.contains("egg") || (housedesert==11 && dropHouseDesert11.contains("egg")) || (housedesert==12 && dropHouseDesert12.contains("egg")) || (housedesert==13 && dropHouseDesert13.contains("egg")) || (housedesert==21 && dropHouseDesert21.contains("egg")) || (housedesert==22 && dropHouseDesert22.contains("egg")) || (housedesert==23 && dropHouseDesert23.contains("egg")) || (housedesert==32 && dropHouseDesert32.contains("egg")))) {
                brokenegg=1;
                if (myInventoryPast.contains("egg")) {myInventoryPast.remove("egg");}
                else if (dropHouseDesert11.contains("egg")) {dropHouseDesert11.remove("egg");}
                else if (dropHouseDesert12.contains("egg")) {dropHouseDesert12.remove("egg");}
                else if (dropHouseDesert13.contains("egg")) {dropHouseDesert13.remove("egg");}
                else if (dropHouseDesert21.contains("egg")) {dropHouseDesert21.remove("egg");}
                else if (dropHouseDesert22.contains("egg")) {dropHouseDesert22.remove("egg");}
                else if (dropHouseDesert23.contains("egg")) {dropHouseDesert23.remove("egg");}
                else if (dropHouseDesert32.contains("egg")) {dropHouseDesert32.remove("egg");}
                secondText.setText("When it crashes on the ground... nothing happens.\nIf there was hope to see another species in B903, now there's none.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("enter")) {  // ENTER VERB 10
            secondText.setText("This verb needs to be used with a place to get in.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("enter")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj10.contains("house")) {
                if (housedesert==12) {
                    if (closeddoor==0) {
                        myLocation.setText("Desert House");
                        housedesert=12;
                        secondText.setText("Congratulations human.\n\nAt least, you finished the demo in " + valueManager.score + " moves.\nNot bad for a species that has pizza as a religion."); // sponge bob
                        linearLayout.addView(secondText);
                        obj=11;
                        //obj11();
                    } else {
                        secondText.setText("The door is closed.");
                        linearLayout.addView(secondText);
                        obj10();
                    }
                } else {
                    secondText.setText("You have wandered off the house. You better get closer to do that.");
                    linearLayout.addView(secondText);
                    obj10();
                }
            } else if (myobj10.contains("matrix")) {
                secondText.setText("Morpheus should be proud.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("You cannot get in here or the ubication's name is incorrect.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if ((myobj10.contains("turn") && myobj10.contains("off")) || (myobj10.contains("shut") && myobj10.contains("down"))) {  // TURN OFF VERB 10
            secondText.setText("There's nothing you can turn off here.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("turn on") || myobj10.contains("turn it on")) {  // TURN ON VERB 10
            secondText.setText("There's nothing you can turn on here.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("stand") || myobj10.contains("get up")) { // STAND VERB 10
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You are already standing.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("sit down") || myobj10.matches("sit") || myobj10.contains("lie")) { // LIE VERB 10
            secondText.setText("It doesn't seem that comfortable.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.matches("examine")) { // EXAMINE VERB 10
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("examine")) {
            if (housedesert==11 || housedesert==12 || housedesert==13 || housedesert==22) {
                if (myobj10.contains("house")) {
                    secondText.setText("The house gives off a feeling of loneliness.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myobj10.contains("sky")) {
                    secondText.setText("At this time of year B903's sky is always orange. It seems like it's almost night the whole time, everyday.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myobj10.contains("door")) {
                    if (closeddoor==0) {
                        secondText.setText("It is open. It looks older than the house.");
                    } else {
                        secondText.setText("It is closed. It looks older than the house.");
                    }
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myobj10.contains("window")) {
                    secondText.setText("You can hardly see anything. It is really dark inside.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myobj10.contains("curtain")) {
                    secondText.setText("The curtains are dark and full of loose seams.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myobj10.contains("stone")) {
                    secondText.setText("The black stones are cold.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myobj10.contains("egg")) {
                    if (myInventoryPast.contains("egg") || (housedesert==11 && dropHouseDesert11.contains("egg")) || (housedesert==12 && dropHouseDesert12.contains("egg")) || (housedesert==13 && dropHouseDesert13.contains("egg")) || (housedesert==22 && dropHouseDesert22.contains("egg"))) {
                        secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                    } else {
                        secondText.setText("This thing cannot be examined or it is not in the place.");
                    }
                    linearLayout.addView(secondText);
                    obj10();
                } else {
                    secondText.setText("This thing cannot be examined or it is not in the place.");
                    linearLayout.addView(secondText);
                    obj10();
                }
            } else {
                if (myobj10.contains("house")) {
                    secondText.setText("You cannot examine that from this position.");
                    linearLayout.addView(secondText);
                    obj10();
                } else if (myobj10.contains("sky")) {
                    secondText.setText("At this time of year B903's sky is always orange. It seems like it's almost night the whole time, everyday.");
                    linearLayout.addView(secondText);
                    obj10();
                } if (housedesert==21) {
                    if (havefoundeggin==21) {
                        if (myobj10.contains("black") && (myobj10.contains("stone") || myobj10.contains("rock"))) {
                            secondText.setText("The black stones are cold.");
                        } else if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                secondText.setText("The black stones are cold.");
                            } else {
                                secondText.setText("It looks like the only white stone in the whole desert.");
                            }
                        } else if (myobj10.contains("egg") && (dropHouseDesert21.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    } else {
                        if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            secondText.setText("The black stones are cold.");
                        } else if (myobj10.contains("egg") && (dropHouseDesert21.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    }
                    linearLayout.addView(secondText);
                    obj10();
                } else if (housedesert==23) {
                    if (havefoundeggin==23) {
                        if (myobj10.contains("black") && (myobj10.contains("stone") || myobj10.contains("rock"))) {
                            secondText.setText("The black stones are cold.");
                        } else if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                secondText.setText("The black stones are cold.");
                            } else {
                                secondText.setText("It looks like the only white stone in the whole desert.");
                            }
                        } else if (myobj10.contains("egg") && (dropHouseDesert23.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    } else {
                        if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            secondText.setText("The black stones are cold.");
                        } else if (myobj10.contains("egg") && (dropHouseDesert23.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    }
                    linearLayout.addView(secondText);
                    obj10();
                } else if (housedesert==32) {
                    if (havefoundeggin==32) {
                        if (myobj10.contains("black") && (myobj10.contains("stone") || myobj10.contains("rock"))) {
                            secondText.setText("The black stones are cold.");
                        } else if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                secondText.setText("The black stones are cold.");
                            } else {
                                secondText.setText("It looks like the only white stone in the whole desert.");
                            }
                        } else if (myobj10.contains("egg") && (dropHouseDesert32.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    } else {
                        if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            secondText.setText("The black stones are cold.");
                        } else if (myobj10.contains("egg") && (dropHouseDesert32.contains("egg") || myInventoryPast.contains("egg"))) {
                            secondText.setText("It feels light on your hand. Fragile.\n\nIf you destroy it, no one will ever know that you killed the last non-human life in the Bubble.");
                        } else {
                            secondText.setText("This thing cannot be examined or it is not in the place.");
                        }
                    }
                    linearLayout.addView(secondText);
                    obj10();
                }
            }
        }
        // TAKE GET VERB 10
        else if (myobj10.matches("take") || myobj10.matches("get") || myobj10.matches("pick") || myobj10.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("take") || myobj10.contains("get") || myobj10.contains("pick")|| myobj10.contains("grab")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (housedesert==11 || housedesert==12 || housedesert==13 || housedesert==22) {
                if (myobj10.contains("stone") || myobj10.contains("rock")) {
                    if (myInventoryPast.contains("stone")) {
                        secondText.setText("You already took one.");
                    } else {
                        myInventoryPast.add("stone");
                        secondText.setText("Taken.");
                    }
                } else if (myobj10.contains("egg")) {
                    if (housedesert==11 && dropHouseDesert11.contains("egg")) {
                        dropHouseDesert11.remove("egg");
                        myInventoryPast.add("egg");
                        secondText.setText("Taken.");
                    } else if (housedesert==12 && dropHouseDesert12.contains("egg")) {
                        dropHouseDesert12.remove("egg");
                        myInventoryPast.add("egg");
                        secondText.setText("Taken.");
                    } else if (housedesert==13 && dropHouseDesert13.contains("egg")) {
                        dropHouseDesert13.remove("egg");
                        myInventoryPast.add("egg");
                        secondText.setText("Taken.");
                    } else if (housedesert==22 && dropHouseDesert22.contains("egg")) {
                        dropHouseDesert22.remove("egg");
                        myInventoryPast.add("egg");
                        secondText.setText("Taken.");
                    } else {
                        secondText.setText("You can't see any egg here!");
                    }
                } else {
                    secondText.setText("This thing cannot be taken or it is not in the place.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else {
                if (housedesert==21) {
                    if (havefoundeggin==21) {
                        if (myobj10.contains("black") && (myobj10.contains("stone") || myobj10.contains("rock"))) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                if (myInventoryPast.contains("stone")) {
                                    secondText.setText("You already took one.");
                                } else {
                                    myInventoryPast.add("stone");
                                    secondText.setText("Taken.");
                                }
                            } else {
                                myInventoryPast.add("egg");
                                secondText.setText("As you hold it in your hand, you realize it is an egg. A pure white egg.\nProbably the last spark of newborn life anybody is going to see in this hell of a planet.");
                            }
                        } else if (myobj10.contains("egg") && dropHouseDesert21.contains("egg")) {
                            dropHouseDesert21.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    } else {
                        if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myobj10.contains("egg") && dropHouseDesert21.contains("egg")) {
                            dropHouseDesert21.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    }
                } else if (housedesert==23) {
                    if (havefoundeggin==23) {
                        if (myobj10.contains("black") && (myobj10.contains("stone") || myobj10.contains("rock"))) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                if (myInventoryPast.contains("stone")) {
                                    secondText.setText("You already took one.");
                                } else {
                                    myInventoryPast.add("stone");
                                    secondText.setText("Taken.");
                                }
                            } else {
                                myInventoryPast.add("egg");
                                secondText.setText("As you hold it in your hand, you realize it is an egg. A pure white egg.\nProbably the last spark of newborn life anybody is going to see in this hell of a planet.");
                            }
                        } else if (myobj10.contains("egg") && dropHouseDesert23.contains("egg")) {
                            dropHouseDesert23.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    } else {
                        if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myobj10.contains("egg") && dropHouseDesert23.contains("egg")) {
                            dropHouseDesert23.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    }
                } else if (housedesert==32) {
                    if (havefoundeggin==32) {
                        if (myobj10.contains("black") && (myobj10.contains("stone") || myobj10.contains("rock"))) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            if (myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1) {
                                if (myInventoryPast.contains("stone")) {
                                    secondText.setText("You already took one.");
                                } else {
                                    myInventoryPast.add("stone");
                                    secondText.setText("Taken.");
                                }
                            } else {
                                myInventoryPast.add("egg");
                                secondText.setText("As you hold it in your hand, you realize it is an egg. A pure white egg.\nProbably the last spark of newborn life anybody is going to see in this hell of a planet.");
                            }
                        } else if (myobj10.contains("egg") && dropHouseDesert32.contains("egg")) {
                            dropHouseDesert32.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    } else {
                        if (myobj10.contains("stone") || myobj10.contains("rock")) {
                            if (myInventoryPast.contains("stone")) {
                                secondText.setText("You already took one.");
                            } else {
                                myInventoryPast.add("stone");
                                secondText.setText("Taken.");
                            }
                        } else if (myobj10.contains("egg") && dropHouseDesert32.contains("egg")) {
                            dropHouseDesert32.remove("egg");
                            myInventoryPast.add("egg");
                            secondText.setText("Taken.");
                        } else {
                            secondText.setText("This thing cannot be taken or it is not in the place.");
                        }
                    }
                }
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.matches("drop") || myobj10.contains("get rid of")) { // DROP VERB 10
            secondText.setText("Just say: Drop (and the object you want to drop).");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("drop")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            int n = 4;
            String s = myobj10.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (myInventoryPast.contains(s)) {
                myInventoryPast.remove(s);
                switch (housedesert) {
                    case 11:
                        dropHouseDesert11.add(s);
                        break;
                    case 12:
                        dropHouseDesert12.add(s);
                        break;
                    case 13:
                        dropHouseDesert13.add(s);
                        break;
                    case 21:
                        dropHouseDesert21.add(s);
                        break;
                    case 22:
                        dropHouseDesert22.add(s);
                        break;
                    case 23:
                        dropHouseDesert23.add(s);
                        break;
                    case 32:
                        dropHouseDesert32.add(s);
                        break;
                }
                secondText.setText("Dropped.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("You're not holding the " + s + ".");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.contains("put") || myobj10.contains("place")) { // PUT VERB 10
            secondText.setText("There is no container here where you can put an object in.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.matches("help")) { // HELP VERB 10
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You don't need help.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("help")) {
            secondText.setText("Just say HELP.");
            linearLayout.addView(secondText);
            obj10();
        }
        // FOLLOW VERB 10
        else if (myobj10.contains("follow") || myobj10.contains("chase") || myobj10.contains("find") || myobj10.contains("search")) {
            secondText.setText("There is no one here you can follow.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("drink")) { // DRINK VERB 10
            secondText.setText("You wish you had water.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.matches("eat")) { // EAT VERB 10
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("eat")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj10.contains("egg") && (myInventoryPast.contains("egg") || (housedesert==11 && dropHouseDesert11.contains("egg")) || (housedesert==12 && dropHouseDesert12.contains("egg")) || (housedesert==13 && dropHouseDesert13.contains("egg")) || (housedesert==21 && dropHouseDesert21.contains("egg")) || (housedesert==22 && dropHouseDesert22.contains("egg")) || (housedesert==23 && dropHouseDesert23.contains("egg")) || (housedesert==32 && dropHouseDesert32.contains("egg")))) {
                brokenegg=1;
                if (myInventoryPast.contains("egg")) {myInventoryPast.remove("egg");}
                else if (dropHouseDesert11.contains("egg")) {dropHouseDesert11.remove("egg");}
                else if (dropHouseDesert12.contains("egg")) {dropHouseDesert12.remove("egg");}
                else if (dropHouseDesert13.contains("egg")) {dropHouseDesert13.remove("egg");}
                else if (dropHouseDesert21.contains("egg")) {dropHouseDesert21.remove("egg");}
                else if (dropHouseDesert22.contains("egg")) {dropHouseDesert22.remove("egg");}
                else if (dropHouseDesert23.contains("egg")) {dropHouseDesert23.remove("egg");}
                else if (dropHouseDesert32.contains("egg")) {dropHouseDesert32.remove("egg");}
                secondText.setText("When it crashes on the ground... nothing happens.\nIf there was hope to see another species in B903, now there's none.");
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("arm") || myobj10.contains("hand")) {
                secondText.setText("What would you do with just one hand?");
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("house")) {
                secondText.setText("Stop. What if there's someone inside?");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("This thing cannot be eaten or it is not in the place.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.contains("write")) {  // WRITE VERB 10
            secondText.setText("You cannot write new notes.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("read")) {  // READ VERB 10
            secondText.setText("You can't see any note here!");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.matches("break") || myobj10.matches("hit") || myobj10.matches("attack") || myobj10.matches("punch") || myobj10.matches("fight") || myobj10.matches("kick")) {  // BREAK VERB 10
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("break") || myobj10.contains("hit") || myobj10.contains("attack") || myobj10.contains("punch") || myobj10.contains("fight") || myobj10.contains("kick")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj10.contains("house")) {
                if (housedesert==12) {
                    secondText.setText("As you punch the house, you hear the wood cracking a little.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("door")) {
                if (housedesert==12) {
                    secondText.setText("You see the dust of the door falling off as you hit the door.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("curtain")) {
                if (housedesert==12) {
                    secondText.setText("The cloth is so fine that it breaks apart when you touch it.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("window")) {
                if (housedesert==12) {
                    secondText.setText("The windows are already half-broken.");
                } else {
                    secondText.setText("You are kind of far from the house.");
                }
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("stone")) {
                secondText.setText("It is just a stone. Why would you hit a stone?");
                linearLayout.addView(secondText);
                obj10();
            } else if (myobj10.contains("egg") && (myInventoryPast.contains("egg") || (housedesert==11 && dropHouseDesert11.contains("egg")) || (housedesert==12 && dropHouseDesert12.contains("egg")) || (housedesert==13 && dropHouseDesert13.contains("egg")) || (housedesert==21 && dropHouseDesert21.contains("egg")) || (housedesert==22 && dropHouseDesert22.contains("egg")) || (housedesert==23 && dropHouseDesert23.contains("egg")) || (housedesert==32 && dropHouseDesert32.contains("egg")))) {
                brokenegg=1;
                if (myInventoryPast.contains("egg")) {myInventoryPast.remove("egg");}
                else if (dropHouseDesert11.contains("egg")) {dropHouseDesert11.remove("egg");}
                else if (dropHouseDesert12.contains("egg")) {dropHouseDesert12.remove("egg");}
                else if (dropHouseDesert13.contains("egg")) {dropHouseDesert13.remove("egg");}
                else if (dropHouseDesert21.contains("egg")) {dropHouseDesert21.remove("egg");}
                else if (dropHouseDesert22.contains("egg")) {dropHouseDesert22.remove("egg");}
                else if (dropHouseDesert23.contains("egg")) {dropHouseDesert23.remove("egg");}
                else if (dropHouseDesert32.contains("egg")) {dropHouseDesert32.remove("egg");}
                secondText.setText("When it crashes on the ground... nothing happens.\nIf there was hope to see another species in B903, now there's none.");
                linearLayout.addView(secondText);
                obj10();
            } else {
                secondText.setText("This thing cannot be hitted or it is not in the place.");
                linearLayout.addView(secondText);
                obj10();
            }
        } else if ((((myobj10.contains("draw") || myobj10.contains("pull")) && (myobj10.contains("back") || myobj10.contains("aside"))) || myobj10.contains("move")) && myobj10.contains("curtain")) { // CURTAIN ACTIONS 10
            if (housedesert==12) {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                secondText.setText("You can hardly see anything. It is really dark inside.");
            } else {
                secondText.setText("You have wandered off the house. You better get closer to do that.");
            }
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("curtain")) {
            if (housedesert==12) {
                secondText.setText("They are just some dark curtains.");
            } else {
                secondText.setText("You are kind of far from the house.");
            }
            linearLayout.addView(secondText);
            obj10();
        } else if ((myobj10.contains("look") && myobj10.contains("around")) || myobj10.matches("l") || myobj10.matches("look")) {
            String s = "";
            switch (housedesert) {
                case 11:
                    s = "Nowhere\nHeading northeast you still see the house.";
                    if (!(dropHouseDesert11.isEmpty())) {
                        int n = dropHouseDesert11.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert11.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 12:
                    if (closeddoor==0) {
                        s = "In the middle of nowhere\nThe ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door open.";
                    } else {
                        s = "In the middle of nowhere\nThe ground is covered with black stones. The sky is still orange.\nHeading NORTH you see a house with the door closed.";
                    }
                    if (!(dropHouseDesert12.isEmpty())) {
                        int n = dropHouseDesert12.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert12.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 13:
                    s = "Nowhere\nHeading northwest you still see the house.";
                    if (!(dropHouseDesert13.isEmpty())) {
                        int n = dropHouseDesert13.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert13.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 21:
                    if (havefoundeggin==21 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1)) {
                        s = "Nowhere\nThe house is northeast.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nThe house is northeast.";
                    }
                    if (!(dropHouseDesert21.isEmpty())) {
                        int n = dropHouseDesert21.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert21.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 22:
                    s = "Nowhere\nHeading north you still see the house.";
                    if (!(dropHouseDesert22.isEmpty())) {
                        int n = dropHouseDesert22.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert22.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 23:
                    if (havefoundeggin==23 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1)) {
                        s = "Nowhere\nThe house is northwest.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nThe house is northwest.";
                    }
                    if (!(dropHouseDesert23.isEmpty())) {
                        int n = dropHouseDesert23.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert23.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
                case 32:
                    if (havefoundeggin==32 && !(myInventoryPast.contains("egg") || dropHouseDesert11.contains("egg") || dropHouseDesert12.contains("egg") || dropHouseDesert13.contains("egg") || dropHouseDesert21.contains("egg") || dropHouseDesert22.contains("egg") || dropHouseDesert23.contains("egg") || dropHouseDesert32.contains("egg") || brokenegg==1)) {
                        s = "Nowhere\nThe house is north.\nHere you see a white stone that calls your attention.";
                    } else {
                        s = "Nowhere\nThe house is north.";
                    }
                    if (!(dropHouseDesert32.isEmpty())) {
                        int n = dropHouseDesert32.size();
                        for (int i = 0; i < n; i++) {
                            s = s + "\nThere's the " + dropHouseDesert32.get(i) + " here.";
                        }
                        secondText.setText(s);
                    } else {
                        secondText.setText(s);
                    }
                    break;
            }
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.matches("i") || myobj10.matches("inventory")) {
            String inventory = "You have:";
            if (!(myInventoryPast.isEmpty())) {
                int n = myInventoryPast.size();
                for (int i=0; i<n; i++) {
                    inventory = inventory + "\n    " + myInventoryPast.get(i) + ".";
                }
                secondText.setText(inventory);
                linearLayout.addView(secondText);
                obj10();
            } else {
                inventory = "You have nothing.";
                secondText.setText(inventory);
                linearLayout.addView(secondText);
                obj10();
            }
        } else if (myobj10.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.matches("sleep")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Even the sky is still orange.\nYou just feel thirstier.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("check") && myobj10.contains("out")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("check") || myobj10.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("jump") || myobj10.contains("climb") || myobj10.contains("turn") || myobj10.contains("shut") || myobj10.contains("look") || myobj10.contains("see") || myobj10.contains("watch") || myobj10.contains("play") || myobj10.contains("run") || myobj10.contains("walk") || myobj10.contains("move") || myobj10.contains("give") || myobj10.contains("offer")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Look around you.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("smell")) {
            secondText.setText("It does not smell as bad as the part of the planet where your house is.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("listen")) {
            secondText.setText("You can barely hear someone crying inside the house.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("wait")) {
            secondText.setText("Time passes...");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.contains("what") && myobj10.contains("time") && myobj10.contains("is") || myobj10.matches("time") || myobj10.matches("the time")) {
            secondText.setText("It is sunset.");
            linearLayout.addView(secondText);
            obj10();
        } else if (myobj10.matches("diagnostic") || myobj10.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            linearLayout.addView(secondText);
            obj10();
        }

        return (String) secondText.getText();
    }







}
