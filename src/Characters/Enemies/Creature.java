package Characters.Enemies;

import Board.Observer;
import Board.Room;
import Characters.Subject;

import java.util.ArrayList;

public abstract class Creature implements Subject {

    // PROTECTED ATTRIBUTES
    protected Room currentRoom;
    protected String entityType;
    protected String sign;
    protected String name;
    protected static boolean alive;
    protected Characters.Action.Combat.combatStyle combatStyle;
    protected ArrayList<Observer> observerList;

    // PUBLIC METHODS
    public abstract String getSign();

    public abstract void setCurrentRoom(Room newRoom);

    public abstract boolean getAlive();

    public abstract Room checkRoom();
}
