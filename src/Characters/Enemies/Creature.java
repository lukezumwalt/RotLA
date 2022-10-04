package Characters.Enemies;

import Board.Room;
import Characters.Combat.combatStyle;
import Characters.Entity;
import Game.Engine;

public abstract class Creature {

    // PROTECTED ATTRIBUTES
    protected Room currentRoom;
    protected String sign;
    protected String name;
    protected static boolean alive;
    protected Characters.Combat.combatStyle combatStyle;

    // PUBLIC METHODS
    public abstract String getSign();

    public abstract void setCurrentRoom(Room newRoom);

    public abstract boolean getAlive();
}
