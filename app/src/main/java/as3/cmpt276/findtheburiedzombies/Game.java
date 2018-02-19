package as3.cmpt276.findtheburiedzombies;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
//http://moziru.com/explore/Graves%20clipart%20cartoon/#go_post_7487_gravestone-clipart-11.png
//https://stackoverflow.com/questions/36371879/how-to-create-a-random-boolean-2d-array-with-a-specific-number-of-true

import java.util.Random;

import as3.cmpt276.findtheburiedzombies.model.GameState;

public class Game extends AppCompatActivity {

    private GameState game;

    Button buttons[][];
    boolean[][] minesArray;
    boolean[][] tappedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = GameState.getInstance();
        buttons = new Button[game.getNumRows()][game.getNumColumns()];
        minesArray = new boolean[game.getNumRows()][game.getNumColumns()];
        tappedButton = new boolean[game.getNumRows()][game.getNumColumns()];
        createMines();
        populateButtons();
        setupTextViews();
    }

    private void createMines() {
        for (int i = 0; i < game.getNumMines(); i++) {
            //Get random position for the next bomb
            Random rand = new Random();
            int row = rand.nextInt(game.getNumRows());
            int col = rand.nextInt(game.getNumColumns());
            while(minesArray[row][col]) { //if this position is a bomb
                //we get new position
                row = rand.nextInt(game.getNumRows());
                col = rand.nextInt(game.getNumColumns());
            }
            minesArray[row][col] = true; //make new position is a bomb
        }
    }

    private void setupTextViews() {
        final TextView minesInfo = findViewById(R.id.minesInfo);
        minesInfo.setText(getString(R.string.mineInfo, game.getNumRevealedMines(),game.getNumMines()));
        //minesInfo.setTextColor(Color.WHITE);

        final TextView scansUsed = findViewById(R.id.scansUsed);
        scansUsed.setText(getString(R.string.scansUsed, game.getNumScans()));
        //minesInfo.setTextColor(Color.WHITE);

        final TextView timesPlayed = findViewById(R.id.timesPlayed);
        timesPlayed.setText(getString(R.string.timesPlayed, MainMenu.getTimesPlayed(this)));
        //minesInfo.setTextColor(Color.WHITE);
    }

    public void tappedButtonUpdate() {
        for(int row = 0;row < game.getNumRows(); row ++) {
            for(int col = 0; col < game.getNumColumns(); col++) {
                if(tappedButton[row][col]) {
                    int totalMinesRowCol = 0;
                    Button button = buttons[row][col];
                    for(int i = 0; i < game.getNumColumns(); i++)
                    {
                        if(minesArray[row][i]) {
                            totalMinesRowCol++;
                        }
                    }
                    for(int i = 0; i < game.getNumRows(); i++)
                    {
                        if(minesArray[i][col]) {
                            totalMinesRowCol++;
                        }
                    }
                    button.setText(getString(R.string.totalMinesRowCol,totalMinesRowCol));
                    button.setTextColor(Color.BLUE);
                }
            }
        }
    }

    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableForButtons);
        for(int row = 0; row < game.getNumRows(); row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);

            for (int col = 0; col < game.getNumColumns(); col++) {
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                final Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                //Make Text not clip
                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (game.getNumRevealedMines() != game.getNumMines()) {
                            if (minesArray[FINAL_ROW][FINAL_COL]) {
                                minesArray[FINAL_ROW][FINAL_COL] = false;
                                game.setNumRevealedMines();
                                setupTextViews();
                                gridButtonClicked(FINAL_ROW, FINAL_COL);
                                tappedButtonUpdate();
                                if(game.getNumRevealedMines() == game.getNumMines()) {
                                setupSetMessage();
                                }
                            } else if (!tappedButton[FINAL_ROW][FINAL_COL]) {
                                tappedButton[FINAL_ROW][FINAL_COL] = true;
                                int totalMinesRowCol = 0;
                                for (int i = 0; i < game.getNumColumns(); i++) {
                                    if (minesArray[FINAL_ROW][i]) {
                                        totalMinesRowCol++;
                                    }
                                }
                                for (int i = 0; i < game.getNumRows(); i++) {
                                    if (minesArray[i][FINAL_COL]) {
                                        totalMinesRowCol++;
                                    }
                                }
                                game.setNumScans();
                                setupTextViews();
                                button.setText(getString(R.string.totalMinesRowCol,totalMinesRowCol));
                                button.setTextColor(Color.BLUE);
                            }
                        }
                    }
                });
            tableRow.addView(button);
            buttons[row][col] = button;
            }
        }
    }

    private void setupSetMessage() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        MessageFragment dialog = new MessageFragment();
        dialog.show(manager, "MessageDialog");
    }

    private void gridButtonClicked(int row, int col) {
        Button button = buttons[row][col];

        lockButtonSizes();

        //Doesn't Scale Image
        //button.setBackgroundResource(R.drawable.gravestone);
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gravestone);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }

    private void setImageGraveStone() {

    }

    private void lockButtonSizes() {
        for(int row = 0;row < game.getNumRows(); row ++) {
            for(int col = 0; col < game.getNumColumns(); col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMaxWidth(width);
                button.setMinWidth(width);

                int height = button.getHeight();
                button.setMaxHeight(height);
                button.setMinHeight(height);
            }
        }
    }
}
