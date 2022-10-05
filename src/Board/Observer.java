package Board;

import Characters.Enemies.Creature;
import Characters.Friendlies.Adventurer;
import Characters.Subject;

public abstract class Observer {
    Subject subject;

    public abstract void updateAdventurerStatus(Adventurer self, String eventID );
    public abstract void updateCreatureStatus(Creature self, String eventID);
}
