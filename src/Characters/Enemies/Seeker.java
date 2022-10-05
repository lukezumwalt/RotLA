package Characters.Enemies;

import Board.Room;
import Characters.Action.Combat.monstrous;
import Characters.Entity;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;

/*
 * code example of Polymorphism
 * Orbiter, Seeker, and
 * Blinker are all extensions of Creature
 */
public class Seeker extends Creature implements Entity {

    // CONSTRUCTORS
    public Seeker() {
        sign = "S";
        name = "seeker";
        alive = true;
        combatStyle = new monstrous();
    }

    // PROTECTED ATTRIBUTES
    protected final String entityType = "creature";

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
}
