package Characters;

import Board.Logger;
import Board.Observer;
import Board.Room;

// https://www.geeksforgeeks.org/observer-pattern-set-2-implementation/
public interface Subject {
    public void registerObserver(Observer o);
    public void unregisterObserver(Observer o);
    public void notifyObservers(String eventID);
}
