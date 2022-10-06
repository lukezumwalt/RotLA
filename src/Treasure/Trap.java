package Treasure;

import Characters.Friendlies.Adventurer;

public class Trap extends Treasure {

    @Override
    public void activate(Adventurer self){
        // Trap causes adventurer to take damage.
        self.takeDamage(1);
    }
}
