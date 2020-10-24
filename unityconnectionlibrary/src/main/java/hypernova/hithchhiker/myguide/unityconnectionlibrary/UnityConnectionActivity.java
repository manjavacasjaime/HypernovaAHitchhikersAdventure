package hypernova.hithchhiker.myguide.unityconnectionlibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class UnityConnectionActivity {
    private static final String TAG = "UnityConnectionActivity";
    public static boolean platformFinished = false;
    static Context myContext = null;

    public void setPlatformFinishedTrue() {
        platformFinished = true;
        Log.d(TAG, "ARNOLD" + platformFinished);
        SharedPreferences.Editor editor = myContext.getSharedPreferences("hypernova.save", Context.MODE_PRIVATE).edit();
        editor.putBoolean("UnityConnectionActivity.platformFinished", UnityConnectionActivity.platformFinished);
        editor.commit();
    }

    public static void setMainActivityContext(Context mContext) {
        myContext = mContext;
    }
}
