package Characters.Action.Combat;

import Characters.Entity;

// Strategy pattern for implementing unique combat algorithm objects.
public interface combatStyle {

    // PUBLIC METHODS
    int fight(Entity self, Entity target);
}
