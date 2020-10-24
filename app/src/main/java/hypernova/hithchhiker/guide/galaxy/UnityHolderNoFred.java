package hypernova.hithchhiker.guide.galaxy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.unity3d.player.UnityPlayerActivity;

public class UnityHolderNoFred extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(UnityHolderNoFred.this, UnityPlayerActivity.class));
    }
}
