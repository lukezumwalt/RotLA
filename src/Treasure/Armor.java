package Treasure;

import Characters.Enemies.Creature;

public class Armor extends Treasure {

    // PUBLIC METHODS
    public int takeCombatBonus(Creature monster) {
        int lowerCombat = monster.getCombatBonus();
        lowerCombat--;
        return lowerCombat;
    }
}
