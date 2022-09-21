package Characters.Enemies;

import Board.Room;
import Characters.Enemies.Creature;
import Characters.Entity;

import java.util.Random;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;

public class Seeker extends Creature implements Entity {

    public Seeker(){
        sign = "S";
        name = "seeker";
    }

    @Override
    public void move() {
        // check room to return valid moves
        String[] addresses = inspectNeighbors(this.currentRoom);
        String choice = "";
        // poll rooms for adventurers
        for(String poll: addresses){
            if(Facility.get(poll).getOccupantAdventurers().size() > 0){
                choice = poll;
                break;
            }
        }
        // move to first room returned with adventurer
        if("".equals(choice)){
            // If no adventurers found nearby, don't move.
        }
        else{
            Room newRoom = Facility.get(choice);
            // finally:
            this.currentRoom.leaveRoom( this );
            this.setCurrentRoom( newRoom );
            newRoom.occupyCreature(this);
        }

    }

    @Override
    public boolean fight(Entity target) {
        return false;
    }

    @Override
    public String getEntityType() {
        return entityType;
    }

    @Override
    public Room checkRoom() {
        return currentRoom;
    }

    @Override
    public boolean rollForTreasure() {
        return false;
    }

    public String getName(){ return name; }
}
