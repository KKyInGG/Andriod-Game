package fall2018.csc2017.gamecentre;


import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A UserManager to manage users.
 */

public class UserManager implements Serializable, PhaseTwoSubject{

    /**
     * A list that store all users.
     */
    private ArrayList<User> allUsers;


    /**
     * The list of observers of this class
     */
    private static List<PhaseTwoObserver> observers;

    /**
     * Construct a new UserManager with context and load it from "allUsers.ser" if needed.
     */
    public UserManager() {
        this.allUsers = new ArrayList<>();
        observers = new ArrayList<>();
    }

    /**
     * Return the list of users
     * @return ArrayList<User>
     */
    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    /**
     * Set this UserManager's user list
     * @param allUsers
     */
    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    /**
     * Return the index of the user in UserManger iff there exists a user with the account name.
     * Otherwise, return -1 iff the user doesn't exist.
     *
     * @param account username
     * @return the index
     */
    public int hasAccount(String account){
        if (allUsers.size() != 0){
            for (int i = 0; i < allUsers.size(); i++) {
                if (allUsers.get(i).getAccount().equals(account)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Add an account in the UserManager iff user fills in the valid signUp information
     * (account name and password) and write it in the file "allUsers.ser".
     *
     * @param account the username that user provides.
     * @param password the password that user provides.
     * @throws DuplicateException if a same username has already exists
     * @throws AccountsException if user doesn't provide username
     * @throws NoPassWordException if user doesn't provide password
     */
    public void signUp(String account, String password) throws DuplicateException, AccountsException,
            NoPassWordException {
        if (hasAccount(account) != -1) {
            throw new DuplicateException();
        }
        else if (account.length() == 0) {
            throw new AccountsException();
        }
        else if (password.length() == 0){
            throw new NoPassWordException();
        }
        allUsers.add(new User(account, password));
        notifyObservers();
    }

    /**
     * Return the user object iff the password that the user provides matches the password
     * of the account which user have.
     * @param account the username
     * @param password the password that user enter
     * @return the User
     * @throws AccountsException if the password is incorrect
     */
    public User signIn (String account, String password) throws AccountsException{
        int index = hasAccount(account);
        if (index == -1) {
            throw new AccountsException();
        }
        else if (!(allUsers.get(index).getPassword()).equals(password)) {
            throw new AuthenticatorException();
        }
        else{
            return allUsers.get(index);
        }
    }

    public static List<PhaseTwoObserver> getObservers() {
        return observers;
    }

    public void register(PhaseTwoObserver obj){
        if(!observers.contains(obj))
        {observers.add(obj);
            obj.setSubject(this);}
    }


    //method to notify observers of change
    public void notifyObservers(){
        for (PhaseTwoObserver obj : observers) {
            obj.update();
        }
    }
}
