package Board;

import Characters.Enemies.Creature;
import Characters.Entity;
import Characters.Friendlies.Adventurer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Room {

    // CONSTRUCTORS
    public Room() {
        occupantAdventurers = new ArrayList<Adventurer>();
        occupantCreatures = new ArrayList<Creature>();
    }

    public Room(int level, int x, int y) {
        coordinates = new int[] { level, x, y };
    }

    // POSITION
    protected int[] coordinates;
    public int[] getCoordinates(){
        return coordinates;
    }

    // TREASURE
    private boolean treasureAvailable = true;

    boolean checkIfTreasure() {
        return treasureAvailable;
    }

    public void takeTreasure() {
        treasureAvailable = false;
    }

    // OCCUPANCY
//    private static ArrayList<Entity> occupantAdventurers = new ArrayList<Entity>();
    private ArrayList<Adventurer> occupantAdventurers;
    private ArrayList<Creature> occupantCreatures;

    public ArrayList<Adventurer> getOccupantAdventurers() {
        if (occupantAdventurers == null) {
            return null;
        }
        return occupantAdventurers;
    }

    public ArrayList<Creature> getOccupantCreatures() {
        if (occupantCreatures == null) {
            return null;
        }
        return occupantCreatures;
    }

    public String renderOccupantAdventurers(){
        String retVal = "";
        for( Adventurer a : this.occupantAdventurers ){
            retVal += a.getSign();
        }
        return retVal;
    }

    public String renderOccupantCreatures(){
        String retVal = "";
        for( Creature a : this.occupantCreatures ){
            retVal += a.getSign();
        }
        return retVal;
    }

    public void leaveRoom(Entity e) {
        switch (e.getEntityType()) {
            default -> throw new IllegalStateException("Unexpected value: " + e.getEntityType());
            case "adventurer" -> this.occupantAdventurers.remove(e);
            case "creature" -> this.occupantCreatures.remove(e);
        }
    }

    public void occupyAdventurer(Adventurer me) {
        this.occupantAdventurers.add(me);
    }

    public void occupyCreature(Creature me) {
        this.occupantCreatures.add(me);
    }
}
