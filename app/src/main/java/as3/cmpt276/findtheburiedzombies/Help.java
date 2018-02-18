package as3.cmpt276.findtheburiedzombies;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView helpText = findViewById(R.id.helpText);
        helpText.setText("Your goal in this game is to find all the zombies buried under the gravestones.\n" +
                "Also you want to reach your goal with the least number of scans possible\n" +
                "You click on a gravestone with a zombie that you have already discovered or a\n" +
                "gravestone that you clicked but turns out to not have a zombie to commence a scan of the\n" +
                "row and column of that particular gravestone to tell you how many zombies there are.\n" +
                "Good Luck!\n" +
                "https://www.shutterstock.com/video/clip-1263220-stock-footage-a-creepy-graveyard-\n" +
                "halloween-background-scene-with-graves-evil-pumpkins-and-spooky-moonlit-sky.html\n" +
                "https://pngtree.com/freepng/gravediggers_2799181.html\n" +
                "https://giphy.com/gifs/halloween-classic-cartoon-10efL7C86gKyzK");
    }
}
