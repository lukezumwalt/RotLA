package Characters.Friendlies;

import java.util.ArrayList;

import Board.Room;
import Treasure.*;

public class Adventurer {

    // PROTECTED ATTRIBUTES
    protected Room currentRoom;
    protected final String entityType = "adventurer";
    protected String sign;
    protected String name;
    protected int health;
    protected static boolean alive;

    // PRIVATE ATTRIBUTES
    /*
     * code example of Encapsulation
     * treasureCount is private and
     * can only be accessed via getter
     * method
     */
    private ArrayList<Treasure> inventory;

    // PUBLIC METHODS
    public void takeDamage() {
        health--;
    }

    public void collectTreasure(Room r) {
        inventory.add(r.takeTreasure());
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

    public boolean getAlive() {
        return alive;
    }
}
