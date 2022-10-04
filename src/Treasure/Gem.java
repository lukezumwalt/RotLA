package Treasure;

import Characters.Friendlies.Adventurer;

public class Gem extends Treasure {

    // PUBLIC METHODS
    @Override
    public void activate(Adventurer self) {
        // Causes opponents to roll relatively higher attacks.
        self.updateDefenseBonus(-1);
    }
}
