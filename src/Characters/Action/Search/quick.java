package Characters.Action.Search;

import Board.Room;
import Characters.Friendlies.Adventurer;
import Treasure.Treasure;
import Utilities.Dice;

import java.util.Random;

public class quick extends searchStyle{
    @Override
    public Treasure search(Adventurer self, Room currentRoom) {
        Treasure item;

        // Roll for treasure.
        if(currentRoom.checkIfTreasure()) {
            if (Dice.rollD6(2) >= 6) {
                // Check to confirm adventurer doesn't already own one
                // of the discovered item.
                for (Treasure select : self.getInventory()) {
                    if (select.getClass().getSimpleName().equals(currentRoom.peekTreasure().getClass().getSimpleName())) {
                        // Too bad!  You already have this item
                        System.out.println("You already possess this item, move on!");
                        return null;
                    }
                }

                // Get item in the room and set the room's treasure
                // availability to false.
                item = currentRoom.takeTreasure();
                return item;
            }
            // failed roll
            return null;
        }
        else{
            System.out.println("No treasure in this room, move on!");
            return null;
        }
    }
}
