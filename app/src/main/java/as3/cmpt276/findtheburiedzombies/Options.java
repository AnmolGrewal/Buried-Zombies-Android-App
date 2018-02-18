package as3.cmpt276.findtheburiedzombies;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        createRadioButtons();
    }

    private void createRadioButtons() {
        RadioGroup group = findViewById(R.id.numMines);
        int[] numMines = getResources().getIntArray(R.array.num_mines);

        for(int i = 0; i < numMines.length; i++) {
            final int numberMine = numMines[i];

            RadioButton button = new RadioButton(this);
            button.setText(numberMine + " Mines");
            button.setTextColor(Color.WHITE);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(Options.this, "You Clicked " + numberMine, Toast.LENGTH_SHORT)
                        .show();
                }
            });
            group.addView(button);
        }



    }
}
