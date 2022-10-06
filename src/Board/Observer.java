package Board;

import Characters.Enemies.Creature;
import Characters.Subject;

public abstract class Observer {
    Subject subject;

    public abstract void updateAdventurerStatus(Subject subject, String eventID);

    public abstract void updateCreatureStatus(Creature self, String eventID);
}
