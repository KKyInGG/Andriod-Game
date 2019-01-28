package fall2018.csc2017.gamecentre;

//Citation :https://www.journaldev.com/1739/observer-design-pattern-in-java
public interface PhaseTwoSubject {

    /**
     * Register the PhaseTwoObserver object to observe
     * @param obj to register
     */
     void register(PhaseTwoObserver obj);

    /**
     * A method to notifyObservers to change
     */
    void notifyObservers();

}
