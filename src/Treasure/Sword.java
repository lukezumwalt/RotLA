package Treasure;

import Characters.Friendlies.Adventurer;

public class Sword extends Treasure {

    // PUBLIC METHODS
    @Override
    public void activate(Adventurer self) {
        // Causes self to roll higher attacks.
        self.updateOffenseBonus(1);
    }
}
