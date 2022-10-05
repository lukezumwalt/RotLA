package Board;

import Characters.Friendlies.Adventurer;
import Characters.Subject;

public abstract class Observer {
    Subject subject;

    public abstract void updateAdventurerStatus(Adventurer self, String eventID );
    public abstract void updateCreatureStatus(String name, Room local);
}
