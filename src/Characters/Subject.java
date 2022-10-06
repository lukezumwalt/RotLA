package Characters;

import Board.Observer;

// https://www.geeksforgeeks.org/observer-pattern-set-2-implementation/
public interface Subject {
    void registerObserver(Observer o);
    void unregisterObserver(Observer o);
    void notifyObservers(String eventID);
}
