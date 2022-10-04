package Characters.Search;

import Board.Room;
import Characters.Friendlies.Adventurer;
import Treasure.Treasure;
import Utilities.Dice;

import java.util.Random;

public class quick extends searchStyle{
    @Override
    public Treasure search(Adventurer self, Room currentRoom) {
        Treasure item;

        // Quick search speed filter.
        Random skip = new Random();
        if( 0 == skip.nextInt(2) ){
            // Too fast, missed your chance!
            return null;
        }

        // Roll for treasure.
        if( Dice.rollD6(2) >= 9 ){
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
