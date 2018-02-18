package as3.cmpt276.findtheburiedzombies;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

//I referenced everything to help in case I need to go back to the code
//https://stackoverflow.com/questions/6533942/adding-gif-image-in-an-imageview-in-android
//https://stackoverflow.com/questions/29047902/how-to-add-an-image-to-the-drawable-folder-in-android-studio
//https://stackoverflow.com/questions/15874117/how-to-set-delay-in-android
//https://stackoverflow.com/questions/2354336/android-pressing-back-button-should-exit-the-app
//https://stackoverflow.com/questions/11308198/start-new-activity-and-finish-current-one-in-android

//ASSETS
//https://giphy.com/gifs/halloween-classic-cartoon-10efL7C86gKyzK
public class welcomeScreen extends AppCompatActivity {

    private static final String TAG = "UserClicks";
    int counter = 0;

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
                //Which is launch the Main Menu Activity
                if(counter == 0) {
                    mainMenu();
                }
            }
        }, 6000);

        ImageButton skip = findViewById(R.id.imageButton);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Skip Pressed");
                counter = 1;
                mainMenu();
            }
        });

    }

    //Makes it if you press back button on activity screen it exits app
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Log.i("DATA", "Hit Actionbar Back Button");
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void mainMenu() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
    }

}
