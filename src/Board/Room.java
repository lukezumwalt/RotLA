package Board;

import Characters.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Room {

    // CONSTRUCTORS
    public Room() {

    }
    public Room(int floor, int x, int y){
        coordinates = new int[]{floor, x, y};
    }

    // POSITION
    protected int[] coordinates;
    {
        coordinates = new int[3];
    }

    // TREASURE
    private boolean treasureAvailable = true;
    boolean checkIfTreasure(){ return treasureAvailable; }
    public void takeTreasure(){ treasureAvailable = false; }

    // OCCUPANCY
    private ArrayList<Entity> occupantAdventurers;
    private ArrayList<Entity> occupantCreatures;
    public ArrayList<Entity> getOccupantAdventurers() {
        return occupantAdventurers;
    }
    public ArrayList<Entity> getOccupantCreatures(){
        return occupantCreatures;
    }

    public void leaveRoom( Entity e ){
        switch (e.getEntityType()) {
            default -> throw new IllegalStateException("Unexpected value: " + e.getEntityType());
            case "adventurer" -> occupantAdventurers.remove(e);
            case "creature" -> occupantCreatures.remove(e);
        }
    }

    public void occupyAdventurer( Entity me ){
        occupantAdventurers.add( me );
    }
    public void occupyCreature( Entity me ){
        occupantCreatures.add( me );
    }
}
