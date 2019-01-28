package fall2018.csc2017.gamecentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * A scoreboard abstract class that can return a representation of the user scores and global scores
 * for any game which fully implements the class.
 */
public class Scoreboard implements Serializable, PhaseTwoSubject {

    /**
     * A lists of globalSocres
     */
    private ArrayList<Score> globalScores;

    /**
     * An Observer.
     */
    private static List<PhaseTwoObserver> observers;

    /**
     * Make a new Scoreboard
     */
    public Scoreboard(){
        globalScores = new ArrayList<>();
        observers = new ArrayList<>();
    }


    /**
     * Make a scoreboard with given globalScores
     * @param globalScores to make a scoreboard from
     */
    public Scoreboard(ArrayList<Score> globalScores){
        this.globalScores = globalScores;
        observers = new ArrayList<>();
    }


    /**
     * Get the globalScores
     * @return ArrayList of global scores
     */
    public ArrayList<Score> getGlobalScores() {
        return globalScores;
    }


    /**
     * Set the gloabl score to given globalScores
     * @param globalScores to set
     */
    public void setGlobalScores(ArrayList<Score> globalScores) {
        this.globalScores = globalScores;
    }

    /**
     * Add's a score to the Scoreboard and then sorts the scores
     * @param currentPlayerId username of current player
     * @param score players score
     */
    public void addScore(String currentPlayerId, int score){
        Score s = new Score(currentPlayerId, score);
        globalScores.add(s);
        globalScores = sortScores(globalScores);
        notifyObservers();
    }

    /**
     * A sortScores() helper method that merges the left and right lists.
     * This code was adapted from Codexpedia's post on merge sort in 2016 retrieved on 11052018
     * from: https://www.codexpedia.com/java/java-merge-sort-implementation/
     * @param left list on the left to be merged
     * @param right list on the right to be merged
     * @param whole full list
     */
    private void merge(ArrayList<Score> left, ArrayList<Score> right, ArrayList<Score> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( (left.get(leftIndex).compareTo(right.get(rightIndex))) > 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }

        ArrayList<Score> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }

        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }

    /**
     * Returns the original ArrayList sorted. This code was adapted from Codexpedia's post on merge
     * sort in 2016 retrieved on 11052018.
     * from: https://www.codexpedia.com/java/java-merge-sort-implementation/
     * @param scores unsorted ArrayList of scores
     * @return scores sorted ArrayList of scores
     */
    public ArrayList<Score> sortScores(ArrayList<Score> scores){
        ArrayList<Score> left = new ArrayList<>();
        ArrayList<Score> right = new ArrayList<>();
        int center;

        if (scores.size() == 1) {
            return scores;
        } else {
            center = scores.size()/2;
            // copy the left half of whole into the left.
            for (int i=0; i<center; i++) {
                left.add(scores.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for (int i=center; i<scores.size(); i++) {
                right.add(scores.get(i));
            }

            // Sort the left and right halves of the arraylist.
            left  = sortScores(left);
            right = sortScores(right);

            // Merge the results back together.
            merge(left, right, scores);
        }
        return scores;
    }

    /**
     * Checks the Sliding Tiles scoreboard for the top scores.
     * If userScoresOnly is true, only looks up scores for the current player.
     * Returns a string of the top 5 (or less if less than 5 exist) scores for this game.
     *
     * @param userScoresOnly true when looking only for current player's scores
     * @return scoreValues
     */
    public String getScoreValues(boolean userScoresOnly, User currentPlayer ) {

        ArrayList<Score> scoresList;
        int numScores;

        if (userScoresOnly) {
            scoresList = getUserScoreboard(currentPlayer);
        }
        else {
            scoresList = globalScores;
        }

        if (scoresList.size() < 5) {
            numScores = scoresList.size();
        }
        else {
            numScores = 5;
        }

        StringBuilder scoreValues = new StringBuilder();
        for (int i = 0; i < numScores; i++) {
            Score currentItem = scoresList.get(i);
            scoreValues.append(String.format(Locale.US, "%s: %d",
                    currentItem.getUsername(), currentItem.getScore())).append("\n");
        }
        return scoreValues.toString();
    }

    /**
     * Returns an ArrayList representing the global scoreboard for sliding tiles game.
     * @return globalScores
     */
    public ArrayList<Score> getGlobalScoreboard(){
        return globalScores;
    }

    /**
     * Returns an ArrayList representing the users scoreboard for sliding tiles game.
     * @return UserScores
     */
    public ArrayList<Score> getUserScoreboard(User current_player){
        ArrayList<Score> UserScores = new ArrayList<>();
        for(int i = 0; i < globalScores.size(); i++){
            if(globalScores.get(i).getUsername().equals(current_player.getAccount())){
                UserScores.add(globalScores.get(i));
            }
        }
        return UserScores;
    }

    /**
     * Register an observer to watch this class
     * @param obj to register
     */
    @Override
    public void register(PhaseTwoObserver obj){
        if(obj == null) throw new NullPointerException("Null Observer");
        if(!observers.contains(obj))
        {observers.add(obj);
        obj.setSubject(this);}
    }

    /**
     * Notify observers for an update
     */
    @Override
    public void notifyObservers(){
        for (PhaseTwoObserver obj : observers) {
            obj.update();
        }
    }

}


