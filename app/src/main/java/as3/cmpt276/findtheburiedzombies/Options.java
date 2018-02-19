package as3.cmpt276.findtheburiedzombies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import as3.cmpt276.findtheburiedzombies.model.GameState;

public class Options extends AppCompatActivity {

    private GameState game;
    private static final String PREF_NAME = "AppPrefs";
    private static final String NUM_MINES = "Number Mines";
    private static final String NUM_ROWS = "Number Row";
    private static final String NUM_COLS = "Number Col";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        createMineRadioButtons();
        createBoardRadioButtons();

        //Help
        Button eraseTimes = findViewById(R.id.eraseButton);
        eraseTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        int boardRow = Options.getBoardSizeRow(this);
        int numberMines = Options.getNumberMines(this);
        int boardCol = Options.getBoardSizeCol(this);

        game = GameState.getInstance();
        game.setGameBoard(boardCol,boardRow,numberMines);
    }

    private void createBoardRadioButtons() {
        RadioGroup group = findViewById(R.id.boardSize);
        int[] boardSize = getResources().getIntArray(R.array.board_size_row);
        int[] boardSizeCol = getResources().getIntArray(R.array.board_size_column);

        for(int i = 0; i < boardSize.length; i++) {
            final int numberRow = boardSize[i];
            final int numberCol = boardSizeCol[i];

            final RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.boardOption, numberRow, numberCol));
            button.setTextColor(Color.WHITE);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(Options.this, "You Clicked " + numberRow + " rows by " + numberCol + " columns", Toast.LENGTH_SHORT)
                            .show();

                    saveBoardSize(numberRow, numberCol);
                }
            });
            group.addView(button);


            //Default
            if(numberRow == getBoardSizeRow(this)){
                button.setChecked(true);
            }

        }
    }


    private void createMineRadioButtons() {
        RadioGroup group = findViewById(R.id.numMines);
        int[] numMines = getResources().getIntArray(R.array.num_mines);

        for (final int numberMine : numMines) {
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.minesOption, numberMine));
            button.setTextColor(Color.WHITE);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(Options.this, "You Clicked " + numberMine + " mines", Toast.LENGTH_SHORT)
                            .show();

                    saveNumberMines(numberMine);
                }
            });
            group.addView(button);

            //Default
            if (numberMine == getNumberMines(this)) {
                button.setChecked(true);
            }
        }
    }



    private void saveNumberMines(int numberMine) {
        SharedPreferences prefs = this.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(NUM_MINES, numberMine);
        editor.apply();
    }

    static public int getNumberMines(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        //CHANGE DEFAULT VALUE
        int defaultvalue = context.getResources().getInteger(R.integer.defaultmines);
        return prefs.getInt(NUM_MINES, defaultvalue);
    }

    private void saveBoardSize(int boardSizeRow, int boardSizeCol) {
        SharedPreferences prefs = this.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(NUM_ROWS, boardSizeRow);
        editor.putInt(NUM_COLS, boardSizeCol);
        editor.apply();
    }

    static public int getBoardSizeRow(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        //CHANGE DEFAULT VALUE
        int defaultvalue = context.getResources().getInteger(R.integer.defaultrow);
        return prefs.getInt(NUM_ROWS, defaultvalue);
    }

    static public int getBoardSizeCol(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        int defaultvalue = context.getResources().getInteger(R.integer.defaultcol);
        //CHANGE DEFAULT VALUE
        return prefs.getInt(NUM_COLS, defaultvalue);
    }

}
