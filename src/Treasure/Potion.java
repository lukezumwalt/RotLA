package Treasure;

import Characters.Friendlies.Adventurer;

public class Potion extends Treasure {

    // PUBLIC METHODS
    @Override
    public void activate(Adventurer self) {
        // Heal self for 1 point.
        self.heal(1);
    }
}
