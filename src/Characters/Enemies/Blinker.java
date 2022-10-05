package Characters.Enemies;

import Board.Observer;
import Board.Room;
import Characters.Action.Combat.monstrous;
import Characters.Entity;
import Characters.Subject;

import java.util.Random;

import static Game.Engine.Facility;
import static Game.Engine.coordinateToKey;

/*
 * code example of Polymorphism
 * Orbiter, Seeker, and
 * Blinker are all extensions of Creature
 */
public class Blinker extends Creature implements Entity, Subject {

    // CONSTRUCTORS
    public Blinker() {
        entityType = "creature";
        sign = "B";
        name = "blinker";
        alive = true;
        combatStyle = new monstrous();
    }

    // PUBLIC METHODS
    @Override
    public boolean fight(Entity target) {

        int fightVal = combatStyle.fight(this,target);

        if (fightVal > 0) {
            // Victory
            return true;
        } else if (fightVal < 0) {
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
    public String getEntityType() {
        return entityType;
    }

    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

    public boolean getAlive() {
        return alive;
    }

    @Override
    public void registerObserver(Observer o) {

    }

    @Override
    public void unregisterObserver(Observer o) {

    }

    @Override
    public void notifyObservers(String eventID) {

    }
}
