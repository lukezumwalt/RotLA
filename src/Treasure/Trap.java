package Treasure;

import Characters.Friendlies.Adventurer;

public class Trap extends Treasure {

    // PUBLIC METHODS
    public void adventurerDamage(Adventurer me) {
        me.takeDamage();
    }
}
