package Characters.Friendlies;

import java.util.ArrayList;

import Board.Observer;
import Board.Room;
import Characters.Action.Combat.combatStyle;
import Characters.Action.Search.searchStyle;
import Treasure.*;

public abstract class Adventurer {

    // PROTECTED ATTRIBUTES
    protected String entityType;
    protected Room currentRoom;
    protected String sign;
    protected String name;
    protected int health;
    protected static boolean alive;
    protected combatStyle combatStyle;
    protected searchStyle searchStyle;
    protected int offenseBonus; // bias added to roll on attack
    protected int defenseBonus; // bias added to roll on defense
    protected ArrayList<Treasure> inventory;
    protected ArrayList<Observer> observerList;

    // PUBLIC METHODS
    public abstract void takeDamage(int amount);

    public abstract void collectTreasure(Room r);

    public abstract void setCurrentRoom(Room newRoom);

    // Getter Suite
    public abstract String getSign();

    public abstract boolean getAlive();

    public abstract void setCombatStyle(combatStyle cs);

    public abstract int getHealth();

    public abstract int getOffenseBonus();

    public abstract int getDefenseBonus();

    public abstract void updateOffenseBonus(int amount);

    public abstract void updateDefenseBonus(int amount);

    public abstract int getTreasureCount();

    public abstract ArrayList<Treasure> getInventory();

    public abstract boolean search();

    public abstract void heal(int amount);

    public abstract Room checkRoom();
}

