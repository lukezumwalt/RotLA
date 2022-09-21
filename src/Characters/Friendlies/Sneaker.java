package Characters.Friendlies;

import Board.Room;
import Characters.Entity;

import java.util.Random;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;
import static Utilities.Dice.rollD6;

public class Sneaker extends Adventurer implements Entity {

    public Sneaker(){
        sign = "S";
        name = "Sneaker";
    }
    @Override
    public void move() {
        // check room to return valid moves
        String[] addresses = inspectNeighbors(this.currentRoom);
        // randomly select a valid move from that list
        int choice;
        if( addresses.length <= 1 ) {
            choice = 0;
        }
        else {
            Random r = new Random();
            choice = r.nextInt(0,addresses.length);
        }
        Room newRoom = Facility.get(addresses[choice]);

        // finally:
        this.currentRoom.leaveRoom( this );
        this.setCurrentRoom( newRoom );
        newRoom.occupyAdventurer(this);
    }

    @Override
    public boolean fight(Entity target) {
        return false;
    }

    public boolean rollForTreasure() {
        if( rollD6(2) >= 10 ){
            return true;
        }
        return false;
    }
    @Override
    public Room checkRoom(){ return currentRoom; }
    public String getEntityType(){ return entityType; }
    public String getName(){ return name; }
}
