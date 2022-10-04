package Treasure;

import Characters.Friendlies.Adventurer;

public class Trap extends Treasure {

    public Trap() {
    }

    private boolean set;

    // PUBLIC METHODS
    public void adventurerDamage(Adventurer me) {
        me.takeDamage();
    }

    public void acquire(Adventurer self){
        if(set){
            self.takeDamage();
        }
    }
}
