package as3.cmpt276.findtheburiedzombies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import as3.cmpt276.findtheburiedzombies.model.GameState;

//ASSETS
//https://pngtree.com/freepng/gravediggers_2799181.html
//https://www.shutterstock.com/video/clip-1263220-stock-footage-a-creepy-graveyard-halloween-background-scene-with-graves-evil-pumpkins-and-spooky-moonlit-sky.html

public class MainMenu extends AppCompatActivity {

    private GameState game;
    private static final String PREF_NAME = "AppPrefs";
    private static final String TIMES_PLAYED = "Times Played";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Play Game
        Button playgameButton = findViewById(R.id.playgameButton);
        playgameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupGame();
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

    private void savetimesPlayed(int timesPlayed) {
        SharedPreferences prefs = this.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(TIMES_PLAYED, timesPlayed);
        editor.apply();
    }

    static public int getTimesPlayed(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        //CHANGE DEFAULT VALUE
        int defaultvalue = context.getResources().getInteger(R.integer.timesPlayed);
        return prefs.getInt(TIMES_PLAYED, defaultvalue);
    }

    private void setupGame() {
        int boardRow = Options.getBoardSizeRow(MainMenu.this);
        int numberMines = Options.getNumberMines(MainMenu.this);
        int boardCol = Options.getBoardSizeCol(MainMenu.this);
        game = GameState.getInstance();
        game.setGameBoard(boardCol,boardRow,numberMines);

        int timesPlayed = getTimesPlayed(MainMenu.this) + 1;
        savetimesPlayed(timesPlayed);

    }

    //Back Button to Exit Application
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
