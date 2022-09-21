package Characters.Enemies;

import Board.Room;
import Characters.Enemies.Creature;
import Characters.Entity;
import Utilities.CircularLinkedList;

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
    }

    // PUBLIC METHODS
    @Override
    public boolean fight(Entity target) {
        // ! @TODO: fight with dice method return true if
        // creature dice roll > adventurer dice roll
        return false;
    }

    @Override
    public void move() {
        // ! @TODO: Orbiter moves to any random room on outer
        // edge a.k.a. not the center room of given level.
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
