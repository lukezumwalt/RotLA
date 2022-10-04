package Characters.Action.Combat;

import Characters.Entity;

public abstract class combatStyle {

    // Returns damage dealt to self
    // If > 0, self won
    // If == 0, self win or tie
    public abstract int fight(Entity self, Entity target);
}
