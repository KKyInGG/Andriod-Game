package fall2018.csc2017.gamecentre.sudoku;

import java.io.Serializable;

public class SudokuGrid implements Serializable {
    /**
     *  The background ID to find the background image
     */
    private int background;

    /**
     * The String number which will indicate the number will be displayed in the grid
     */
    private String number;


    /**
     * Constrct a SudokuGrid with background and number.
     *
     * @param background a background id
     * @param number a String number
     */
    public SudokuGrid(int background, String number){
        this.background = background;
        this.number = number;
    }

    /**
     * Return the background id
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Return the number will be displayed.
     * @return  the number
     */

    public String getNumber() {
        return number;
    }

    /**
     * Set up another int background id
     * @param background background id
     */

    public void setBackground(int background){
        this.background = background;
    }

    /**
     * Set up another String number
     * @param number  the number
     */
    public void setNumber(String number){
        this.number = number;
    }

}
