package Characters.Action.Combat;

import java.util.ArrayList;

import Board.Observer;
import Characters.Entity;
import Characters.Friendlies.Adventurer;

public interface combatStyle {

    // PUBLIC METHODS
    // Returns damage dealt to self
    // If > 0, self won
    // If == 0, self win or tie
    public abstract int fight(Entity self, Entity target);

    public abstract void unregisterObserver(Observer o);

    public abstract void registerObserver(Observer o);

    public abstract void notifyObservers(Adventurer subject, String eventID);

}
