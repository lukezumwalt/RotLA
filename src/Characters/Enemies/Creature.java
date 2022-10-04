package Characters.Enemies;

import Board.Room;

public abstract class Creature {

    // PROTECTED ATTRIBUTES
    protected Room currentRoom;
    protected String sign;
    protected String name;
    protected static boolean alive;
    protected Characters.Action.Combat.combatStyle combatStyle;

    // PUBLIC METHODS
    public abstract String getSign();

    public abstract void setCurrentRoom(Room newRoom);

    public abstract boolean getAlive();
}
