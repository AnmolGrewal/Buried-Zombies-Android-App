package as3.cmpt276.findtheburiedzombies.model;

/**
 * Created by anmol on 2018-02-18.
 * Singleton Class for GameState when playing game.
 */

public class GameState {
    private int numColumns;
    private int numRows;
    private int numMines;
    private int numScans;
    private int numRevealedMines;


    private static GameState instance;
    private GameState() {
        //Prevent others from making another one
    }

    public static GameState getInstance() {
        if(instance == null) {
            instance = new GameState();
        }
        return instance;
    }


    //Normal code
    public void setGameBoard(int columns, int rows, int mines) {
        numMines = mines;
        numRows = rows;
        numColumns = columns;
        numScans = 0;
        numRevealedMines = 0;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumRevealedMines() {
        return numRevealedMines;
    }

    public int getNumScans() {
        return numScans;
    }

    public int getNumMines() {
        return numMines;
    }

    public void setNumRevealedMines() {
        numRevealedMines++;
    }

    public void setNumScans() {
        numScans++;
    }
}
