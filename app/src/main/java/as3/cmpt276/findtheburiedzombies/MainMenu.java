package as3.cmpt276.findtheburiedzombies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//ASSETS
//https://pngtree.com/freepng/gravediggers_2799181.html
//https://www.shutterstock.com/video/clip-1263220-stock-footage-a-creepy-graveyard-halloween-background-scene-with-graves-evil-pumpkins-and-spooky-moonlit-sky.html

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Play Game
        Button playgameButton = findViewById(R.id.playgameButton);
        playgameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playGame = new Intent(MainMenu.this, Game.class);
                startActivity(playGame);
            }
        });

        //Options
        Button optionsButton = findViewById(R.id.optionsButton);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent options = new Intent(MainMenu.this, Options.class);
                startActivity(options);
            }
        });

        //Help
        Button helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent help = new Intent(MainMenu.this, Help.class);
                startActivity(help);
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
}
