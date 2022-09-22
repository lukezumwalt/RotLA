package Board;

import Characters.Enemies.Creature;
import Characters.Entity;
import Characters.Friendlies.Adventurer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Game.Engine.coordinateToKey;

public class Room {

    // CONSTRUCTORS
    public Room() {
        occupantAdventurers = new ArrayList<Adventurer>();
        occupantCreatures = new ArrayList<Creature>();
    }

    public Room(int level, int x, int y) {
        coordinates = new int[] { level, x, y };
        occupantAdventurers = new ArrayList<Adventurer>();
        occupantCreatures = new ArrayList<Creature>();
    }

    // PROTECTED ATTRIBUTES
    protected int[] coordinates;
    protected ArrayList<Adventurer> occupantAdventurers;
    protected ArrayList<Creature> occupantCreatures;
    // PRIVATE ATTRIBUTES
    private boolean treasureAvailable = true;

    // PUBLIC METHODS
    public int[] getCoordinates() {
        return coordinates;
    }

    public boolean checkIfTreasure() {
        return treasureAvailable;
    }

    public void takeTreasure() {
        treasureAvailable = false;
    }

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

    // The following methods grabs the occupant
    // entities signs (BRST:OSB) for rendering
    public String renderOccupantAdventurers() {
        String retVal = "";
        for (Adventurer a : this.occupantAdventurers) {
            retVal += a.getSign();
        }
        return retVal;
    }

    public String renderOccupantCreatures() {
        String retVal = "";
        for (Creature a : this.occupantCreatures) {
            retVal += a.getSign();
        }
        return retVal;
    }

    // removes occupant entites from ArrayList and error-checks
    public void leaveRoom(Entity e) {
        switch (e.getEntityType()) {
            default -> throw new IllegalStateException("Unexpected value: " + e.getEntityType());
            case "adventurer" -> this.occupantAdventurers.remove(e);
            case "creature" -> this.occupantCreatures.remove(e);
        }
    }

    // These methods add entities to corresponding
    // occupant arraylist -- needed for rendering
    // and room occupancy tracking
    public void occupyAdventurer(Adventurer me) {
        this.occupantAdventurers.add(me);
    }

    public void occupyCreature(Creature me) {
        this.occupantCreatures.add(me);
    }

    // NEIGHBORHOOD
    // We created a neighborhood schema to reference
    // for legal moves to be made
    // since we used a hash map, we hardcoded a neighborhood
    // for each key (string) to match for movement

    // inspectNeighbors will grab coordinates to get the key
    // that will be cross-referenced to neighborhood to get
    // neighbors
    public static String[] inspectNeighbors(Room current) {
        int[] position = current.getCoordinates();
        String key = coordinateToKey(position[0], position[1], position[2]);
        return Neighborhood.get(key);
    }

    public static Map<String, String[]> Neighborhood = new HashMap<>();

    public static void mapNeighborhood() {
        // Spawn Room
        // ! @TODO: Declare this room illegal to ENTER ...somehow...
        Neighborhood.put("011", new String[] { "111" });
        // First Floor
        Neighborhood.put("100", new String[] { "101", "110" });
        Neighborhood.put("101", new String[] { "100", "102", "111" });
        Neighborhood.put("102", new String[] { "101", "112" });
        Neighborhood.put("110", new String[] { "100", "111", "120" });
        Neighborhood.put("111", new String[] { "101", "110", "112", "121", "211" });
        Neighborhood.put("112", new String[] { "102", "111", "122" });
        Neighborhood.put("120", new String[] { "110", "121" });
        Neighborhood.put("121", new String[] { "111", "120", "122" });
        Neighborhood.put("122", new String[] { "112", "121" });
        // Second Floor
        Neighborhood.put("200", new String[] { "201", "210" });
        Neighborhood.put("201", new String[] { "200", "202", "211" });
        Neighborhood.put("202", new String[] { "201", "212" });
        Neighborhood.put("210", new String[] { "200", "211", "220" });
        Neighborhood.put("211", new String[] { "111", "201", "210", "212", "221", "311" });
        Neighborhood.put("212", new String[] { "202", "211", "222" });
        Neighborhood.put("220", new String[] { "210", "221" });
        Neighborhood.put("221", new String[] { "211", "220", "222" });
        Neighborhood.put("222", new String[] { "212", "221" });
        // Third Floor
        Neighborhood.put("300", new String[] { "301", "310" });
        Neighborhood.put("301", new String[] { "300", "302", "311" });
        Neighborhood.put("302", new String[] { "301", "312" });
        Neighborhood.put("310", new String[] { "300", "311", "320" });
        Neighborhood.put("311", new String[] { "211", "301", "310", "312", "321", "411" });
        Neighborhood.put("312", new String[] { "302", "311", "322" });
        Neighborhood.put("320", new String[] { "310", "321" });
        Neighborhood.put("321", new String[] { "311", "320", "322" });
        Neighborhood.put("322", new String[] { "312", "321" });
        // Fourth Floor
        Neighborhood.put("400", new String[] { "401", "410" });
        Neighborhood.put("401", new String[] { "400", "402", "411" });
        Neighborhood.put("402", new String[] { "401", "412" });
        Neighborhood.put("410", new String[] { "400", "411", "420" });
        Neighborhood.put("411", new String[] { "311", "401", "410", "412", "421" });
        Neighborhood.put("412", new String[] { "402", "411", "422" });
        Neighborhood.put("420", new String[] { "410", "421" });
        Neighborhood.put("421", new String[] { "411", "420", "422" });
        Neighborhood.put("422", new String[] { "412", "421" });
    }
}
