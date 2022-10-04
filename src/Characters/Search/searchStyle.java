package Characters.Search;

import Board.Room;
import Characters.Friendlies.Adventurer;
import Treasure.Treasure;

public abstract class searchStyle {

    // PUBLIC METHODS
    public abstract Treasure Search(Adventurer self, Room currentRoom);
}
