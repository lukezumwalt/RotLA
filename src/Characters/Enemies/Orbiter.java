package Characters.Enemies;

import Board.Room;
import Characters.Entity;

import java.util.Random;

import static Game.Engine.*;
import static Utilities.Dice.rollD6;

/*
 * code example of Polymorphism
 * Orbiter, Seeker, and
 * Blinker are all extensions of Creature
 * 
 * code example of Abstraction
 * Orbiter extendes Creature implements Entity
 * Abstraction occurring through all entity classes(creatures
 * and adventurers) extend superclasses (Creature or Adventurer)
 */
public class Orbiter extends Creature implements Entity {

    // CONSTRUCTORS
    public Orbiter() {
        sign = "O";
        name = "orbiter";
        alive = true;

        // Randomly choose clockwise/counter-clockwise
        Random r = new Random();
        if(r.nextBoolean()){
            clockwiseFlag = true;
        }
        else{
            clockwiseFlag = false;
        }
    }
    protected boolean clockwiseFlag;

    // PUBLIC METHODS
    @Override
    public boolean fight(Entity target) {
        int myRoll = rollD6(2);
        int targetRoll = rollD6(2);

        if (myRoll > targetRoll) {
            // Victory
            return true;
            // target.die();
        } else if (myRoll == targetRoll) {
            // Tie
            return false;
        } else {
            // Loss
            alive = false;
        }
        return false;
    }

    @Override
    public void move() {
        // ! @TODO: Orbiter moves to any random room on outer
        // edge a.k.a. not the center room of given level.

        // Get indices from current room
        int floor = checkRoom().getCoordinates()[0];
        int x = checkRoom().getCoordinates()[1];
        int y = checkRoom().getCoordinates()[2];

        // Conduct circular arithmetic to rotate around room.
        if(this.clockwiseFlag){
            // Clockwise rotation scheme
            if( x > 0 && y == 0 ){
                // west wall condition
                x--;
            } else if ( x == 0 && y < 2 ) {
                // north wall condition
                y++;
            } else if ( x < 2 && y == 2 ) {
                // east wall condition
                x++;
            } else if ( x == 2 && y > 0 ) {
                // south wall condition
                y--;
            }
        }
        else{
            // Counter-clockwise rotation scheme
            if( x < 0 && y == 0 ){
                // west wall condition
                x++;
            } else if ( x == 0 && y > 0 ) {
                // north wall condition
                y--;
            } else if ( x > 0 && y == 2 ) {
                // east wall condition
                x--;
            } else if ( x == 2 && y < 2 ) {
                // south wall condition
                y++;
            }

        }

        this.currentRoom.leaveRoom(this);
        this.setCurrentRoom(Facility.get(coordinateToKey(floor,x,y)));
        Facility.get(coordinateToKey(floor,x,y)).occupyCreature(this);
    }

    @Override
    public Room checkRoom() {
        return currentRoom;
    }

    @Override
    public boolean rollForTreasure() {
        // creatures do not get treasure so always return false
        return false;
    }

    @Override
    public String getEntityType() {
        return entityType;
    }

    public String getName() {
        return name;
    }
}
