package fall2018.csc2017.gamecentre;


import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Score object class
 */

public class Score implements Comparable<Score>, Serializable {
    /**
     * An String of UserId
     */
    private String userId;

    /**
     * A int Score
     */
    private int score;

    /**
     * Creates a score object
     * @param userId the user the score belongs to
     * @param score numerical representation of score
     */
    public Score(String userId, int score){
        this.userId = userId;
        this.score = score;
    }

    /**
     * Returns the numerical representation of score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the username that the score belongs to
     * @return username
     */
    public String getUsername(){
        return userId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Score)
        {Score o = (Score) obj;
        return (o.getUsername().equals(this.getUsername()) && o.getScore() == this.getScore());}
        return false;
    }

    /**
     * Making the Scores comparable
     * @param o Score being compared
     * @return -1, 1, 0 if less than, greater than, or equal to.
     */
    @Override
    public int compareTo(@NonNull Score o) {
        return Integer.compare(o.getScore(), this.score);
    }
}
