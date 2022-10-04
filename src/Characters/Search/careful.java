package Characters.Search;

import Board.Room;
import Characters.Friendlies.Adventurer;
import Treasure.Treasure;
import Utilities.Dice;

import java.util.Random;

public class careful extends searchStyle{
    @Override
    public Treasure search(Adventurer self, Room currentRoom) {
        Treasure item;

        // Roll for treasure.
        if( Dice.rollD6(2) >= 7 ){

            // Check to confirm adventurer doesn't already own one
            // of the discovered item.
            for( Treasure select : self.getInventory() ){
                if( select == currentRoom.peekTreasure() ){
                    // Too bad!  You already have this item
                }
            }

            // Get item in the room and set the room's treasure
            // availability to false.
            item = currentRoom.takeTreasure();

            // Careful search trap dodge check.
            if(item.getClass().getSimpleName().equals("Trap")){
                Random r = new Random();
                if(r.nextBoolean()){
                    // Lucky roll!  Trap dodged >:)
                    return null;
                }
                // Ouch!
            }
            // success roll
            return item;
        }
        // failed roll
        return null;
    }
}
