package Characters.Friendlies;

import java.util.ArrayList;

import Board.Room;
import Characters.Combat.combatStyle;
import Treasure.*;

public abstract class Adventurer {

    // PROTECTED ATTRIBUTES
    protected Room currentRoom;
    protected String sign;
    protected String name;
    protected static boolean alive;
    protected combatStyle combatStyle;
    protected int offenseBonus; // bias added to roll on attack
    protected int defenseBonus; // bias added to roll on defense
    protected ArrayList<Treasure> inventory;

    // PUBLIC METHODS
    public abstract void takeDamage();

    public abstract void collectTreasure(Room r);

    public abstract void setCurrentRoom(Room newRoom);

    // Getter Suite
    public abstract String getSign();

    public abstract boolean getAlive();

    public abstract void setCombatStyle(combatStyle cs);

    public abstract int getHealth();

    public abstract int getOffenseBonus();

    public abstract int getDefenseBonus();

    public abstract int getTreasureCount();
}
