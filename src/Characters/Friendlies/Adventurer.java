package Characters.Friendlies;

import Board.Room;

public class Adventurer {

    // PROTECTED ATTRIBUTES
    protected Room currentRoom;
    protected final String entityType = "adventurer";
    protected String sign;
    protected String name;
    protected int health;

    // PRIVATE METHODS
    /*
     * code example of Encapsulation
     * treasureCount is private and
     * can only be accessed via getter
     * method
     */
    private int treasureCount;

    // PUBLIC METHODS
    public void takeDamage() {
        health--;
    }

    public void collectTreasure(Room r) {
        r.takeTreasure();
        treasureCount++;
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

    // ! Getter Suite
    public String getSign() {
        return sign;
    }

    public int getHealth() {
        return health;
    }

    /*
     * code example of Encapsulation
     * getter method for private attribute
     */
    public int getTreasureCount() {
        return treasureCount;
    }
}
