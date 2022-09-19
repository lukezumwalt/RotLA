package Board;

import Characters.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Room {

    int[][][] Room = new int[5][3][3];
    public static Room[][][] Facility = new Room[5][3][3];
    public static Room Entrance = Facility[0][1][1];

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
    private static ArrayList<Entity> occupantAdventurers;
    private static ArrayList<Entity> occupantCreatures;

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

    public static void occupyAdventurer(Entity me) {
        occupantAdventurers.add(me);
    }

    public static void occupyCreature(Entity me) {
        occupantCreatures.add(me);
    }
}
