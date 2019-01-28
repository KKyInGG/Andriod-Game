package fall2018.csc2017.gamecentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A User.
 */

public class User implements Serializable {
    private String account;
    private String password;
    private List<String> gameFile;


    public User(String account, String password) {
        this.account = account;
        this.password = password;
        this.gameFile = generateGameFiles();
    }

    private List<String> generateGameFiles(){
        List<String> gameFiles = new ArrayList<>();
        gameFiles.add(this.account + "Sudoku.ser");
        gameFiles.add(this.account + "SlidingTiles.ser");
        gameFiles.add(this.account + "Memory.ser");
        return gameFiles;
    }
    /**
     * Returns the file containing this user's data.
     */
    public String getSudokuGameFile() {
        return gameFile.get(0);
    }


    public String getSlidingTilesGameFile(){return gameFile.get(1);}

    public String getMemoryGameFile(){return gameFile.get(2);}

    /**
     * Returns the string identifier for this user.
     */
    public String getAccount() {
        return account;
    }

    /**
     * Returns this user's password.
     */
    public String getPassword() { return password; }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User o = (User) obj;
            return(o.getAccount().equals(this.account));
        }
        return false;
    }
}
