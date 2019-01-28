package fall2018.csc2017.gamecentre;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ScoreboardFileSaver implements Serializable, PhaseTwoObserver {
    /**
     * A name of the file that store the object Scoreboard.
     */
    private String fileName;

    /**
     * A context.
     */
    private Context context;

    /**
     * A list of GlobalScores.
     */
    private ArrayList<Score> globalScores = new ArrayList<>();

    /**
     * A ScoreBoard Subject.
     */
    private Scoreboard subject;

    /**
     * Construct a new SlidingTilesScoreboardFileSaver with context and load it from "globalscores.ser" if needed
     * @param context the context to store Scoreboard in the phone's storage
     */
    public ScoreboardFileSaver(Context context, String fileName){
        this.context = context;
        this.fileName = fileName;
        loadFromFile();
    }

    /**
     * Return the save global scores
     * @return
     */
    public ArrayList<Score> getGlobalScores() {
        return globalScores;
    }

    /**
     * Set the Obsevable subject for this class
     * @param subject to be observed
     */
    public void setSubject(PhaseTwoSubject subject){
        this.subject = (Scoreboard) subject;
    }

    /**
     * Update the subjects global scores and save the global scores
     */
    public void update() {
        globalScores = subject.getGlobalScores();
        saveToFile(fileName);
    }

    @SuppressWarnings("unchecked")
    /**
     * Load globalScores from fileName.
     */
    public void loadFromFile() {
        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                globalScores = (ArrayList<Score>)input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        }
        catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save globalScores to fileName.
     * @param fileName where scores are saved
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(globalScores);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
