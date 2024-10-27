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
                              case 5:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.objectiveFive.checkObjAnswer(myobjxlow, MainActivity.this).matches("") && levelManager.commonAnswers.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.consultGuide.checkObjAnswer(myobjxlow, MainActivity.this).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 6:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.objectiveSix.checkObjAnswer(myobjxlow, MainActivity.this).matches("") && levelManager.commonAnswers.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.consultGuide.checkObjAnswer(myobjxlow, MainActivity.this).matches("")) {
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
                secondText.setText("This thing cannot be hit or it is not in the place.");
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
