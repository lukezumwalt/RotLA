package Characters.Enemies;

import Board.Observer;
import Board.Room;
import Characters.Action.Combat.monstrous;
import Characters.Entity;
import Characters.Subject;

import java.util.ArrayList;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;

/*
 * code example of Polymorphism
 * Orbiter, Seeker, and
 * Blinker are all extensions of Creature
 */
public class Seeker extends Creature implements Entity, Subject {

    // CONSTRUCTORS
    public Seeker() {
        entityType = "creature";
        sign = "S";
        name = "seeker";
        alive = true;
        combatStyle = new monstrous();
        observerList = new ArrayList<>();
    }

    // PUBLIC METHODS
    @Override
    public boolean fight(Entity target) {

        int fightVal = combatStyle.fight(this,target);

        if (fightVal > 0) {
            // Victory
            notifyObservers("wonCombat");
            return true;
        } else if (fightVal < 0) {
            // Tie
            return false;
        } else {
            // Loss
            notifyObservers("lostCombat");
            notifyObservers("died");
            alive = false;
        }
        return false;
    }

    @Override
    public void move() {
        // check room to return valid moves
        String[] addresses = inspectNeighbors(this.currentRoom);
        String choice = "";
        // poll rooms for adventurers
        for (String poll : addresses) {
            if (Facility.get(poll).getOccupantAdventurers().size() > 0) {
                choice = poll;
                break;
            }
        }
        // move to first room returned with adventurer
        if ("".equals(choice)) {
            // If no adventurers found nearby, don't move.
        } else {
            Room newRoom = Facility.get(choice);
            // finally:
            this.currentRoom.leaveRoom(this);
            this.setCurrentRoom(newRoom);
            newRoom.occupyCreature(this);
        }

        notifyObservers("roomEntered");
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
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers(String eventID) {
        for (Observer o : observerList) {
            o.updateCreatureStatus(this, eventID);
        }
    }
}
