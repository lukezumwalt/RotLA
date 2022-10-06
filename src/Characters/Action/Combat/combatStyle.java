package Characters.Action.Combat;

import Characters.Entity;

public interface combatStyle {

    // PUBLIC METHODS
    // Returns damage dealt to self
    // If > 0, self won
    // If == 0, self win or tie
    int fight(Entity self, Entity target);
}
