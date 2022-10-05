package Characters;

import Board.Observer;

// https://www.geeksforgeeks.org/observer-pattern-set-2-implementation/
public interface Subject {
    public void registerObserver(Observer o);
    public void unregisterObserver(Observer o);
    public void notifyObservers();
}
