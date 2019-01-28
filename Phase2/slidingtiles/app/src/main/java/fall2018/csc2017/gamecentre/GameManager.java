package fall2018.csc2017.gamecentre;


/**
 * An Interface of GameManager
 */
public interface GameManager {

    /**
     * Get the Score of the game.
     */
    int getScore();

    /**
     * Return true iff finish the game.
     */
    boolean isGameOver();

    /**
     * Return true iff the position is valid.
     */
    boolean isValidTap(int Position);

    /**
     * Make Move with corresponding Position.
     * @param Postion int position
     */
    void touchMove(int Postion);
}
