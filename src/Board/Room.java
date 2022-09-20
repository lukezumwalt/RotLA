package Board;

import Characters.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Room {

    // CONSTRUCTORS
    public Room() {

    }

    public Room(int floor, int x, int y) {
        coordinates = new int[] { floor, x, y };
    }

    // POSITION
    protected int[] coordinates;
    {
        coordinates = new int[3];
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
    private static ArrayList<Entity> occupantAdventurers = new ArrayList<Entity>();
    private static ArrayList<Entity> occupantCreatures = new ArrayList<Entity>();

    public ArrayList<Entity> getOccupantAdventurers() {
        if (occupantAdventurers == null) {
            return null;
        }
        return occupantAdventurers;
    }

    public ArrayList<Entity> getOccupantCreatures() {
        if (occupantCreatures == null) {
            return null;
        }
        return occupantCreatures;
    }

    public void leaveRoom(Entity e) {
        switch (e.getEntityType()) {
            default -> throw new IllegalStateException("Unexpected value: " + e.getEntityType());
            case "adventurer" -> occupantAdventurers.remove(e);
            case "creature" -> occupantCreatures.remove(e);
        }
    }

    public void occupyAdventurer(Entity me) {
        occupantAdventurers.add(me);
    }

    public void occupyCreature(Entity me) {
        occupantCreatures.add(me);
    }
}
