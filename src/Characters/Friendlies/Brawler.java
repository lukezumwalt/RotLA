package Characters.Friendlies;

import Board.Room;
import Characters.Entity;
import Utilities.Dice;

import java.lang.annotation.Documented;
import java.util.Arrays;
import java.util.Random;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;

public class Brawler extends Adventurer implements Entity {

    static final int combatBonus = 2;

    public Brawler(){
        sign = "B";
        name = "Brawler";
    }

    @Override
    public boolean fight(Entity target){
        int myRoll = Dice.rollD6(2) + combatBonus;
        int targetRoll = Dice.rollD6(2);

        if( myRoll > targetRoll ) {
            // Victory
            return true;
//            target.die();
        }
        else if ( myRoll == targetRoll ) {
            // Tie
            return false;
        }
        else {
            // Loss
            takeDamage();
        }
        return false;
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
    public Room checkRoom(){ return currentRoom; }
    public String getEntityType(){ return entityType; }
    public String getName(){ return name; }
}
