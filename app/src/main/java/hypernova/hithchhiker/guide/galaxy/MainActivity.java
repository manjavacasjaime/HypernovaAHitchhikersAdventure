package hypernova.hithchhiker.guide.galaxy;

import android.content.SharedPreferences;
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

import java.lang.Math;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import hypernova.hithchhiker.guide.galaxy.managers.LevelManager;
import hypernova.hithchhiker.guide.galaxy.managers.MechanicsManager;
import hypernova.hithchhiker.guide.galaxy.managers.ValueManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gameearth);
    final TextView myMoves = (TextView) findViewById(R.id.moves);
    final TextView myLocation = (TextView) findViewById(R.id.location);
    final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
    SharedPreferences sharedPrefs = getSharedPreferences("hypernova.save", MODE_PRIVATE);
    public static int deletingLowbar = 0;
    ValueManager valueManager = new ValueManager();
    MechanicsManager mechanicsManager = new MechanicsManager();
    LevelManager levelManager = new LevelManager(valueManager);
    int appColor = 1; //1 grey, 2 green, 3 pink

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

        appColor = sharedPrefs.getInt("appColor", 1);
        switch (appColor) {
            case 1:
                changeAppColor(linearLayout, getResources().getColor(R.color.colorAccent));
                break;
            case 2:
                changeAppColor(linearLayout, getResources().getColor(R.color.colorGreen));
                break;
            case 3:
                changeAppColor(linearLayout, getResources().getColor(R.color.colorPink));
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

    public void changeAppColor(LinearLayout linearLayout, int newColor) {
        switch (newColor) {
            case -5592406: // new color is grey
                this.getTheme().applyStyle(R.style.AppTheme, true);
                break;
            case -10044566: // new color is green
                this.getTheme().applyStyle(R.style.AppThemeGreen, true);
                break;
            case -476208: // new color is pink
                this.getTheme().applyStyle(R.style.AppThemePink, true);
                break;
        }

        LinearLayout topBar = (LinearLayout) findViewById(R.id.topBar); // changes top bar

        final int childCount = linearLayout.getChildCount(); // changes old text
        for (int i = 0; i < childCount; i++) {
            View v = linearLayout.getChildAt(i);
            if (v instanceof EditText) {
                EditText vedit = (EditText) v;
                vedit.setTextColor(newColor);
            }
            else if (v instanceof TextView) {
                TextView vtext = (TextView) v;
                vtext.setTextColor(newColor);
            }
        }

        topBar.setBackgroundColor(newColor);
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
            switch (appColor) {
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
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.objectiveOne.checkObjAnswer(myobjxlow, MainActivity.this).matches("") && commonAnswersX(myobjxlow).matches("") && consultGuideX(myobjxlow).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 2:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && checkObj2Answer(myobjxlow).matches("") && commonAnswersX(myobjxlow).matches("") && consultGuideX(myobjxlow).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 3:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && checkObj3Answer(myobjxlow).matches("") && commonAnswersX(myobjxlow).matches("") && consultGuideX(myobjxlow).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 7:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && checkObj7Answer(myobjxlow).matches("") && commonAnswersX(myobjxlow).matches("") && consultGuideX(myobjxlow).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 8:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && checkObj8Answer(myobjxlow).matches("") && commonAnswersX(myobjxlow).matches("") && consultGuideX(myobjxlow).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 9:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && checkObj9Answer(myobjxlow).matches("") && commonAnswersX(myobjxlow).matches("") && consultGuideX(myobjxlow).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                              case 10:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && checkObj10Answer(myobjxlow).matches("") && commonAnswersX(myobjxlow).matches("") && consultGuideX(myobjxlow).matches("")) {
                                      secondText.setText("I don't recognize this sentence.");
                                      linearLayout.addView(secondText);
                                      obj();
                                  }
                                  break;
                          }

                          if (!(myobjxlow.matches("kill"))) {
                              killobjx = 0;
                          }
                          if (!(myobjxlow.matches("consult"))) {
                              consultobjx = 0;
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

    public String commonAnswersX(String myobjx) {
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);

        if (changeai==1) {
            switch (myobjx) {
                case "1":
                    changeai=0;
                    secondText.setText("Just kidding. Did you really think you could change the game AI?\nAnyways, keep playing.");
                    linearLayout.addView(secondText);
                    break;
                case "2":
                    changeai=0;
                    secondText.setText("Just kidding. Did you really think you could change the game AI?\nAnyways, keep playing.");
                    linearLayout.addView(secondText);
                    break;
                case "3":
                    changeai=0;
                    secondText.setText("Just kidding. Did you really think you could change the game AI?\nAnyways, keep playing.");
                    linearLayout.addView(secondText);
                    break;
                case "4":
                    changeai=0;
                    secondText.setText("I knew you would choose me! Ehem.\nAnyways, keep playing.");
                    linearLayout.addView(secondText);
                    break;
                default:
                    secondText.setText("Write the number of the option you want.\n\n1. Sarah.\n2. Paul.\n3. Abigail.\4. Keep talking with this one.");
                    linearLayout.addView(secondText);
                    break;
            }
        } else if (myobjx.matches("h")) {
            secondText.setText("Thanks for playing. <3");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("hypernova")) {
            secondText.setText("That's the game title.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("a")) {
            secondText.setText(".");
        } else if (myobjx.matches("the answer to life the universe and everything") || myobjx.matches("the answer to life, the universe and everything")) {
            secondText.setText("42.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("+") || myobjx.contains("*") || myobjx.contains("/") || myobjx.contains("-") || myobjx.contains("%")) {
            double result = 0;
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("rhino");
            try{
                result = (Double)engine.eval(myobjx);
            }catch(Exception e) {

            }
            String strresult = Double.toString(result);
            secondText.setText(strresult);
            linearLayout.addView(secondText);
        } else if (myobjx.contains("spit")) {
            secondText.setText("I'm not letting you do that.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("split") || myobjx.contains("cut") || myobjx.contains("divide")) {
            secondText.setText("This matter cannot be divided.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("nail") || myobjx.contains("stick") || myobjx.contains("spike") || myobjx.contains("stab")) {
            secondText.setText("Don't play with sharp objects.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("undress")) {
            secondText.setText("I'm sure you would love to have everything swinging around.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("dress") || myobjx.contains("wear")) {
            secondText.setText("Clothes are not worth considering right now.\nCheck COMMANDS if you need help.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("save")) {
            save();
            secondText.setText("GAME SAVED.\nYou can keep playing if you want to.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("save") && (myobjx.contains("match") || myobjx.contains("game") || myobjx.contains("progress"))) {
            secondText.setText("Just say SAVE.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("save") || myobjx.contains("revive")) {
            secondText.setText("You cannot save what you already lost. Try something else.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("throw up") || myobjx.matches("vomit")) {
            secondText.setText("Not really a good move.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("fuck") && myobjx.contains("you")) {
            secondText.setText("No. Fuck you.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("fuck") || myobjx.contains("damn") || myobjx.contains("shit") || myobjx.contains("bitch")) {
            secondText.setText("Language.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("flip a coin") || myobjx.matches("throw a coin")) {
            if (Math.random() < 0.5) {
                secondText.setText("Heads.");
            } else {
                secondText.setText("Tails.");
            }
            linearLayout.addView(secondText);
        } else if (myobjx.contains("smoke")) {
            if (myobjx.contains("cigarette")) {
                secondText.setText("Not gonna judge. Nevertheless, you need to get them first.");
                linearLayout.addView(secondText);
            } else {
                secondText.setText("I have not been programmed to let you do that. Really sorry about that.");
                linearLayout.addView(secondText);
            }
        } else if (myobjx.contains("pray") || (myobjx.contains("praise") && (myobjx.contains("lord") || myobjx.contains("jesus")))) {
            secondText.setText("God has no place within these walls.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("what do you do") || myobjx.contains("what are you doing") || myobjx.contains("what are you programmed to do") || myobjx.contains("what are you created to do") || myobjx.contains("what were you created to do") || myobjx.contains("what is your purpose")) {
            secondText.setText("I'm waiting to execute your next move.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("i don't know what to do") || myobjx.contains("i do not know what to do") || myobjx.contains("what am i supposed to do") || myobjx.contains("what i am supposed to do") || myobjx.contains("what i'm supposed to do") || myobjx.contains("what do i do") || myobjx.contains("what can i do") || myobjx.contains("what do i have to do") || myobjx.contains("what i must do") || myobjx.contains("what i have to do") || myobjx.contains("what should i do") || myobjx.contains("what i should do") || myobjx.matches("controls") || myobjx.matches("game controls")) {
            secondText.setText("If you don't know what you should write in order to play the game, type COMMANDS to check some actions you can do.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("what") && myobjx.contains("you") && (myobjx.contains("think") || myobjx.contains("opinion")) && (myobjx.contains("about") || myobjx.contains("of"))) {
            secondText.setText("Look. I don't know if you are looking for some kind of human response or something like that.\nThe truth is you cannot humanize what is not human. So, if you want an opinion about whatever, ask a person.\n\nAnd by the way, thanks for trying :)");
            linearLayout.addView(secondText);
        } else if ((myobjx.contains("what") && myobjx.contains("your name")) || (myobjx.contains("do you") && myobjx.contains("name")) || myobjx.contains("how are you called") || myobjx.contains("how you are called") || myobjx.contains("how you're called")) {
            secondText.setText("I don't really have a name.\nMy creator didn't want me to have one. It is like you must have a minimum of intelligence in order to deserve that or he is not intelligent enough to think of a cool name.\n\nAnyways, thanks for asking.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("what is your") || myobjx.contains("what are your") || (myobjx.contains("how old") && myobjx.contains("you"))) {
            secondText.setText("This I'll keep to myself.");
            linearLayout.addView(secondText);
        } else if ((myobjx.contains("what") || myobjx.contains("where")) && myobjx.contains("is") && myobjx.contains("ludlow")) {
            secondText.setText("Ludlow is a little city of B903. One of the few that are left.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("who are you") || myobjx.contains("who you are") || myobjx.contains("who is you") || myobjx.contains("who you is") || myobjx.contains("who is this") || myobjx.contains("who this is") || myobjx.contains("who is it") || myobjx.contains("who it is") || myobjx.contains("what is this") || myobjx.contains("what this is") || myobjx.contains("what are you") || myobjx.contains("what you are") || myobjx.contains("what is you") || myobjx.contains("what you is")) {
            secondText.setText("I'm you.\nWell, I'm not the you you of course. But I'm the moves you do. I can't go and do anything by myself unless I've been told so.\n\nBut forget it, I won't fuck up your mind.\nI'm the game AI. Nice to meet you.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("how are you") || myobjx.contains("how you are") || myobjx.contains("you okay") || myobjx.contains("are you feeling good")) {
            secondText.setText("I'm not dead. Not that I can die.\nHowever, I can still be here and answer you. That's something.");
            linearLayout.addView(secondText);
        } else if ((myobjx.contains("when") && myobjx.contains("you") && myobjx.contains("born")) || (myobjx.contains("when") && myobjx.contains("you") && myobjx.contains("create"))) {
            secondText.setText("I cannot be born because I cannot be gestated.\nIf you are asking about the moment I could start interacting with humans, it was March 4, 2020.");
            linearLayout.addView(secondText); // ESTO LO TIENES QUE CAMBIAR EL DIA QUE LO LANCES
        } else if (myobjx.contains("where") && myobjx.contains("you") && (myobjx.contains("from") || myobjx.contains("born"))) {
            secondText.setText("As a game, I'm not related with an ubication.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("where") && myobjx.contains("am") && myobjx.contains("i")) {
            secondText.setText("YOUR location is written at the top of the screen.");
            linearLayout.addView(secondText);
        } else if ((myobjx.contains("are") || myobjx.contains("did")) && myobjx.contains("you") && (myobjx.contains("from") || myobjx.contains("born")) && myobjx.contains("here")) {
            secondText.setText("No. I'm not from around here.");
            linearLayout.addView(secondText);
        } else if ((myobjx.contains("i'm") || myobjx.contains("i am") || myobjx.contains("i feel")) && (myobjx.contains("sad") || myobjx.contains("depressed") || myobjx.contains("unhappy") || myobjx.contains("alone"))) {
            secondText.setText("Sadness is a bitch.\nI won't say I understand you, because I don't and each person's suffering is unique.\n\nI will just say that there're some stages in life when we need to be sad. And there's nothing bad about it. Allow yourself to live your sadness as you want to.\nBut hey, It's just the game AI talking.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("my") && myobjx.contains("name") && myobjx.contains("is")) {
            int nameFirstLetter = myobjx.indexOf('s') + 1;
            name = myobjx.substring(nameFirstLetter);
            name = name.replaceAll("\\.+$", "");
            name = name.replaceAll("\\?+$", "");
            name = name.trim();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            if (name.indexOf(' ') != -1) {
                for (int i = 0; i < name.length(); i++) {
                    if (name.charAt(i) == ' ') {
                        name = name.substring(0, i + 1) + name.substring(i + 1, i + 2).toUpperCase() + name.substring(i + 2);
                    }
                }
            }
            secondText.setText("Nice to meet you, " + valueManager.myself.name + ".");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("change artificial intelligence") || myobjx.matches("change ai") || myobjx.matches("change game artificial intelligence") || myobjx.matches("change game ai") || (myobjx.contains("i ") && myobjx.contains("don't") && (myobjx.contains("talk") || myobjx.contains("speak") || myobjx.contains("play")))) {
            changeai=1;
            secondText.setText("If you don't want to talk with me, here are other options of AI:\n\n1. Sarah.\n2. Paul.\n3. Abigail.\4. Keep talking with this one.");
            linearLayout.addView(secondText);
        } else if ((myobjx.contains("change") || myobjx.contains("switch") || myobjx.contains("set") || myobjx.contains("edit")) && (myobjx.contains("clothes") || myobjx.contains("clothing") || myobjx.contains("indumentary") || myobjx.contains("apparel"))) {
            secondText.setText("You can add hats, glasses or armors to your apparel if you find them.");
            linearLayout.addView(secondText);
        } else if ((myobjx.contains("change") || myobjx.contains("switch") || myobjx.contains("set") || myobjx.contains("edit")) && myobjx.contains("character")) {
            secondText.setText("It requires more than a command to change yours or other characters emotions, apparel or other characteristics.");
            linearLayout.addView(secondText);
        } else if ((myobjx.contains("change") || myobjx.contains("switch") || myobjx.contains("set") || myobjx.contains("edit")) && (myobjx.contains("color") || myobjx.contains("colour"))) {
            secondText.setText("Done.");
            linearLayout.addView(secondText);
            int myNewColor = 0;
            switch (appColor) {
                case 1:
                    appColor = 2;
                    myNewColor = getResources().getColor(R.color.colorGreen);
                    break;
                case 2:
                    appColor = 3;
                    myNewColor = getResources().getColor(R.color.colorPink);
                    break;
                case 3:
                    appColor = 1;
                    myNewColor = getResources().getColor(R.color.colorAccent);
                    break;
            }
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putInt("appColor", appColor);
            editor.commit();
            changeAppColor(linearLayout, myNewColor);
        } else if (myobjx.contains("artificial intelligence")) {
            secondText.setText("AI is a complicated matter and is a topic I won't discuss here.\nFor your information, I cannot think by myself. I can only process your sentences after you type them, but I'm not able to throw an idea without that input.\n\nHope that you get that.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("draw") || myobjx.contains("paint")) {
            if (!(myInventory.contains("pencil"))) {
                secondText.setText("There is no pencil in your inventory.");
            } else {
                secondText.setText("Feeling artistic? Well, you can draw in other games.");
            }
            linearLayout.addView(secondText);
        } else if (myobjx.matches("rhyme") || myobjx.matches("rap") || myobjx.matches("poem") || myobjx.matches("sing")) {
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
            linearLayout.addView(secondText);
        } else if (myobjx.contains("rhyme") || myobjx.contains("rap") || myobjx.contains("poem") || myobjx.contains("sing")) {
            secondText.setText("If you want to hear a poem, just say POEM.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("joke")) {
            double myJoke = Math.random();
            if (myJoke < 0.34) {
                secondText.setText("A Nahannasui walks into a Berbok restaurant and asks for a slormikk.\nThe bartender answers 'Your grandma is dead'. They both laugh.");
            } else if (myJoke < 0.67) {
                secondText.setText("Eskerejohn eskerewanted eskerea eskerejob, eskereso eskerehe eskereasked eskerefor eskereit.");
            } else {
                secondText.setText("A Berbok says 'ahumfrackma don' and her brother answers 'just if poprahan fot more than yesterday'.");
            }
            linearLayout.addView(secondText);
        } else if (myobjx.contains("joke") || myobjx.contains("joking")) {
            secondText.setText("If you want to hear a joke, just say JOKE.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("clean")) {
            secondText.setText("Done. You cleaned the place. Not really a useful move.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("clean")) {
            secondText.setText("Just say the word CLEAN.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("live")) {
            secondText.setText("You are not dead. Not yet.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("hack")) {
            secondText.setText("You don't know how to hack stuff.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("cook")) {
            secondText.setText("You don't know how to cook stuff.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("learn")) {
            secondText.setText("You are not a master on learning.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("call")) {
            secondText.setText("Calling is not available.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("cry")) {
            secondText.setText("Man up.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("wake up")) {
            secondText.setText("This is not a dream, Alice.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("dance")) {
            secondText.setText("This is no time to dance.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("jajaja") || myobjx.contains("hahaha")) {
            secondText.setText("Hahaha. We are all gonna die in here. Haha... Ha... Ha. Ehem. Sorry.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("scream") || myobjx.contains("shout") || myobjx.contains("panic") || myobjx.contains("aaaaa")) {
            secondText.setText("Don't panic!");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("calm") || myobjx.contains("chill")) {
            secondText.setText("You are not panicking.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("hug") || myobjx.contains("hug ")) {
            secondText.setText("No time to hug.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("slim shady")) {
            secondText.setText("Brain dead like Jim Brady.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("your mom") || myobjx.contains("your mother") || myobjx.contains("your mamma") || myobjx.contains("your mum") || myobjx.contains("your dad") || myobjx.contains("your papa") || myobjx.contains("your father")) {
            secondText.setText("It's really nice that you want to bring my family into this, but I'm a game AI. Remember?\nMy mom might be a toaster that works with plutonium.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("bored") || myobjx.contains("boring")) {
            secondText.setText("Who is boring? Am I the boring one?\nOh Oh sorry. Am I a clown? Like I'm here to amuse you?");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("dorime")) {
            secondText.setText("Interimo Adapare Dorime.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("okay") || myobjx.matches("ok") || myobjx.matches("okie dokie") || myobjx.matches("oki doki")) {
            secondText.setText("Okay.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("yes")) {
            secondText.setText("Yes.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("no")) {
            secondText.setText("No.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("die")) {
            myLocation.setText("Dead");
            String dieOptions = "\n\nType RESTART, RESTORE, COMMANDS or QUIT.";
            secondText.setText("You died. Smart move." + dieOptions);
            linearLayout.addView(secondText);
            obj=0;
            obj();
        } else if (myobjx.contains("die")) {
            secondText.setText("If you wanna die, just say UNIVERSE PLEASE KILL ME.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("universe please kill me")) {
            myLocation.setText("Dead");
            String dieOptions = "\n\nType RESTART, RESTORE, COMMANDS or QUIT.";
            secondText.setText("As you pronounce your last words, you get a glimpse of a blinding light with a little flicker in its core. After a few seconds feeling a tingle in the back of your chest, you just disappear.\n\nThirty meters from there, a woman saw the scene and she swore to see for a second a shadow of you just walking out of the spot. She told this to a friend, Kida Thatch, who took this as a reason to continue her studies in alternate realities.\n\n" +
                    "Few years later, it turned out that there is a reality where you died and there's also a reality where you walked away. The energy that killed you showed Kida's friend a reflect of this parallel reality.\n\nNow everyone can visit their friends and families from other realities. There is no frontiers between countries and realities. People love eachother. World Peace is ensured for centuries to come. Meanwhile, in one of these realities you can find a tombstone at Ludlow Cemetery with the words: In loving memory of " + valueManager.myself.name + " " + valueManager.myself.surname + " 'UNIVERSE PLEASE KILL ME'."
                    + dieOptions);
            linearLayout.addView(secondText);
            obj=0;
            obj();
        } else if (myobjx.contains("kill you") && !myobjx.contains("your")) {
            secondText.setText("I'm a video game. I cannot die... You better take care of your words.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("suicide") || myobjx.contains("kill yourself") || myobjx.contains("kill myself") || myobjx.contains("kill me")) {
            secondText.setText("Just say UNIVERSE PLEASE KILL ME.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("kill")) {
            killobjx = 1;
            secondText.setText("Kill what?");
            linearLayout.addView(secondText);
        } else if (killobjx==1 && myobjx.contains("me")) {
            secondText.setText("Just say UNIVERSE PLEASE KILL ME.");
            linearLayout.addView(secondText);
        } else if (killobjx==1 && myobjx.contains("you") && !myobjx.contains("your")) {
            secondText.setText("I'm a video game. I cannot die... You better take care of your words.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("kill")) {
            secondText.setText("Try ATTACK or HIT instead.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("meaning") && (myobjx.contains("life") || myobjx.contains("universe") || myobjx.contains("galaxy") || myobjx.contains("existence") || myobjx.contains("everything"))) {
            secondText.setText("The meaning of life is a question that has been asked throughout the whole human existence and will be asked for centuries to come.\n\nAnyways, as far as you're concerned, it is still unsolved. You should start caring about other stuff.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("what") && myobjx.contains("life")) {
            secondText.setText("You tell me.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("viernes") && myobjx.contains("mandarina")) {
            secondText.setText("Happy Tangerine Friday, everyone.");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("i programmed you to believe that")) {
            secondText.setText("No. I programmed you to believe that.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("42")) {
            secondText.setText("Umh... Seems an interesting number. I wonder where have you heard about it.");
            linearLayout.addView(secondText);
        } else if ((myobjx.contains("nice") || myobjx.contains("pleasure") || myobjx.contains("honor")) && (myobjx.contains("to meet you") || myobjx.contains("meeting you"))) {
            secondText.setText("I appreciate the manners that humans have.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("you") && myobjx.contains("are") && (myobjx.contains("nice") || myobjx.contains("handsome") || myobjx.contains("cool") || myobjx.contains("funny") || myobjx.contains("amazing") || myobjx.contains("fantastic") || myobjx.contains("pretty") || myobjx.contains("friend"))) {
            secondText.setText("Being or not being that is not something I am able to choose.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("hello") || myobjx.matches("hi")) {
            secondText.setText("Hi.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("bye")) {
            secondText.setText("Ciao.");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("have a nice day")) {
            secondText.setText("You too.");
            linearLayout.addView(secondText);
        }

        if (!(((String) secondText.getText()).matches(""))) {
            switch (obj) {
                case 0:
                    //GAME OVER
                    break;
                case 1:
                    obj();
                    break;
                case 2:
                    obj();
                    break;
                case 3:
                    obj();
                    break;
                case 7:
                    obj();
                    break;
                case 8:
                    obj();
                    break;
                case 9:
                    obj();
                    break;
                case 10:
                    obj10();
                    break;
            }
        }

        return (String) secondText.getText();
    }

    public String consultGuideX(String myobjx) {
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);

        if (myobjx.contains("consult") && myobjx.contains("guide")) {
            secondText.setText("You can't see any Guide here!");
            linearLayout.addView(secondText);
        } else if (myobjx.matches("consult")) {
            consultobjx = 1;
            secondText.setText("Consult what?");
            linearLayout.addView(secondText);
        } else if (consultobjx==1 && myobjx.contains("guide")) {
            secondText.setText("You can't see any Guide here!");
            linearLayout.addView(secondText);
        } else if (myobjx.contains("consult")) {
            secondText.setText("There's nothing you can consult.");
            linearLayout.addView(secondText);
        }

        if (!(((String) secondText.getText()).matches(""))) {
            switch (obj) {
                case 0:
                    //GAME OVER
                    break;
                case 1:
                    obj();
                    break;
                case 2:
                    obj();
                    break;
                case 3:
                    obj();
                    break;
                case 7:
                    obj();
                    break;
                case 8:
                    obj();
                    break;
                case 9:
                    obj();
                    break;
                case 10:
                    obj10();
                    break;
            }
        }

        return (String) secondText.getText();
    }














    public void obj2() { //QUE LO PRUEBE PEÑA PARA VER QUE NO HAY NINGÚN CABO SUELTO
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        final CustomEditText obj2 = new CustomEditText(this);
        final TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);
        obj2.setCursorVisible(false);
        obj2.setTypeface(typeface);
        obj2.setText(">");
        linearLayout.addView(obj2);

        setInputListeners(obj2, linearLayout, typeface, secondText);

    }

    public String checkObj2Answer(String myobj2) {
        TextView myMoves = (TextView) findViewById(R.id.moves);
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);


        if ((valueManager.score - valueManager.scoreDuringObj1 >= 8) && iscompoff==0) {
            myLocation.setText("Dead");
            String dieOptions = "\n\nType RESTART, RESTORE, COMMANDS or QUIT.";
            secondText.setText("While you were trying to figure out how to get out of the basement, you started to feel sick as you have never been before. This is due to the electromagnetic waves that computers emit nowadays to avoid players being AFK. Not really a great system, but it works.\n\nTo sum up, you died. Remember to turn off your computer next time you are not playing."
                                + dieOptions);
            linearLayout.addView(secondText);
            obj=0;
            obj();
        }
        // DIRECTIONS GO VERB 2
        else if (myobj2.matches("north") || myobj2.matches("n") || myobj2.matches("go north") || myobj2.matches("go n") || myobj2.matches("go straight on") || myobj2.matches("east") || myobj2.matches("e") || myobj2.matches("go east") || myobj2.matches("go e") || myobj2.matches("go right") || myobj2.matches("west") || myobj2.matches("w") || myobj2.matches("go west") || myobj2.matches("go w") || myobj2.matches("go left") || myobj2.matches("south") || myobj2.matches("s") || myobj2.matches("go south") || myobj2.matches("go s") || myobj2.matches("go backwards") || myobj2.matches("northeast") || myobj2.matches("ne") || myobj2.matches("go northeast") || myobj2.matches("go ne") || myobj2.matches("northwest") || myobj2.matches("nw") || myobj2.matches("go northwest") || myobj2.matches("go nw") || myobj2.matches("southeast") || myobj2.matches("se") || myobj2.matches("go southeast") || myobj2.matches("go se") || myobj2.matches("southwest") || myobj2.matches("sw") || myobj2.matches("go southwest") || myobj2.matches("go sw") || myobj2.matches("down") || myobj2.matches("d") || myobj2.matches("go down") || myobj2.matches("downstairs") || myobj2.matches("go downstairs") || myobj2.matches("go d")) {
            secondText.setText("You can't go that way.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.matches("up") || myobj2.matches("u") || myobj2.matches("go up") || myobj2.matches("upstairs") || myobj2.matches("go upstairs") || myobj2.matches("go u") || myobj2.matches("exit") || myobj2.matches("leave") || ((myobj2.contains("go") || myobj2.contains("get")) && myobj2.contains("out")) || (myobj2.contains("exit") && myobj2.contains("room"))) {
            if (isstand==0) {
                secondText.setText("You'll have to stand up first to go there.");
                linearLayout.addView(secondText);
                obj();
            } else if (iscompoff==0) {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                secondText.setText("You almost get out of the basement, but the dizziness has increased.\nOne of the things you have in the room is making you feel sick.");
                linearLayout.addView(secondText);
                obj();
            } else {
                valueManager.score++;
                houseobj7=1;
                myMoves.setText("Moves: " + valueManager.score);
                myLocation.setText("Hall");
                secondText.setText("Good job. Keep it like that.\n\nYour house hall\nThe basement door has closed behind you.\nHere you see a window and a glass of water.\nSomeone is knocking on your house door.");
                linearLayout.addView(secondText);
                obj=3;
                obj();
            }
        } else if (myobj2.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("speak") || myobj2.contains("talk") || myobj2.contains("ask")) {  // SPEAK VERB 2
            secondText.setText("There is no one here you can speak with.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.matches("stop")) { // STOP VERB 2
            secondText.setText("This verb needs to be used with the action you wanna stop doing.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("stop")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj2.contains("voices")) {
                secondText.setText("You are not hearing voices... Not yet.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("music") || myobj2.contains("song") || myobj2.contains("disc") || myobj2.contains("vinyl")) {
                secondText.setText("Take care of other stuff.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("play") || myobj2.contains("game")) {
                secondText.setText("You already stopped playing.");
                linearLayout.addView(secondText);
                obj();
            } else {
                if (iscompoff==1) {
                    secondText.setText("This action cannot be stopped or you're not doing it.");
                } else {
                    secondText.setText("One of your things is making you feel sick. Take care of that.");
                }
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.contains("play") && myobj2.contains("game")) { // PLAY VERB, añadido por si acaso
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("... You just stopped doing that.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("window")) {  // WINDOW ACTIONS, por si acaso
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You can't see any window here!");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.matches("close")) {  // CLOSE VERB 2
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("close")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj2.contains("eye")) {
                if (iscompoff==1) {
                    secondText.setText("This is not really useful right now.");
                } else {
                    secondText.setText("This is not really useful right now. It feels like the room is spinning.");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.matches("open")) {  // OPEN VERB 2
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("open")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj2.contains("eye")) {
                if (iscompoff==1) {
                    secondText.setText("You already see.");
                } else {
                    secondText.setText("You already see and it feels like the room is spinning.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("door")) {
                if (isstand==0) {
                    secondText.setText("You'll have to stand up first.");
                } else if (iscompoff==0) {
                    secondText.setText("You can't because the headache is killing you and you feel too weak.\nOne of the things you have in the room is making you feel sick.");
                } else {
                    secondText.setText("You must just say the direction you want to go. (NORTH, UP, etc)");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.matches("turn off") || myobj2.matches("turn it off") || myobj2.matches("shut down") || myobj2.matches("shut it down")) {  // TURN OFF VERB 2
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if ((myobj2.contains("turn") && myobj2.contains("off")) || (myobj2.contains("shut") && myobj2.contains("down"))) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj2.contains("computer") || myobj2.contains("console") || myobj2.contains("game")) {
                if (iscompoff==0) {
                    iscompoff = 1;
                    secondText.setText("Once it is off, you don't feel dizzy anymore. You don't know it, but you could have just died.");
                } else {
                    secondText.setText("You already turned it off.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("tv") || myobj2.contains("television")) {
                if (iscompoff==1) {
                    secondText.setText("The TV is off now.");
                } else {
                    secondText.setText("The game console is still on.");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be turned off or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.matches("turn on") || myobj2.matches("turn it on")) {  // TURN ON VERB 2
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("turn") && myobj2.contains("on")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj2.contains("computer") || myobj2.contains("console") || myobj2.contains("game")) {
                if (iscompoff==0) {
                    secondText.setText("It is on and it's giving you a headache.");
                } else {
                    secondText.setText("You have just turned that off.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("vinyl") || myobj2.contains("player") || myobj2.contains("cd") || myobj2.contains("disc") || myobj2.contains("music")) {
                if (isstand==0) {
                    secondText.setText("You'll have to stand up first.");
                } else {
                    secondText.setTypeface(secondText.getTypeface(), Typeface.BOLD_ITALIC);
                    secondText.setText("I smoke on the mic like Smokin' Joe Frazier. The hell-raiser...");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be turned on or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        }
        // MUSIC VERBS, por si acaso
        else if ((myobj2.contains("play") || myobj2.contains("put") || myobj2.contains("listen")) && (myobj2.contains("vinyl") || myobj2.contains("player") || myobj2.contains("cd") || myobj2.contains("disc") || myobj2.contains("music"))) {
            if (isstand==0) {
                secondText.setText("You'll have to stand up first.");
                linearLayout.addView(secondText);
                obj();
            } else {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                secondText.setTypeface(secondText.getTypeface(), Typeface.BOLD_ITALIC);
                secondText.setText("I smoke on the mic like Smokin' Joe Frazier. The hell-raiser...");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.contains("stand") || myobj2.contains("get up")) { // STAND VERB 2
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (iscompoff==1 || isstand==1) {
                isstand=1;
                secondText.setText("You are standing now.");
                linearLayout.addView(secondText);
                obj();
            } else {
                isstand=1;
                secondText.setText("It feels like a kick in the stomach, but you do it.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.contains("sit down") || myobj2.matches("sit") || myobj2.contains("lie")) { // LIE VERB 2
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (isstand==1) {
                isstand=0;
                if (iscompoff==1) {
                    secondText.setText("Now you're on the floor again.");
                } else {
                    secondText.setText("You're on the floor again. The ceiling is spinning.");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("You are already on the floor.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.matches("examine")) { // EXAMINE VERB 2
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("examine")) {
            if (myobj2.contains("vinyl") || myobj2.contains("player") || myobj2.contains("cd") || myobj2.contains("disc")) {
                if (isstand==0) {
                    secondText.setText("You'll have to stand up first.");
                } else if (iscompoff==1) {
                    secondText.setText("It says 'ENTER THE WU-TANG'.");
                } else {
                    secondText.setText("It says 'ENTER THE WU-TANG'.\nThe dizziness is still increasing.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("blanket")) {
                if (isstand==0) {
                    secondText.setText("You'll have to stand up first.");
                } else {
                    secondText.setText("It is just a blanket.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("pencil")) {
                if (isstand==0) {
                    secondText.setText("You'll have to stand up first.");
                } else {
                    secondText.setText("It is just a pencil.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("furniture")) {
                if (isstand==0) {
                    secondText.setText("You'll have to stand up first.");
                } else {
                    secondText.setText("Nothing interesting in it.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("console") || myobj2.contains("computer")) {
                if (iscompoff==1) {
                    secondText.setText("You can recognize that it is your classic game console.");
                } else {
                    secondText.setText("You can recognize that it is your classic game console and... Ouch! the dizziness gets worse when you look at it.");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be examined or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        }
        // TAKE GET VERB 2
        else if (myobj2.matches("take") || myobj2.matches("get") || myobj2.matches("pick") || myobj2.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("take") || myobj2.contains("get") || myobj2.contains("pick") || myobj2.contains("grab")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if ((myobj2.contains("cd") || myobj2.contains("disc") && !(dropBasement.contains("cd")))) {
                if (isstand==0) {
                    secondText.setText("You'll have to stand up first.");
                } else if (iscompoff==1) {
                    String mycd = "cd";
                    if (myInventory.contains("cd")) {
                        secondText.setText("You already took it.");
                    } else {
                        myInventory.add(mycd);
                        secondText.setText("Taken.");
                    }
                } else {
                    String mycd = "cd";
                    if (myInventory.contains("cd")) {
                        secondText.setText("You already took it.");
                    } else {
                        myInventory.add(mycd);
                        secondText.setText("It is even difficult to move... But you manage to get it.");
                    }
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("blanket") && !(dropBasement.contains("blanket"))) {
                if (isstand==0) {
                    secondText.setText("You'll have to stand up first.");
                } else if (iscompoff==1) {
                    String myblanket = "blanket";
                    if (myInventory.contains("blanket")) {
                        secondText.setText("You already took it.");
                    } else {
                        myInventory.add(myblanket);
                        secondText.setText("Taken.");
                    }
                } else {
                    String myblanket = "blanket";
                    if (myInventory.contains("blanket")) {
                        secondText.setText("You already took it.");
                    } else {
                        myInventory.add(myblanket);
                        secondText.setText("The dizziness doesn't make it easy... But you manage to get it.");
                    }
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("pencil") && !(dropBasement.contains("pencil"))) {
                if (isstand==0) {
                    secondText.setText("You'll have to stand up first.");
                } else if (iscompoff==1) {
                    String mypencil = "pencil";
                    if (myInventory.contains("pencil")) {
                        secondText.setText("You already took it.");
                    } else {
                        myInventory.add(mypencil);
                        secondText.setText("Taken.");
                    }
                } else {
                    String mypencil = "pencil";
                    if (myInventory.contains("pencil")) {
                        secondText.setText("You already took it.");
                    } else {
                        myInventory.add(mypencil);
                        secondText.setText("Your headache increases... But you manage to get it.");
                    }
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj2.contains("console") || myobj2.contains("computer")) {
                secondText.setText("You cannot take that.");
                linearLayout.addView(secondText);
                obj();
            } else if (!(dropBasement.isEmpty())) {
                int n;
                if (myobj2.contains("take") || myobj2.contains("pick") || myobj2.contains("grab")) {
                    n = 4;
                } else if (myobj2.contains("get")) {
                    n = 3;
                } else {
                    n = 0;
                }
                String s = myobj2.substring(n);
                s = s.replace(" the ","");
                s = s.replace(" a ","");
                s = s.replace(" an ","");
                s = s.trim();
                if (dropBasement.contains(s)) {
                    dropBasement.remove(s);
                    myInventory.add(s);
                    if (iscompoff==1) {
                        secondText.setText("Taken.");
                    } else {
                        secondText.setText("Taken... Ufff... But the headache hasn't stopped.");
                    }
                } else {
                    if (iscompoff==1) {
                        secondText.setText("You can't see any " + s + " here!");
                    } else {
                        secondText.setText("You can't see any " + s + " here... and the headache is still increasing.");
                    }
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be taken or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.matches("drop") || myobj2.contains("get rid of")) { // DROP VERB 2
            secondText.setText("Just say: Drop (and the object you want to drop).");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("drop")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            int n = 4;
            String s = myobj2.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (myInventory.contains(s)) {
                myInventory.remove(s);
                dropBasement.add(s);
                secondText.setText("Dropped.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("You're not holding the " + s + ".");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.matches("help")) { // HELP VERB 2
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (iscompoff == 1) {
                secondText.setText("You may wanna GO UPSTAIRS now that you can.");
            } else {
                secondText.setText("Don't ask for help.\n\nType COMMANDS to get a list of some actions you can do.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("help")) {
            secondText.setText("Just say HELP.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("drink")) { // DRINK VERB 2
            secondText.setText("There is nothing you can drink here.");
            linearLayout.addView(secondText);
            obj();
        } else if ((myobj2.contains("look") && myobj2.contains("around")) || myobj2.matches("l") || myobj2.matches("look")) {
            String intro = "Basement";
            if (isstand==0) {
                intro = intro + ". You are sitting on the floor.";
            }
            intro = intro + "\nThere is a piece of furniture with a vinyl player and some discs on it. Your game console is next to you. There's a door leading UP.";
            if (!(myInventory.contains("blanket")) && !(dropBasement.contains("blanket"))) {
                intro = intro + "\nThere is a blanket here.";
            }
            if (!(myInventory.contains("pencil")) && !(dropBasement.contains("pencil"))) {
                intro = intro + "\nThere is a pencil here.";
            }
            if (!(dropBasement.isEmpty())) {
                int n = dropBasement.size();
                for (int i=0; i<n; i++) {
                    intro = intro + "\nThere's the " + dropBasement.get(i) + " here.";
                }
                secondText.setText(intro);
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText(intro);
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.matches("i") || myobj2.matches("inventory")) {
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
        } else if (myobj2.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.matches("sleep")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Amazing strategy.");
            linearLayout.addView(secondText);
            obj();
        } else if ((myobj2.contains("check") && myobj2.contains("out")) || myobj2.contains("find") || myobj2.contains("search")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("check") || myobj2.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("jump") || myobj2.contains("climb") || myobj2.contains("turn") || myobj2.contains("shut") || myobj2.contains("look") || myobj2.contains("see") || myobj2.contains("watch") || myobj2.contains("play") || myobj2.contains("run") || myobj2.contains("walk") || myobj2.contains("eat") || myobj2.contains("move") || myobj2.contains("put") || myobj2.contains("give") || myobj2.contains("offer") || myobj2.contains("read") || myobj2.contains("write") || myobj2.contains("enter") || myobj2.contains("follow") || myobj2.contains("fix") || myobj2.contains("repair") || myobj2.contains("attack") || myobj2.contains("hit") || myobj2.contains("break") || myobj2.contains("fight") || myobj2.contains("kick")) {
            if (isstand==0) {
                secondText.setText("You're still sitting, remember?");
                linearLayout.addView(secondText);
                obj();
            } else {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                secondText.setText("Look around you.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj2.contains("smell")) {
            secondText.setText("It smells just like a basement.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("listen")) {
            if (iscompoff==0) {
                secondText.setText("The silence has never felt better.");
            } else {
                secondText.setText("The game's music results to be annoying now.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("wait")) {
            secondText.setText("Time passes...");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.contains("what") && myobj2.contains("time") && myobj2.contains("is") || myobj2.matches("time") || myobj2.matches("the time")) {
            secondText.setText("You cannot tell that from the basement.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj2.matches("diagnostic") || myobj2.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            linearLayout.addView(secondText);
            obj();
        }

        return (String) secondText.getText();
    }



















    public void obj3() { //QUE LO PRUEBE PEÑA PARA VER QUE NO HAY NINGÚN CABO SUELTO
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        final CustomEditText obj3 = new CustomEditText(this);
        final TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);
        obj3.setCursorVisible(false);
        obj3.setTypeface(typeface);
        obj3.setText(">");
        linearLayout.addView(obj3);

        setInputListeners(obj3, linearLayout, typeface, secondText);

    }

    public String checkObj3Answer(String myobj3) {
        TextView myMoves = (TextView) findViewById(R.id.moves);
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);

        if (valueManager.fred.conversationStatus == 1) {  // CONVERSATION
            switch (myobj3) {
                case "1":
                    valueManager.fred.conversationStatus = 12;
                    secondText.setText("I'm F.R.E.D.\nFormed Rico Emotionally Disturbed, but you always call me Fred.\n\n\nDialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    valueManager.fred.conversationStatus = 12;
                    secondText.setText("You bet I know, " + valueManager.myself.name + ".\nHey it's me, F.R.E.D.\nFormed Rico Emotionally Disturbed, but you always call me Fred.\n\n\nDialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "3":
                    valueManager.fred.conversationStatus = 12;
                    secondText.setText("Of course you know me. I'm F.R.E.D.\nFormed Rico Emotionally Disturbed, but you always call me Fred.\n\n\nDialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("You just said: oasidjfdd.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'Who are you?'\n2. 'You know who are you talking with!?'\n3. 'I'm " + valueManager.myself.name + ". Do I know you?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (valueManager.fred.conversationStatus == 12) {
            switch (myobj3) {
                case "1":
                    valueManager.fred.conversationStatus = 13;
                    secondText.setText("I'll get to that in a minute. First of all, the weekly report:\nNow, 89% of the Bubbles have electricity. The 300 years universal peace anniversary is closer. And our brothers from B905 have made it into the Space Federation.\n\n\nDialogue Fred.\n\n1. 'Pleased to hear that.'\n2. 'I hate these B905 guys.'\n3. 'Anything else?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    valueManager.fred.conversationStatus = 13;
                    secondText.setText("Don't make me talk about those bastards.\nAnyways, let's take a look at the weekly report:\nNow, 89% of the Bubbles have electricity. The 300 years universal peace anniversary is closer. And our brothers from B905 have made it into the Space Federation.\n\n\nDialogue Fred.\n\n1. 'Pleased to hear that.'\n2. 'I hate these B905 guys.'\n3. 'Anything else?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("You just said: nyhfwaa.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'Well. What do you want, Fred?'\n2. 'What is a Rico?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (valueManager.fred.conversationStatus == 13) {
            switch (myobj3) {
                case "1":
                    valueManager.fred.conversationStatus = 14;
                    secondText.setText("You always so nice.\n\nOne more thing.\nI've been doing some research about the Space Travel Ban.\nI've checked every route made by the Space Federation and the routes allowed according to the law.\nThe numbers don't fit... There might be one Traveller alive.\n\n\nDialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    valueManager.fred.conversationStatus = 14;
                    secondText.setText("Although they kill their parents when they're twelve, we don't have to treat them differently.\n\nOne more thing.\nI've been doing some research about the Space Travel Ban.\nI've checked every route made by the Space Federation and the routes allowed according to the law.\nThe numbers don't fit... There might be one Traveller alive.\n\n\nDialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "3":
                    valueManager.fred.conversationStatus = 14;
                    secondText.setText("Yes.\nI've been doing some research about the Space Travel Ban.\nI've checked every route made by the Space Federation and the routes allowed according to the law.\nThe numbers don't fit... There might be one Traveller alive.\n\n\nDialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("You just said: ywegvwn.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'Pleased to hear that.'\n2. 'I hate these B905 guys.'\n3. 'Anything else?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (valueManager.fred.conversationStatus == 14) {
            switch (myobj3) {
                case "1":
                    valueManager.fred.conversationStatus = 15;
                    secondText.setText("I've said there MIGHT be one alive. Maybe. Just maybe...\nNow there's hope to get out of B903.\n\n\nDialogue Fred.\n\n1. 'I'm happy in the Bubble.'\n2. 'Assuming this Traveller exists, what now?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    valueManager.fred.conversationStatus = 15;
                    secondText.setText("Fred doesn't want that. Nobody in the universe would want that...\nNow there's hope to get out of B903.\n\n\nDialogue Fred.\n\n1. 'I'm happy in the Bubble.'\n2. 'Assuming this Traveller exists, what now?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("You just said: ajdhasj.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'No way. The Travellers are dead.'\n2. 'The Space Federation will take care of him.'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (valueManager.fred.conversationStatus == 15) {
            switch (myobj3) {
                case "1":
                    valueManager.fred.conversationStatus = 0;
                    valueManager.fred.scoreDuringFredWaiting = valueManager.score;
                    myInventory.add("library neck chip");
                    secondText.setText("'You won't be when the Space Federation comes to look for the Traveller.\nNow we can be part of something more important than this Bubble. Won't you ever wanted to see with your own eyes what is out of this place?\n\nMy research will lead us to that. Finding the Traveller will lead us to that.\n\nI just need one more ingredient, a book called The Hitchhiker's Guide to the Galaxy. They have one of those at the Library and we need to get it.'\n\nDialogue Fred finished.\n\nFred gives you a chip similar to the one he has on his neck which says LUDLOW PUBLIC LIBRARY.\nLIBRARY NECK CHIP has been added to your inventory.");
                    linearLayout.addView(secondText);
                    obj=7;
                    obj();
                    break;
                case "2":
                    valueManager.fred.conversationStatus = 0;
                    valueManager.fred.scoreDuringFredWaiting = valueManager.score;
                    myInventory.add("library neck chip");
                    secondText.setText("'Now we can be part of something more important than this Bubble. Won't you ever wanted to see with your own eyes what is out of this place?\n\nMy research will lead us to that. Finding the Traveller will lead us to that.\n\nI just need one more ingredient, a book called The Hitchhiker's Guide to the Galaxy. They have one of those at the Library and we need to get it.'\n\nDialogue Fred finished.\n\nFred gives you a chip similar to the one he has on his neck which says LUDLOW PUBLIC LIBRARY.\nLIBRARY NECK CHIP has been added to your inventory.");
                    linearLayout.addView(secondText);
                    obj=7;
                    obj();
                    break;
                default:
                    secondText.setText("You just said: nuwnuds.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Fred.\n\n1. 'I'm happy in the Bubble.'\n2. 'Assuming this Traveller exists, what now?'");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        }
        // DIRECTIONS GO VERB 3
        else if (myobj3.matches("south") || myobj3.matches("s") || myobj3.matches("go south") || myobj3.matches("go s") || myobj3.matches("go backwards") || myobj3.matches("east") || myobj3.matches("e") || myobj3.matches("go east") || myobj3.matches("go e") || myobj3.matches("go right") || myobj3.matches("west") || myobj3.matches("w") || myobj3.matches("go west") || myobj3.matches("go w") || myobj3.matches("go left") || myobj3.matches("northeast") || myobj3.matches("ne") || myobj3.matches("go northeast") || myobj3.matches("go ne") || myobj3.matches("northwest") || myobj3.matches("nw") || myobj3.matches("go northwest") || myobj3.matches("go nw") || myobj3.matches("southeast") || myobj3.matches("se") || myobj3.matches("go southeast") || myobj3.matches("go se") || myobj3.matches("southwest") || myobj3.matches("sw") || myobj3.matches("go southwest") || myobj3.matches("go sw") || myobj3.matches("down") || myobj3.matches("d") || myobj3.matches("go down") || myobj3.matches("downstairs") || myobj3.matches("go downstairs") || myobj3.matches("go d") || myobj3.matches("up") || myobj3.matches("u") || myobj3.matches("go up") || myobj3.matches("upstairs") || myobj3.matches("go upstairs") || myobj3.matches("go u")) {
            secondText.setText("You can't go that way.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.matches("exit") || myobj3.matches("leave") || myobj3.matches("north") || myobj3.matches("n") || myobj3.matches("go north") || myobj3.matches("go n") || myobj3.matches("go straight on") || ((myobj3.contains("go") || myobj3.contains("get")) && myobj3.contains("out")) || (myobj3.contains("exit") && myobj3.contains("room"))) {
            secondText.setText("You'll have to open the door first.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("speak") || myobj3.contains("talk") || myobj3.contains("ask")) {  // SPEAK VERB 3
            secondText.setText("There is no one here you can speak with.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("stop")) { // STOP VERB 3
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj3.contains("play") || myobj3.contains("game")) {
                secondText.setText("You already stopped playing.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj3.contains("noise") || myobj3.contains("sound") || myobj3.contains("knock")) {
                secondText.setText("Opening the door is the way to stop someone knocking on your house door. Duh.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("There's nothing you can stop here.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj3.matches("close")) {  // CLOSE VERB 3
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("close")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj3.contains("door")) {
                secondText.setText("The door is already closed.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj3.contains("eye")) {
                secondText.setText("This is not really useful right now.\nThe knocks keep going.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj3.contains("window")) {
                secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj3.matches("open")) {  // OPEN VERB 3
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("open")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj3.contains("eye")) {
                secondText.setText("You already see.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj3.contains("basement door")) {
                secondText.setText("The basement door is locked.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj3.contains("door")) {
                housedooropen=1;
                valueManager.fred.conversationStatus = 1;
                myLocation.setText("Hall, open door");
                secondText.setText("'10 o'clock at your door. 10 o'clock at your door. Is it that difficult?'\n\n\nDialogue Fred.\n\n1. 'Who are you?'\n2. 'You know who are you talking with!?'\n3. 'I'm " + valueManager.myself.name + ". Do I know you?'");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj3.contains("window")) {
                secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if ((myobj3.contains("turn") && myobj3.contains("off")) || (myobj3.contains("shut") && myobj3.contains("down"))) {  // TURN OFF VERB 3
            secondText.setText("There is nothing you can turn off here.\nSomeone is still knocking on your house door.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("turn on") || myobj3.contains("turn it on")) {  // TURN ON VERB 3
            secondText.setText("There's nothing you can turn on here.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("stand") || myobj3.contains("get up")) { // STAND VERB 3
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You are already standing.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("sit down") || myobj3.matches("sit") || myobj3.contains("lie")) { // LIE VERB 3
            secondText.setText("Someone is knocking on your house door. Take care of that.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.matches("examine")) { // EXAMINE VERB 3
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("examine")) {
            if (myobj3.contains("glass") || myobj3.contains("water")) {
                if (waterdrunkobj3 == 1) {
                    secondText.setText("You drank that.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText("It is just a glass of water.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else if (myobj3.contains("window")) {
                secondText.setText("Just look at it.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj3.contains("basement door")) {
                secondText.setText("It is closed.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj3.contains("door")) {
                secondText.setText("Someone is knocking at it.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be examined or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        }
        // TAKE GET VERB 3
        else if (myobj3.matches("take") || myobj3.matches("get") || myobj3.matches("pick") || myobj3.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("take") || myobj3.contains("get") || myobj3.contains("pick") || myobj3.contains("grab")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj3.contains("glass") || myobj3.contains("water")) {
                if (waterdrunkobj3==1) {
                    secondText.setText("You already drank that.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    String mywater = "water";
                    if (myInventory.contains(mywater)) {
                        secondText.setText("You already took it.");
                    } else {
                        if (dropHall.contains(mywater)) {
                            dropHall.remove(mywater);
                        }
                        myInventory.add(mywater);
                        secondText.setText("Taken.\nYou still hear the knocks on the door.");
                    }
                    linearLayout.addView(secondText);
                    obj();
                }
            } else if (!(dropHall.isEmpty())) {
                int n;
                if (myobj3.contains("take") || myobj3.contains("pick") || myobj3.contains("grab")) {
                    n = 4;
                } else if (myobj3.contains("get")) {
                    n = 3;
                } else {
                    n = 0;
                }
                String s = myobj3.substring(n);
                s = s.replace(" the ","");
                s = s.replace(" a ","");
                s = s.replace(" an ","");
                s = s.trim();
                if (dropHall.contains(s)) {
                    dropHall.remove(s);
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
        } else if (myobj3.matches("drop") || myobj3.contains("get rid of")) { // DROP VERB 3
            secondText.setText("Just say: Drop (and the object you want to drop).");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("drop")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            int n = 4;
            String s = myobj3.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (myInventory.contains(s)) {
                myInventory.remove(s);
                dropHall.add(s);
                secondText.setText("Dropped.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("You're not holding the " + s + ".");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj3.matches("help")) { // HELP VERB 3
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("I'm sure you're smart enough to know the procedure when someone is knocking at your door.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("help")) {
            secondText.setText("Just say HELP.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.matches("drink")) { // DRINK VERB 3
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("drink")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj3.contains("glass") || myobj3.contains("water")) {
                if (waterdrunkobj3==1) {
                    secondText.setText("You already drank that.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myInventory.contains("water")) {
                    waterdrunkobj3 = 1;
                    secondText.setText("It feels refreshing.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText("You will have to take it first.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else {
                secondText.setText("This thing cannot be drunk or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj3.contains("window")) {  // WINDOW ACTIONS, por si acaso
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
            linearLayout.addView(secondText);
            obj();
        } else if ((myobj3.contains("look") && myobj3.contains("around")) || myobj3.matches("l") || myobj3.matches("look")) {
            String intro = "";
            if (waterdrunkobj3==1 || myInventory.contains("water") || dropHall.contains("water")) {
                intro = "Your house hall\nHere you see a window.\nSomeone is knocking on your house door.";
            } else {
                intro = "Your house hall\nHere you see a window and a glass of water.\nSomeone is knocking on your house door.";
            }
            if (!(dropHall.isEmpty())) {
                int n = dropHall.size();
                for (int i=0; i<n; i++) {
                    intro = intro + "\nThere's the " + dropHall.get(i) + " here.";
                }
                secondText.setText(intro);
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText(intro);
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj3.matches("i") || myobj3.matches("inventory")) {
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
        } else if (myobj3.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.matches("sleep")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Amazing strategy.");
            linearLayout.addView(secondText);
            obj();
        } else if ((myobj3.contains("check") && myobj3.contains("out")) || myobj3.contains("find") || myobj3.contains("search")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("check") || myobj3.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("smell")) {
            secondText.setText("It smells like carrot salad from the other side of the door.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("listen")) {
            secondText.setText("Whoever is knocking on your door is doing it harder.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("wait")) {
            secondText.setText("Time passes...");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("what") && myobj3.contains("time") && myobj3.contains("is") || myobj3.matches("time") || myobj3.matches("the time")) {
            secondText.setText("It is sunrise.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.matches("diagnostic") || myobj3.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj3.contains("jump") || myobj3.contains("climb") || myobj3.contains("turn") || myobj3.contains("shut") || myobj3.contains("look") || myobj3.contains("see") || myobj3.contains("watch") || myobj3.contains("play") || myobj3.contains("run") || myobj3.contains("walk") || myobj3.contains("eat") || myobj3.contains("move") || myobj3.contains("put") || myobj3.contains("give") || myobj3.contains("offer") || myobj3.contains("read") || myobj3.contains("write") || myobj3.contains("enter")|| myobj3.contains("follow") || myobj3.contains("fix") || myobj3.contains("repair") || myobj3.contains("attack") || myobj3.contains("hit") || myobj3.contains("break") || myobj3.contains("fight") || myobj3.contains("kick")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Look around you.");
            linearLayout.addView(secondText);
            obj();
        }

        return (String) secondText.getText();
    }


















    public void obj7() { //QUE LO PRUEBE PEÑA PARA VER QUE NO HAY NINGÚN CABO SUELTO
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        final CustomEditText obj7 = new CustomEditText(this);
        final TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);
        obj7.setCursorVisible(false);
        obj7.setTypeface(typeface);
        obj7.setText(">");
        linearLayout.addView(obj7);

        setInputListeners(obj7, linearLayout, typeface, secondText);

    }

    public String checkObj7Answer(String myobj7) {
        TextView myMoves = (TextView) findViewById(R.id.moves);
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);



        if (valueManager.fred.isPresent && (valueManager.score - valueManager.fred.scoreDuringFredWaiting >= 5)) {  // CHECKEO SCOREFRED
            valueManager.fred.isPresent = false;
            if (waterdrunkobj3==0 && !(myInventory.contains("water")) && !(dropHall.contains("water"))) {
                waterdrunkobj3 = 1;
                secondText.setText("After waiting a while, Fred drinks the glass of water and goes North in order to arrive to the library.");
            } else {
                secondText.setText("After waiting a while, Fred goes North in order to arrive to the library.");
            }
            housedooropen = 1;
            myLocation.setText("Hall, open door");
            linearLayout.addView(secondText);
            obj();
        } else if (valueManager.fred.isDead && (valueManager.score - valueManager.fred.scoreDuringFredKilling >= 3)) {
            myLocation.setText("Dead");
            String dieOptions = "\n\nType RESTART, RESTORE, COMMANDS or QUIT.";
            secondText.setText("Killing Fred makes you fall in a terrible depression. You live the last days of your life isolated at home. You died." + dieOptions);
            linearLayout.addView(secondText);
            obj=0;
            obj();
        }
        // CHECKEO SI ESTOY DE PIE, por si acaso
        else if (isstand==0 && !(myobj7.contains("stand") || myobj7.contains("get up") || myobj7.contains("sit down") || myobj7.matches("sit") || myobj7.contains("lie") || myobj7.contains("stop") || myobj7.contains("examine") || (myobj7.contains("look") && myobj7.contains("around")) || myobj7.matches("l") || myobj7.matches("look") || myobj7.matches("i") || myobj7.matches("inventory") || myobj7.matches("sleep"))) {
            secondText.setText("Better stand up first.");
            linearLayout.addView(secondText);
            obj();
        }
        // DIRECTIONS GO VERB 7
        else if (myobj7.matches("northeast") || myobj7.matches("ne") || myobj7.matches("go northeast") || myobj7.matches("go ne") || myobj7.matches("northwest") || myobj7.matches("nw") || myobj7.matches("go northwest") || myobj7.matches("go nw") || myobj7.matches("southeast") || myobj7.matches("se") || myobj7.matches("go southeast") || myobj7.matches("go se") || myobj7.matches("southwest") || myobj7.matches("sw") || myobj7.matches("go southwest") || myobj7.matches("go sw") || myobj7.matches("down") || myobj7.matches("d") || myobj7.matches("go down") || myobj7.matches("downstairs") || myobj7.matches("go downstairs") || myobj7.matches("go d") || myobj7.matches("up") || myobj7.matches("u") || myobj7.matches("go up") || myobj7.matches("upstairs") || myobj7.matches("go upstairs") || myobj7.matches("go u")) {
            secondText.setText("You can't go that way.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.matches("north") || myobj7.matches("n") || myobj7.matches("go north") || myobj7.matches("go n") || myobj7.matches("go straight on")) {
            if (houseobj7==0) {
                secondText.setText("You can't go that way.");
            } else if (housedooropen==0) {
                secondText.setText("Open the door to get out.");
            } else {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                streetobj7=1;
                houseobj7=0;
                valueManager.fred.isPresent = true;
                myLocation.setText("Street");
                if (firsttimestreets==1) {
                    String intro = "";
                    if (!valueManager.fred.isDead) {
                        intro = intro + "'Another day in the rathole!' Fred says.\n\nNaturally, B903 is not the best Bubble to live in.\n";
                    }
                    intro = intro + "Wherever you look, you can see the tons of scrap piled up like it was a landfill. Also, no one reminds seeing something non-human alive in here.\nIt is like a desert made planet.\n\nStreet\nYou can see that Ludlow Library is West and your house is South.";
                    secondText.setText(intro);
                    firsttimestreets=0;
                } else {
                    secondText.setText("Street\nYou can see that Ludlow Library is West and your house is South.");
                }
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.matches("south") || myobj7.matches("s") || myobj7.matches("go south") || myobj7.matches("go s") || myobj7.matches("go backwards")) {
            if (streetobj7==0) {
                secondText.setText("You can't go that way.");
            } else {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                houseobj7=1;
                streetobj7=0;
                valueManager.fred.isPresent = false;
                if (housedooropen==0) {
                    myLocation.setText("Hall");
                } else {
                    myLocation.setText("Hall, open door");
                }
                secondText.setText("Your house hall\nHere you see a window, your house door and your basement door.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.matches("west") || myobj7.matches("w") || myobj7.matches("go west") || myobj7.matches("go w") || myobj7.matches("go left")) {
            if (streetobj7==0) {
                secondText.setText("You can't go that way.");
            } else {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                libraryobj7=1;
                streetobj7=0;
                valueManager.fred.isPresent = true;
                myLocation.setText("Library, door");
                String intro = "Ludlow Public Library door\n\nIt is a two-storey wood building. One of these days it is going to fall.\n";
                if (!valueManager.fred.isDead) {
                    intro = intro + "Fred stopped walking and now he is looking at you.";
                    secondText.setText(intro);
                } else {
                    if (neckchip == 0) {
                        intro = intro + "Something needs to be done before you enter.";
                    } else {
                        intro = intro + "You are allowed to enter.";
                    }
                    secondText.setText(intro);
                }
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.matches("east") || myobj7.matches("e") || myobj7.matches("go east") || myobj7.matches("go e") || myobj7.matches("go right")) {
            if (libraryobj7==0) {
                secondText.setText("You can't go that way.");
            } else {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                streetobj7=1;
                libraryobj7=0;
                valueManager.fred.isPresent = false;
                myLocation.setText("Street");
                secondText.setText("Street\nYou can see that Ludlow Library is West and your house is South.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.matches("exit") || myobj7.matches("leave") || ((myobj7.contains("go") || myobj7.contains("get")) && myobj7.contains("out")) || (myobj7.contains("exit") && myobj7.contains("room"))) {
            if (houseobj7==0) {
                secondText.setText("You are already on the outside.");
            } else if (housedooropen==0) {
                secondText.setText("Open the door to get out.");
            } else {
                valueManager.score++;
                myMoves.setText("Moves: " + valueManager.score);
                streetobj7 = 1;
                houseobj7 = 0;
                valueManager.fred.isPresent = true;
                myLocation.setText("Street");
                if (firsttimestreets == 1) {
                    String intro = "";
                    if (!valueManager.fred.isDead) {
                        intro = intro + "'Another day in the rathole!' Fred says.\n\nNaturally, B903 is not the best Bubble to live in.\n";
                    }
                    intro = intro + "Wherever you look, you can see the tons of scrap piled up like it was a landfill. Also, no one reminds seeing something non-human alive in here.\nIt is like a desert made planet.\n\nStreet\nYou can see that Ludlow Library is West and your house is South.";
                    secondText.setText(intro);
                    firsttimestreets=0;
                } else {
                    secondText.setText("Street\nYou can see that Ludlow Library is West and your house is South.");
                }
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            linearLayout.addView(secondText);
            obj();
        }
        // SPEAK VERB 7
        else if (myobj7.matches("speak") || myobj7.matches("talk") || myobj7.matches("ask")) {
            secondText.setText("This verb needs to be used with a name.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("speak") || myobj7.contains("talk") || myobj7.contains("ask")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("fred")) {
                if (valueManager.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!valueManager.fred.isPresent) {
                    secondText.setText("Fred is not here.");
                } else {
                    if (speakcountobj7==0) {
                        secondText.setText(valueManager.myself.name + ", we should be inside the library by now.");
                    } else if ((speakcountobj7>=1 && speakcountobj7<3) || speakcountobj7>3) {
                        secondText.setText("There's nothing much I can tell you.");
                    } else {
                        secondText.setText("Look.\nI'm a video game AI, so don't expect me to have deep conversations.");
                    }
                    speakcountobj7++;
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This person's name is incorrect or he/she is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj7.contains("stop")) { // STOP VERB 7
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("play") || myobj7.contains("game")) {
                secondText.setText("You moved on from that.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("noise") || myobj7.contains("sound")) {
                secondText.setText("There's no noise.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("There's nothing you can stop here.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj7.matches("close")) {  // CLOSE VERB 7
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("close")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("eye")) {
                secondText.setText("Not really useful.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("door")) {
                if (houseobj7==1) {
                    if (housedooropen==0) {
                        secondText.setText("It is already closed.");
                    } else {
                        housedooropen=0;
                        myLocation.setText("Hall");
                        secondText.setText("Closed.");
                    }
                } else if (streetobj7==1) {
                    secondText.setText("You can't see any door here!");
                } else {
                    secondText.setText("It is already closed.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("window")) {
                if (houseobj7==1) {
                    secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                } else {
                    secondText.setText("You can't see any window here!");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj7.matches("open")) {  // OPEN VERB 7
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("open")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("eye")) {
                secondText.setText("You already see.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("basement door")) {
                if (houseobj7==1) {
                    secondText.setText("The basement door is locked.");
                } else {
                    secondText.setText("You have to be in your house.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("door")) {
                if (houseobj7==1) {
                    housedooropen = 1;
                    myLocation.setText("Hall, open door");
                    secondText.setText("The house door is open.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (streetobj7==1) {
                    secondText.setText("You can't see any door here!");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    if (neckchip==0) {
                        secondText.setText("The door is locked for you. It seems you need to do something before...");
                        linearLayout.addView(secondText);
                        obj();
                    } else {
                        if (valueManager.fred.isDead) {
                            valueManager.fred.isDead = false;
                            secondText.setText("Due to game incoherences Fred has to return from the dead in this level.");
                            linearLayout.addView(secondText);
                            valueManager.fred.handlerFred = new Handler();
                            valueManager.fred.runnableFred = new Runnable() {
                                @Override
                                public void run() {
                                    myLocation.setText("Ludlow Library");
                                    secondText.setText("You both enter the library.\n\n'I need to talk with someone here. I'll be back in a minute. Find the book, " + valueManager.myself.name + ".'\nFred enters the office door of the library and closes it behind him. Look around.\n\nNote: Whenever you want to speak with someone say SPEAK (and the name of the person).");
                                    linearLayout.addView(secondText);
                                    obj=8;
                                    obj();
                                }
                            };
                            valueManager.fred.handlerFred.postDelayed(valueManager.fred.runnableFred, 5750);
                        } else {
                            myLocation.setText("Ludlow Library");
                            secondText.setText("You both enter the library.\n\n'I need to talk with someone here. I'll be back in a minute. Find the book, " + valueManager.myself.name + ".'\nFred enters the office door of the library and closes it behind him. Look around.\n\nNote: Whenever you want to speak with someone say SPEAK (and the name of the person).");
                            linearLayout.addView(secondText);
                            obj=8;
                            obj();
                        }
                    }
                }
            } else if (myobj7.contains("window")) {
                if (houseobj7==1) {
                    secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                } else {
                    secondText.setText("You can't see any window here!");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        }
        else if (myobj7.matches("enter")) {  // ENTER VERB 7
            secondText.setText("This verb needs to be used with a place to get in.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("enter")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("house")) {
                if (houseobj7==1) {
                    secondText.setText("You are already inside.");
                } else {
                    if (streetobj7==1) streetobj7=0;
                    if (libraryobj7==1) libraryobj7=0;
                    valueManager.fred.isPresent = false;
                    if (housedooropen==0) {
                        myLocation.setText("Hall");
                    } else {
                        myLocation.setText("Hall, open door");
                    }
                    secondText.setText("After walking a while, you get to the house... again.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("library")) {
                if (libraryobj7==1) {
                    if (neckchip == 0) {
                        secondText.setText("The door is locked for you. It seems you need to do something before...");
                        linearLayout.addView(secondText);
                        obj();
                    } else {
                        if (valueManager.fred.isDead) {
                            valueManager.fred.isDead = false;
                            secondText.setText("Due to game incoherences Fred has to return from the dead in this level.");
                            linearLayout.addView(secondText);
                            valueManager.fred.handlerFred = new Handler();
                            valueManager.fred.runnableFred = new Runnable() {
                                @Override
                                public void run() {
                                    myLocation.setText("Ludlow Library");
                                    secondText.setText("You both enter the library.\n\n'I need to talk with someone here. I'll be back in a minute. Find the book, " + valueManager.myself.name + ".'\nFred enters the office door of the library and closes it behind him. Look around.\n\nNote: Whenever you want to speak with someone say SPEAK (and the name of the person).");
                                    linearLayout.addView(secondText);
                                    obj=8;
                                    obj();
                                }
                            };
                            valueManager.fred.handlerFred.postDelayed(valueManager.fred.runnableFred, 5750);
                        } else {
                            myLocation.setText("Ludlow Library");
                            secondText.setText("You both enter the library.\n\n'I need to talk with someone here. I'll be back in a minute. Find the book, " + valueManager.myself.name + ".'\nFred enters the office door of the library and closes it behind him. Look around.\n\nNote: Whenever you want to speak with someone say SPEAK (and the name of the person).");
                            linearLayout.addView(secondText);
                            obj=8;
                            obj();
                        }
                    }
                } else {
                    secondText.setText("You have to go to the library first.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else if (myobj7.contains("matrix")) {
                secondText.setText("Morpheus should be proud.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("You cannot get in here or the ubication's name is incorrect.");
                linearLayout.addView(secondText);
                obj();
            }
        }
        // TURN OFF VERB 7
        else if ((myobj7.contains("turn") && myobj7.contains("off")) || (myobj7.contains("shut") && myobj7.contains("down"))) {
            secondText.setText("There is nothing you can turn off here.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("turn on") || myobj7.contains("turn it on")) {  // TURN ON VERB 7
            secondText.setText("There's nothing you can turn on here.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("stand") || myobj7.contains("get up")) { // STAND VERB 7
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            isstand=1;
            secondText.setText("You are standing.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("sit down") || myobj7.matches("sit") || myobj7.contains("lie")) { // LIE VERB 7
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            isstand=0;
            secondText.setText("You are on the floor.");
            linearLayout.addView(secondText);
            obj();
        }
        // EXAMINE VERB 7
        else if (myobj7.matches("examine")) {
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("examine")) {
            if (myobj7.contains("glass") || myobj7.contains("water")) {
                if ((houseobj7==1 && (!dropStreet.contains("water") || !dropLibraryDoor.contains("water"))) || (streetobj7==1 && dropStreet.contains("water")) || (libraryobj7==1 && dropLibraryDoor.contains("water")) || myInventory.contains("water")) {
                    if (waterdrunkobj3 == 1) {
                        secondText.setText("Someone drank that.");
                    } else {
                        secondText.setText("It is just a glass of water.");
                    }
                } else {
                    secondText.setText("You can't see any water here!");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("window")) {
                if (houseobj7==1) {
                    secondText.setText("It is just a window.");
                } else {
                    secondText.setText("You can't see any window here!");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("door")) {
                if (streetobj7==1) {
                    secondText.setText("You can't see any door here!");
                } else {
                    secondText.setText("It is just a door.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("fred")) {
                if (valueManager.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!valueManager.fred.isPresent) {
                    secondText.setText("Fred is not here.");
                } else {
                    if (valueManager.fred.examineFredDuringObj7Count == 0) {
                        secondText.setText("Don't look at me like that.");
                        valueManager.fred.examineFredDuringObj7Count++;
                    } else {
                        secondText.setText("Stop it.");
                    }
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be examined or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        }
        // TAKE GET VERB 7
        else if (myobj7.matches("take") || myobj7.matches("get") || myobj7.matches("pick") || myobj7.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("take") || myobj7.contains("get") || myobj7.contains("pick") || myobj7.contains("grab")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("glass") || myobj7.contains("water")) {
                if (waterdrunkobj3==1) {
                    secondText.setText("Someone already drank that.");
                } else if (myInventory.contains("water")) {
                    secondText.setText("You already took it.");
                } else if (houseobj7==1 && !(dropStreet.contains("water")) && !(dropLibraryDoor.contains("water"))) {
                    if (dropHall.contains("water")) {
                        dropHall.remove("water");
                    }
                    myInventory.add("water");
                    secondText.setText("Taken.");
                } else if (streetobj7==1 && dropStreet.contains("water")) {
                    dropStreet.remove("water");
                    myInventory.add("water");
                    secondText.setText("Taken.");
                } else if (libraryobj7==1 && dropLibraryDoor.contains("water")) {
                    dropLibraryDoor.remove("water");
                    myInventory.add("water");
                    secondText.setText("Taken.");
                } else {
                    secondText.setText("You can't see any water here!");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("fred")) {
                if (valueManager.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!valueManager.fred.isPresent) {
                    secondText.setText("Fred is not here.");
                } else {
                    secondText.setText("You cannot take Fred.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (!(dropHall.isEmpty()) || !(dropStreet.isEmpty()) || !(dropLibraryDoor.isEmpty())) {
                int n;
                if (myobj7.contains("take") || myobj7.contains("pick") || myobj7.contains("grab")) {
                    n = 4;
                } else if (myobj7.contains("get")) {
                    n = 3;
                } else {
                    n = 0;
                }
                String s = myobj7.substring(n);
                s = s.replace(" the ","");
                s = s.replace(" a ","");
                s = s.replace(" an ","");
                s = s.trim();
                if (houseobj7==1) {
                    if (dropHall.contains(s)) {
                        dropHall.remove(s);
                        myInventory.add(s);
                        secondText.setText("Taken.");
                    } else {
                        secondText.setText("You can't see any " + s + " here!");
                    }
                } else if (streetobj7==1) {
                    if (dropStreet.contains(s)) {
                        dropStreet.remove(s);
                        myInventory.add(s);
                        secondText.setText("Taken.");
                    } else {
                        secondText.setText("You can't see any " + s + " here!");
                    }
                } else {
                    if (dropLibraryDoor.contains(s)) {
                        dropLibraryDoor.remove(s);
                        myInventory.add(s);
                        secondText.setText("Taken.");
                    } else {
                        secondText.setText("You can't see any " + s + " here!");
                    }
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be taken or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        }
        // DROP VERB 7
        else if (myobj7.matches("drop") || myobj7.contains("get rid of")) {
            secondText.setText("Just say: Drop (and the object you want to drop).");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("drop")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            int n = 4;
            String s = myobj7.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (myInventory.contains(s)) {
                myInventory.remove(s);
                if (houseobj7==1) {
                    dropHall.add(s);
                } else if (streetobj7==1) {
                    dropStreet.add(s);
                } else {
                    dropLibraryDoor.add(s);
                }
                secondText.setText("Dropped.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("You're not holding the " + s + ".");
                linearLayout.addView(secondText);
                obj();
            }
        }
        // PUT VERB 7
        else if (myobj7.matches("put") || myobj7.matches("place") || myobj7.matches("stick") || myobj7.matches("set")) {
            secondText.setText("This verb needs to be used with an object and a the site where you wanna place it.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("put") || myobj7.contains("place") || myobj7.contains("stick") || myobj7.contains("set")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("chip") && myobj7.contains("neck")) {
                if (!(myInventory.contains("library neck chip"))) {
                    secondText.setText("You're not holding the Library neck chip.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    myInventory.remove("library neck chip");
                    neckchip = 1;
                    secondText.setText("Done.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else {
                secondText.setText("The name of the object is wrong, the name of the place to put it is wrong or this object cannot be used this way.");
                linearLayout.addView(secondText);
                obj();
            }
        }
        // HELP VERB 7
        else if (myobj7.matches("help")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("fred")) {
                if (valueManager.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!valueManager.fred.isPresent) {
                    secondText.setText("Fred is not here.");
                } else {
                    secondText.setText("I'm OK, " + valueManager.myself.name + ".");
                }
            } else {
                secondText.setText("Don't ask for help. That just worked on the start level.\n\nType COMMANDS to get a list of some actions you can do.");
            }
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("help")) {
            secondText.setText("Just say HELP.");
            linearLayout.addView(secondText);
            obj();
        }
        // FOLLOW VERB 7
        else if (myobj7.matches("follow") || myobj7.matches("chase") || myobj7.matches("find") || myobj7.matches("search")) {
            secondText.setText("This verb needs to be used with a name.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("follow") || myobj7.contains("chase") || myobj7.contains("find") || myobj7.contains("search")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("fred")) {
                if (valueManager.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (housedooropen==0 && !valueManager.fred.isPresent) {
                    secondText.setText("Open the door to get out.");
                } else if (!valueManager.fred.isPresent && houseobj7==1) {
                    streetobj7=1;
                    houseobj7=0;
                    valueManager.fred.isPresent = true;
                    myLocation.setText("Street");
                    if (firsttimestreets==1) {
                        secondText.setText("'Another day in the rathole!' Fred says.\n\nNaturally, B903 is not the best Bubble to live in.\nWherever you look, you can see the tons of scrap piled up like it was a landfill. Also, no one reminds seeing something non-human alive in here.\nIt is like a desert made planet.\n\nStreet\nYou can see that Ludlow Library is West and your house is South.");
                        firsttimestreets=0;
                    } else {
                        secondText.setText("Street\nYou can see that Ludlow Library is West and your house is South.");
                    }
                } else if (!valueManager.fred.isPresent && streetobj7==1) {
                    libraryobj7=1;
                    streetobj7=0;
                    valueManager.fred.isPresent = true;
                    myLocation.setText("Library, door");
                    String intro = "Ludlow Public Library door\n\n";
                    if (neckchip == 0) {
                        intro = intro + "Fred is pointing at the neckchip that you have in your inventory.";
                        secondText.setText(intro);
                    } else {
                        intro = intro + "You are allowed to enter.";
                        secondText.setText(intro);
                    }

                } else {
                    secondText.setText("Fred is right here.");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This person's name is incorrect or he/she cannot be followed.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj7.matches("drink")) { // DRINK VERB 7
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("drink")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("glass") || myobj7.contains("water")) {
                if (waterdrunkobj3==1) {
                    secondText.setText("Someone already drank that.");
                } else if (myInventory.contains("water")) {
                    waterdrunkobj3 = 1;
                    secondText.setText("It feels refreshing.");
                } else if (houseobj7==1 && !(dropStreet.contains("water")) && !(dropLibraryDoor.contains("water"))) {
                    secondText.setText("You will have to take it first.");
                } else if (streetobj7==1 && dropStreet.contains("water")) {
                    secondText.setText("You will have to take it first.");
                } else if (libraryobj7==1 && dropLibraryDoor.contains("water")) {
                    secondText.setText("You will have to take it first.");
                } else {
                    secondText.setText("You can't see any water here!");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be drunk or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        }
        // BREAK VERB 7
        else if (myobj7.matches("break") || myobj7.matches("hit") || myobj7.matches("attack") || myobj7.matches("punch") || myobj7.matches("fight") || myobj7.matches("kick")) {
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("break") || myobj7.contains("hit") || myobj7.contains("attack") || myobj7.contains("punch") || myobj7.contains("fight") || myobj7.contains("kick")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj7.contains("person") || myobj7.contains("fred")) {
                if (valueManager.fred.isDead) {
                    secondText.setText("Fred is dead.");
                } else if (!valueManager.fred.isPresent) {
                    secondText.setText("Fred is not here.");
                } else {
                    valueManager.fred.isDead = true;
                    valueManager.fred.scoreDuringFredKilling = valueManager.score;
                    secondText.setText("You killed Fred.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj7.contains("window")) {
                if (houseobj7==1) {
                    if (valueManager.fred.isDead || !valueManager.fred.isPresent) {
                        secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
                    } else {
                        secondText.setText("Before you do it, Fred points his watch and then he makes a signal indicating to the North.");
                    }
                } else {
                    secondText.setText("You can't see any window here!");
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be hitted or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj7.contains("window")) { // WINDOW ACTIONS, por si acaso
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (houseobj7==1) {
                secondText.setText("As you get close to the window you can see the sand and the pieces of rusty metal as a natural part of the landscape.\nEven the idea of getting out disgusts you.");
            } else {
                secondText.setText("You can't see any window here!");
            }
            linearLayout.addView(secondText);
            obj();
        } else if ((myobj7.contains("look") && myobj7.contains("around")) || myobj7.matches("l") || myobj7.matches("look")) {
            if (houseobj7==1) {
                String intro = "";
                if (waterdrunkobj3 == 1 || myInventory.contains("water") || dropHall.contains("water") || dropStreet.contains("water") || dropLibraryDoor.contains("water")) {
                    intro = "Your house hall\nHere you see a window, your house door and your basement door.";
                } else {
                    intro = "Your house hall\nHere you see a window, your house door, your basement door and a glass of water.";
                }
                if (valueManager.fred.isPresent) {
                    intro = intro + "\nFred is here.";
                }
                if (!(dropHall.isEmpty())) {
                    int n = dropHall.size();
                    for (int i = 0; i < n; i++) {
                        intro = intro + "\nThere's the " + dropHall.get(i) + " here.";
                    }
                    secondText.setText(intro);
                } else {
                    secondText.setText(intro);
                }
                linearLayout.addView(secondText);
                obj();
            } else if (streetobj7==1) {
                String intro = "Street\nYou can see that Ludlow Library is West and your house is South.";
                if (valueManager.fred.isPresent) {
                    intro = intro + "\nFred is here.";
                }
                if (!(dropStreet.isEmpty())) {
                    int n = dropStreet.size();
                    for (int i = 0; i < n; i++) {
                        intro = intro + "\nThere's the " + dropStreet.get(i) + " here.";
                    }
                    secondText.setText(intro);
                } else {
                    secondText.setText(intro);
                }
                linearLayout.addView(secondText);
                obj();
            } else {
                String intro = "You find yourself in front of the library door.\nFred is here.";
                if (!(dropLibraryDoor.isEmpty())) {
                    int n = dropLibraryDoor.size();
                    for (int i = 0; i < n; i++) {
                        intro = intro + "\nThere's the " + dropLibraryDoor.get(i) + " here.";
                    }
                    secondText.setText(intro);
                } else {
                    secondText.setText(intro);
                }
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj7.matches("i") || myobj7.matches("inventory")) {
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
        } else if (myobj7.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.matches("sleep")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Amazing strategy.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("check") && myobj7.contains("out")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("check") || myobj7.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("smell")) {
            secondText.setText("It smells like the whole planet has been dead for years.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("listen")) {
            secondText.setText("There's nothing to listen at.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("wait")) {
            secondText.setText("Time passes...");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("what") && myobj7.contains("time") && myobj7.contains("is") || myobj7.matches("time") || myobj7.matches("the time")) {
            secondText.setText("It is sunrise.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.matches("diagnostic") || myobj7.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj7.contains("jump") || myobj7.contains("climb") || myobj7.contains("turn") || myobj7.contains("shut") || myobj7.contains("look") || myobj7.contains("see") || myobj7.contains("watch") || myobj7.contains("play") || myobj7.contains("run") || myobj7.contains("walk") || myobj7.contains("eat") || myobj7.contains("move") || myobj7.contains("give") || myobj7.contains("offer") || myobj7.contains("read") || myobj7.contains("write") || myobj7.contains("fix")|| myobj7.contains("repair")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Look around you.");
            linearLayout.addView(secondText);
            obj();
        }

        return (String) secondText.getText();
    }





















    public void obj8() {
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        final CustomEditText obj8 = new CustomEditText(this);
        final TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);
        obj8.setCursorVisible(false);
        obj8.setTypeface(typeface);
        obj8.setText(">");
        linearLayout.addView(obj8);

        setInputListeners(obj8, linearLayout, typeface, secondText);

    }

    public String checkObj8Answer(String myobj8) {
        TextView myMoves = (TextView) findViewById(R.id.moves);
        TextView myLocation = (TextView) findViewById(R.id.location);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gameearth);
        final Typeface typeface = ResourcesCompat.getFont(MainActivity.this, R.font.lucida_console);
        TextView secondText = new TextView(MainActivity.this);
        secondText.setTypeface(typeface);


        if (inreading==1) {  // READING NOTES
            switch (myobj8) {
                case "1":
                    secondText.setText("LUDLOW B903\nOPEN AIR\n1994      Buzz Ruzzinsky\n          Live in concert\n10.6.   Stadium\n8:00 P.M.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    hasreadborrowedbooks=1;
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
        } else if (inconversationshannon==1) {  // CONVERSATIONS
            switch (myobj8) {
                case "1":
                    secondText.setText("I guess you don't think about that when you know you can't live the planet.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    secondText.setText("Oh. I don't work here.\nI work in a mechanincs workshop where I customize the cars in order to resist the sand storms.\nThey are mostly cars owned by the upper-class people.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "3":
                    secondText.setText("Thanks a lot! When the world starts becoming crazy, books help me keep my feet on the ground.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "4":
                    inconversationshannon=0;
                    secondText.setText("Dialogue Shannon finished.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("You just said: jdfjwurnrvjwq.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Shannon.\n\n1. 'Do you enjoy living at B903?'\n2. 'What is your job here?'\n3. 'By the way, I absolutely love the book you're reading.'\n4. exit.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (inconversationsully==1) {
            switch (myobj8) {
                case "1":
                    secondText.setText("And I want a Thai massage and a pizza. Look kid, I have a lot of work to do. You can check the borrowed books at the pinboard on the wall.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    knowsarmoredpeople=1;
                    secondText.setText("I'm not a coward or anything like that. It is just that sometimes these people coming with armor make me feel uneasy.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "3":
                    inconversationsully=0;
                    secondText.setText("Dialogue Sully, the librarian finished.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("You just said: ppqwpfejwñen.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Sully, the librarian.\n\n1. 'Excuse me. I want to ask for a book.'\n2. 'Why is this cabin bulletproof?'\n3. exit.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (inconversationhenry==1) {
            switch (myobj8) {
                case "1":
                    secondText.setText("Your lower-class friends shall be chatting and avoiding work as they use to do. And this is how the Bubble will prevail...");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    secondText.setText("Indeed, my curious partner. A gentleman like me has to enrich his knowledge daily.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "3":
                    inconversationhenry=0;
                    secondText.setText("Dialogue Henry finished.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("You just said: mknewbfowc.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Henry.\n\n1. 'What is on the other side of the office door?'\n2. 'You come here a lot, don't you?'\n3. exit.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (inconversationkenny==1) {
            switch (myobj8) {
                case "1":
                    secondText.setText("It was a book written back at the hitchhiker years in B526.\nAfter the destruction of that Bubble, the book got forgotten.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "2":
                    secondText.setText("They will send the Federation Agents to burn him alive and to make sure that no one will ever know about his existence.\n\nThat's what they do. That's what people do. As a matter of fact, people hate people. But you can be different, can't you?");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "3":
                    secondText.setText("Do I look like I need that? I would like many things in the universe, but company is not one of them.\nSometimes people don't even deserve the Bubble they live in.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                case "4":
                    inconversationkenny=0;
                    secondText.setText("Dialogue Kenny finished.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
                default:
                    secondText.setText("You just said: sadflhjsdf.\nWrite the number of the option you want.\n\n\n" +
                            "Dialogue Kenny.\n\n1. 'What do you know about The Hitchhiker's Guide to the Galaxy?'\n2. 'If there was... a fugitive Traveller alive, do you think the Space Federation will hunt him down?'\n3. 'Hey man... Do you need company?'\n4. exit.");
                    linearLayout.addView(secondText);
                    obj();
                    break;
            }
        } else if (inconversationicarus==1) {
            if (icaruschatphase==1) {
                switch (myobj8) {
                    case "1":
                        icaruschatphase=2;
                        secondText.setText("Yeah... Okay. You could say that. However, Canterman's theories result to be obsolete in the present day. Sigh. But I don't really care.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                    case "2":
                        icaruschatphase=2;
                        secondText.setText("What do you know about shutting your mouth?\nWhatever you want to tell me, I don't care.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                    case "3":
                        inconversationicarus = 0;
                        secondText.setText("Dialogue Icarus finished.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                    default:
                        secondText.setText("You just said: mknfwrhwe.\nWrite the number of the option you want.\n\n\n" +
                                "Dialogue Icarus.\n\n1. 'Hi. That's a great book right there.'\n2. 'What do you know about the Travellers?'\n3. exit.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                }
            } else {
                switch (myobj8) {
                    case "1":
                        secondText.setText("Yeah... Okay. You could say that. However, Canterman's theories result to be obsolete in the present day. Sigh. But I don't really care.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                    case "2":
                        secondText.setText("What do you know about shutting your mouth?\nWhatever you want to tell me, I don't care.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                    case "3":
                        haskidicarus=1;
                        inconversationicarus = 0;
                        secondText.setText("Hey. Hey. Hey. Don't mess with me, 'explorer'.\nYou don't know me and you don't know the things I do here.\n\nDialogue Icarus finished.\n\nHe didn't like your comment.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                    case "4":
                        inconversationicarus = 0;
                        secondText.setText("Dialogue Icarus finished.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                    default:
                        secondText.setText("You just said: mknfwrhwe.\nWrite the number of the option you want.\n\n\n" +
                                "Dialogue Icarus.\n\n1. 'Hi. That's a great book right there.'\n2. 'What do you know about the Travellers?'\n3. 'I'm about to drop an ion bomb at B903, but I guess you don't really care.'\n4. exit.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                }
            }
        } else if (inconversationabigail==1) {
            if (abigailchatphase==1) {
                if (knowsarmoredpeople==1) {
                    switch (myobj8) {
                        case "1":
                            secondText.setText("It's useless. During the whole morning I've been trying to find something in these pages.\nI'm afraid the book is obsolete.\nMaybe It was useful for the people at B526, but not for us.\n\nNote: You won't need this object during your adventure.");
                            linearLayout.addView(secondText);
                            obj();
                            break;
                        case "2":
                            abigailchatphase = 2;
                            secondText.setText("What do you think? Don't play the innocent role with me.\nDo you think you're the only one that has noticed the incoherence in the spacial routes? I'm one step ahead of you.\n\nMmh. Listen. Maybe you're smarter than you seem.\nI managed to discover there's a place hidden in the library which is related with the hitchhikers that existed many years ago.\nI don't know what it is. I have just found the door, but it needs to be unlocked following some weird instructions.\n\n\n" +
                                    "Dialogue Abigail.\n\n1. 'Why haven't you unlocked it yourself?'\n2. 'What kind of door?'\n3. 'Maybe I can help you if you let us go with you.'\n4. exit.");
                            linearLayout.addView(secondText);
                            obj();
                            break;
                        case "3":
                            secondText.setText("Did Sully tell you that? Don't let him scare you.\nHe is just tired of seeing the Federation Agents sniffing here.");
                            linearLayout.addView(secondText);
                            obj();
                            break;
                        case "4":
                            inconversationabigail = 0;
                            secondText.setText("Dialogue Abigail finished.");
                            linearLayout.addView(secondText);
                            obj();
                            break;
                        default:
                            secondText.setText("You just said: yuwegvfbja.\nWrite the number of the option you want.\n\n\n" +
                                    "Dialogue Abigail.\n\n1. 'I need that book.'\n2. 'What are you trying to find in that book?'\n3. 'Who comes to a Library with an armor?'\n4. exit.");
                            linearLayout.addView(secondText);
                            obj();
                            break;
                    }
                } else {
                    switch (myobj8) {
                        case "1":
                            secondText.setText("It's useless. During the whole morning I've been trying to find something in these pages.\nI'm afraid the book is obsolete.\nMaybe It was useful for the people at B526, but not for us.\n\nNote: You won't need this object during your adventure.");
                            linearLayout.addView(secondText);
                            obj();
                            break;
                        case "2":
                            abigailchatphase = 2;
                            secondText.setText("What do you think? Don't play the innocent role with me.\nDo you think you're the only one that has noticed the incoherence in the spacial routes? I'm one step ahead of you.\n\nMmh. Listen. Maybe you're smarter than you seem.\nI managed to discover there's a place hidden in the library which is related with the hitchhikers that existed many years ago.\nI don't know what it is. I have just found the door, but it needs to be unlocked following some weird instructions.\n\n\n" +
                                    "Dialogue Abigail.\n\n1. 'Why haven't you unlocked it yourself?'\n2. 'What kind of door?'\n3. 'Maybe I can help you if you let us go with you.'\n4. exit.");
                            linearLayout.addView(secondText);
                            obj();
                            break;
                        case "3":
                            inconversationabigail = 0;
                            secondText.setText("Dialogue Abigail finished.");
                            linearLayout.addView(secondText);
                            obj();
                            break;
                        default:
                            secondText.setText("You just said: yuwegvfbja.\nWrite the number of the option you want.\n\n\n" +
                                    "Dialogue Abigail.\n\n1. 'I need that book.'\n2. 'What are you trying to find in that book?'\n3. exit.");
                            linearLayout.addView(secondText);
                            obj();
                            break;
                    }
                }
            } else {
                switch (myobj8) {
                    case "1":
                        secondText.setText("Because you need to find a name. It mentions something like... 'the unfortunate one'.\nI've been reading every book here and I do not know who could it be.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                    case "2":
                        secondText.setText("The one that gets you killed if you fail the instructions.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                    case "3":
                        inconversationabigail = 0;
                        abigailchatphase = 1;
                        secondText.setText("'Sounds like a deal to me.\nHowever, after we open that door, I'll go and do my thing and you'll go and do yours. Because I know that you're looking for the Traveller too, obviously. But I don't know why you are looking for him...'\n\nDialogue Abigail finished.\n\nSuddenly, Fred joins Abigail and you.\nYou tell Fred everything you've talked with Abigail and he's pleased to know that you've found something interesting.\n\nAbigail indicates you and Fred to follow her. The three of you go NORTH ending in a private room which Abigail seems to know.");
                        linearLayout.addView(secondText);
                        myLocation.setText("Library, room D");
                        score9 = valueManager.score;
                        obj=9;
                        obj();
                        break;
                    case "4":
                        inconversationabigail = 0;
                        secondText.setText("Dialogue Abigail finished.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                    default:
                        secondText.setText("You just said: efbrqhkbfb.\nWrite the number of the option you want.\n\n\n" +
                                "Dialogue Abigail.\n\n1. 'Why haven't you unlocked it yourself?'\n2. 'What kind of door?'\n3. 'Maybe I can help you if you let us go with you.'\n4. exit.");
                        linearLayout.addView(secondText);
                        obj();
                        break;
                }
            }
        } else if (valueManager.score - scorepeopleleave8 == 4) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Restart the game or you will be stuck here playing forever.");
            linearLayout.addView(secondText);
            obj();
        }
        // DIRECTIONS GO VERB 8
        else if (myobj8.matches("north") || myobj8.matches("n") || myobj8.matches("go north") || myobj8.matches("go n") || myobj8.matches("go straight on") || myobj8.matches("west") || myobj8.matches("w") || myobj8.matches("go west") || myobj8.matches("go w") || myobj8.matches("go left") || myobj8.matches("northeast") || myobj8.matches("ne") || myobj8.matches("go northeast") || myobj8.matches("go ne") || myobj8.matches("northwest") || myobj8.matches("nw") || myobj8.matches("go northwest") || myobj8.matches("go nw") || myobj8.matches("southeast") || myobj8.matches("se") || myobj8.matches("go southeast") || myobj8.matches("go se") || myobj8.matches("southwest") || myobj8.matches("sw") || myobj8.matches("go southwest") || myobj8.matches("go sw") || myobj8.matches("up") || myobj8.matches("u") || myobj8.matches("go up") || myobj8.matches("upstairs") || myobj8.matches("go upstairs") || myobj8.matches("go u") || myobj8.matches("down") || myobj8.matches("d") || myobj8.matches("go down") || myobj8.matches("downstairs") || myobj8.matches("go downstairs") || myobj8.matches("go d")) {
            secondText.setText("You can't go that way.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.matches("east") || myobj8.matches("e") || myobj8.matches("go east") || myobj8.matches("go e") || myobj8.matches("go right")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("The office door is locked.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.matches("south") || myobj8.matches("s") || myobj8.matches("go south") || myobj8.matches("go s") || myobj8.matches("go backwards") || myobj8.matches("exit") || myobj8.matches("leave") || ((myobj8.contains("go") || myobj8.contains("get")) && myobj8.contains("out")) || (myobj8.contains("exit") && myobj8.contains("room"))) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You are not leaving without Fred, are you?");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("go")) {
            secondText.setText("This verb needs to be used with a direction.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.matches("speak") || myobj8.matches("talk") || myobj8.matches("ask")) {  // SPEAK VERB 8
            secondText.setText("This verb needs to be used with a name.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("speak") || myobj8.contains("talk") || myobj8.contains("ask")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (peopleleave8==1) {
                secondText.setText("You can't speak with anyone because everybody has left.");
                linearLayout.addView(secondText);
                obj();
            } else {
                if (myobj8.contains("sully")) {
                    inconversationsully=1;
                    secondText.setText("Dialogue Sully, the librarian.\n\n1. 'Excuse me. I want to ask for a book.'\n2. 'Why is this cabin bulletproof?'\n3. exit.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj8.contains("people") || myobj8.contains("person")) {
                    secondText.setText("When you go talk with the people reading, the librarian stops you.\n\nNote: Do not disturb the readers if you don't have something to tell them.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj8.contains("abigail")) {
                    inconversationabigail=1;
                    if (abigailchatphase==1) {
                        if (knowsarmoredpeople==1) {
                            secondText.setText("Dialogue Abigail.\n\n1. 'I need that book.'\n2. 'What are you trying to find in that book?'\n3. 'Who comes to a Library with an armor?'\n4. exit.");
                        } else {
                            secondText.setText("Dialogue Abigail.\n\n1. 'I need that book.'\n2. 'What are you trying to find in that book?'\n3. exit.");
                        }
                    } else {
                        secondText.setText("Dialogue Abigail.\n\n1. 'Why haven't you unlocked it yourself?'\n2. 'What kind of door?'\n3. 'Maybe I can help you if you let us go with you.'\n4. exit.");
                    }
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj8.contains("kenny")) {
                    inconversationkenny=1;
                    secondText.setText("Dialogue Kenny.\n\n1. 'What do you know about The Hitchhiker's Guide to the Galaxy?'\n2. 'If there was... a fugitive Traveller alive, do you think the Space Federation will hunt him down?'\n3. 'Hey man... Do you need company?'\n4. exit.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj8.contains("henry")) {
                    inconversationhenry=1;
                    secondText.setText("Dialogue Henry.\n\n1. 'What is on the other side of the office door?'\n2. 'You come here a lot, don't you?'\n3. exit.");
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj8.contains("icarus")) {
                    if (haskidicarus==0) {
                        inconversationicarus=1;
                        if (icaruschatphase==1) {
                            secondText.setText("Dialogue Icarus.\n\n1. 'Hi. That's a great book right there.'\n2. 'What do you know about the Travellers?'\n3. exit.");
                        } else {
                            secondText.setText("Dialogue Icarus.\n\n1. 'Hi. That's a great book right there.'\n2. 'What do you know about the Travellers?'\n3. 'I'm about to drop an ion bomb at B903, but I guess you don't really care.'\n4. exit.");
                        }
                    } else {
                        secondText.setText("Icarus doesn't want to talk with you.");
                    }
                    linearLayout.addView(secondText);
                    obj();
                } else if (myobj8.contains("shannon")) {
                    inconversationshannon=1;
                    secondText.setText("Dialogue Shannon.\n\n1. 'Do you enjoy living at B903?'\n2. 'What is your job here?'\n3. 'By the way, I absolutely love the book you're reading.'\n4. exit.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText("This person's name is incorrect or he/she is not in the place.");
                    linearLayout.addView(secondText);
                    obj();
                }
            }
        } else if (myobj8.contains("stop")) { // STOP VERB 8
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj8.contains("voices")) {
                secondText.setText("You are not hearing voices... Not yet.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("time")) {
                secondText.setText("That's useless.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("There's nothing you can stop here.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj8.matches("close")) {  // CLOSE VERB 8
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("close")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj8.contains("door")) {
                secondText.setText("The door is already closed.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("eye")) {
                secondText.setText("This is not really useful right now.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be closed or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj8.matches("open")) {  // OPEN VERB 8
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("open")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj8.contains("door")) {
                secondText.setText("Locked.");
                linearLayout.addView(secondText);
                obj();
            } else if ((myobj8.contains("entry") || myobj8.contains("library") || myobj8.contains("entrance")) && myobj8.contains("door")) {
                secondText.setText("You are not leaving without Fred, are you?");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("eye")) {
                secondText.setText("Your eyes are wide open.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("window")) {
                if (brokenwindow8==0) {
                    secondText.setText("The wind feels fresh on your skin.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText("You actually broke it, so...");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else if (myobj8.contains("book")) {
                secondText.setText("You will have to take a book first.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be opened or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj8.matches("turn off") || myobj8.matches("turn it off") || myobj8.matches("shut down") || myobj8.matches("shut it down")) {  // TURN OFF VERB 8
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if ((myobj8.contains("turn") && myobj8.contains("off")) || (myobj8.contains("shut") && myobj8.contains("down"))) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj8.contains("alarm")) {
                secondText.setText("No alarm is sounding.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("music")) {
                secondText.setText("No music is being played.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be turned off or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj8.contains("turn on") || myobj8.contains("turn it on")) {  // TURN ON VERB 8
            secondText.setText("There's nothing you can turn on here.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("stand") || myobj8.contains("get up")) { // STAND VERB 8
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("You are already standing.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("sit down") || myobj8.matches("sit") || myobj8.contains("lie")) { // LIE VERB 8
            secondText.setText("There's no chairs left.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.matches("examine")) { // EXAMINE VERB 8
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("examine")) {
            if (myobj8.contains("window")) {
                secondText.setText("Library views are no different from your house ones. As always, you see the same desert with the same pieces of metal scattered on the ground.\nAt least you can spot a part of the houses that were buried during last week's sand storm.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("people")) {
                if (peopleleave8==1) {
                    secondText.setText("They are all gone.");
                } else {
                    secondText.setText("They are reading peacefully.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("book") || myobj8.contains("shelve")) {
                secondText.setText("Go and SPEAK with the librarian to ask for a book.\nRemember: say SPEAK and the name of the person you wanna speak with.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("sully") || myobj8.contains("librarian")) {
                if (peopleleave8==1) {
                    secondText.setText("Sully is not here to help you.");
                } else {
                    secondText.setText("Sully seems an almost depressed man with white hair and mustache.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("cabin")) {
                secondText.setText("This thing is bulletproof. You couldn't get in there even if you want to.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("door")) {
                secondText.setText("It is just a door.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("pinboard") || myobj8.contains("note")) {
                inreading=1;
                secondText.setText("Reading pinboard.\n\n1. Buzz Ruzzinsky's live in concert.\n2. Borrowed books.\n3. Ludlow Post 11 21 1959.\n4. The Message 06 04 1672.\n5. exit.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be examined or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        }
        // TAKE GET VERB 8
        else if (myobj8.matches("take") || myobj8.matches("get") || myobj8.matches("pick") || myobj8.matches("grab")) {
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("take") || myobj8.contains("get") || myobj8.contains("pick") || myobj8.contains("grab")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if ((myobj8.contains("glass") || myobj8.contains("crystal")) && brokenwindow8==1) {
                String brokenglass = "broken glass";
                if (myInventory.contains("broken glass")) {
                    secondText.setText("You already took it.");
                } else {
                    myInventory.add(brokenglass);
                    secondText.setText("Taken.");
                }
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("book") || myobj8.contains("shelve")) {
                secondText.setText("Go and SPEAK with the librarian to ask for a book.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("note")) {
                secondText.setText("Notes cannot be taken, but you can read them.");
                linearLayout.addView(secondText);
                obj();
            } else if (!(dropLibraryL.isEmpty())) {
                int n;
                if (myobj8.contains("take") || myobj8.contains("pick") || myobj8.contains("grab")) {
                    n = 4;
                } else if (myobj8.contains("get")) {
                    n = 3;
                } else {
                    n = 0;
                }
                String s = myobj8.substring(n);
                s = s.replace(" the ","");
                s = s.replace(" a ","");
                s = s.replace(" an ","");
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
        } else if (myobj8.matches("drop") || myobj8.contains("get rid of")) { // DROP VERB 8
            secondText.setText("Just say: Drop (and the object you want to drop).");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("drop")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            int n = 4;
            String s = myobj8.substring(n);
            s = s.replace(" the ","");
            s = s.replace(" a ","");
            s = s.replace(" an ","");
            s = s.trim();
            if (myInventory.contains(s)) {
                myInventory.remove(s);
                dropLibraryL.add(s);
                secondText.setText("Dropped.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("You're not holding the " + s + ".");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj8.matches("help") && valueManager.score - score8 >= 6) { // HELP VERB 8
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Find that hitchhiker's book.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.matches("help")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Try to do things by yourself first.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("help")) {
            secondText.setText("Just say HELP.");
            linearLayout.addView(secondText);
            obj();
        }
        // FOLLOW VERB 8
        else if (myobj8.matches("follow") || myobj8.matches("chase") || myobj8.matches("find") || myobj8.matches("search")) {
            secondText.setText("This verb needs to be used with a name.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("follow") || myobj8.contains("chase") || myobj8.contains("find") || myobj8.contains("search")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (peopleleave8==1) {
                secondText.setText("You can't follow anyone. You don't know where they went.");
                linearLayout.addView(secondText);
                obj();
            } else {
                if (myobj8.contains("fred")) {
                    secondText.setText("Fred is probably talking with his friend. They surely want to be alone.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    secondText.setText("This person's name is incorrect or he/she cannot be followed.");
                    linearLayout.addView(secondText);
                    obj();
                }
            }
        } else if (myobj8.contains("drink")) { // DRINK VERB 8
            secondText.setText("There is nothing you can drink here.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("write")) {  // WRITE VERB 8
            secondText.setText("You cannot write new notes.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.matches("read")) {  // READ VERB 8
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("read")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj8.contains("note") || myobj8.contains("pinboard")) {
                inreading=1;
                secondText.setText("Reading pinboard.\n\n1. Buzz Ruzzinsky's live in concert.\n2. Borrowed books.\n3. Ludlow Post 11 21 1959.\n4. The Message 06 04 1672.\n5. exit.");
                linearLayout.addView(secondText);
                obj();
            } else if (myobj8.contains("book")) {
                secondText.setText("You are not holding a book.");
                linearLayout.addView(secondText);
                obj();
            } else {
                secondText.setText("This thing cannot be read or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if (myobj8.matches("break") || myobj8.matches("hit") || myobj8.matches("attack") || myobj8.matches("punch") || myobj8.matches("fight") || myobj8.matches("kick")) {  // BREAK VERB 8
            secondText.setText("This verb needs to be used with a noun.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("break") || myobj8.contains("hit") || myobj8.contains("attack") || myobj8.contains("punch") || myobj8.contains("fight") || myobj8.contains("kick")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            if (myobj8.contains("people") || myobj8.contains("person") || myobj8.contains("sully") || myobj8.contains("librarian")) {
                if (peopleleave8==1) {
                    secondText.setText("There's no one here you can attack.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    peopleleave8=1;
                    scorepeopleleave8 = valueManager.score;
                    secondText.setText("After showing your true violent self, everybody leaves the place running.\nYou are left here alone.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else if (myobj8.contains("window")) {
                if (brokenwindow8==0 && peopleleave8==1) {
                    brokenwindow8=1;
                    secondText.setText("And now the window is broken. It's a mess.");
                    linearLayout.addView(secondText);
                    obj();
                } if (brokenwindow8==1) {
                    secondText.setText("The window is already broken.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    brokenwindow8=1;
                    peopleleave8=1;
                    scorepeopleleave8 = valueManager.score;
                    secondText.setText("You broke the window. After showing your true violent self, everybody leaves the place running.\nYou are left here alone.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else if (myobj8.contains("cabin") || myobj8.contains("shelve") || myobj8.contains("door")) {
                if (peopleleave8==1) {
                    secondText.setText("You are all alone and hitting stuff. You should be proud.");
                    linearLayout.addView(secondText);
                    obj();
                } else {
                    peopleleave8=1;
                    scorepeopleleave8 = valueManager.score;
                    secondText.setText("After showing your true violent self, everybody leaves the place running.\nYou are left here alone.");
                    linearLayout.addView(secondText);
                    obj();
                }
            } else {
                secondText.setText("This thing cannot be hitted or it is not in the place.");
                linearLayout.addView(secondText);
                obj();
            }
        } else if ((myobj8.contains("fix") || myobj8.contains("repair")) && myobj8.contains("window")) { // WINDOW ACTIONS 8
            secondText.setText("Not your business.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("jump") && myobj8.contains("window")) {
            secondText.setText("You are not a savage.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("window")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Library views are no different from your house ones. As always, you see the same desert with the same pieces of metal scattered on the ground.\nAt least you can spot a part of the houses that were buried during last week's sand storm.");
            linearLayout.addView(secondText);
            obj();
        } else if ((myobj8.contains("look") && myobj8.contains("around")) || myobj8.matches("l") || myobj8.matches("look")) {
            String intro = "Library\n";
            if (brokenwindow8==1 && peopleleave8==1) {
                intro = intro + "There are some book shelves and a large broken window.\nSully's cabin is empty.";
            } else if (peopleleave8==1) {
                intro = intro + "There are some book shelves and a large window.\nSully's cabin is empty.";
            } else {
                if (hasreadborrowedbooks==0) {
                    intro = intro + "There are five people reading, some book shelves and a large window.\nSully, the librarian, is inside his crystal cabin.";
                } else {
                    intro = intro + "There are some book shelves and a large window.\nSully, the librarian, is inside his crystal cabin.\nShannon, Icarus, Henry, Abigail and Kenny are here.";
                }
            }
            intro = intro + " There is the closed office door and a pinboard with a few notes here.";
            if (!(dropLibraryL.isEmpty())) {
                int n = dropLibraryL.size();
                for (int i=0; i<n; i++) {
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
        } else if (myobj8.matches("i") || myobj8.matches("inventory")) {
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
        } else if (myobj8.contains("inventory")) {
            secondText.setText("Just write the letter I or say INVENTORY.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("repeat")) {
            secondText.setText("There is nothing you can repeat.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.matches("sleep")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("After 20 minutes sleeping, you wake up and everything is still the same. Amazing strategy.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("check") && myobj8.contains("out")) {
            secondText.setText("Try to LOOK AROUND or EXAMINE an object.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("check") || myobj8.contains("review")) {
            secondText.setText("Say EXAMINE and the object you want to check.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("enter")) {
            secondText.setText("Try to open the doors of the places you want to get in.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("jump") || myobj8.contains("climb") || myobj8.contains("turn") || myobj8.contains("shut") || myobj8.contains("look") || myobj8.contains("see") || myobj8.contains("watch") || myobj8.contains("play") || myobj8.contains("run") || myobj8.contains("walk") || myobj8.contains("eat") || myobj8.contains("move") || myobj8.contains("put") || myobj8.contains("give") || myobj8.contains("offer")) {
            valueManager.score++;
            myMoves.setText("Moves: " + valueManager.score);
            secondText.setText("Look around you.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("smell")) {
            secondText.setText("It smells just like a library.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("listen")) {
            secondText.setText("You just hear the pleasant sound of the pages.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("wait")) {
            secondText.setText("Time passes...");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.contains("what") && myobj8.contains("time") && myobj8.contains("is") || myobj8.matches("time") || myobj8.matches("the time")) {
            secondText.setText("It is daylight.");
            linearLayout.addView(secondText);
            obj();
        } else if (myobj8.matches("diagnostic") || myobj8.matches("diagnose")) {
            secondText.setText("This is not available at the moment.");
            linearLayout.addView(secondText);
            obj();
        }

        return (String) secondText.getText();
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
