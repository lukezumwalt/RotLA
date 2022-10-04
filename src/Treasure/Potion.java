package Treasure;

import Characters.Friendlies.Adventurer;

public class Potion extends Treasure {

    // PUBLIC METHODS
    public void potionDamage(Adventurer me) {
        boolean isAlive = me.getAlive();
        if (!isAlive) {
            me.takeDamage();
        }
    }
}
