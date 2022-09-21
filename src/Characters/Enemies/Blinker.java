package Characters.Enemies;

import Board.Room;
import Characters.Enemies.Creature;
import Characters.Entity;

import java.util.Random;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;
import static Game.Engine.coordinateToKey;
import static Utilities.Dice.rollD6;

/*
 * code example of Polymorphism
 * Orbiter, Seeker, and
 * Blinker are all extensions of Creature
 */
public class Blinker extends Creature implements Entity {

    // CONSTRUCTORS
    public Blinker() {
        sign = "B";
        name = "blinker";
        alive = true;
    }

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
        // Blinker moves to any random room in the Facility.
        Random r = new Random();
        int floor = r.nextInt(3) + 1;
        int x = r.nextInt(2);
        int y = r.nextInt(2);
        Room newRoom = Facility.get(coordinateToKey(floor, x, y));

        // Update knowledge of position.
        this.currentRoom.leaveRoom(this);
        this.setCurrentRoom(newRoom);
        newRoom.occupyCreature(this);
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
