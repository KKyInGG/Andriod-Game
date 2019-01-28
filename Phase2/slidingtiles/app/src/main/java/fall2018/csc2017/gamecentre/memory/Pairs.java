package fall2018.csc2017.gamecentre.memory;

import java.io.Serializable;

import fall2018.csc2017.gamecentre.R;

public class Pairs implements Serializable {

    /**
     * The background id to find the pair image.
     */
    private int background;

    /**
     * The unique id.
     */
    private int id;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Return the Pair id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    /**
     * A Pair with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    public Pairs(int id, int background) {
        this.id = id;
        this.background = background;
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the backgroundId
     */
    public Pairs(int backgroundId) {
        id = backgroundId + 1;
        // "This looks so ugly" Part 2!
        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.card_1;
                break;
            case 2:
                background = R.drawable.card_2;
                break;
            case 3:
                background = R.drawable.card_3;
                break;
            case 4:
                background = R.drawable.card_4;
                break;
            case 5:
                background = R.drawable.card_5;
                break;
            case 6:
                background = R.drawable.card_6;
                break;
            case 7:
                background = R.drawable.card_7;
                break;
            case 8:
                background = R.drawable.card_8;
                break;
            case 9:
                background = R.drawable.card_9;
                break;
            case 10:
                background = R.drawable.card_10;
                break;
            case 11:
                background = R.drawable.card_11;
                break;
            case 12:
                background = R.drawable.card_12;
                break;
            case 13:
                background = R.drawable.card_13;
                break;
            case 14:
                background = R.drawable.card_14;
                break;
            case 15:
                background = R.drawable.card_15;
                break;
            case 16:
                background = R.drawable.card_16;
                break;
            case 17:
                background = R.drawable.card_17;
                break;
            case 18:
                background = R.drawable.card_18;
                break;
            case 19:
                background = R.drawable.card_back;
            default:
                background = R.drawable.card_back;
        }
    }
}
