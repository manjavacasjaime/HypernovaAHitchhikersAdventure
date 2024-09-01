package hypernova.hithchhiker.guide.galaxy;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

import static hypernova.hithchhiker.guide.galaxy.MainActivity.deletingLowbar;

public class CustomEditText extends EditText {

    private static final String TAG = "CustomEditText";

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            Editable s = this.getText();
            int lowbar = s.toString().indexOf('_');

            if (lowbar != -1) {
                deletingLowbar = 1;
                s.delete(lowbar, lowbar + 1);
            }
        }

        return false;
    }
}
