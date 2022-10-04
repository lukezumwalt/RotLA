package Characters.Search;

import Board.Room;
import Characters.Friendlies.Adventurer;
import Treasure.Treasure;

public abstract class searchStyle {

    // PUBLIC METHODS
    public abstract Treasure search(Adventurer self, Room currentRoom);
}
