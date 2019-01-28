package fall2018.csc2017.gamecentre;

//Citation: https://www.journaldev.com/1739/observer-design-pattern-in-java
public interface PhaseTwoObserver {
    /**
     * Update accordingly after subject calls notifyObservers()
     */
    void update();

    /**
     * Set the subject to be obsevred
     * @param subject to be observed
     */
    void setSubject(PhaseTwoSubject subject);
}
