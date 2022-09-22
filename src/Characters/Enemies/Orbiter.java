package Characters.Enemies;

import Board.Room;
import Characters.Entity;
import Utilities.CircularLinkedList;

import static Game.Engine.Facility;
import static Game.Engine.OrbiterDungeonMap;
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
        currentRoomNode = null;
    }

    private CircularLinkedList.Node currentRoomNode;

    public CircularLinkedList.Node getCurrentRoomNode(){
        return this.currentRoomNode;
    }

    public void setCurrentRoomNode(CircularLinkedList.Node currentRoomNode) {
        this.currentRoomNode = currentRoomNode;
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
        // ! @TODO: Orbiter moves to any random room on outer
        // edge a.k.a. not the center room of given level.

        // Get floor index from first coordinate of current room.
        int floorIndex = checkRoom().getCoordinates()[0] - 1;

        // Use floor index to check orbiter map for appropriate circular list.
        CircularLinkedList floorList = OrbiterDungeonMap.get(floorIndex);

        // Find next room node based on current room node within appropriate floor list.
        CircularLinkedList.Node nextRoom = floorList.getNext(this.currentRoomNode);

        // Finally, leave current room and enter new room, while updating current room node.
        this.currentRoom.leaveRoom(this);
        this.setCurrentRoom(Facility.get(nextRoom.getCoordinateKey()));
        this.setCurrentRoomNode(nextRoom);
        Facility.get(nextRoom.getCoordinateKey()).occupyCreature(this);
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
