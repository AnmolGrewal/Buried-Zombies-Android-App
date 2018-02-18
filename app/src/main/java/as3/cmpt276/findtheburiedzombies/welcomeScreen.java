package as3.cmpt276.findtheburiedzombies;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//https://stackoverflow.com/questions/6533942/adding-gif-image-in-an-imageview-in-android
//https://stackoverflow.com/questions/29047902/how-to-add-an-image-to-the-drawable-folder-in-android-studio
//https://stackoverflow.com/questions/15874117/how-to-set-delay-in-android

public class welcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //Animation lasts about 2seconds so 4seconds after
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 6s = 6000ms

            }
        }, 6000);
    }
}
