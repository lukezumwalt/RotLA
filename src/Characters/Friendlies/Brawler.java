package Characters.Friendlies;

import Board.Room;
import Characters.Entity;
import Utilities.Dice;

import java.lang.annotation.Documented;

public class Brawler extends Adventurer implements Entity {

    static final int combatBonus = 2;

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
//        currentRoom.checkAdjacent();
        // finally:
        checkRoom().leaveRoom( this );
        checkRoom().occupyAdventurer( this );
    }

    @Override
    public Room checkRoom(){ return currentRoom; }
    public String getEntityType(){ return entityType; }
}
