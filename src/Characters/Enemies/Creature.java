package Characters.Enemies;

import Board.Room;

public class Creature {

    // PROTECTED ATTRIBUTES
    protected Room currentRoom;
    protected final String entityType = "creature";
    protected String sign;
    protected String name;

    // PUBLIC METHODS
    public String getSign() {
        return sign;
    }

    public void die() {

    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }
}
