package Characters.Action.Search;

import Board.Room;
import Characters.Friendlies.Adventurer;
import Treasure.Treasure;

// Strategy pattern for implementing unique search algorithm objects.
public abstract class searchStyle {

    // PUBLIC METHODS
    public abstract Treasure search(Adventurer self, Room currentRoom);
}
