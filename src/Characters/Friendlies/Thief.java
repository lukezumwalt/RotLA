package Characters.Friendlies;

import Board.Room;
import Characters.Entity;

import java.util.Random;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;
import static Utilities.Dice.rollD6;

/*
 * code example of Inheritance
 * all of the adventurer subclasses
 * inherit the superclass Adventurer
 */
public class Thief extends Adventurer implements Entity {

    // CONSTRUCTORS
    public Thief() {
        sign = "T";
        name = "Thief";
        health = 3;
        alive = true;
    }

    // PROTECTED ATTRIBUTES
    protected static final int treasureRollMod = 1;

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
        if (addresses.length <= 1) {
            choice = 0;
        } else {
            Random r = new Random();
            choice = r.nextInt(0, addresses.length);
        }
        Room newRoom = Facility.get(addresses[choice]);

        // finally:
        this.currentRoom.leaveRoom(this);
        this.setCurrentRoom(newRoom);
        newRoom.occupyAdventurer(this);
    }

    @Override
    public Room checkRoom() {
        return currentRoom;
    }

    @Override
    public boolean rollForTreasure() {
        if ((rollD6(2) + treasureRollMod) >= 10) {
            return true;
        }
        return false;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getName() {
        return name;
    }
}
