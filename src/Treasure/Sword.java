package Treasure;

import java.util.ArrayList;
import Characters.Friendlies.Adventurer;

public class Sword extends Treasure {

    // PUBLIC METHODS
    public int addCombatBonus(Adventurer me, ArrayList<Treasure> inventory) {
        // ! @TODO check if sword is in inventory
        int combatBonus = me.getCombatBonus();
        combatBonus++;
        return combatBonus;
    }
}
