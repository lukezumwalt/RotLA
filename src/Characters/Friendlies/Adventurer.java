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
    protected int combatBonus; // bias added to roll on attack
    protected int defenseBonus; // bias added to roll on defense

    // PRIVATE ATTRIBUTES
    /*
     * code example of Encapsulation
     * treasureCount is private and
     * can only be accessed via getter
     * method
     */
    private ArrayList<Treasure> inventory;

    // PUBLIC METHODS
    public abstract void takeDamage();

    public void collectTreasure(Room r) {
        inventory.add(r.takeTreasure());
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

    // ! Getter Suite
    public abstract String getSign();

    /*
     * code example of Encapsulation
     * getter method for private attribute
     */

    public boolean getAlive() {
        return alive;
    }

    public void setCombatStyle(combatStyle cs) {
        combatStyle = cs;
    }

    public void setCombatBonus(int bonus) {
        combatBonus = bonus;
    }

    public abstract int getHealth();

    public abstract int getCombatBonus();

    public abstract int getDefenseBonus();

    public abstract int getTreasureCount();
}
