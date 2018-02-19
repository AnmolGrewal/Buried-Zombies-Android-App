package as3.cmpt276.findtheburiedzombies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int boardRow = Options.getBoardSizeRow(this);
        int numberMines = Options.getNumberMines(this);
        int boardCol = Options.getBoardSizeCol(this);

    }
}
