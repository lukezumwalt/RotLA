package Treasure;

import Characters.Friendlies.Adventurer;

public class Armor extends Treasure {

    // PUBLIC METHODS
    @Override
    public void activate(Adventurer self) {
        // Causes self to resist higher opponent rolls.
        self.updateDefenseBonus(1);
    }
}
