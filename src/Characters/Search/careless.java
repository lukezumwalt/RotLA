package Characters.Search;

import Board.Room;
import Characters.Friendlies.Adventurer;
import Treasure.Treasure;
import Utilities.Dice;

public class careless extends searchStyle{
    @Override
    public Treasure search(Adventurer self, Room currentRoom) {
        Treasure item;

        // Roll for treasure.
        if( Dice.rollD6(2) >= 10 ){
            // Get item in the room and set the room's treasure
            // availability to false.
            item = currentRoom.takeTreasure();
            // success roll
            return item;
        }
        // failed roll
        return null;
    }
}
