package as3.cmpt276.findtheburiedzombies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import as3.cmpt276.findtheburiedzombies.model.GameState;

public class Game extends AppCompatActivity {

    private GameState game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = GameState.getInstance();

    }

    private void updateScans() {

    }
}
