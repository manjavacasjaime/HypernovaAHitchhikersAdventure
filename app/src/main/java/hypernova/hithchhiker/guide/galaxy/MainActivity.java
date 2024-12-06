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
                              case 7:
                                  if (levelManager.objectiveZero.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.objectiveSeven.checkObjAnswer(myobjxlow, MainActivity.this).matches("") && levelManager.commonAnswers.checkObjAnswer(myobjxlow, MainActivity.this, mechanicsManager).matches("") && levelManager.consultGuide.checkObjAnswer(myobjxlow, MainActivity.this).matches("")) {
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
}
