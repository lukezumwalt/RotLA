package Characters.Enemies;

import Board.Room;
import Characters.Entity;
import Game.Engine;

public class Creature {

    // PROTECTED ATTRIBUTES
    protected Room currentRoom;
    protected final String entityType = "creature";
    protected String sign;
    protected String name;
    protected static boolean alive;

    // PUBLIC METHODS
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
