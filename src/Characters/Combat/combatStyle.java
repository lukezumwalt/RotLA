package Characters.Combat;

import Characters.Entity;
import Characters.Friendlies.Adventurer;

import java.util.Random;

public abstract class combatStyle {

    // Returns damage dealt to self
    // If > 0, self won
    // If == 0, self win or tie
    public abstract int fight(Adventurer self, Entity target);
}
